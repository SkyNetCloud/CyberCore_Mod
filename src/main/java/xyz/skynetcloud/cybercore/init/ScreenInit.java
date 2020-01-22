package xyz.skynetcloud.cybercore.init;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.skynetcloud.cybercore.api.containers.ContainerNames;
import xyz.skynetcloud.cybercore.util.screen.LunaGenScreen;
import xyz.skynetcloud.cybercore.util.screen.PowedFurnaceScreen;
import xyz.skynetcloud.cybercore.util.screen.PowerStorageScreen;

public class ScreenInit {

	@OnlyIn(Dist.CLIENT)
	public static final void registerGUI() {
		ScreenManager.registerFactory(ContainerNames.LUNARGEN_CON, LunaGenScreen::new);
		ScreenManager.registerFactory(ContainerNames.POWER_BOX_CON, PowerStorageScreen::new);
		ScreenManager.registerFactory(ContainerNames.POWER_FURNCAE_CON, PowedFurnaceScreen::new);
		
		/*
		ScreenManager.registerFactory(ContainerNames.IRON_CHEST, CyberCoreChestScreen::new);
		ScreenManager.registerFactory(ContainerNames.GOLD_CHEST, CyberCoreChestScreen::new);
		ScreenManager.registerFactory(ContainerNames.DIAMOND_CHEST, CyberCoreChestScreen::new);
		ScreenManager.registerFactory(ContainerNames.CRYSTAL_CHEST, CyberCoreChestScreen::new);
		ScreenManager.registerFactory(ContainerNames.COPPER_CHEST, CyberCoreChestScreen::new);
		ScreenManager.registerFactory(ContainerNames.SILVER_CHEST, CyberCoreChestScreen::new);
		ScreenManager.registerFactory(ContainerNames.OBSIDIAN_CHEST, CyberCoreChestScreen::new);
		*/
	}
}
