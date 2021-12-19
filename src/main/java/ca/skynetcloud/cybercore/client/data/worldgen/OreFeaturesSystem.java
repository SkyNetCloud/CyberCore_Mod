package ca.skynetcloud.cybercore.client.data.worldgen;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OreFeaturesSystem {

    public static String MODID = CyberCore.MODID;

    public static String find(String name)
    {
        return MODID + ":" + name;
    }

    public static final List<OreConfiguration.TargetBlockState> CyberOres = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.CYBER_ORE_BLOCK.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_RUBY_ORE_BLOCK.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> RubyOres = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.RUBY_ORE_BLOCK.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_CYBER_ORE_BLOCK.get().defaultBlockState()));
    public static final List<OreConfiguration.TargetBlockState> DarkSteelOres = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.DARK_STEEL_ORE_BLOCK.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_DARK_STEEL_ORE_BLOCK.get().defaultBlockState()));

    public static final  ConfiguredFeature<?, ?>  Config_ORES_RUBY =  FeatureUtils.register(find("ruby_ores"), Feature.ORE.configured(new OreConfiguration(RubyOres, CyberConfig.Config.rubyOreVeinSize.get())));
    public static final  ConfiguredFeature<?, ?>  Config_ORES_CYBER =  FeatureUtils.register(find("cyber_ores"), Feature.ORE.configured(new OreConfiguration(CyberOres, CyberConfig.Config.cyberOreVeinSize.get())));
    public static final  ConfiguredFeature<?, ?>  Config_ORES_DARK_STEEL =  FeatureUtils.register(find("darksteel_ores"), Feature.ORE.configured(new OreConfiguration(DarkSteelOres, CyberConfig.Config.darksteelOreVeinSize.get())));

    public static final PlacedFeature ORES_RUBY = PlacementUtils.register(find("ruby_ores"), Config_ORES_RUBY.placed(List.of(CountPlacement.of(CyberConfig.Config.rubyOreVeinSize.get()), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CyberConfig.Config.rubyOreMinHeight.get()), VerticalAnchor.absolute(CyberConfig.Config.rubyOreMaxHeight.get())), BiomeFilter.biome())));
    public static final PlacedFeature ORES_CYBER = PlacementUtils.register(find("cyber_ores"), Config_ORES_CYBER.placed(List.of(CountPlacement.of(CyberConfig.Config.cyberOreVeinSize.get()), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CyberConfig.Config.cyberOreMinHeight.get()), VerticalAnchor.absolute(CyberConfig.Config.cyberOreMaxHeight.get())), BiomeFilter.biome())));
    public static final PlacedFeature ORES_DARK_STEEL = PlacementUtils.register(find("darksteel_ores"), Config_ORES_DARK_STEEL.placed(List.of(CountPlacement.of(CyberConfig.Config.darksteelOreVeinSize.get()), InSquarePlacement.spread(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CyberConfig.Config.darksteelOreMinHeight.get()), VerticalAnchor.absolute(CyberConfig.Config.darksteelOreMaxHeight.get())), BiomeFilter.biome())));


    public static void init()
    {
    }
}
