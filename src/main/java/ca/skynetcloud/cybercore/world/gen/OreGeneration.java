package ca.skynetcloud.cybercore.world.gen;

import ca.skynetcloud.cybercore.init.CoreInit;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {

	public static final RangeDecoratorConfiguration RANGE_20_20 = new RangeDecoratorConfiguration(
			UniformHeight.of(VerticalAnchor.aboveBottom(20), VerticalAnchor.belowTop(20)));

	public static void generateOres(final BiomeLoadingEvent event) {

		generateOre(event.getGeneration(), OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
				CoreInit.BlockInit.DARK_STEEL_DeepSlate_ORE.defaultBlockState(), 12);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
				CoreInit.BlockInit.CYBER_DeepSlate_ORE.defaultBlockState(), 12);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,
				CoreInit.BlockInit.RUBY_DeepSlate_ORE.defaultBlockState(), 12);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
				CoreInit.BlockInit.DARK_STEEL_ORE.defaultBlockState(), 12);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
				CoreInit.BlockInit.CYBER_ORE.defaultBlockState(), 12);

		generateOre(event.getGeneration(), OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
				CoreInit.BlockInit.RUBY_ORE.defaultBlockState(), 12);

	}

	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
			int veinSize) {
		settings.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,
				Feature.ORE.configured(new OreConfiguration(fillerType, state, veinSize)).range(RANGE_20_20).squared()
						.count(veinSize));
	}

}
