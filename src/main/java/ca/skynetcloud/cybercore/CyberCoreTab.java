package ca.skynetcloud.cybercore;


import ca.skynetcloud.cybercore.client.init.CoreInit;
import ca.skynetcloud.cybercore.client.networking.helper.NameHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CyberCoreTab extends CreativeModeTab {

	public static final CyberCoreTab instance = new CyberCoreTab(CyberCoreTab.TABS.length, NameHelper.CyberTAB);
	public static final CyberCoreTab item_cable = new CyberCoreTab(CyberCoreTab.TABS.length, NameHelper.CyberTAB_Item_Cable);
	public static final CyberCoreTab power_cable = new CyberCoreTab(CyberCoreTab.TABS.length,
			NameHelper.CyberTAB_Power_Cable);
	public static final CyberCoreTab other = new CyberCoreTab(CyberCoreTab.TABS.length, NameHelper.CyberTAB_Other_Tab);

	private CyberCoreTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {

		if (this == instance) {
			return new ItemStack(CoreInit.ItemInit.cyber_ingot);
		} else if (this == item_cable) {
			return new ItemStack(CoreInit.BlockInit.BLOCK_PIPE);
		} else if (this == power_cable) {
			return new ItemStack(CoreInit.BlockInit.CABLE);
		} else if (this == other) {
			return new ItemStack(CoreInit.BlockInit.Fence_Gate_Block);
		}

		return new ItemStack(CoreInit.ItemInit.cyber_ingot);
	}
}
