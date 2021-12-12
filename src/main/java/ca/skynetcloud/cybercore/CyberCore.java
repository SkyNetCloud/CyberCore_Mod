package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.client.init.*;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import ca.skynetcloud.cybercore.client.world.gen.OreGen;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



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
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::generateOres);
        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        ContainerInit.CONTAINERS.register(modEventBus);
        ItemInit.Enchantments.register(modEventBus);
        BlockInit.registerItemBlocks();
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event){
        ScreenInit.registerScreenInit();
    }
}
