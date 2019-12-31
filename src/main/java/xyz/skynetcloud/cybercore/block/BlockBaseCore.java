package xyz.skynetcloud.cybercore.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;

@SuppressWarnings("unused")
public class BlockBaseCore extends Block {

	private ItemGroup group;

	public BlockBaseCore(Properties property, ItemGroup group) {
		super(property);
		this.group = group;

	}

}
