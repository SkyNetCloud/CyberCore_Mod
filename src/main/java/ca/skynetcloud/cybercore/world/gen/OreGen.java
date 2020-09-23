package ca.skynetcloud.cybercore.world.gen;

import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGen {

	public static void setupOreGeneration() {

		for (Biome biome : ForgeRegistries.BIOMES) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockInit.CYBER_ORE.getDefaultState(), 5))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(12, 0, 0, 12))));

			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockInit.RUBY_ORE.getDefaultState(), 10))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(7, 0, 0, 13))));

			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockInit.DARK_STEEL_ORE.getDefaultState(), 10))
							.withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(7, 0, 0, 12))));

		}

	}

}
