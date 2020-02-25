package xyz.skynetcloud.cybercore.entities.passive;

import java.util.List;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerTrade;

public class ShadowVillagerEntity extends AgeableEntity {

	private List<BaseVillagerTrade> offers;

	public ShadowVillagerEntity(EntityType<ShadowVillagerEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public EntityType<?> getEntityType() {
		return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(Names.CYBERVILLAGER));
	}

	@SuppressWarnings("unchecked")
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {

		return new ShadowVillagerEntity((EntityType<ShadowVillagerEntity>) getEntityType(), this.world);
	}
}
