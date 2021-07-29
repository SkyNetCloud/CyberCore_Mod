package ca.skynetcloud.cybercore.block.blocks.slab.color;

import ca.skynetcloud.cybercore.block.blocks.slab.Slab_Block;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item.Properties;

public class SlabColor extends Slab_Block {

	private DyeColor color;

	public SlabColor(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isSlabBlock(Slab_Block slab) {
		if (slab instanceof SlabColor) {
			return ((SlabColor) slab).color.equals(this.color);
		} else {
			return true;
		}
	}

}
