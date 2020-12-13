package ca.skynetcloud.cybercore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.event.ModClientEvent;
import ca.skynetcloud.cybercore.init.RendererInit;
import ca.skynetcloud.cybercore.init.ScreenInit;
import ca.skynetcloud.cybercore.packets.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.recipes.CyberRecipeTypes;
import ca.skynetcloud.cybercore.util.networking.config.ConfigLoadder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

@Mod("cybercore")
public class CyberCoreMain {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
			new ResourceLocation("cybercore", "cybercore"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals);
	public static final String NAME = Names.NAME;
	public static final String MODID = Names.MODID;
	public static final Logger LOGGER = LogManager.getLogger();

	public CyberCoreMain() {

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		modBus.addListener(this::setup);

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigLoadder.COMMON, Names.Server_CONFIG);
		ConfigLoadder.loadConfig(ConfigLoadder.COMMON,
				FMLPaths.CONFIGDIR.get().resolve(Names.Server_CONFIG).toString());

		modBus.addListener(this::doClientStuff);

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigLoadder.CLIENT, Names.Client_CONFIG);
		ConfigLoadder.loadConfig(ConfigLoadder.CLIENT,
				FMLPaths.CONFIGDIR.get().resolve(Names.Client_CONFIG).toString());

	}

	private void setup(FMLCommonSetupEvent event) {

		new CyberRecipeTypes();
		CyberCorePacketHandler.register();

	}

	private void doClientStuff(final FMLClientSetupEvent event) {

		RendererInit.registerEntityRenderer();
		ScreenInit.registerGUI();

		RenderTypeLookup.setRenderLayer(BlockInit.POWER_BOX, RenderType.getCutoutMipped());
		RenderTypeLookup.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.getCutout());

	}

	public static class CyberCoreTab extends ItemGroup {

		public static final CyberCoreTab instance = new CyberCoreTab(ItemGroup.GROUPS.length, Names.CyberTAB);

		private CyberCoreTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {

			return new ItemStack(ItemInit.cyber_ingot);
		}
	}
}
