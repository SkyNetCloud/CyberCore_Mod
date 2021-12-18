package ca.skynetcloud.cybercore.client.data.worldgen;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class OrePlacedFeatureSystem {

    public static String MODID = CyberCore.MODID;

    public static final PlacedFeature ORE_RUBY = OreConfigFeatures.ORE_RUBY.placed(commonOrePlacement(CyberConfig.Config.rubyOrePerChunk.get(), HeightRangePlacement.uniform(VerticalAnchor.absolute(CyberConfig.Config.rubyOreMinHeight.get()), VerticalAnchor.absolute(CyberConfig.Config.rubyOreMaxHeight.get()))));
    public static final PlacedFeature ORE_CYBERORE = OreConfigFeatures.ORE_CYBERORE.placed(commonOrePlacement(CyberConfig.Config.cyberOrePerChunk.get(), HeightRangePlacement.uniform(VerticalAnchor.absolute(CyberConfig.Config.cyberOreMinHeight.get()), VerticalAnchor.absolute(CyberConfig.Config.cyberOreMaxHeight.get()))));
    public static final PlacedFeature ORE_DARK_STEEL = OreConfigFeatures.ORE_DARK_STEEL.placed(rareOrePlacement(CyberConfig.Config.darksteelOrePerChunk.get(), HeightRangePlacement.triangle(VerticalAnchor.absolute(CyberConfig.Config.darksteelOreMinHeight.get()), VerticalAnchor.absolute(CyberConfig.Config.darksteelOreMaxHeight.get()))));

    public static void initOrePlacedFeatures()
    {
        PlacementUtils.register(new ResourceLocation(MODID, "ore_cyber").toString(), ORE_CYBERORE);
        PlacementUtils.register(new ResourceLocation(MODID, "ore_ruby").toString(), ORE_RUBY);
        PlacementUtils.register(new ResourceLocation(MODID, "ore_dark_steel").toString(), ORE_DARK_STEEL);
    }


    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}
