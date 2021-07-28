package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.entites.models.ModelRobotEnemy;
import ca.skynetcloud.cybercore.entites.render.RenderEntityRobotEnemy;
import ca.skynetcloud.cybercore.init.EntityInit;
import ca.skynetcloud.cybercore.util.networking.handler.SoulBoundHandler;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvent {

	public static ModelLayerLocation ROBOT_LAYER = new ModelLayerLocation(
			new ResourceLocation(CyberCoreMain.MODID, "robot"), "robot");

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(EntityInit.RoBot, RenderEntityRobotEnemy::new);
	}

	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ROBOT_LAYER, ModelRobotEnemy::createBodyLayer);
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void retrievalEvent(LivingDropsEvent event) {
		if (event.getEntity() instanceof Player) {
			SoulBoundHandler.getOrCreateSoulboundHandler((Player) event.getEntityLiving())
					.retainDrops(event.getDrops());
		}

	}

	@SubscribeEvent
	public static void itemTransferEvent(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			Player oldPlayer = event.getOriginal();
			if (SoulBoundHandler.hasStoredDrops(oldPlayer)) {
				SoulBoundHandler.getOrCreateSoulboundHandler(oldPlayer).transferItems(event.getPlayer());
			} else if (SoulBoundHandler.hasStoredDrops(event.getPlayer())) {
				SoulBoundHandler.getOrCreateSoulboundHandler(event.getPlayer()).transferItems(event.getPlayer());
			}
		}
	}
}
