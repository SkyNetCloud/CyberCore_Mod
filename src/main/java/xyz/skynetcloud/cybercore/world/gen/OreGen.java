package xyz.skynetcloud.cybercore.world.gen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;

public class OreGen {

	public static void setupOreGeneration() {

		for (Biome biome : ForgeRegistries.BIOMES) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockInit.CYBER_ORE.getDefaultState(), 5))
							.func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 5))));

			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockInit.RUBY_ORE.getDefaultState(), 10))
							.func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(15, 0, 0, 5))));

			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
					Feature.ORE
							.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
									BlockInit.DARK_STEEL_ORE.getDefaultState(), 10))
							.func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(15, 0, 0, 5))));

		}

	}

}
