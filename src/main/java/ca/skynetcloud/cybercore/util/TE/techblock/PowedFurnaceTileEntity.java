package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.Optional;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import ca.skynetcloud.cybercore.util.container.PowerFurnaceContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class PowedFurnaceTileEntity extends CyberCoreEndPowerTE {

	public int[] ticksPassed = new int[6];
	boolean isSmelting;
	protected ItemStackHandler dummyitemhandler = new ItemStackHandler(1);

	private RangedWrapper inputs;
	private RangedWrapper outputs;
	private LazyOptional<IItemHandler> inputs_provider;
	private LazyOptional<IItemHandler> outputs_provider;

	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return PowedFurnaceTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return PowedFurnaceTileEntity.this.energystorage.getMaxEnergyStored();
			case 2:
				return PowedFurnaceTileEntity.this.ticksPassed[0];
			case 3:
				return PowedFurnaceTileEntity.this.ticksPassed[1];
			case 4:
				return PowedFurnaceTileEntity.this.ticksPassed[2];
			case 5:
				return PowedFurnaceTileEntity.this.ticksPassed[3];
			case 6:
				return PowedFurnaceTileEntity.this.ticksPassed[4];
			case 7:
				return PowedFurnaceTileEntity.this.ticksPassed[5];
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				PowedFurnaceTileEntity.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				PowedFurnaceTileEntity.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				PowedFurnaceTileEntity.this.ticksPassed[0] = value;
				break;
			case 3:
				PowedFurnaceTileEntity.this.ticksPassed[1] = value;
				break;
			case 4:
				PowedFurnaceTileEntity.this.ticksPassed[2] = value;
				break;
			case 5:
				PowedFurnaceTileEntity.this.ticksPassed[3] = value;
				break;
			case 6:
				PowedFurnaceTileEntity.this.ticksPassed[4] = value;
				break;
			case 7:
				PowedFurnaceTileEntity.this.ticksPassed[5] = value;
				break;
			}

		}

		public int size() {
			return 8;
		}
	};

	public PowedFurnaceTileEntity() {
		super(TileEntityNames.POWER_FURNACE_TE, 10000, 15);
		inputs = new RangedWrapper(itemhandler, 0, 6);
		outputs = new RangedWrapper(itemhandler, 6, 12);
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

		isSmelting = false;
		for (int i = 0; i < 6; i++) {
			if (this.energystorage.getEnergyStored() > this.getEnergyPerTickPerItem()) {
				if (this.canSmelt(i)) {
					isSmelting = true;
					ticksPassed[i]++;
					if (ticksPassed[i] >= this.getTicksPerItem()) {
						this.smeltItem(i);
						ticksPassed[i] = 0;
					}
				} else if (ticksPassed[i] > 0) {
					ticksPassed[i] = 0;
				}
			} else {
				if (!this.canSmelt(i) && ticksPassed[i] > 0) {
					ticksPassed[i] = 0;
				}
				break;
			}
		}
		if (isSmelting) {
			this.energystorage.extractEnergy(getEnergyPerTickPerItem(), false);
		}

		doEnergyLoop();
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	private boolean canSmelt(int slot) {
		ItemStack itemstack = itemhandler.getStackInSlot(slot);
		if (itemstack.isEmpty()) {
			return false;
		} else {

			ItemStack output = getOutput(slot);
			if (output.isEmpty()) {
				return false;
			} else {
				ItemStack outputslot = itemhandler.getStackInSlot(slot + 6);
				if (outputslot.isEmpty()) {
					return true;
				} else if (!output.isItemEqual(outputslot)) {
					return false;
				} else if (outputslot.getCount() + output.getCount() <= 64
						&& outputslot.getCount() + output.getCount() <= outputslot.getMaxStackSize()) {
					return true;
				} else {
					return outputslot.getCount() + output.getCount() <= output.getMaxStackSize();
				}

			}
		}
	}

	public ItemStack getOutput(int slot) {
		dummyitemhandler.setStackInSlot(0, itemhandler.getStackInSlot(slot));
		RecipeWrapper wrapper = new RecipeWrapper(dummyitemhandler);
		Optional<FurnaceRecipe> recipeopt = world.getRecipeManager().getRecipe(IRecipeType.SMELTING, wrapper, world);
		FurnaceRecipe recipe = recipeopt.orElse(null);
		if (recipe != null) {
			return recipe.getRecipeOutput();
		}
		return ItemStack.EMPTY;
	}

	public void smeltItem(int slot) {
		if (this.canSmelt(slot)) {
			ItemStack itemstack = this.itemhandler.getStackInSlot(slot);
			ItemStack itemstack1 = getOutput(slot);
			ItemStack itemstack2 = this.itemhandler.getStackInSlot(slot + 6);

			if (itemstack2.isEmpty()) {
				this.itemhandler.setStackInSlot(slot + 6, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}
			itemstack.shrink(1);
		}
	}

	private int currentcard = -1;

	@Override
	public void onSlotContentChanged() {
		if (world != null) {
			if (!world.isRemote) {
				int newcard = getMarkcard(2, ItemType.POWER_UPGRADE);
				if (currentcard != newcard) {
					switch (currentcard) {
					case 0:
						energystorage.setEnergyMaxStored(1000);
						break;
					case 1:
						energystorage.setEnergyMaxStored(10000);
						break;
					case 2:
						energystorage.setEnergyMaxStored(100000);
						break;
					case 3:
						energystorage.setEnergyMaxStored(1000000);
						break;
					}
					BlockState state = world.getBlockState(pos);
					if (state != null) {
						if (state.getBlock() == BlockInit.POWER_FURNACE_BLOCK) {
							world.setBlockState(pos, state.with(CyberCorePowerBlock.card, newcard), 2);
							markDirty();
						}
					}
					currentcard = newcard;
				}
			}
		}
	}

	public int getEnergyPerTickPerItem() {
		return 4 + (getMarkcard(12, ItemType.SPEED_UPGRADE) * 4);
	}

	public int getTicksPerItem() {
		return 200 - (getMarkcard(12, ItemType.SPEED_UPGRADE) * 35);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		for (int i = 0; i < 6; i++) {
			compound.putInt("cooktime_" + i, ticksPassed[i]);
		}
		super.write(compound);
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT compound) {
		for (int i = 0; i < 6; i++) {
			this.ticksPassed[i] = compound.getInt("cooktime_" + i);
		}
		super.read(state, compound);
	}

	@Override
	public String getNameString() {
		return "megafurnace";
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new PowerFurnaceContainer(id, inv, this);
	}

	@Override
	public int getEnergyInSlot() {
		return 13;
	}

	@Override
	public int getEnergyOutSlot() {
		return 14;
	}

}
