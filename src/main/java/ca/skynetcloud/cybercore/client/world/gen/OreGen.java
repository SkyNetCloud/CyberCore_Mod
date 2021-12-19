package ca.skynetcloud.cybercore.client.world.gen;


import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.data.worldgen.OreFeaturesSystem;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;


public class OreGen {





    public static void generateOres(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();

            if (CyberConfig.Config.cyberOreGeneration.get()) {
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeaturesSystem.ORES_CYBER);
            }
            if (CyberConfig.Config.rubyOreGeneration.get()) {
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeaturesSystem.ORES_RUBY);
            }
            if (CyberConfig.Config.darksteelOreGeneration.get()) {
                builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OreFeaturesSystem.ORES_DARK_STEEL);
            }


    }



}
