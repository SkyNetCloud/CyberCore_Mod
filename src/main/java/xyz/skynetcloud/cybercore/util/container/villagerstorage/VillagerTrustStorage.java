package xyz.skynetcloud.cybercore.util.container.villagerstorage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.IVillagerTrust;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.VillagerTrust;

public class VillagerTrustStorage implements IStorage<IVillagerTrust> {

	@Override
	public INBT writeNBT(Capability<IVillagerTrust> capability, IVillagerTrust instance, Direction side) {
		if (instance instanceof VillagerTrust) {
			return ((VillagerTrust) instance).serializeNBT();
		}
		return new CompoundNBT();
	}

	@Override
	public void readNBT(Capability<IVillagerTrust> capability, IVillagerTrust instance, Direction side, INBT nbt) {
		if (instance instanceof VillagerTrust) {
			((VillagerTrust) instance).deserializeNBT((CompoundNBT) nbt);
		}
	}

}
