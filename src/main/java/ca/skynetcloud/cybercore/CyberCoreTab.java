package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CyberCoreTab extends CreativeModeTab {

	public static final CyberCoreTab instance = new CyberCoreTab(CyberCoreTab.TABS.length, Names.CyberTAB);
	public static final CyberCoreTab other = new CyberCoreTab(CyberCoreTab.TABS.length, Names.CyberTAB_Other_Tab);

	private CyberCoreTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {

		if (this == instance) {
			return new ItemStack(ItemInit.cyber_ingot);
		} else if (this == other) {
			return new ItemStack(BlockInit.Fence_Gate_Block);
		}

		return new ItemStack(ItemInit.cyber_ingot);
	}
}
