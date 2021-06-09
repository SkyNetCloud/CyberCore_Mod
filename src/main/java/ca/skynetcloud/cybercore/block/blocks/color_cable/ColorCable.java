package ca.skynetcloud.cybercore.block.blocks.color_cable;

import ca.skynetcloud.cybercore.block.blocks.CableBlock;
import net.minecraft.util.DyeColor;

public class ColorCable extends CableBlock {

	private DyeColor color;

	public ColorCable(DyeColor color, Settings settings) {
		super(settings);
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
