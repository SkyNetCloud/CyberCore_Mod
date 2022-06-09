package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.client.init.gui.PowerCubeScreen;
import ca.skynetcloud.cybercore.client.init.gui.PoweredFunranceMenuScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.data.Main;

public class ScreenInit {

    public static void registerScreenInit()
    {
        MenuScreens.register(MainInit.POWER_CUBE_MENU.get(), PowerCubeScreen::new);
        MenuScreens.register(MainInit.POWERED_FURNACE_MENU.get(), PoweredFunranceMenuScreen::new);

    }
}
