package ca.skynetcloud.cybercore.block.tech.blocks;

import java.util.function.Supplier;
import java.util.stream.Stream;

import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CyberCorePowerBlock extends TechBlockBaseSubCore {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final IntegerProperty card = IntegerProperty.create("card", 0, 3);

	public CyberCorePowerBlock(Supplier<? extends TileEntity> teCreator) {
		super(teCreator);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(card, 0));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		Stream.of(Block.makeCuboidShape(15, 0, 0, 16, 1, 1), Block.makeCuboidShape(14, 1, 0, 15, 2, 1),
				Block.makeCuboidShape(13, 2, 0, 14, 3, 1), Block.makeCuboidShape(0, 3, 0, 16, 18, 16),
				Block.makeCuboidShape(1, 1, 0, 2, 2, 1), Block.makeCuboidShape(15, 0, 15, 16, 1, 16),
				Block.makeCuboidShape(2, 2, 0, 3, 3, 1), Block.makeCuboidShape(0, 0, 15, 1, 1, 16),
				Block.makeCuboidShape(1, 1, 15, 2, 2, 16), Block.makeCuboidShape(2, 2, 15, 3, 3, 16),
				Block.makeCuboidShape(13, 2, 15, 14, 3, 16), Block.makeCuboidShape(14, 1, 15, 15, 2, 16),
				Block.makeCuboidShape(0, 0, 0, 1, 1, 1)).reduce((v1, v2) -> {
					return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
				});
		return super.getShape(state, worldIn, pos, context);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		worldIn.setBlockState(pos, this.getDefaultState().with(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate((Direction) state.get(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation((Direction) state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING).add(card);
	}

}
