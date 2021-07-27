package ca.skynetcloud.cybercore.init;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;

public class SeedsInit extends ItemNameBlockItem {

	public SeedsInit(Block crop) {
		super(crop, new Item.Properties().tab(CyberCoreTab.instance));

	}

}
