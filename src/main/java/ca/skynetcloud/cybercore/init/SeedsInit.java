package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class SeedsInit extends BlockNamedItem {

	public SeedsInit(Block crop) {
		super(crop, new Item.Properties().group(CyberCoreTab.instance));

	}

}
