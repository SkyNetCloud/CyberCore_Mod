package ca.skynetcloud.cybercore.enegry.baseclasses;

import ca.skynetcloud.cybercore.enegry.CyberEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

abstract public class CoreEnergyBlockEntity extends BlockEntity implements MenuProvider {

	protected CyberEnergyStorage energystorage;
	private LazyOptional<IEnergyStorage> energyCap;
	public String customname;

	public CoreEnergyBlockEntity(BlockEntityType<?> type, int energyStorage, BlockPos pos, BlockState state) {
		super(type, pos, state);
		energystorage = new CyberEnergyStorage(energyStorage);
		energyCap = LazyOptional.of(() -> energystorage);
	}


	public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity be)
	{
		if (level != null && !level.isClientSide){
			if(be instanceof CoreEnergyBlockEntity ebe){
				ebe.doUpdate();
			}
		}
	}

	public void notifyClient()
	{
		if (level != null && !level.isClientSide())
		{
			BlockState state = level.getBlockState(getBlockPos());
			level.sendBlockUpdated(getBlockPos(), state, state, 3);
		}
	}

	@Override
	public Component getDisplayName()
	{
		return new TranslatableComponent("container." + getNameString());
	}

	public void doUpdate() {

	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		if (capability == CapabilityEnergy.ENERGY) {
			return energyCap.cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public CompoundTag save(CompoundTag compound) {

		compound.put("energy", this.energystorage.serializeNBT());
		super.save(compound);
		return compound;
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
		this.energystorage.deserializeNBT(compound.getCompound("energy"));
	}

	public int getEnergyStored() {
		return this.energystorage.getEnergyStored();
	}

	public int getMaxEnergyStored() {
		return this.energystorage.getMaxEnergyStored();
	}

	public boolean isUsableByPlayer(Player player) {
		return this.level.getBlockEntity(this.worldPosition) != this ? false
				: player.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
						(double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
	}

	public void onSlotContentChanged() {

	}
	public abstract ContainerData getContainerData();

	public boolean requireSyncUponOpen()
	{
		return false;
	}

	public String getNameString() {

		return "default";
	}

}
