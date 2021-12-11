package ca.skynetcloud.cybercore.common.block.blocks.cables.color;

import ca.skynetcloud.cybercore.common.block.blocks.cables.CableBlock;
import net.minecraft.world.item.DyeColor;

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
