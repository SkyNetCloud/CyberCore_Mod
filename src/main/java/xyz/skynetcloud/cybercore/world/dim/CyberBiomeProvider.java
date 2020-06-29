package xyz.skynetcloud.cybercore.world.dim;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;

public class CyberBiomeProvider extends BiomeProvider {
	public static final List<Biome> SPAWNS = Lists.newArrayList(Biomes.PLAINS);
	private static final Set<Biome> BIOMES = ImmutableSet.of(Biomes.PLAINS, Biomes.BIRCH_FOREST);

	protected CyberBiomeProvider() {
		super(BIOMES);
	}

	@Override
	public List<Biome> getBiomesToSpawnIn() {
		return SPAWNS;
	}

	@Override
	public Biome getNoiseBiome(int p_225526_1_, int p_225526_2_, int p_225526_3_) {
		return Biomes.PLAINS;
	}

}