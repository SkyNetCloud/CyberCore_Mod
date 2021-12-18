package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.container.PowerCubeMenu;
import ca.skynetcloud.cybercore.client.container.PoweredFurnaceMenu;
import net.minecraft.world.inventory.MenuType;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerInit {

    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, CyberCore.MODID);

    public static final RegistryObject<MenuType<PoweredFurnaceMenu>> POWERED_FURNACE_MENU = CONTAINERS.register("powered_furnace_menu", () -> new MenuType<>(PoweredFurnaceMenu::new));
    public static final RegistryObject<MenuType<PowerCubeMenu>> POWER_CUBE_MENU = CONTAINERS.register("power_cube_menu", () -> new MenuType<>(PowerCubeMenu::new));


}
