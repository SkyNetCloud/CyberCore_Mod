package ca.skynetcloud.cybercore.common.block.blocks.slab;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class Slab_Block extends SlabBlock {

	public Slab_Block() {
		super(Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.2F));

	}

	public boolean isSlabBlock(Slab_Block cable) {
		return true;
	}

}
