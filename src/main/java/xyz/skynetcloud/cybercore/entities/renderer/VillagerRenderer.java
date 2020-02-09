package xyz.skynetcloud.cybercore.entities.renderer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.entities.models.VillagerModels;
import xyz.skynetcloud.cybercore.entities.passive.VillagerEntity;

@OnlyIn(Dist.CLIENT)
public class VillagerRenderer extends MobRenderer<VillagerEntity, VillagerModel<VillagerEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(CyberCoreMain.MODID,
			"textures/entities/villager/cybercore_villager.png");

	public VillagerRenderer(EntityRendererManager manager) {
		super(manager, new VillagerModels<>(0.0F), 0.5F);
		this.addLayer(new HeadLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(VillagerEntity entity) {
		return TEXTURE;
	}
}
