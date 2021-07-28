package ca.skynetcloud.cybercore.block.blocks.fences.top.color;

import ca.skynetcloud.cybercore.block.blocks.fences.top.ElectricFenceTop;
import net.minecraft.world.item.DyeColor;

public class ElectricFenceTopColor extends ElectricFenceTop {

	private DyeColor color;

	public ElectricFenceTopColor(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isFenceTopsBlock(ElectricFenceTop fencetop) {
		if (fencetop instanceof ElectricFenceTopColor) {
			return ((ElectricFenceTopColor) fencetop).color.equals(this.color);
		} else {
			return true;
		}
	}

}
	