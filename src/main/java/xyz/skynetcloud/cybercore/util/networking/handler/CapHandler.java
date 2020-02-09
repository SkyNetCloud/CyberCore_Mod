package xyz.skynetcloud.cybercore.util.networking.handler;

import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.IVillagerTrust;
import xyz.skynetcloud.cybercore.entities.capabilities.villagertrust.VillagerTrust;
import xyz.skynetcloud.cybercore.util.container.villagerstorage.VillagerTrustStorage;

public class CapHandler {

	public static final void register() {
		CapabilityManager.INSTANCE.register(IVillagerTrust.class, new VillagerTrustStorage(), VillagerTrust::new);
		CyberCoreMain.LOGGER.info("Cap Handler Loadded");

	}

}
