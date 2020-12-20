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
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
								BlockInit.CYBER_ORE.getDefaultState(), 4)) // Vein Size
						.range(12).square() // Spawn height start
						.func_242731_b(50))); // Chunk spawn frequency
		overworldOres.add(register("ruby_ore",
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
								BlockInit.RUBY_ORE.getDefaultState(), 4)) // Vein Size
						.range(12).square() // Spawn height start
						.func_242731_b(5))); // Chunk spawn frequency
		overworldOres.add(register("dark_steel_ore",
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
								BlockInit.DARK_STEEL_ORE.getDefaultState(), 4)) // Vein Size
						.range(12).square() // Spawn height start
						.func_242731_b(20))); // Chunk spawn frequency

	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void gen(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder generation = event.getGeneration();
		for (ConfiguredFeature<?, ?> ore : overworldOres) {
			if (ore != null)
				generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ore);
		}

	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String name,
			ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, CyberCoreMain.MODID + ":" + name,
				configuredFeature);
	}

}
