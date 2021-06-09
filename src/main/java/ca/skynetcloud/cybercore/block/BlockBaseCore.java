package ca.skynetcloud.cybercore.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

@SuppressWarnings("unused")
public class BlockBaseCore extends Block {

	public BlockBaseCore(Settings settings) {
		super(settings);

	}

	private String name;

	protected VoxelShape[] makeShapes(float nodeWidth, float extensionWidth, float p_196408_3_, float p_196408_4_,
			float p_196408_5_) {
		float f = 8.0F - nodeWidth;
		float f1 = 8.0F + nodeWidth;
		float f2 = 8.0F - extensionWidth;
		float f3 = 8.0F + extensionWidth;
		VoxelShape voxelshape = Block.createCuboidShape(f, 0.0D, f, f1, p_196408_3_, f1);
		VoxelShape voxelshape1 = Block.createCuboidShape(f2, p_196408_4_, 0.0D, f3, p_196408_5_, f3);
		VoxelShape voxelshape2 = Block.createCuboidShape(f2, p_196408_4_, f2, f3, p_196408_5_, 16.0D);
		VoxelShape voxelshape3 = Block.createCuboidShape(0.0D, p_196408_4_, f2, f3, p_196408_5_, f3);
		VoxelShape voxelshape4 = Block.createCuboidShape(f2, p_196408_4_, f2, 16.0D, p_196408_5_, f3);
		VoxelShape voxelshape5 = VoxelShapes.union(voxelshape1, voxelshape4);
		VoxelShape voxelshape6 = VoxelShapes.union(voxelshape2, voxelshape3);
		VoxelShape[] avoxelshape = { VoxelShapes.empty(), voxelshape2, voxelshape3, voxelshape6, voxelshape1,
				VoxelShapes.union(voxelshape2, voxelshape1), VoxelShapes.union(voxelshape3, voxelshape1),
				VoxelShapes.union(voxelshape6, voxelshape1), voxelshape4, VoxelShapes.union(voxelshape2, voxelshape4),
				VoxelShapes.union(voxelshape3, voxelshape4), VoxelShapes.union(voxelshape6, voxelshape4), voxelshape5,
				VoxelShapes.union(voxelshape2, voxelshape5), VoxelShapes.union(voxelshape3, voxelshape5),
				VoxelShapes.union(voxelshape6, voxelshape5) };
		for (int i = 0; i < 16; i++)
			avoxelshape[i] = VoxelShapes.union(voxelshape, avoxelshape[i]);
		return avoxelshape;
	}

	public StateManager<Block, BlockState> getCurrentState() {
		return null;
	}
}
