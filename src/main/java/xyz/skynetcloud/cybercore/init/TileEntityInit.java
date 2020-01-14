package xyz.skynetcloud.cybercore.init;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.skynetcloud.cybercore.api.tileentity.TileEntityNames;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityInit {

	@SubscribeEvent
	public static void TileEntityRegister(RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().registerAll(TileEntityNames.LUNAR_GEN_MACHINE_TE.setRegistryName(MODID, "tileentitylunar"),
				TileEntityNames.POWER_BOX_TE.setRegistryName(MODID, "tileentitypowerbox"),
				TileEntityNames.CABLE_TE.setRegistryName(MODID, "tileentitycable"));
	}
}
