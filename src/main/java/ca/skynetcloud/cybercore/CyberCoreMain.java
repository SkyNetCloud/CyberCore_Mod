package ca.skynetcloud.cybercore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.RenderInit;
import ca.skynetcloud.cybercore.init.ScreenInit;
import ca.skynetcloud.cybercore.item.enchantment.EnchantmentSoulbound;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeTypes;
import ca.skynetcloud.cybercore.util.networking.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.util.networking.config.CyberConfig;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import ca.skynetcloud.cybercore.world.gen.OreGeneration;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Names.MODID)
public class CyberCoreMain {
	public static final String NAME = Names.NAME;
	public static final String MODID = Names.MODID;
	public static final Logger LOGGER = LogManager.getLogger();

	public static EnchantmentSoulbound soulbound;

	public CyberCoreMain() {

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CyberConfig.CONFIG_SPEC);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CyberConfig.CONFIG_SPEC);

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
		modBus.addListener(this::setup);
		modBus.addListener(this::clientSetup);
	}

	private void clientSetup(final FMLClientSetupEvent event) {
		RenderInit.registerEntityRenderer();
		ScreenInit.registerGUI();
		ItemBlockRenderTypes.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.cutout());
		CyberCoreMain.LOGGER.info("Client Event Loadded");
	}

	private void setup(final FMLCommonSetupEvent event) {

		new ModedRecipeTypes();
		CyberCorePacketHandler.register();

		CyberCoreMain.LOGGER.info("Common Event Loadded");

	}
}
