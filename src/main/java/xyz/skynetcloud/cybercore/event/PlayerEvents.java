package xyz.skynetcloud.cybercore.event;

import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.IVillagerTrust;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.VillagerTrust;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerEvents {

	@SubscribeEvent
	public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity) {
			event.addCapability(Names.CyberVILLAGERTRUSTCAP, new VillagerTrust());
		}
	}

	@SubscribeEvent
	public static void onPlayerClone(PlayerEvent.Clone event) {
		if (event.isWasDeath()) {
			event.getOriginal().revive();
			IVillagerTrust old = event.getOriginal().getCapability(VillagerTrust.INSTANCE).orElse(null);
			IVillagerTrust playercap = event.getPlayer().getCapability(VillagerTrust.INSTANCE)
					.orElse(new VillagerTrust());
			if (old != null) {
				for (Entry<String, Integer> entry : old.getTrustsMap().entrySet()) {
					playercap.setTrust(entry.getKey(), entry.getValue());
				}
			}
		}
	}

}
