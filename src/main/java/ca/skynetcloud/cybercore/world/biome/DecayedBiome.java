package ca.skynetcloud.cybercore.world.biome;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.CoreInit;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HeightmapConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoiseDependantDecoratorConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.event.RegistryEvent;

public class DecayedBiome {

	public static Biome biome;

	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		if (biome == null) {
			BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder().fogColor(12638463).waterColor(4159204)
					.waterFogColor(329011).skyColor(7972607).foliageColorOverride(10387789).grassColorOverride(9470285)
					.build();
			BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
					.surfaceBuilder(SurfaceBuilder.DEFAULT
							.configured(new SurfaceBuilderBaseConfiguration(Blocks.GRASS_BLOCK.defaultBlockState(),
									Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState())));
			biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Feature.TREE
					.configured(new TreeConfiguration.TreeConfigurationBuilder(
							new SimpleStateProvider(Blocks.OAK_LOG.defaultBlockState()), new FancyTrunkPlacer(9, 5, 0),
							new SimpleStateProvider(Blocks.OAK_LEAVES.defaultBlockState()),
							new SimpleStateProvider(Blocks.OAK_SAPLING.defaultBlockState()),
							new FancyFoliagePlacer(ConstantInt.of(3), ConstantInt.of(6), 4),
							new TwoLayersFeatureSize(0, 3, 0)).build())
					.decorated(FeatureDecorator.HEIGHTMAP
							.configured(new HeightmapConfiguration(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES)))
					.squared());
			biomeGenerationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
					Feature.RANDOM_PATCH.configured(Features.Configs.DEFAULT_GRASS_CONFIG)
							.decorated(FeatureDecorator.COUNT_NOISE
									.configured(new NoiseDependantDecoratorConfiguration(-0.8D, 5, 4))));

			BiomeDefaultFeatures.addDefaultCarvers(biomeGenerationSettings);
			BiomeDefaultFeatures.addDefaultOres(biomeGenerationSettings);
			BiomeDefaultFeatures.addSurfaceFreezing(biomeGenerationSettings);
			MobSpawnSettings.Builder mobSpawnInfo = new MobSpawnSettings.Builder().setPlayerCanSpawn();
			mobSpawnInfo.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(CoreInit.EntityInit.RoBot, 20, 4, 4));
			biome = new Biome.BiomeBuilder().precipitation(Biome.Precipitation.RAIN)
					.biomeCategory(Biome.BiomeCategory.FOREST).depth(0.1f).scale(0.2f).temperature(0.5f).downfall(0.5f)
					.specialEffects(effects).mobSpawnSettings(mobSpawnInfo.build())
					.generationSettings(biomeGenerationSettings.build()).build();
			event.getRegistry().register(biome.setRegistryName(CyberCoreMain.MODID + ":decayed_biome"));
		}

	}

}