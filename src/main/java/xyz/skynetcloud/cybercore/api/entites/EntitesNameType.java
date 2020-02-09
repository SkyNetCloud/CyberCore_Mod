package xyz.skynetcloud.cybercore.api.entites;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.entities.passive.VillagerEntity;

public class EntitesNameType {

	public static final EntityType<VillagerEntity> Villager = EntityType.Builder
			.<VillagerEntity>create(VillagerEntity::new, EntityClassification.MISC).size(0.6F, 1.95F)
			.setUpdateInterval(3).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).build(Names.CYBERVILLAGER);

	public static void registerAll(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(Villager.setRegistryName(Names.CYBERVILLAGER));
	}

}
