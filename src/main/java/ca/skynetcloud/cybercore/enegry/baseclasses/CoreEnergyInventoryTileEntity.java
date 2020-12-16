package ca.skynetcloud.cybercore.enegry.baseclasses;

import java.util.ArrayList;
import java.util.List;

import ca.skynetcloud.cybercore.item.UpgradeLvl;
import ca.skynetcloud.cybercore.util.networking.util.IItemChargeable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

abstract public class CoreEnergyInventoryTileEntity extends CoreEnergyTileEntity {
	protected ItemStackHandler itemhandler;
	protected LazyOptional<IItemHandler> inventoryCap;
	protected int tier;

	public CoreEnergyInventoryTileEntity(TileEntityType<?> type, int energyStorage, int invSize, int tier) {
		super(type, energyStorage);
		itemhandler = new ItemStackHandler(invSize);
		inventoryCap = LazyOptional.of(() -> itemhandler);
	}


	public List<ItemStack> getInventoryContent() {
		List<ItemStack> stack = new ArrayList<ItemStack>();

		for (int i = 0; i < itemhandler.getSlots(); i++) {
			stack.add(itemhandler.getStackInSlot(i).copy());
		}

		return stack;
	}

	public void doEnergyLoop() {
		ItemStack stack = itemhandler.getStackInSlot(getEnergyInSlot());
		ItemStack stack2 = itemhandler.getStackInSlot(getEnergyOutSlot());
		if (stack != null) {
			if (stack.getItem() instanceof IItemChargeable) {
				if (energystorage.getEnergyStored() < energystorage.getMaxEnergyStored()) {
					energystorage.receiveEnergy(((IItemChargeable) stack.getItem()).extractEnergyLoad(stack, 1, false));
				}
			}
		}

		if (stack2 != null) {
			if (stack2.getItem() instanceof IItemChargeable) {
				if (energystorage.getEnergyStored() >= 1) {
					energystorage.extractEnergy(((IItemChargeable) stack2.getItem()).receiveEnergyLoad(stack2, 1, false));
				}
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return inventoryCap.cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("inventory", itemhandler.serializeNBT());
		super.write(compound);
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT compound) {
		int slotamount = itemhandler.getSlots();// prevent crash
		itemhandler.deserializeNBT(compound.getCompound("inventory"));
		if (itemhandler.getSlots() != slotamount)
			itemhandler.setSize(slotamount);// prevent crash when invsize changed while update
		super.read(state, compound);
	}

	public static void spawnAsEntity(World worldIn, BlockPos pos, ItemStack stack) {
		// TODO Implement Gamerule "doTileDrops"
		if (!worldIn.isRemote && !stack.isEmpty() && !worldIn.restoringBlockSnapshots) // do not drop items while
																						// restoring
		// blockstates, prevents item dupe
		{
			double d0 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			double d1 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			double d2 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			ItemEntity entityitem = new ItemEntity(worldIn, (double) pos.getX() + d0, (double) pos.getY() + d1,
					(double) pos.getZ() + d2, stack);
			entityitem.setDefaultPickupDelay();
			worldIn.addEntity(entityitem);
		}
	}

	public int getMarkcard(int slot, UpgradeLvl.ItemType itemType) {
		ItemStack stack = itemhandler.getStackInSlot(slot);
		if (!stack.isEmpty() && stack.getItem() instanceof UpgradeLvl) {
			UpgradeLvl item = (UpgradeLvl) stack.getItem();
			if (item.getItemType() == itemType) {
				return item.getLevel();
			}
		}
		return 0;
	}

	protected abstract int getEnergyInSlot();

	protected abstract int getEnergyOutSlot();
}
