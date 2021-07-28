package ca.skynetcloud.cybercore.init;

import java.util.List;

import com.google.common.collect.Lists;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.entites.hostile.RobotEnemy;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;

public class EntityInit {

	public static Item entity_egg_item;

	public static List<EntityType<?>> ENTITIES = Lists.newArrayList();

	public static final EntityType<RobotEnemy> RoBot = createEntity(RobotEnemy.class, RobotEnemy::new,
			MobCategory.MONSTER, "robot_golem", 1.75F, 4.25F, 0, 0);

	private static <T extends Entity> EntityType<T> createEntity(Class<T> entityClass, EntityFactory<T> factory,
			MobCategory entityClassification, String name, float width, float height, int eggPrimary,
			int eggSecondary) {
		ResourceLocation location = new ResourceLocation(CyberCoreMain.MODID, name);

		EntityType<T> entity = EntityType.Builder.of(factory, entityClassification).sized(width, height)
				.setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)
				.build(location.toString());
		entity.setRegistryName(location);
		ENTITIES.add(entity);
		return entity;
	}

}
