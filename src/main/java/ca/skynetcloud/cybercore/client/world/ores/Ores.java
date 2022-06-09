package ca.skynetcloud.cybercore.client.world.ores;

import ca.skynetcloud.cybercore.client.init.MainInit;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import ca.skynetcloud.cybercore.client.world.dimensions.Dimensions;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class Ores {

    public static final RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);

    //    public static Holder<PlacedFeature> MYSTERIOUS_OREGEN;
//    public static Holder<PlacedFeature> OVERWORLD_OREGEN;
    public static Holder<PlacedFeature> DEEPSLATE_OREGEN;
    public static Holder<PlacedFeature> NETHER_OREGEN;
    public static Holder<PlacedFeature> END_OREGEN;

    public static void registerConfiguredFeatures() {
//        MYSTERIOUS_OREGEN = createMysteriousOregen();
//        OVERWORLD_OREGEN = createOverworldOregen();

        OreConfiguration deepslateConfig = new OreConfiguration(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MainInit.CYBER_ORE_BLOCK_block.get().defaultBlockState(), CyberConfig.Config.DEEPSLATE_VEINSIZE.get());
        DEEPSLATE_OREGEN = registerPlacedFeature("deepslate_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, deepslateConfig),
                CountPlacement.of(CyberConfig.Config.DEEPSLATE_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(64)));

        OreConfiguration netherConfig = new OreConfiguration(OreFeatures.NETHER_ORE_REPLACEABLES, MainInit.RUBY_ORE_BLOCK_block.get().defaultBlockState(), CyberConfig.Config.NETHER_VEINSIZE.get());
        NETHER_OREGEN = registerPlacedFeature("nether_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, netherConfig),
                CountPlacement.of(CyberConfig.Config.NETHER_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration endConfig = new OreConfiguration(IN_ENDSTONE, MainInit.DARK_STEEL_ORE_BLOCK_block.get().defaultBlockState(), CyberConfig.Config.END_VEINSIZE.get());
        END_OREGEN = registerPlacedFeature("end_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, endConfig),
                CountPlacement.of(CyberConfig.Config.END_AMOUNT.get()),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(100)));
    }

    @NotNull
    public static Holder<PlacedFeature> createOverworldOregen() {
        OreConfiguration overworldConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, MainInit.MYSTERIOUS_ORE_OVERWORLD.get().defaultBlockState(), CyberConfig.Config.OVERWORLD_VEINSIZE.get());
        return registerPlacedFeature("overworld_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, overworldConfig),
                CountPlacement.of(CyberConfig.Config.OVERWORLD_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> !Dimensions.MYSTERIOUS.equals(key)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
    }

    @NotNull
    public static Holder<PlacedFeature> createMysteriousOregen() {
        OreConfiguration mysteriousConfig = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, MainInit.MYSTERIOUS_ORE_OVERWORLD.get().defaultBlockState(), CyberConfig.Config.MYSTERIOUS_VEINSIZE.get());
        return registerPlacedFeature("mysterious_mysterious_ore", new ConfiguredFeature<>(Feature.ORE, mysteriousConfig),
                CountPlacement.of(CyberConfig.Config.MYSTERIOUS_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimensionBiomeFilter(key -> key.equals(Dimensions.MYSTERIOUS)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));
    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    // @todo 1.19
//    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
//        if (event.getCategory() == Biome.BiomeCategory.NETHER) {
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NETHER_OREGEN);
//        } else if (event.getCategory() == Biome.BiomeCategory.THEEND) {
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_OREGEN);
//        } else {
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MYSTERIOUS_OREGEN);
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_OREGEN);
//            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_OREGEN);
//        }
//    }
}
