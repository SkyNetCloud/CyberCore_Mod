package ca.skynetcloud.cybercore.block.building.block.color;

import ca.skynetcloud.cybercore.block.building.block.SlabBlocks;

import net.minecraft.item.DyeColor;

public class SlabColors extends SlabBlocks {

	private DyeColor color;

	public SlabColors(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isSlabCap(SlabBlocks slab) {
		if (slab instanceof SlabColors) {
			return ((SlabColors) slab).color.equals(this.color);
		} else {
			return true;
		}
	}

}
