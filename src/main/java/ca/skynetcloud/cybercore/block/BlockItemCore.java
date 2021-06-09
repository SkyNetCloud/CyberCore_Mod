package ca.skynetcloud.cybercore.block;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemCore extends BlockItem {

	public BlockItemCore(Block blockIn) {
		super();
		setRegistryName(blockIn.getName());
	}

}
