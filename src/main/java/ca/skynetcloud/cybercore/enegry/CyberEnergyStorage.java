package ca.skynetcloud.cybercore.enegry;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.energy.EnergyStorage;

public class CyberEnergyStorage extends EnergyStorage
{
    public CyberEnergyStorage(int capacity)
    {
	super(capacity, capacity, capacity, 0);
    }

    public CyberEnergyStorage(int capacity, int maxTransfer)
    {
	super(capacity, maxTransfer, maxTransfer, 0);
    }

    public CyberEnergyStorage(int capacity, int maxReceive, int maxExtract)
    {
	super(capacity, maxReceive, maxExtract, 0);
    }

    public int receiveEnergy(int maxReceive)
    {
	return super.receiveEnergy(maxReceive, false);
    }

    public int extractEnergy(int maxExtract)
    {
	return super.extractEnergy(maxExtract, false);
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
	energy = nbt.getInt("energy");
	capacity = nbt.getInt("capacity");
	maxReceive = nbt.getInt("maxreceive");
	maxExtract = nbt.getInt("maxextract");
    }

    public CompoundNBT serializeNBT()
    {
    	CompoundNBT nbtList = new CompoundNBT();
	nbtList.putInt("energy", energy);
	nbtList.putInt("capacity", capacity);
	nbtList.putInt("maxreceive", maxReceive);
	nbtList.putInt("maxextract", maxExtract);
	return nbtList;
    }

    // Sync client/server
    public void setEnergyStored(int amount)
    {
	this.energy = amount;
    }

    public void setEnergyMaxStored(int amount)
    {
	this.capacity = amount;
    }
}
