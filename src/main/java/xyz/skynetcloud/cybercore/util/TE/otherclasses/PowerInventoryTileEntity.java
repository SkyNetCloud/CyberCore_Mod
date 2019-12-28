package xyz.skynetcloud.cybercore.util.TE.otherclasses;

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
import xyz.skynetcloud.cybercore.util.IItemChargeable;

abstract public class PowerInventoryTileEntity extends PowerTileEntity {

	protected ItemStackHandler itemhandler;
	protected LazyOptional<IItemHandler> invCap;

	public PowerInventoryTileEntity(TileEntityType<?> type, int powerStorage, int invSize) {
		super(type, powerStorage);
		itemhandler = new ItemStackHandler(invSize);
		invCap = LazyOptional.of(() -> itemhandler);
	}

	public List<ItemStack> getInventortyContent() {
		List<ItemStack> Stack = new ArrayList<ItemStack>();
		for (int i = 0; i < itemhandler.getSlots(); i++) {
			Stack.add(itemhandler.getStackInSlot(i).copy());
		}
		return Stack;

	}

	public void doPowerLoop() {
		ItemStack stack = itemhandler.getStackInSlot(getPowerInSlot());
		ItemStack stack2 = itemhandler.getStackInSlot(getPowerOutSlot());
		if (stack != null) {
			if (stack.getItem() instanceof IItemChargeable) {
				if (powerstorage.getEnergyStored() < powerstorage.getMaxEnergyStored()) {
					powerstorage.receiveEnergy(((IItemChargeable) stack.getItem()).extractEnergyLoad(stack, 1, false),
							removed);
				}
			}
		}

		if (stack2 != null) {
			if (stack2.getItem() instanceof IItemChargeable) {
				if (powerstorage.getEnergyStored() >= 1) {
					powerstorage.extractEnergy(((IItemChargeable) stack2.getItem()).receiveEnergyLoad(stack2, 1, false),
							removed);
				}
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction facing) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return invCap.cast();
		}
		return super.getCapability(cap, facing);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("inv", itemhandler.serializeNBT());
		super.write(compound);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		int slotamount = itemhandler.getSlots();
		itemhandler.deserializeNBT(compound.getCompound("inv"));
		if (itemhandler.getSlots() != slotamount)
			itemhandler.setSize(slotamount);
		super.read(compound);
		super.read(compound);
	}

	public static void spawnAsEntity(World worldIn, BlockPos pos, ItemStack stack) {

		if (!worldIn.isRemote && !stack.isEmpty() && !worldIn.restoringBlockSnapshots) {
			double e1 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			double e2 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25D;
			double e3 = (double) (worldIn.rand.nextFloat() * 0.5F) + 0.25;
			ItemEntity entityitem = new ItemEntity(worldIn, (double) pos.getX() + e1, (double) pos.getY() + e2,
					(double) pos.getZ() + e3);
			worldIn.addEntity(entityitem);
		}
	}

	public int markUpLevel(int slot, int itemtype) {
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

	protected abstract int getPowerInSlot();

	protected abstract int getPowerOutSlot();

}
