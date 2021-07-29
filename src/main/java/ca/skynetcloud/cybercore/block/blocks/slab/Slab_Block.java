package ca.skynetcloud.cybercore.block.blocks.slab;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class Slab_Block extends SlabBlock {

	public Slab_Block() {
		super(BlockBehaviour.Properties.of(Material.STONE).sound(SoundType.STONE).strength(0.2F));

	}

	public boolean isSlabBlock(Slab_Block cable) {
		return true;
	}

}
