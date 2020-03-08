package xyz.skynetcloud.cybercore.entities.models;

import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CloudVillagerModel<T extends Entity> extends VillagerModel<T>
{
	public CloudVillagerModel(float scale)
	{
		super(scale);
	}
}