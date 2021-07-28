package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.init.TileEntityInit;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeTypes;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ColorChangerRecipe;
import ca.skynetcloud.cybercore.util.networking.config.CyberConfig.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

public class ColorChangeTileEntity extends CoreEnergyInventoryTileEntity {

	private int ticksPassed = 0;
	private int selectedId = -1;
	private HashMap<Integer, Pair<ItemStack, Integer>> recipeList = new HashMap<Integer, Pair<ItemStack, Integer>>();
	private ItemStack previousInput = null;

	private RangedWrapper inputs;
	private RangedWrapper outputs;
	private LazyOptional<IItemHandler> inputs_provider;
	private LazyOptional<IItemHandler> outputs_provider;

	protected final ContainerData field_array = new ContainerData() {
		public int get(int index) {
			switch (index) {
			case 0:
				return ColorChangeTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return ColorChangeTileEntity.this.energystorage.getMaxEnergyStored();
			case 2:
				return ColorChangeTileEntity.this.ticksPassed;
			case 3:
				return ColorChangeTileEntity.this.selectedId;
			case 4:
				return ColorChangeTileEntity.this.worldPosition.getX();
			case 5:
				return ColorChangeTileEntity.this.worldPosition.getY();
			case 6:
				return ColorChangeTileEntity.this.worldPosition.getZ();
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				ColorChangeTileEntity.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				ColorChangeTileEntity.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				ColorChangeTileEntity.this.ticksPassed = value;
				break;
			case 3:
				ColorChangeTileEntity.this.selectedId = value;
				break;
			case 4:
				BlockPos newPos = new BlockPos(value, ColorChangeTileEntity.this.worldPosition.getY(),
						ColorChangeTileEntity.this.worldPosition.getZ());
				ColorChangeTileEntity.this.worldPosition = newPos;
				break;
			case 5:
				BlockPos newPos2 = new BlockPos(ColorChangeTileEntity.this.worldPosition.getX(), value,
						ColorChangeTileEntity.this.worldPosition.getZ());
				ColorChangeTileEntity.this.worldPosition = newPos2;
				break;
			case 6:
				BlockPos newPos3 = new BlockPos(ColorChangeTileEntity.this.worldPosition.getX(),
						ColorChangeTileEntity.this.worldPosition.getY(), value);
				ColorChangeTileEntity.this.worldPosition = newPos3;
				break;
			}

		}

		public int getCount() {
			return 7;
		}
	};

