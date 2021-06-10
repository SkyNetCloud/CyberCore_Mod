package ca.skynetcloud.cybercore.block.blocks.slab;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class Slab_Block extends SlabBlock {

	public Slab_Block() {
		super(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.2F));

	}

	public boolean isSlabBlock(Slab_Block cable) {
		return true;
	}

}
