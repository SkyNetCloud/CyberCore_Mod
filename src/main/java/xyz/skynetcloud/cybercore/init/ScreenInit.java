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

	}

}
