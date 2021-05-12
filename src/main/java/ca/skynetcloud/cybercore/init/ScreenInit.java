package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.util.screen.ColorChangeScreen;
import ca.skynetcloud.cybercore.util.screen.PowerCubeScreen;
import ca.skynetcloud.cybercore.util.screen.PowredFurnaceScreen;
import net.minecraft.client.gui.ScreenManager;

public class ScreenInit {

	public static final void registerGUI() {

		ScreenManager.register(ContainerInit.POWER_FURNCAE_CON, PowredFurnaceScreen::new);
		ScreenManager.register(ContainerInit.POWER_CUBE_CON, PowerCubeScreen::new);
		ScreenManager.register(ContainerInit.c_changer_CON, ColorChangeScreen::new);

	}

}
