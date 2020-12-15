package ca.skynetcloud.cybercore.block.blocks;

import java.util.Random;

import org.apache.commons.lang3.tuple.Triple;

import ca.skynetcloud.cybercore.block.BlockBaseCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class CropBlockGrower extends BlockBaseCore {

	public CropBlockGrower(boolean hasItem) {
		super(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.5F, 10.0F).harvestTool(ToolType.PICKAXE)
				.sound(SoundType.GLASS).tickRandomly(), hasItem);

	}

	public boolean isOpaqueCube(BlockState state) {
		return false;
	}

	public boolean isFullCube(BlockState state) {
		return false;
	}

	public int getLightOpacity(BlockState state) {
		return 0;
	}

	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
		if (world.isRemote)
			return;
		if (world.canBlockSeeSky(pos) && world.isDaytime()) {
			Triple<BlockPos, BlockState, IGrowable> trip = firstBlock(world, pos);
			boolean once = false;
			if (trip != null)
				for (int i = 0; i < 3; i++) {
					BlockState growState = i == 0 ? trip.getMiddle() : world.getBlockState(trip.getLeft());
					if (growState.getBlock() == trip.getRight()
							&& trip.getRight().canGrow(world, trip.getLeft(), growState, false)) {
						trip.getRight().grow(world, rand, trip.getLeft(), growState);
						once = true;
					}
				}
			if (once)
				world.playEvent(2005, trip.getMiddle().isOpaqueCube(world, pos) ? trip.getLeft().up() : trip.getLeft(),
						0);
		}
	}

	public Triple<BlockPos, BlockState, IGrowable> firstBlock(World world, BlockPos glassPos) {
		BlockPos.Mutable mut = new BlockPos.Mutable();
		while (true) {
			mut.setPos(mut.getX(), mut.getY() - 1, mut.getZ());
			if (mut.getY() < 0)
				return null;
			BlockState state = world.getBlockState(mut);
			if (state.isOpaqueCube(world, glassPos) || state.getBlock() instanceof IGrowable
					|| state.getBlock() == this) {
				if (state.getBlock() instanceof IGrowable)
					return Triple.of(mut.toImmutable(), state, (IGrowable) state.getBlock());
				else
					return null;
			}
		}
	}

}
