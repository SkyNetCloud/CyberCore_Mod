package ca.skynetcloud.cybercore.client.datagen;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.MainInit;
import ca.skynetcloud.cybercore.client.world.ores.OreBiomeModifier;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;

import java.io.IOException;
import java.nio.file.Path;

import static ca.skynetcloud.cybercore.CyberCore.MODID;

public class ModBiomeModifiers implements DataProvider {

    private final DataGenerator generator;

    public ModBiomeModifiers(DataGenerator generator) {
        this.generator = generator;
    }


    @Override
    public void run(CachedOutput cache) throws IOException {
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        Registry<PlacedFeature> placedFeatures = ops.registry(Registry.PLACED_FEATURE_REGISTRY).get();
        final Path outputFolder = generator.getOutputFolder();
        final String directory = PackType.SERVER_DATA.getDirectory();
        final String featurePathString = String.join("/", directory, MODID, Registry.PLACED_FEATURE_REGISTRY.location().getPath(), "ore_overworld.json");
        final Path featurePath = outputFolder.resolve(featurePathString);
        final String biomeModifierPathString = String.join("/", directory, MODID, MainInit.ORE_BIOME_MODIFIER.getId().getNamespace(), MainInit.ORE_BIOME_MODIFIER.getId().getPath(), "ore_modifiers.json");
        final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);

        PlacedFeature.DIRECT_CODEC.encodeStart(ops, MainInit.ORE_OVERWORLD.get())
                .resultOrPartial(msg -> CyberCore.LOGGER.error("Failed to encode {}: {}", featurePathString, msg))
                .ifPresent(json -> {
                    try {
                        DataProvider.saveStable(cache, json, featurePath);
                    } catch (IOException e) {
                        CyberCore.LOGGER.error("Failed to save " + featurePathString, e);
                    }
                });
        BiomeModifier biomeModifier = new OreBiomeModifier(
                new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                HolderSet.direct(placedFeatures.getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, MainInit.ORE_OVERWORLD.getId()))));
        BiomeModifier.DIRECT_CODEC.encodeStart(ops, biomeModifier)
                .resultOrPartial(msg -> CyberCore.LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg)) // Log error on encode failure.
                .ifPresent(json -> {
                    try {
                        DataProvider.saveStable(cache, json, biomeModifierPath);
                    } catch (IOException e) {
                        CyberCore.LOGGER.error("Failed to save " + biomeModifierPathString, e);
                    }
                });
        ;
    }

    @Override
    public String getName() {
        return "CyberCore Biome Modifiers";
    }
}
