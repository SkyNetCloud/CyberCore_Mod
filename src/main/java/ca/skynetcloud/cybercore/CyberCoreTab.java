package ca.skynetcloud.cybercore;

import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CyberCoreTab extends ItemGroup {

	public static final CyberCoreTab instance = new CyberCoreTab(ItemGroup.TABS.length, Names.CyberTAB);
	public static final CyberCoreTab item_cable = new CyberCoreTab(ItemGroup.TABS.length, Names.CyberTAB_Item_Cable);
	public static final CyberCoreTab power_cable = new CyberCoreTab(ItemGroup.TABS.length, Names.CyberTAB_Power_Cable);

	private CyberCoreTab(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {

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
 