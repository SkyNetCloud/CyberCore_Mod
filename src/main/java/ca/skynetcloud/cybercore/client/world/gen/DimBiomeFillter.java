package ca.skynetcloud.cybercore.client.world.gen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.Random;
import java.util.function.Predicate;

public class DimBiomeFillter extends PlacementFilter {

    private final Predicate<ResourceKey<Level>> levelTest;

    public DimBiomeFillter(Predicate<ResourceKey<Level>> levelTest) {
        this.levelTest = levelTest;
    }

    @Override
    protected boolean shouldPlace(PlacementContext context, RandomSource p_226383_, BlockPos pos) {
        if (levelTest.test(context.getLevel().getLevel().dimension())) {
            PlacedFeature placedfeature = context.topFeature().orElseThrow(() -> new IllegalStateException("Tried to biome check an unregistered feature"));
            Holder<Biome> biome = context.getLevel().getBiome(pos);
            return biome.value().getGenerationSettings().hasFeature(placedfeature);
        } else {
            return false;
        }
    }

    @Override
    public PlacementModifierType<?> type() {
        return PlacementModifierType.BIOME_FILTER;
    }


}
