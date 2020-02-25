package xyz.skynetcloud.cybercore.entities.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.entities.models.CloudVillagerModel;
import xyz.skynetcloud.cybercore.entities.passive.ShadowVillagerEntity;

@OnlyIn(Dist.CLIENT)
public class BaseVillagerRenderer extends MobRenderer<ShadowVillagerEntity, CloudVillagerModel<ShadowVillagerEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(CyberCoreMain.MODID,
			"textures/entities/villager/cybercore_villager.png");

	public BaseVillagerRenderer(EntityRendererManager manager) {
		super(manager, new CloudVillagerModel<>(0.0F), 0.5F);
		this.addLayer(new HeadLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ShadowVillagerEntity entity) {
		return TEXTURE;
	}
}
