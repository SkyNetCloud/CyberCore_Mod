package ca.skynetcloud.cybercore.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

@SuppressWarnings("unused")
public class BlockBaseCore extends Block {

	private String name;

	public BlockBaseCore(Properties property, boolean hasItem) {
		super(property);
	}

	protected VoxelShape[] makeShapes(float nodeWidth, float extensionWidth, float p_196408_3_, float p_196408_4_,
			float p_196408_5_) {
		float f = 8.0F - nodeWidth;
		float f1 = 8.0F + nodeWidth;
		float f2 = 8.0F - extensionWidth;
		float f3 = 8.0F + extensionWidth;
		VoxelShape voxelshape = Block.box(f, 0.0D, f, f1, p_196408_3_, f1);
		VoxelShape voxelshape1 = Block.box(f2, p_196408_4_, 0.0D, f3, p_196408_5_, f3);
		VoxelShape voxelshape2 = Block.box(f2, p_196408_4_, f2, f3, p_196408_5_, 16.0D);
		VoxelShape voxelshape3 = Block.box(0.0D, p_196408_4_, f2, f3, p_196408_5_, f3);
		VoxelShape voxelshape4 = Block.box(f2, p_196408_4_, f2, 16.0D, p_196408_5_, f3);
		VoxelShape voxelshape5 = Shapes.or(voxelshape1, voxelshape4);
		VoxelShape voxelshape6 = Shapes.or(voxelshape2, voxelshape3);
		VoxelShape[] avoxelshape = { Shapes.empty(), voxelshape2, voxelshape3, voxelshape6, voxelshape1,
				Shapes.or(voxelshape2, voxelshape1), Shapes.or(voxelshape3, voxelshape1),
				Shapes.or(voxelshape6, voxelshape1), voxelshape4, Shapes.or(voxelshape2, voxelshape4),
				Shapes.or(voxelshape3, voxelshape4), Shapes.or(voxelshape6, voxelshape4), voxelshape5,
				Shapes.or(voxelshape2, voxelshape5), Shapes.or(voxelshape3, voxelshape5),
				Shapes.or(voxelshape6, voxelshape5) };
		for (int i = 0; i < 16; i++)
			avoxelshape[i] = Shapes.or(voxelshape, avoxelshape[i]);
		return avoxelshape;
	}

	public StateHolder<Block, BlockState> getCurrentState() {
		return null;
	}
}
