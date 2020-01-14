package xyz.skynetcloud.cybercore.init;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.api.containers.ContainerNames;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainersInit {

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().registerAll(
				ContainerNames.LUNARGEN_CON.setRegistryName(MODID + ":lunarsolargenerator_block_con"),
				ContainerNames.POWER_BOX_CON.setRegistryName(MODID + ":power_storage_block_con"));

		CyberCoreMain.LOGGER.info("Loaded ContainerType");
	}
}
