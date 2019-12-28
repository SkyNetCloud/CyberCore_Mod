package xyz.skynetcloud.cybercore.util.TE.otherclasses;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import xyz.skynetcloud.cybercore.init.OtherInit.power.CyberCorePower;

abstract public class PowerTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	protected CyberCorePower powerstorage;
	private LazyOptional<IEnergyStorage> energyCap;
	public String customname;

	public PowerTileEntity(TileEntityType<?> type, int energyStorage) {
		super(type);
		powerstorage = new CyberCorePower(energyStorage);
		energyCap = LazyOptional.of(() -> powerstorage);
	}

	@Override
	public void tick() {
		if (this.world != null && !this.world.isRemote) {
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
	public CompoundNBT write(CompoundNBT compound) {

		compound.put("energy", this.powerstorage.serializeNBT());
		super.write(compound);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.powerstorage.deserializeNBT(compound.getCompound("energy"));
	}

	public String getNameString() {
		return "default";
	}

	public int getEnergyStored() {
		return this.powerstorage.getEnergyStored();
	}

	public int getMaxEnergyStored() {
		return this.powerstorage.getMaxEnergyStored();
	}

	public boolean isUsableByPlayer(PlayerEntity player) {
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

	public void onSlotContentChanged() {

	}

	public abstract IIntArray getIntArray();

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container." + getNameString());
	}

}
