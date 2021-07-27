package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.Optional;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.TileEntityInit;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.container.PowredFurnaceContainer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class PowredFurnaceTileEntity extends CoreEnergyInventoryTileEntity {

	private int power_storage_card = 1;
	public int[] ticksPassed = new int[6];
	boolean isSmelting;
	int workload = 0;
	protected ItemStackHandler dummyitemhandler = new ItemStackHandler(1);

	private RangedWrapper inputs;
	private RangedWrapper outputs;
	private LazyOptional<IItemHandler> inputs_provider;
	private LazyOptional<IItemHandler> outputs_provider;

	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return PowredFurnaceTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return PowredFurnaceTileEntity.this.energystorage.getMaxEnergyStored();
			case 2:
				return PowredFurnaceTileEntity.this.ticksPassed[0];
			case 3:
				return PowredFurnaceTileEntity.this.ticksPassed[1];
			case 4:
				return PowredFurnaceTileEntity.this.ticksPassed[2];
			case 5:
				return PowredFurnaceTileEntity.this.ticksPassed[3];
			case 6:
				return PowredFurnaceTileEntity.this.ticksPassed[4];
			case 7:
				return PowredFurnaceTileEntity.this.ticksPassed[5];
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				PowredFurnaceTileEntity.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				PowredFurnaceTileEntity.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				PowredFurnaceTileEntity.this.ticksPassed[0] = value;
				break;
			case 3:
				PowredFurnaceTileEntity.this.ticksPassed[1] = value;
				break;
			case 4:
				PowredFurnaceTileEntity.this.ticksPassed[2] = value;
				break;
			case 5:
				PowredFurnaceTileEntity.this.ticksPassed[3] = value;
				break;
			case 6:
				PowredFurnaceTileEntity.this.ticksPassed[4] = value;
				break;
			case 7:
				PowredFurnaceTileEntity.this.ticksPassed[5] = value;
				break;
			}

		}

		public int getCount() {
			return 8;
		}
	};

	public PowredFurnaceTileEntity() {
		super(TileEntityInit.POWER_FURNACE_TE, 10000, 20);
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

		if (level.isDay() || level.isNight() && level.canSeeSky(worldPosition.above())) {
			if (energystorage.getMaxEnergyStored() - energystorage.getEnergyStored() > 0) {
				workload++;
				if (workload >= getTicksPerAmount()) {
					energystorage.receiveEnergy(getEnergyPerTick(getTicksPerAmount()));
					workload = 0;
				}
			}

		}

		isSmelting = false;
		for (int i = 0; i < 6; i++) {
			if (this.energystorage.getEnergyStored() > this.energyPerAction()) {
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
			this.energystorage.extractEnergy(energyPerAction(), false);

		}

		doEnergyLoop();
	}

	public int energyPerAction() {
		return 5 + getMarkcard(12, ItemType.SPEED_UPGRADE) * 4;
	}

	private int getEnergyPerTick(int focusLevel) {
		switch (focusLevel) {
		case 1:
			return 1000;
		case 2:
			return 2000;
		case 3:
			return 150000;
		case 4:
			return 2500000;
		}

		return 100;
	}

	public int getTicksPerAmount() {
		return 100 + (getMarkcard(12, ItemType.SPEED_UPGRADE)) - 5;
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	private boolean canSmelt(int slot) {
		ItemStack itemstack = itemhandler.getStackInSlot(slot);
		if (itemstack.isEmpty())
			return false;
		else {

			ItemStack output = getOutput(slot);
			if (output.isEmpty())
				return false;
			else {
				ItemStack outputslot = itemhandler.getStackInSlot(slot + 6);
				if (outputslot.isEmpty())
					return true;
				else if (!output.sameItem(outputslot))
					return false;
				else if (outputslot.getCount() + output.getCount() <= 64
						&& outputslot.getCount() + output.getCount() <= outputslot.getMaxStackSize())
					return true;
				else
					return outputslot.getCount() + output.getCount() <= output.getMaxStackSize();

			}
		}
	}

	public ItemStack getOutput(int slot) {
		if (level == null)
			return ItemStack.EMPTY;
		dummyitemhandler.setStackInSlot(0, itemhandler.getStackInSlot(slot));
		RecipeWrapper wrapper = new RecipeWrapper(dummyitemhandler);
		Optional<FurnaceRecipe> recipeopt = level.getRecipeManager().getRecipeFor(IRecipeType.SMELTING, wrapper, level);
		FurnaceRecipe recipe = recipeopt.orElse(null);
		return recipe == null ? ItemStack.EMPTY : recipe.getResultItem();
	}

	public void smeltItem(int slot) {
		if (this.canSmelt(slot)) {
			ItemStack itemstack = this.itemhandler.getStackInSlot(slot);
			ItemStack itemstack1 = getOutput(slot);
			ItemStack itemstack2 = this.itemhandler.getStackInSlot(slot + 6);
			if (itemstack2.isEmpty())
				this.itemhandler.setStackInSlot(slot + 6, itemstack1.copy());
			else if (itemstack2.getItem() == itemstack1.getItem())
				itemstack2.grow(itemstack1.getCount());
			itemstack.shrink(5);
		}
	}

	@Override
	public void onSlotContentChanged() {
		if (level != null) {
			if (!level.isClientSide) {
				int newcard = getMarkcard(13, ItemType.POWER_UPGRADE);
				if (power_storage_card != newcard) {
					switch (power_storage_card) {
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
					BlockState state = level.getBlockState(worldPosition);
					if (state != null) {
						if (state.getBlock() == BlockInit.POWER_FURNACE_BLOCK) {
							level.setBlock(worldPosition, state.setValue(null, newcard), 2);
							setChanged();
						}
					}
					power_storage_card = newcard;
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
	public CompoundNBT save(CompoundNBT compound) {
		for (int i = 0; i < 6; i++) {
			compound.putInt("cooktime_" + i, ticksPassed[i]);
		}
		super.save(compound);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		for (int i = 0; i < 6; i++) {
			this.ticksPassed[i] = compound.getInt("cooktime_" + i);
		}
		super.load(state, compound);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new PowredFurnaceContainer(id, inv, this);
	}

	@Override
	public int getEnergyInSlot() {
		return 13;
	}

	@Override
	public int getEnergyOutSlot() {
		return 14;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("");
	}

}
