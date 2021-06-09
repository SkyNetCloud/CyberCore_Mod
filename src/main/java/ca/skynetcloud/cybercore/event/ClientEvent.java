package ca.skynetcloud.cybercore.event;

import com.sun.org.apache.xpath.internal.operations.Mod;

import ca.skynetcloud.cybercore.util.networking.handler.SoulBoundHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvent {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void retrievalEvent(LivingDropsEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			SoulBoundHandler.getOrCreateSoulboundHandler((PlayerEntity) event.getEntityLiving())
					.retainDrops(event.getDrops());
		}

	}

	@SubscribeEvent
	public static void itemTransferEvent(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			PlayerEntity oldPlayer = event.getOriginal();
			if (SoulBoundHandler.hasStoredDrops(oldPlayer)) {
				SoulBoundHandler.getOrCreateSoulboundHandler(oldPlayer).transferItems(event.getPlayer());
			} else if (SoulBoundHandler.hasStoredDrops(event.getPlayer())) {
				SoulBoundHandler.getOrCreateSoulboundHandler(event.getPlayer()).transferItems(event.getPlayer());
			}
		}
	}
}
