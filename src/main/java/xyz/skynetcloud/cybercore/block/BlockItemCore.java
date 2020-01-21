package xyz.skynetcloud.cybercore.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;

public class BlockItemCore extends BlockItem {

	public BlockItemCore(Block blockIn) {
		super(blockIn, new Properties().group(CyberCoreTab.instance));
		setRegistryName(blockIn.getRegistryName());
	}

}
