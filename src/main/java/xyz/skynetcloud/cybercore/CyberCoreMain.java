package xyz.skynetcloud.cybercore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.api.items.ItemInit;
import xyz.skynetcloud.cybercore.event.ModClientEvent;
import xyz.skynetcloud.cybercore.init.ScreenInit;
import xyz.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import xyz.skynetcloud.cybercore.world.gen.OreGen;

@Mod("cybercore")
public class CyberCoreMain {

	public static final String NAME = "CyberCore";
	public static final String MODID = "cybercore";
	public static final Logger LOGGER = LogManager.getLogger();

	public CyberCoreMain() {

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CyberCoreConfig.COMMON, "cybercore-server.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CyberCoreConfig.CLIENT, "cybercore-client.toml");

		CyberCoreConfig.loadConfig(CyberCoreConfig.CLIENT,
				FMLPaths.CONFIGDIR.get().resolve("cybercore-client.toml").toString());
		CyberCoreConfig.loadConfig(CyberCoreConfig.COMMON,
				FMLPaths.CONFIGDIR.get().resolve("cybercore-server.toml").toString());

		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

		modBus.addListener(this::setup);

		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {

			modBus.register(ModClientEvent.INSTANCE);
			modBus.addListener(this::doClientStuff);

		});

	}

	private void setup(FMLCommonSetupEvent event) {

	}

	private void doClientStuff(final FMLClientSetupEvent event) {

		ScreenInit.registerGUI();
		OreGen.setupOreGeneration();

		RenderTypeLookup.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.func_228643_e_());
		RenderTypeLookup.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.func_228643_e_());
	}

	public static class CyberCoreTab extends ItemGroup {

		public static final CyberCoreTab instance = new CyberCoreTab(ItemGroup.GROUPS.length, "cybercore");

		private CyberCoreTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.lunar_upgrade_lvl_1);
		}
	}

}
