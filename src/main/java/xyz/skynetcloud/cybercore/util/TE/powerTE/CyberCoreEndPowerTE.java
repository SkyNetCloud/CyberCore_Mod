package xyz.skynetcloud.cybercore.util.TE.powerTE;

import java.util.ArrayList;
import java.util.List;

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
import xyz.skynetcloud.cybercore.item.UpgradeLvl;
import xyz.skynetcloud.cybercore.util.networking.util.IItemChargeable;

abstract public class CyberCoreEndPowerTE extends CyberCorePowerTE {
	protected ItemStackHandler itemhandler;
	protected LazyOptional<IItemHandler> inventoryCap;

	public CyberCoreEndPowerTE(TileEntityType<?> type, int energyStorage, int invSize) {
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
					energystorage
							.extractEnergy(((IItemChargeable) stack2.getItem()).receiveEnergyLoad(stack2, 1, false));
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
	public void read(CompoundNBT compound) {
		int slotamount = itemhandler.getSlots();
		itemhandler.deserializeNBT(compound.getCompound("inventory"));
		if (itemhandler.getSlots() != slotamount)
			itemhandler.setSize(slotamount);
		super.read(compound);
	}

	public static void spawnAsEntity(World worldIn, BlockPos pos, ItemStack stack) {
		if (!worldIn.isRemote && !stack.isEmpty() && !worldIn.restoringBlockSnapshots)

		{
			double e0 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			double e1 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			double e2 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			ItemEntity entityitem = new ItemEntity(worldIn, (double) pos.getX() + e0, (double) pos.getY() + e1,
					(double) pos.getZ() + e2, stack);
			entityitem.setDefaultPickupDelay();
			worldIn.addEntity(entityitem);
		}
	}

	public int getMarkLvl(int slot, int itemtype) {
		ItemStack stack = itemhandler.getStackInSlot(slot);
		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof UpgradeLvl) {
				UpgradeLvl item = (UpgradeLvl) stack.getItem();
				if (item.getItemType() == itemtype) {
					return item.getLevel();
				}
			}
		}
		return 0;
	}

	protected abstract int getEnergyInSlot();

	protected abstract int getEnergyOutSlot();
}
