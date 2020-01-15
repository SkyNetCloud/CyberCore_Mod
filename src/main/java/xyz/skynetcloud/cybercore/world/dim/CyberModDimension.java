package xyz.skynetcloud.cybercore.world.dim;

import java.util.function.BiFunction;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import xyz.skynetcloud.cybercore.api.resourcelocation.ResourceLocationNames;

public class CyberModDimension extends ModDimension {
	
	public CyberModDimension() {
		this.setRegistryName(ResourceLocationNames.CYBERLAND_LOC);
	}
	
	public static DimensionType getDimensionType() {
		return DimensionType.byName(ResourceLocationNames.CYBERLAND_LOC);
	}

	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
		return CyberDimesion::new;
	}

}
