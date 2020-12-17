package ca.skynetcloud.cybercore.block.blocks.color_cable;

import ca.skynetcloud.cybercore.block.blocks.ItemCable;
import net.minecraft.item.DyeColor;

public class ColorItemCable extends ItemCable {

	private DyeColor color;

	public ColorItemCable(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isTubeCompatible(ItemCable cable) {
		if (cable instanceof ColorItemCable) {
			return ((ColorItemCable) cable).color.equals(this.color);
		} else {
			return true;
		}
	}

}
