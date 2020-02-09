package xyz.skynetcloud.cybercore.init;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.skynetcloud.cybercore.api.entites.EntitesNameType;
import xyz.skynetcloud.cybercore.entities.renderer.VillagerRenderer;

public class RendererInit {

	public static void registerEntityRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntitesNameType.Villager, VillagerRenderer::new);
	}

}
