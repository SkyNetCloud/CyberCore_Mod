package ca.skynetcloud.cybercore.world.gen;

import ca.skynetcloud.cybercore.init.BlockInit;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {

	public static void generateOres(final BiomeLoadingEvent event) {

		generateOre(event.getGeneration(), OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
				BlockInit.DARK_STEEL_DeepSlate_ORE.defaultBlockState(), 20, 11, 41, 24);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
				BlockInit.CYBER_DeepSlate_ORE.defaultBlockState(), 20, 1, 34, 15);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
				BlockInit.RUBY_DeepSlate_ORE.defaultBlockState(), 20, 2, 20, 10);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
				BlockInit.DARK_STEEL_ORE.defaultBlockState(), 20, 11, 11, 10);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
				BlockInit.CYBER_ORE.defaultBlockState(), 20, 11, 11, 15);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
				BlockInit.RUBY_ORE.defaultBlockState(), 20, 11, 11, 11);

	}

	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
			int veinSize, int minHeight, int maxHeight, int amount) {
		settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
				Feature.ORE.configured(new OreConfiguration(fillerType, state, veinSize)).squared().count(amount));
	}

}
