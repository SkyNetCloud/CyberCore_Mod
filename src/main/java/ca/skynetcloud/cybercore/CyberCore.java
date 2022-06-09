package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.client.init.ScreenInit;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import ca.skynetcloud.cybercore.client.world.dimensions.Dimensions;
import ca.skynetcloud.cybercore.client.world.level.block.ShockflowerBlock;
import ca.skynetcloud.cybercore.client.world.ores.Ores;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static ca.skynetcloud.cybercore.client.init.MainInit.*;
import static ca.skynetcloud.cybercore.client.init.SoundInit.SOUND_EVENT_DEFERRED;


@Mod("cybercore")
public class CyberCore {

    public static final String MODID = "cybercore";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public CyberCore() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CyberConfig.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CyberConfig.CONFIG_SPEC);

        MinecraftForge.EVENT_BUS.register(this);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        ENCHANTMENTS.register(modEventBus);
        SOUND_EVENT_DEFERRED.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        CONTAINERS.register(modEventBus);
        STRUCTURES.register(modEventBus);
        BIOME_MODIFIERS.register(modEventBus);
        PLACED_FEATURES.register(modEventBus);

    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Ores.registerConfiguredFeatures();
            Dimensions.register();
        });
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ShockflowerBlock.registerRenderLayer();
        ScreenInit.registerScreenInit();
    }
}
