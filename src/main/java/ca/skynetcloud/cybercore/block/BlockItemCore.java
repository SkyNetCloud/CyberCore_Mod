package ca.skynetcloud.cybercore.block;

import ca.skynetcloud.cybercore.CyberCoreTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class BlockItemCore extends BlockItem {

	public BlockItemCore(Block blockIn) {
		super(blockIn, new Properties().tab(CyberCoreTab.instance));
		setRegistryName(blockIn.getRegistryName());
	}

}
