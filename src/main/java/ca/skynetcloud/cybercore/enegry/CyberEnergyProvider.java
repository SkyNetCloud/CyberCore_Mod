package ca.skynetcloud.cybercore.enegry;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class CyberEnergyProvider implements ICapabilitySerializable<CompoundNBT>
{
	
	protected CyberEnergyStorage energystorage;
	private LazyOptional<IEnergyStorage> energyCap;
	
	public CyberEnergyProvider(int storage)
	{
		energystorage = new CyberEnergyStorage(storage);
		energyCap = LazyOptional.of(() -> energystorage);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side)
	{
		if (cap == CapabilityEnergy.ENERGY && side == Direction.DOWN)
		{
		    return energyCap.cast();
		}
		return LazyOptional.empty();
	}

	@Override
	public CompoundNBT serializeNBT()
	{
		CompoundNBT compound = new CompoundNBT();
		compound.put("energy", energystorage.serializeNBT());
		return compound;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt)
	{
		energystorage.deserializeNBT(nbt.getCompound("energy"));
	}

}
