package ca.skynetcloud.cybercore.client.enegry.baseclasses;

import java.util.ArrayList;
import java.util.List;

import ca.skynetcloud.cybercore.common.item.UpgradeLvl;
import ca.skynetcloud.cybercore.common.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.client.networking.util.IItemChargeable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

abstract public class CoreEnergyInventoryBlockEntity extends CoreEnergyBlockEntity {

	protected ItemStackHandler itemhandler;
	protected LazyOptional<IItemHandler> inventoryCap;
	protected int ticksPassed = 0;


	public CoreEnergyInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int energyStorage,  int invSize) {
		super(type, energyStorage, pos, state);
		itemhandler = new ItemStackHandler(invSize);
		inventoryCap = LazyOptional.of(() -> itemhandler);
	}

	/**
	 * Get Items that will be spawned in the Level when the block is destroyed
	 *
	 * @return list of items to spawn
	 */
	public List<ItemStack> getInventoryContent() {
		List<ItemStack> stacks = new ArrayList<ItemStack>();
		for (int i = 0; i < itemhandler.getSlots(); i++)
			stacks.add(itemhandler.getStackInSlot(i).copy());
		return stacks;
	}

	@Override
	public void doUpdate() {
		doEnergyLoop();
	}

	protected void resetProgress(boolean forced) {
		ticksPassed = 0;
	}

	public void doEnergyLoop() {
		ItemStack stack = itemhandler.getStackInSlot(getEnergyInSlot());
		ItemStack stack2 = itemhandler.getStackInSlot(getEnergyOutSlot());
		if (stack.getItem() instanceof IItemChargeable)
			if (energystorage.getEnergyStored() < energystorage.getMaxEnergyStored())
				energystorage.receiveEnergy(((IItemChargeable) stack.getItem()).extractEnergyLoad(stack, 1, false));
		if (stack2.getItem() instanceof IItemChargeable)
			if (energystorage.getEnergyStored() >= 1)
				energystorage.extractEnergy(((IItemChargeable) stack2.getItem()).receiveEnergyLoad(stack2, 1, false));
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return inventoryCap.cast();
		return super.getCapability(capability, facing);
	}

	@Override
	public CompoundTag save(CompoundTag compound) {
		compound.put("inventory", itemhandler.serializeNBT());
		compound.putInt("tickspassed", ticksPassed);
		return super.save(compound);
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		int slotamount = itemhandler.getSlots();// prevent crash
		itemhandler.deserializeNBT(compound.getCompound("inventory"));
		if (itemhandler.getSlots() != slotamount)
			itemhandler.setSize(slotamount);// prevent crash when invsize changed while update
		this.ticksPassed = compound.getInt("tickspassed");
	}

	public static void spawnAsEntity(Level LevelIn, BlockPos pos, ItemStack stack) {
		if (LevelIn != null && !LevelIn.isClientSide && !stack.isEmpty() && !LevelIn.restoringBlockSnapshots
				&& LevelIn.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) // do not drop items while restoring
		// blockstates, prevents item dupe
		{
			double d0 = (double) (LevelIn.random.nextFloat() * 0.5F) + 0.25D;
			double d1 = (double) (LevelIn.random.nextFloat() * 0.5F) + 0.25D;
			double d2 = (double) (LevelIn.random.nextFloat() * 0.5F) + 0.25D;
			ItemEntity entityitem = new ItemEntity(LevelIn, (double) pos.getX() + d0, (double) pos.getY() + d1,
					(double) pos.getZ() + d2, stack);
			entityitem.setDefaultPickUpDelay();
			LevelIn.addFreshEntity(entityitem);
		}
	}

	/**
	 * Gets energy needed per action, normally used for: -> energy required per tick
	 * -> energy required per process
	 * 
	 * @return amount of energy
	 */
	public int energyPerAction() {
		return 4 + getMarkcard(0, ItemType.SPEED_UPGRADE) * 4;
	}

	public int ticksPerItem() {
		return 200 - getMarkcard(0, ItemType.SPEED_UPGRADE) * 35;
	}

	protected EnergyConsumptionType getEnergyConsumptionType() {
		return EnergyConsumptionType.PER_TICK;
	}

	protected void increaseProgress() {
		ticksPassed++;
	}

	public int getMarkcard(int slot, UpgradeLvl.ItemType itemType) {
		if (slot == -1)
			return 0;
		ItemStack stack = itemhandler.getStackInSlot(slot);
		if (!stack.isEmpty() && stack.getItem() instanceof UpgradeLvl) {
			UpgradeLvl item = (UpgradeLvl) stack.getItem();
			if (item.getItemType() == itemType)
				return item.getLevel();
		}
		return 0;
	}

	public void onContainerUpdated(int slotIndex) {

	}

	protected abstract int getEnergyInSlot();

	protected abstract int getEnergyOutSlot();

	public int getTotalCapacity(int capacityChipSlot) {
		return (int) (1000 * Math.pow(10, getMarkcard(capacityChipSlot, UpgradeLvl.ItemType.CAPACITY_UPGRADE)));
	}


	public enum EnergyConsumptionType {
		PER_TICK, PER_PROCESS, NONE
	}

	public enum FluidConsumptionType {
		PER_TICK, PER_PROCESS, NONE
	}
}
