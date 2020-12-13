package ca.skynetcloud.cybercore.block.tech.blocks.color;

import ca.skynetcloud.cybercore.block.tech.blocks.CyberCoreItemPipe;
import net.minecraft.item.DyeColor;

public class ColorItemCable extends CyberCoreItemPipe {

	private DyeColor color;

	public ColorItemCable(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	public boolean isTubeCompatible(CyberCoreItemPipe tube) {
		if (tube instanceof ColorItemCable) {
			return ((ColorItemCable) tube).color.equals(this.color);
		} else {
			return true;
		}
	}

}
