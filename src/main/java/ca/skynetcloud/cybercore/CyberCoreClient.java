package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.init.ScreenInit;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CyberCoreMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CyberCoreClient {

	@SubscribeEvent
	static void clientSetup(final FMLClientSetupEvent event) {

		ScreenInit.registerGUI();

		RenderTypeLookup.setRenderLayer(BlockInit.LETTUCE_CROP, RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(BlockInit.TOMATO_CROP, RenderType.getCutout());
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
