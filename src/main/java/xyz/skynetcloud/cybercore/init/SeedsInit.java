package xyz.skynetcloud.cybercore.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;

public class SeedsInit extends BlockNamedItem {

	public SeedsInit(Block crop) {
		super(crop, new Item.Properties().group(CyberCoreTab.instance));

	}

}
