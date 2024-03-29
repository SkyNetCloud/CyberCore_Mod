package ca.skynetcloud.cybercore.client.energy;

import net.minecraftforge.energy.EnergyStorage;


public class PyroEnergyStorage extends EnergyStorage {

    public PyroEnergyStorage(int capacity) {
        super(capacity);
    }
    public PyroEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);

    }
    public PyroEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    // Override this to (for example) call setChanged() on your block entity
    protected void onEnergyChanged() {
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int rc = super.receiveEnergy(maxReceive, simulate);
        if (rc > 0 && !simulate) {
            onEnergyChanged();
        }
        return rc;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int rc = super.extractEnergy(maxExtract, simulate);
        if (rc > 0 && !simulate) {
            onEnergyChanged();
        }
        return rc;
    }

    // Sync client/server
    public void setEnergy(int amount)
    {
        this.energy = amount;
        onEnergyChanged();
    }

    public void setEnergyMax(int amount)
    {
        this.capacity = amount;
        onEnergyChanged();
    }


    public void addEnergy(int energy) {
        this.energy += energy;
        if (this.energy > getMaxEnergyStored()) {
            this.energy = getEnergyStored();
        }
        onEnergyChanged();
    }

    public void consumeEnergy(int energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
        onEnergyChanged();
    }
}
