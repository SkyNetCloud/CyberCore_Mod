package ca.skynetcloud.cybercore.enegry;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class CyberSystemPowerStorage extends EnergyStorage {

	public CyberSystemPowerStorage(int capacity) {
		super(capacity, capacity, capacity, 0);
	}

	public CyberSystemPowerStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer, maxTransfer, 0);
	}

	public CyberSystemPowerStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract, 0);
	}

	public int receiveEnergy(int maxReceive) {
		return super.receiveEnergy(maxReceive, false);
	}

	public int extractEnergy(int maxExtract) {
		return super.extractEnergy(maxExtract, false);
	}

	public void deserializeNBT(CompoundNBT nbt) {
		this.energy = nbt.getInt("energy");
		this.capacity = nbt.getInt("capacity");
		this.maxReceive = nbt.getInt("maxreceive");
		this.maxExtract = nbt.getInt("maxextract");
	}

	public CompoundNBT serializeNBT() {
		CompoundNBT nbtList = new CompoundNBT();
		nbtList.putInt("energy", this.energy);
		nbtList.putInt("capacity", this.capacity);
		nbtList.putInt("maxreceive", this.maxReceive);
		nbtList.putInt("maxextract", this.maxExtract);
		return nbtList;
	}

	public void setEnergyStored(int amount) {
		this.energy = amount;
	}

	public void setEnergyMaxStored(int amount) {
		this.capacity = amount;
	}

}
