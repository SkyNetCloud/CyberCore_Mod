package ca.skynetcloud.cybercore.client.world.dimensions;


import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

import static ca.skynetcloud.cybercore.CyberCore.MODID;

public class Dimensions {

    public static final ResourceKey<Level> MYSTERIOUS = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(MODID, "mysterious"));

    public static void register() {
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(MODID, "mysterious_chunkgen"),
                MysteriousChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(MODID, "biomes"),
                MysteriousBiomeProvider.CODEC);
    }
}
