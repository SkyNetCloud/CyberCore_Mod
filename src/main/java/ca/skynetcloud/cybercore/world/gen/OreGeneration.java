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

		generateOre(event.getGeneration(), OreConfiguration.Predicates.NATURAL_STONE,
				BlockInit.DARK_STEEL_ORE.defaultBlockState(), 5, 11, 41, 24);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.NATURAL_STONE,
				BlockInit.CYBER_ORE.defaultBlockState(), 5, 1, 34, 15);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.NATURAL_STONE,
				BlockInit.RUBY_ORE.defaultBlockState(), 5, 2, 20, 11);

	}

	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
			int veinSize, int minHeight, int maxHeight, int amount) {
		settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
				Feature.ORE.configured(new OreConfiguration(fillerType, state, veinSize)).squared().count(amount));
	}

}
