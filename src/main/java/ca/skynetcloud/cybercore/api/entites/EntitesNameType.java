package ca.skynetcloud.cybercore.api.entites;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Function;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class EntitesNameType {

	private static final List<EntityType<?>> ENTITY_TYPES = new ArrayList<>();

	@SuppressWarnings("unused")
	private static <T extends Entity> EntityType<T> build(ResourceLocation id, Function<World, T> function, float width,
			float height) {
		EntityType<T> type = EntityType.Builder
				.<T>of((entityType, world) -> function.apply(world), EntityClassification.CREATURE)
				.sized(width, height).setCustomClientFactory((spawnEntity, world) -> function.apply(world))
				.build(id.toString());
		type.setRegistryName(id);
		ENTITY_TYPES.add(type);
		return type;
	}

	@SubscribeEvent
	public static void registerTypes(final RegistryEvent.Register<EntityType<?>> event) {
		IForgeRegistry<EntityType<?>> registry = event.getRegistry();
		ENTITY_TYPES.forEach(registry::register);
	}

}
