package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.entites.render.RenderEntityRobotEnemy;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderInit {

	public static void registerEntityRenderer() {
		RenderingRegistry.registerEntityRenderingHandler(EntityInit.RoBot, RenderEntityRobotEnemy::new);

	}

}
