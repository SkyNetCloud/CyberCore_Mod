package ca.skynetcloud.cybercore.enegry.baseclasses;

import ca.skynetcloud.cybercore.enegry.CyberEnergyStorage;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

abstract public class CoreEnergyTileEntity extends BlockEntity implements Tickable {

	protected CyberEnergyStorage energystorage;
	private LazyOptional<IEnergyStorage> energyCap;
	public String customname;

	public CoreEnergyTileEntity(BlockEntityType<?> type, BlockPos p_155545_, BlockState p_155546_, int energyStorage) {
		super(type, p_155545_, p_155546_);
		energystorage = new CyberEnergyStorage(energyStorage);
		energyCap = LazyOptional.of(() -> energystorage);
	}

	@Override
	public void tick() {
		if (this.level != null && !this.level.isClientSide) {
			doUpdate();
		}
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

	public abstract ContainerData getIntArray();

	public String getNameString() {

		return "default";
	}

}
