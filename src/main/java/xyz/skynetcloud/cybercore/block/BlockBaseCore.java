package xyz.skynetcloud.cybercore.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

public class BlockBaseCore extends Block {

	@SuppressWarnings("unused")
	private ItemGroup group;

	public BlockBaseCore(Properties properties, ItemGroup group, boolean hasItem) {
		super(properties);
		this.group = group;
		// this.name = name;

	}

}
