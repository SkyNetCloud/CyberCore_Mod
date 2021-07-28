package ca.skynetcloud.cybercore.block.blocks.fences.gate.color;

import ca.skynetcloud.cybercore.block.blocks.fences.gate.ElecticFenceGate;
import net.minecraft.world.item.DyeColor;

public class ColorFenceGate extends ElecticFenceGate {

	private DyeColor color;

	public ColorFenceGate(DyeColor color, Properties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isFencesGateBlock(ElecticFenceGate fencegate) {
		if (fencegate instanceof ColorFenceGate) {
			return ((ColorFenceGate) fencegate).color.equals(this.color);
		} else {
			return true;
		}
	}

}
