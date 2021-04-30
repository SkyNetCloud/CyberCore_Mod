package ca.skynetcloud.cybercore.enegry.baseclasses;

import ca.skynetcloud.cybercore.enegry.CyberEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

abstract public class CoreEnergyTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	protected CyberEnergyStorage energystorage;
	private LazyOptional<IEnergyStorage> energyCap;
	public String customname;

	public CoreEnergyTileEntity(TileEntityType<?> type, int energyStorage) {
		super(type);
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
	public CompoundNBT save(CompoundNBT compound) {

		compound.put("energy", this.energystorage.serializeNBT());
		super.save(compound);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.energystorage.deserializeNBT(compound.getCompound("energy"));
	}

	public int getEnergyStored() {
		return this.energystorage.getEnergyStored();
	}

	public int getMaxEnergyStored() {
		return this.energystorage.getMaxEnergyStored();
	}

	public boolean isUsableByPlayer(PlayerEntity player) {
		return this.level.getBlockEntity(this.worldPosition) != this ? false
				: player.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
						(double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
	}

	public void onSlotContentChanged() {

	}

	public abstract IIntArray getIntArray();

	public String getNameString() {

		return "default";
	}

}
