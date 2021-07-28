package ca.skynetcloud.cybercore.block.blocks.fences;

import ca.skynetcloud.cybercore.block.blocks.fences.gate.ElecticFenceGate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CrossCollisionBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ElecticFencesBlock extends BasicElecticFence {

	public static final BooleanProperty NORTH = CrossCollisionBlock.NORTH;
	public static final BooleanProperty EAST = CrossCollisionBlock.EAST;
	public static final BooleanProperty SOUTH = CrossCollisionBlock.SOUTH;
	public static final BooleanProperty WEST = CrossCollisionBlock.WEST;

	public static final VoxelShape POST = Block.box(7.5D, 0.10D, 7.5D, 8.5D, 15.90D, 8.5D);
	public static final VoxelShape NEGATIVE_Z = Block.box(7.0D, 0.10D, 0.10D, 9.0D, 15.90D, 8.5D);
	public static final VoxelShape POSITIVE_Z = Block.box(7.0D, 0.10D, 7.5D, 9.0D, 15.90D, 15.90D);
	public static final VoxelShape NEGATIVE_X = Block.box(0.10D, 0.10D, 7.0D, 8.5D, 15.90D, 9.0D);
	public static final VoxelShape POSITIVE_X = Block.box(7.5D, 0.10D, 7.0D, 15.90D, 15.90D, 9.0D);

	public ElecticFencesBlock() {
		super(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(30.0F));
		this.registerDefaultState(stateDefinition.any().setValue(ELECTRIC_POWER, 0)
				.setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false))
				.setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)));
	}

	public boolean isFencesBlock(ElecticFencesBlock fence) {
		return true;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(NORTH, EAST, SOUTH, WEST);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		return getState(this.defaultBlockState().setValue(ELECTRIC_POWER, calculatePower((Level) worldIn, currentPos)),
				(Level) worldIn, currentPos);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return getState(
				this.defaultBlockState().setValue(ELECTRIC_POWER,
						calculatePower(context.getLevel(), context.getClickedPos())),
				context.getLevel(), context.getClickedPos());
	}

	public boolean canConnectTo(Level world, BlockPos pos, Direction direction) {
		BlockState state = world.getBlockState(pos.relative(direction));
		Block block = state.getBlock();
		return ((block instanceof ElecticFencesBlock || block instanceof ElecticFenceGate)
				|| state.isFaceSturdy(world, pos.relative(direction), direction.getOpposite()));
	}

	private BlockState getState(BlockState state, Level worldIn, BlockPos pos) {
		return state.setValue(NORTH, canConnectTo(worldIn, pos, Direction.SOUTH))
				.setValue(EAST, canConnectTo(worldIn, pos, Direction.WEST))
				.setValue(SOUTH, canConnectTo(worldIn, pos, Direction.NORTH))
				.setValue(WEST, canConnectTo(worldIn, pos, Direction.EAST));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		VoxelShape shape = POST;
		if (state.getValue(NORTH))
			shape = Shapes.or(shape, POSITIVE_Z);
		if (state.getValue(SOUTH))
			shape = Shapes.or(shape, NEGATIVE_Z);
		if (state.getValue(WEST))
			shape = Shapes.or(shape, POSITIVE_X);
		if (state.getValue(EAST))
			shape = Shapes.or(shape, NEGATIVE_X);
		return shape;
	}

}
