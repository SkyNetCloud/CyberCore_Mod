package ca.skynetcloud.cybercore.world.gen;

import ca.skynetcloud.cybercore.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {

	public static void generateOres(final BiomeLoadingEvent event) {

		generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE,
				BlockInit.DARK_STEEL_ORE.defaultBlockState(), 5, 11, 41, 24);

		generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE,
				BlockInit.CYBER_ORE.defaultBlockState(), 5, 1, 34, 15);

		generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.NATURAL_STONE,
				BlockInit.RUBY_ORE.defaultBlockState(), 5, 2, 20, 11);

	}

	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state,
			int veinSize, int minHeight, int maxHeight, int amount) {
		settings.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.configured(new OreFeatureConfig(fillerType, state, veinSize))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
						.squared().count(amount));
	}

	/*
	 * private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new
	 * ArrayList<ConfiguredFeature<?, ?>>();
	 * 
	 * public static void registerOres() {
	 * 
	 * overworldOres.add(register("cyber_ore", Feature.ORE.configured(new
	 * OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
	 * BlockInit.CYBER_ORE.defaultBlockState(), 9)).range(12).chance(50)));
	 * overworldOres.add(register("ruby_ore", Feature.ORE.configured(new
	 * OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
	 * BlockInit.RUBY_ORE.defaultBlockState(), 12)).range(12).chance(24)));
	 * overworldOres.add(register("dark_steel_ore", Feature.ORE.configured(new
	 * OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
	 * BlockInit.DARK_STEEL_ORE.defaultBlockState(), 5)).range(12).chance(20)));
	 * 
	 * }
	 * 
	 * @SubscribeEvent() public void gen(BiomeLoadingEvent event,
	 * ConfiguredFeature<?, ?> configuredFeature, boolean enabled) { if (enabled) {
	 * event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES
	 * ).add(() -> configuredFeature); } }
	 * 
	 * private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?>
	 * register(String name, ConfiguredFeature<FC, ?> configuredFeature) { return
	 * Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, CyberCoreMain.MODID
	 * + ":" + name, configuredFeature); }
	 */
}
