package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.util.screen.ColorChangeScreen;
import ca.skynetcloud.cybercore.util.screen.PowerCubeScreen;
import ca.skynetcloud.cybercore.util.screen.PowredFurnaceScreen;
import net.minecraft.client.gui.ScreenManager;

public class ScreenInit {

	public static final void registerGUI() {

		ScreenManager.registerFactory(ContainerNames.POWER_FURNCAE_CON, PowredFurnaceScreen::new);
		ScreenManager.registerFactory(ContainerNames.POWER_CUBE_CON, PowerCubeScreen::new);
		ScreenManager.registerFactory(ContainerNames.c_changer_CON, ColorChangeScreen::new);
	}

}
