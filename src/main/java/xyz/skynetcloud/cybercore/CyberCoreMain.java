package xyz.skynetcloud.cybercore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.skynetcloud.cybercore.init.ItemInit;
import xyz.skynetcloud.cybercore.init.OtherInit.ScreenInit;
import xyz.skynetcloud.cybercore.world.gen.OreGen;

@Mod("cybercore")
public class CyberCoreMain {

	public static String MODID = "cybercore";

	public static final Logger LOGGER = LogManager.getLogger();

	public CyberCoreMain() {

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

		MinecraftForge.EVENT_BUS.register(this);

	}

	private void setup(FMLCommonSetupEvent event) {

		ScreenInit.registerGUI();
		OreGen.setupOreGeneration();
	}

	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {

		LOGGER.info("HELLO from server starting");
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
