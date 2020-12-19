package ca.skynetcloud.cybercore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeTypes;
import ca.skynetcloud.cybercore.util.networking.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.util.networking.config.ConfigLoadder;
import ca.skynetcloud.cybercore.world.gen.OreGeneration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

		new ModedRecipeTypes();
		CyberCorePacketHandler.register();
		OreGeneration.registerOres();

	}

	public static class CyberCoreTab extends ItemGroup {

		public static final CyberCoreTab instance = new CyberCoreTab(ItemGroup.GROUPS.length, Names.CyberTAB);
		public static final CyberCoreTab item_cable = new CyberCoreTab(ItemGroup.GROUPS.length,
				Names.CyberTAB_Item_Cable);
		public static final CyberCoreTab power_cable = new CyberCoreTab(ItemGroup.GROUPS.length,
				Names.CyberTAB_Power_Cable);

		private CyberCoreTab(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {

			if (this == instance) {
				return new ItemStack(ItemInit.cyber_ingot);
			} else if (this == item_cable) {
				return new ItemStack(BlockInit.BLOCK_PIPE);
			} else if (this == power_cable) {
				return new ItemStack(BlockInit.CABLE);
			}

			return new ItemStack(ItemInit.cyber_ingot);
		}
	}

}
