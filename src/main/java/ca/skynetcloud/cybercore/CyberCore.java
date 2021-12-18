package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.client.data.worldgen.OreConfigFeatures;
import ca.skynetcloud.cybercore.client.data.worldgen.OrePlacedFeatureSystem;
import ca.skynetcloud.cybercore.client.init.*;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import ca.skynetcloud.cybercore.client.world.gen.OreGen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@Mod("cybercore")
public class CyberCore {

    public static CyberCore instance;

    public static final String MODID = "cybercore";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public CyberCore() {

        instance = this;

        final IEventBus FML = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus MTA = MinecraftForge.EVENT_BUS;

        FML.addListener(this::setup);
        FML.addListener(this::clientSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CyberConfig.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CyberConfig.CONFIG_SPEC);

        MTA.register(this);
        MTA.register(OreGen.class);


        BlockInit.BLOCKS.register(FML);
        ItemInit.ITEMS.register(FML);
        ContainerInit.CONTAINERS.register(FML);
        ItemInit.Enchantments.register(FML);
        BlockInit.registerItemBlocks();
        BlockEntityInit.BLOCK_ENTITIES.register(FML);
    }

    private void setup(FMLCommonSetupEvent event) {

        OrePlacedFeatureSystem.initOrePlacedFeatures();
        OreConfigFeatures.initModFeatures();
    }

    private void clientSetup(final FMLClientSetupEvent event){
        ScreenInit.registerScreenInit();
    }
}
