package xyz.skynetcloud.cybercore.api.entites;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.entities.passive.ShadowVillagerEntity;
import xyz.skynetcloud.cybercore.entities.passive.WildSharkEntity;

public class EntitesNameType {

	public static final EntityType<ShadowVillagerEntity> Villager_ETITY = EntityType.Builder
			.<ShadowVillagerEntity>create(ShadowVillagerEntity::new, EntityClassification.MISC).size(0.6F, 1.95F)
			.setUpdateInterval(3).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).build(Names.CYBERVILLAGER);

	public static final EntityType<WildSharkEntity> SHARK_ETITY = EntityType.Builder
			.<WildSharkEntity>create(WildSharkEntity::new, EntityClassification.MISC).size(0.6F, 1.95F)
			.setUpdateInterval(3).setTrackingRange(16).setShouldReceiveVelocityUpdates(true).build(Names.SHARK_MOB);

	public static void registerAll(RegistryEvent.Register<EntityType<?>> event) {
		event.getRegistry().registerAll(Villager_ETITY.setRegistryName(Names.CYBERVILLAGER),
				SHARK_ETITY.setRegistryName(Names.SHARK_MOB));

	}

}
