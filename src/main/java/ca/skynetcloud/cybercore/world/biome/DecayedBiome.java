package ca.skynetcloud.cybercore.world.biome;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.EntityInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.event.RegistryEvent;

public class DecayedBiome {

	public static Biome biome;

	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		if (biome == null) {
			BiomeAmbience effects = new BiomeAmbience.Builder().fogColor(12638463).waterColor(4159204)
					.waterFogColor(329011).skyColor(7972607).foliageColorOverride(10387789).grassColorOverride(9470285)
					.build();
			BiomeGenerationSettings.Builder biomeGenerationSettings = new BiomeGenerationSettings.Builder()
					.surfaceBuilder(SurfaceBuilder.DEFAULT
							.configured(new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.defaultBlockState(),
									Blocks.STONE.defaultBlockState(), Blocks.STONE.defaultBlockState())));
			biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.TREE
					.configured((new BaseTreeFeatureConfig.Builder(
							new SimpleBlockStateProvider(Blocks.OAK_LOG.defaultBlockState()),
							new SimpleBlockStateProvider(Blocks.OAK_LEAVES.defaultBlockState()),
							new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(0), 3),
							new StraightTrunkPlacer(4, 2, 0), new TwoLayerFeature(1, 0, 1))).ignoreVines().build())
					.decorated(Features.Placements.HEIGHTMAP_SQUARE)
					.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
			biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Feature.RANDOM_PATCH.configured(Features.Configs.DEFAULT_GRASS_CONFIG)
							.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE)
							.decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 4))));
			biomeGenerationSettings.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Feature.FLOWER.configured(Features.Configs.DEFAULT_FLOWER_CONFIG)
							.decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE)
							.count(5));
			DefaultBiomeFeatures.addDefaultCarvers(biomeGenerationSettings);
			DefaultBiomeFeatures.addDefaultOres(biomeGenerationSettings);
			DefaultBiomeFeatures.addSurfaceFreezing(biomeGenerationSettings);
			MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder().setPlayerCanSpawn();
			mobSpawnInfo.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityInit.RoBot, 20, 4, 4));
			biome = new Biome.Builder().precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST)
					.depth(0.1f).scale(0.2f).temperature(0.5f).downfall(0.5f).specialEffects(effects)
					.mobSpawnSettings(mobSpawnInfo.build()).generationSettings(biomeGenerationSettings.build()).build();
			event.getRegistry().register(biome.setRegistryName(CyberCoreMain.MODID + ":decayed_biome"));
		}

	}

}