	public ColorChangeTileEntity() {
		super(TileEntityInit.COLOR_Changer_TE, Config.POWERLMIT.get(), 25);
		inputs = new RangedWrapper(itemhandler, 0, 1);
		outputs = new RangedWrapper(itemhandler, 1, 2);
		inputs_provider = LazyOptional.of(() -> inputs);
		outputs_provider = LazyOptional.of(() -> outputs);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.DOWN)
				return outputs_provider.cast();
			if (facing != null)
				return inputs_provider.cast();
			return inventoryCap.cast();
		}

		return super.getCapability(capability, Direction.DOWN);
	}

	@Override
	public void doUpdate() {

		if (this.energystorage.getEnergyStored() > energyPerTick()) {
			ItemStack stack1 = itemhandler.getStackInSlot(0);
			ItemStack stack2 = itemhandler.getStackInSlot(1);
			if (!stack1.isEmpty() && selectedId >= 0) {
				if (recipeList != null) {
					if (!recipeList.isEmpty() && recipeList.size() > selectedId) {
						Pair<ItemStack, Integer> recipe = recipeList.get(selectedId);
						int neededInput = recipe.getValue();
						ItemStack stackOutput = recipe.getKey().copy();
						if (neededInput <= stack1.getCount()) {
							previousInput = stack1;
							if (ticksPassed < ticksPerItem()) {
								ticksPassed++;
								energystorage.extractEnergy(energyPerTick(), false);
							} else {
								if (stack2.isEmpty()) {
									itemhandler.setStackInSlot(1, stackOutput);
									energystorage.extractEnergy(energyPerTick(), false);
									stack1.shrink(neededInput);
									ticksPassed = 0;
								} else if (stack2.getItem() == stackOutput.getItem()) {
									if (stack2.getMaxStackSize() >= stack2.getCount() + stackOutput.getCount()) {
										stack2.grow(stackOutput.getCount());
										energystorage.extractEnergy(energyPerTick(), false);
										stack1.shrink(neededInput);
										ticksPassed = 0;

									}
								}
							}
						}
					} else {
						selectedId = -1;
					}
				} else if (selectedId >= 0) {
					initRecipeList();
				}
			}
		}
		doEnergyLoop();
	}

	@Override
	public ContainerData getIntArray() {
		return field_array;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

	public void setRecipe() {
		if (previousInput == null) {
			this.selectedId = -2;
			initRecipeList();
		} else if (previousInput.getItem() != itemhandler.getStackInSlot(0).getItem()) {
			this.selectedId = -2;
			initRecipeList();
		}

	}

	@SuppressWarnings("resource")
	public void initRecipeList() {
		// reset old values
		for (int i = 0; i < 20; i++) {
			itemhandler.setStackInSlot(i + 3, ItemStack.EMPTY);
		}

		// set new values
		HashMap<Integer, Pair<ItemStack, Integer>> temprecipeList = new HashMap<Integer, Pair<ItemStack, Integer>>();
		List<Integer> keys = new ArrayList<Integer>();
		recipeList = new HashMap<Integer, Pair<ItemStack, Integer>>();

		ItemStack particleStack = itemhandler.getStackInSlot(0);

		if (!particleStack.isEmpty()) {
			Item particle = particleStack.getItem();
			if (level != null) {
				for (Recipe<?> recipe : this.level.getRecipeManager().getRecipes()) {
					if (recipe.getType() == ModedRecipeTypes.COLORING) {
						ColorChangerRecipe compRecipe = (ColorChangerRecipe) recipe;
						if (compRecipe.getInput().getItem() == particle) {
							temprecipeList.put(Item.getId(compRecipe.getResultItem().getItem()),
									Pair.of(compRecipe.getResultItem(), compRecipe.getAmountInput()));
							keys.add(Item.getId(compRecipe.getResultItem().getItem()));
						}
					}

				}
			} else {
				for (Recipe<?> recipe : Minecraft.getInstance().level.getRecipeManager().getRecipes()) {
					if (recipe.getType() == ModedRecipeTypes.COLORING) {
						ColorChangerRecipe compRecipe = (ColorChangerRecipe) recipe;
						if (compRecipe.getInput().getItem() == particle) {
							temprecipeList.put(Item.getId(compRecipe.getResultItem().getItem()),
									Pair.of(compRecipe.getResultItem(), compRecipe.getAmountInput()));
							keys.add(Item.getId(compRecipe.getResultItem().getItem()));
						}
					}

				}
			}

		}

		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			recipeList.put(i, temprecipeList.get(keys.get(i)));
			itemhandler.setStackInSlot(i + 3, temprecipeList.get(keys.get(i)).getLeft());
		}
		ticksPassed = 0;
	}

	public int energyPerTick() {
		return 4 + (getMarkcard(12, ItemType.SPEED_UPGRADE) * 4);
	}

	public int ticksPerItem() {
		return 200 - (getMarkcard(12, ItemType.SPEED_UPGRADE) * 300);
	}

	@Override
	public List<ItemStack> getInventoryContent() {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		for (int i = 0; i < 3; i++) {
			stack.add(itemhandler.getStackInSlot(i).copy());
		}
		return stack;
	}

	@Override
	public CompoundTag save(CompoundTag compound) {
		compound.putInt("tickspassed", ticksPassed);
		compound.putInt("selectedId", selectedId);
		super.save(compound);
		return compound;
	}

	@Override
	public void load(CompoundTag compound) {
		this.ticksPassed = compound.getInt("tickspassed");
		this.selectedId = compound.getInt("selectedId");
		super.load(compound);
	}

	@Override
	public int getEnergyInSlot() {
		return 23;
	}

	@Override
	public int getEnergyOutSlot() {
		return 24;
	}

}
