package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.util.screen.ColorChangeScreen;
import ca.skynetcloud.cybercore.util.screen.LunaGenScreen;
import ca.skynetcloud.cybercore.util.screen.PowedFurnaceScreen;
import ca.skynetcloud.cybercore.util.screen.PowerStorageScreen;
import net.minecraft.client.gui.ScreenManager;

public class ScreenInit {

	public static final void registerGUI() {
		ScreenManager.registerFactory(ContainerNames.LUNARGEN_CON, LunaGenScreen::new);
		ScreenManager.registerFactory(ContainerNames.POWER_BOX_CON, PowerStorageScreen::new);
		ScreenManager.registerFactory(ContainerNames.POWER_FURNCAE_CON, PowedFurnaceScreen::new);
		ScreenManager.registerFactory(ContainerNames.CABLE_PAINTER_CON, ColorChangeScreen::new);

	}

}
