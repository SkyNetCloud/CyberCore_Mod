package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.CyberCoreClient.CyberCoreTab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;

public class SeedsInit extends BlockNamedItem {

	public SeedsInit(Block crop) {
		super(crop, new Item.Properties().tab(CyberCoreTab.instance));

	}

}
