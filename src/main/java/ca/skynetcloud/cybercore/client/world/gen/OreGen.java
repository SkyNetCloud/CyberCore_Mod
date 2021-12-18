package ca.skynetcloud.cybercore.client.world.gen;


import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.data.worldgen.OrePlacedFeatureSystem;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class OreGen {


    public static void generateOres(BiomeLoadingEvent event) {

        CyberCore.LOGGER.info("Generating");
        BiomeGenerationSettings.Builder builder = event.getGeneration();
        Biome.BiomeCategory biome = event.getCategory();

        if (biome == Biome.BiomeCategory.UNDERGROUND) {
            if (CyberConfig.Config.cyberOreGeneration.get()) {
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacedFeatureSystem.ORE_CYBERORE);

            }
            if (CyberConfig.Config.rubyOreGeneration.get()) {
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacedFeatureSystem.ORE_RUBY);
            }
            if (CyberConfig.Config.darksteelOreGeneration.get()) {
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacedFeatureSystem.ORE_DARK_STEEL);
            }

        }


    }



}
