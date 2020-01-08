package xyz.skynetcloud.cybercore.init;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;

public class ContainersInit {

	public static final ContainerType<LunaGenContainer> LUNARGEN_CON = new ContainerType<LunaGenContainer>(
			LunaGenContainer::new);
	public static final ContainerType<PowerStorageContainer> POWER_CON = new ContainerType<PowerStorageContainer>(
			PowerStorageContainer::new);

	public static final void register(IForgeRegistry<ContainerType<?>> registry) {
		LUNARGEN_CON.setRegistryName("lunargen_con");
		POWER_CON.setRegistryName("power_con");

	}

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
		ContainersInit.register(event.getRegistry());

		CyberCoreMain.LOGGER.info("Loaded ContainerType");
	}
}
