package ca.skynetcloud.cybercore.client.events;

import ca.skynetcloud.cybercore.client.utilities.items.handler.SoulBoundHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientBookEvent {

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
