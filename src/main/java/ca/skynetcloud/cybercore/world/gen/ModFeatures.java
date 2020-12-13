package ca.skynetcloud.cybercore.world.gen;

import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModFeatures {

	public static ConfiguredFeature<?, ?> CYBER_ORE_CONFIG;

	public static ConfiguredFeature<?, ?> DARK_STEEL_ORE_CONFIG;

	public static ConfiguredFeature<?, ?> RUBY_ORE_CONFIG;

	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
		CYBER_ORE_CONFIG = Registry
				.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_silver",
						Feature.ORE
								.withConfiguration(
										new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
												BlockInit.CYBER_ORE.getDefaultState(), 9))
								.range(64).square().func_242731_b(20));

		DARK_STEEL_ORE_CONFIG = Registry
				.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_silver",
						Feature.ORE
								.withConfiguration(
										new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
												BlockInit.DARK_STEEL_ORE.getDefaultState(), 9))
								.range(64).square().func_242731_b(20));

		RUBY_ORE_CONFIG = Registry
				.register(WorldGenRegistries.CONFIGURED_FEATURE, "ore_silver",
						Feature.ORE
								.withConfiguration(
										new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
												BlockInit.RUBY_ORE.getDefaultState(), 9))
								.range(64).square().func_242731_b(20));

	}

}
