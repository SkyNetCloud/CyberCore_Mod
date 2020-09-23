package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.recipes.CyberRecipeTypes;
import ca.skynetcloud.cybercore.recipes.recipeclasses.PainterRecipe;
import ca.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import ca.skynetcloud.cybercore.util.container.PainterContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

public class CablePainterTE extends CyberCoreEndPowerTE {

	private int ticksPassed = 0;
	private int selectedId = -1;
	private HashMap<Integer, Pair<ItemStack, Integer>> recipeList = new HashMap<Integer, Pair<ItemStack, Integer>>();
	private ItemStack previousInput = null;

	private RangedWrapper inputs;
	private RangedWrapper outputs;
	private LazyOptional<IItemHandler> inputs_provider;
	private LazyOptional<IItemHandler> outputs_provider;

	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return CablePainterTE.this.energystorage.getEnergyStored();
			case 1:
				return CablePainterTE.this.energystorage.getMaxEnergyStored();
			case 2:
				return CablePainterTE.this.ticksPassed;
			case 3:
				return CablePainterTE.this.selectedId;
			case 4:
				return CablePainterTE.this.pos.getX();
			case 5:
				return CablePainterTE.this.pos.getY();
			case 6:
				return CablePainterTE.this.pos.getZ();
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				CablePainterTE.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				CablePainterTE.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				CablePainterTE.this.ticksPassed = value;
				break;
			case 3:
				CablePainterTE.this.selectedId = value;
				break;
			case 4:
				BlockPos newPos = new BlockPos(value, CablePainterTE.this.pos.getY(), CablePainterTE.this.pos.getZ());
				CablePainterTE.this.pos = newPos;
				break;
			case 5:
				BlockPos newPos2 = new BlockPos(CablePainterTE.this.pos.getX(), value, CablePainterTE.this.pos.getZ());
				CablePainterTE.this.pos = newPos2;
				break;
			case 6:
				BlockPos newPos3 = new BlockPos(CablePainterTE.this.pos.getX(), CablePainterTE.this.pos.getY(), value);
				CablePainterTE.this.pos = newPos3;
				break;
			}

		}

		public int size() {
			return 7;
		}
	};

	public CablePainterTE() {
		super(TileEntityNames.CABLE_PAINTER_TE, 1000, 26);
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

		return super.getCapability(capability, facing);
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
	public IIntArray getIntArray() {
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
			if (world != null) {
				for (IRecipe<?> recipe : this.world.getRecipeManager().getRecipes()) {
					if (recipe.getType() == CyberRecipeTypes.PAINTER) {
						PainterRecipe compRecipe = (PainterRecipe) recipe;
						if (compRecipe.getInput().getItem() == particle) {
							temprecipeList.put(Item.getIdFromItem(compRecipe.getRecipeOutput().getItem()),
									Pair.of(compRecipe.getRecipeOutput(), compRecipe.getAmountInput()));
							keys.add(Item.getIdFromItem(compRecipe.getRecipeOutput().getItem()));
						}
					}

				}
			} else {
				for (IRecipe<?> recipe : Minecraft.getInstance().world.getRecipeManager().getRecipes()) {
					if (recipe.getType() == CyberRecipeTypes.PAINTER) {
						PainterRecipe compRecipe = (PainterRecipe) recipe;
						if (compRecipe.getInput().getItem() == particle) {
							temprecipeList.put(Item.getIdFromItem(compRecipe.getRecipeOutput().getItem()),
									Pair.of(compRecipe.getRecipeOutput(), compRecipe.getAmountInput()));
							keys.add(Item.getIdFromItem(compRecipe.getRecipeOutput().getItem()));
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
		return 4 + (getMarkcard(3, ItemType.SPEED_UPGRADE) * 4);
	}

	public int ticksPerItem() {
		return 200 - (getMarkcard(3, ItemType.SPEED_UPGRADE) * 35);
	}

	@Override
	public String getNameString() {
		return "compressor";
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
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("tickspassed", ticksPassed);
		compound.putInt("selectedId", selectedId);
		super.write(compound);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		this.ticksPassed = compound.getInt("tickspassed");
		this.selectedId = compound.getInt("selectedId");
		super.read(compound);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new PainterContainer(id, inv, this);
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
