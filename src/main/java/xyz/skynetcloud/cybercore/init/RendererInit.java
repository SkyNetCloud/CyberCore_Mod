package xyz.skynetcloud.cybercore.init;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.skynetcloud.cybercore.api.entites.EntitesNameType;
import xyz.skynetcloud.cybercore.entities.renderer.BaseVillagerRenderer;
import xyz.skynetcloud.cybercore.entities.renderer.SharkRenderer;

public class RendererInit {

	public static void registerEntityRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntitesNameType.Villager_ETITY, BaseVillagerRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitesNameType.SHARK_ETITY, SharkRenderer::new);
	}

}
