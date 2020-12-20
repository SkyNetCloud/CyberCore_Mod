package ca.skynetcloud.cybercore.block;

import ca.skynetcloud.cybercore.CyberCoreClient.CyberCoreTab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemCore extends BlockItem {

	public BlockItemCore(Block blockIn) {
		super(blockIn, new Properties().group(CyberCoreTab.instance));
		setRegistryName(blockIn.getRegistryName());
	}

}
