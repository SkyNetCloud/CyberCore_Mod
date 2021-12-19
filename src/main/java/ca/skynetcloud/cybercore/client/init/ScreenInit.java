package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.client.gui.PowerCubeScreen;
import ca.skynetcloud.cybercore.client.gui.PoweredFunranceMenuScreen;
import ca.skynetcloud.cybercore.client.gui.SolarGenScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class ScreenInit {

    public static void registerScreenInit()
    {
        MenuScreens.register(ContainerInit.POWER_CUBE_MENU.get(), PowerCubeScreen::new);
        MenuScreens.register(ContainerInit.POWERED_FURNACE_MENU.get(), PoweredFunranceMenuScreen::new);
        MenuScreens.register(ContainerInit.SOLAR_GEN_MENU.get(), SolarGenScreen::new);
    }
}
