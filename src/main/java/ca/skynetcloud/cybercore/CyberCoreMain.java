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

@Mod(Names.MODID)
public class CyberCoreMain {
	public static final String NAME = Names.NAME;
	public static final String MODID = Names.MODID;
	public static final Logger LOGGER = LogManager.getLogger();

	public CyberCoreMain() {

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigLoadder.COMMON);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigLoadder.CLIENT);

		modBus.addListener(this::setup);

	}

	private void setup(final FMLCommonSetupEvent event) {

		new CyberRecipeTypes();
		CyberCorePacketHandler.register();

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
