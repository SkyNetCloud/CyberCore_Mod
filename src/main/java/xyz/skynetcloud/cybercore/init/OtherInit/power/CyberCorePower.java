package xyz.skynetcloud.cybercore.init.OtherInit.power;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CyberCorePower extends EnergyStorage implements INBTSerializable<CompoundNBT> {

	public CyberCorePower(int capacity) {
		super(capacity, capacity, capacity, 0);
	}

	public CyberCorePower(int capacity, int maxTransfer) {
		super(capacity, maxTransfer, maxTransfer, 0);
	}

	public CyberCorePower(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract, 0);
	}

	public int receiveEnergy(int maxReceive) {
		return super.receiveEnergy(maxReceive, false);
	}

	public int extractEnergy(int maxExtract) {
		return super.extractEnergy(maxExtract, false);
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void addEnergy(int energy) {
		this.energy += energy;
		if (this.energy > getMaxEnergyStored()) {
			this.energy = getEnergyStored();
		}
	}

	public void consumeEnergy(int energy) {
		this.energy -= energy;
		if (this.energy < 0) {
			this.energy = 0;
		}
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("energy", getEnergyStored());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		setEnergy(nbt.getInt("energy"));
	}

	// Sync client/server
	public void setEnergyStored(int amount) {
		this.energy = amount;
	}

	public void setEnergyMaxStored(int amount) {
		this.capacity = amount;
	}

}
