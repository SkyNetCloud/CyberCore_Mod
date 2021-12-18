package ca.skynetcloud.cybercore.client.data.worldgen;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class OreConfigFeatures {

    public static String MODID = CyberCore.MODID;

    public static ConfiguredFeature<?, ?> ORE_RUBY = Feature.ORE
            .configured(new OreConfiguration(
                    OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.RUBY_ORE_BLOCK.get().defaultBlockState(),
                    CyberConfig.OreConfig.rubyOreVeinSize));

    public static ConfiguredFeature<?, ?>  ORE_CYBERORE = Feature.ORE
            .configured(new OreConfiguration(
                    OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.CYBER_ORE_BLOCK.get().defaultBlockState(),
                    CyberConfig.OreConfig.cyberOreVeinSize));

    public static ConfiguredFeature<?, ?>  ORE_DARK_STEEL = Feature.ORE
            .configured(new OreConfiguration(
                    OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.DARK_STEEL_ORE_BLOCK.get().defaultBlockState(),
                    CyberConfig.OreConfig.darksteelOreVeinSize));


    public static void initModFeatures ()
    {
        FeatureUtils.register(new ResourceLocation(MODID, "cyber_ore").toString(), ORE_CYBERORE);
        FeatureUtils.register(new ResourceLocation(MODID, "ruby_ore").toString(), ORE_RUBY);
        FeatureUtils.register(new ResourceLocation(MODID, "dark_steel_ore").toString(), ORE_DARK_STEEL);
    }
}
