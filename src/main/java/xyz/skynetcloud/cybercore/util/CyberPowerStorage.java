package xyz.skynetcloud.cybercore.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class CyberPowerStorage extends EnergyStorage {

	protected int power;
	protected int cap;
	protected int maxLoad;
	protected int maxUnload;

	public CyberPowerStorage(int cap) {
		super(cap, cap, cap, 0);
	}

	public CyberPowerStorage(int cap, int maxTran) {
		super(cap, maxTran, maxTran, 0);
	}

	public CyberPowerStorage(int cap, int maxLoad, int maxUnload) {
		super(cap, maxLoad, maxUnload, 0);
	}

	public int loadEnergy(int maxLoad) {
		return super.receiveEnergy(maxLoad, false);
	}

	public int unloadEnergy(int maxUnload) {
		return super.extractEnergy(maxUnload, false);

	}

	public void deserializeNBT(CompoundNBT nbt) {
		power = nbt.getInt("energy");
		cap = nbt.getInt("capacity");
		maxLoad = nbt.getInt("maxreceive");
		maxUnload = nbt.getInt("maxextract");
	}

	public CompoundNBT serializeNBT() {
		CompoundNBT nbtList = new CompoundNBT();
		nbtList.putInt("energy", power);
		nbtList.putInt("capacity", cap);
		nbtList.putInt("maxreceive", maxLoad);
		nbtList.putInt("maxextract", maxUnload);
		return nbtList;
	}

	// Sync client/server
	public void setEnergyStored(int amount) {
		this.energy = amount;
	}

	public void setEnergyMaxStored(int amount) {
		this.capacity = amount;
	}

}
