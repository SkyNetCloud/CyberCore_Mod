package xyz.skynetcloud.cybercore.entities.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.entities.models.SharkModel;
import xyz.skynetcloud.cybercore.entities.passive.WildSharkEntity;

@OnlyIn(Dist.CLIENT)
public class SharkRenderer extends MobRenderer<WildSharkEntity, SharkModel<WildSharkEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(CyberCoreMain.MODID,
			"textures/entity/shark/wild_shark.png");

	public SharkRenderer(final EntityRendererManager manager) {
		super(manager, new SharkModel<>(0.0F), 0.7F);

	}

	@Override
	public ResourceLocation getEntityTexture(final WildSharkEntity entity) {
		return TEXTURE;
	}

}