package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.init.RendererInit;
import ca.skynetcloud.cybercore.init.ScreenInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CyberCoreMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CyberCoreClient {

	@SubscribeEvent
	static void clientSetup(final FMLClientSetupEvent event) {

		ScreenInit.registerGUI();

	
		RenderTypeLookup.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.getCutout());
	}

}
