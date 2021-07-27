package ca.skynetcloud.cybercore.block.blocks.fences.color;

import ca.skynetcloud.cybercore.block.blocks.fences.ElecticFencesBlock;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FencesColor extends ElecticFencesBlock {

	private DyeColor color;

	public FencesColor(DyeColor color, BlockStateProperties properties) {
		super();
		this.color = color;
	}

	@Override
	public boolean isFencesBlock(ElecticFencesBlock fences) {
		if (fences instanceof FencesColor) {
			return ((FencesColor) fences).color.equals(this.color);
		} else {
			return true;
		}
	}
}
