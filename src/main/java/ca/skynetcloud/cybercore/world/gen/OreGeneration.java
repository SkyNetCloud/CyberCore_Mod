package ca.skynetcloud.cybercore.world.gen;

import java.util.ArrayList;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class OreGeneration {

	private static final ArrayList<ConfiguredFeature<?, ?>> overworldOres = new ArrayList<ConfiguredFeature<?, ?>>();

	public static void registerOres() {
		overworldOres.add(register("cyber_ore",
				Feature.ORE
						.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								BlockInit.CYBER_ORE.defaultBlockState(), 4)) // Vein Size
						.range(12).squared().chance(50))); // Chunk spawn frequency
		overworldOres.add(register("ruby_ore",
				Feature.ORE
						.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								BlockInit.RUBY_ORE.defaultBlockState(), 4)) // Vein Size
						.range(12).squared() // Spawn height start
						.chance(5))); // Chunk spawn frequency
		overworldOres.add(register("dark_steel_ore",
				Feature.ORE
						.configured(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE,
								BlockInit.DARK_STEEL_ORE.defaultBlockState(), 4)) // Vein Size
						.range(12).squared() // Spawn height start
						.chance(20))); // Chunk spawn frequency

	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void gen(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		for (ConfiguredFeature<?, ?> ore : overworldOres) {
			if (ore != null)
				generation.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
		}

	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name,
			ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, CyberCoreMain.MODID + ":" + name,
				configuredFeature);
	}

}
