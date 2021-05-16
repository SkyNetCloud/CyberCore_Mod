package ca.skynetcloud.cybercore.entites.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.entites.hostile.RobotEnemy;
import ca.skynetcloud.cybercore.entites.models.ModelRobotEnemy;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class RenderEntityRobotEnemy extends MobRenderer<RobotEnemy, ModelRobotEnemy<RobotEnemy>> {

	protected static final ResourceLocation mobTexture = new ResourceLocation(CyberCoreMain.MODID,
			"textures/entity/robot_golem.png");

	public RenderEntityRobotEnemy(EntityRendererManager rendermanagerIn) {
		super(rendermanagerIn, new ModelRobotEnemy<RobotEnemy>(), 1F);
	}

	protected void applyRotations(RobotEnemy entityLiving, MatrixStack matrixStackIn, float ageInTicks,
			float rotationYaw, float partialTicks) {
		super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		if (!((double) entityLiving.animationSpeed < 0.01D)) {
			// float f = 13.0F;
			float f1 = entityLiving.animationSpeed - entityLiving.animationSpeed * (1.0F - partialTicks) + 6.0F;
			float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
			matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(6.5F * f2));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(RobotEnemy p_110775_1_) {
		return mobTexture;
	}
}
