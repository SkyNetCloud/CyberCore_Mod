package ca.skynetcloud.cybercore.block.blocks.color_cable;

import ca.skynetcloud.cybercore.block.blocks.CableBlock;
import net.minecraft.item.DyeColor;

public class ColorCable extends CableBlock {

	private DyeColor color;

	public ColorCable(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isTubeCompatible(CableBlock cable) {
		if (cable instanceof ColorCable) {
			return ((ColorCable) cable).color.equals(this.color);
		} else {
			return true;
		}
	}

}
