package ca.skynetcloud.cybercore.client.world.gen;


import ca.skynetcloud.cybercore.client.data.worldgen.OrePlacedFeatureSystem;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class OreGen {

    @SubscribeEvent(priority= EventPriority.HIGH)
    public static void generateOres(BiomeLoadingEvent event) {

        BiomeGenerationSettingsBuilder builder = event.getGeneration();

        if(CyberConfig.OreConfig.cyberOreGeneration) {
            builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacedFeatureSystem.ORE_CYBERORE);
        }
        if(CyberConfig.OreConfig.rubyOreGeneration) {
            builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacedFeatureSystem.ORE_RUBY);
        }
        if(CyberConfig.OreConfig.darksteelOreGeneration) {
            builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacedFeatureSystem.ORE_DARK_STEEL);
        }

    }



}
