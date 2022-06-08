package ca.skynetcloud.cybercore.client.world.gen;

import ca.skynetcloud.cybercore.client.init.BlockInit;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;

public class OreGen {

    public static Holder<PlacedFeature> CYBER_ORE_BLOCK;
    public static Holder<PlacedFeature> DARK_STEEL_ORE_BLOCK;
    public static Holder<PlacedFeature> RUBY_ORE_BLOCK;


    public static void generateOres() {

        OreConfiguration cyber_ore = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.CYBER_ORE_BLOCK.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        CYBER_ORE_BLOCK = registerPlacedFeature("overworld_cyber_ore", new ConfiguredFeature<>(Feature.ORE, cyber_ore),
                CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimBiomeFillter(id -> !id.equals(Level.OVERWORLD)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration dark_steel_ore = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.DARK_STEEL_ORE_BLOCK.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        DARK_STEEL_ORE_BLOCK = registerPlacedFeature("overworld_dark_steel_ore", new ConfiguredFeature<>(Feature.ORE, dark_steel_ore),
                CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimBiomeFillter(id -> !id.equals(Level.OVERWORLD)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

        OreConfiguration ruby_ore = new OreConfiguration(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.RUBY_ORE_BLOCK.get().defaultBlockState(), OresConfig.OVERWORLD_VEINSIZE.get());
        RUBY_ORE_BLOCK = registerPlacedFeature("overworld_ruby_ore", new ConfiguredFeature<>(Feature.ORE, ruby_ore),
                CountPlacement.of(OresConfig.OVERWORLD_AMOUNT.get()),
                InSquarePlacement.spread(),
                new DimBiomeFillter(id -> !id.equals(Level.OVERWORLD)),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(90)));

    }

    private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
        return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
    }

    public static void onBiomeLoadingEvent(BiomeModifier event) {



    }

}
