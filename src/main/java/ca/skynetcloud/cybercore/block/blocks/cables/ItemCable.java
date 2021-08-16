package ca.skynetcloud.cybercore.block.blocks.cables;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.block.blocks.cables.color.ColorCable;
import ca.skynetcloud.cybercore.block.blocks.cables.color.ColorItemCable;
import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;

public class ItemCable extends Block implements EntityBlock, BucketPickup, LiquidBlockContainer {
	public static final Direction[] FACING_VALUES = Direction.values();

	public static final BooleanProperty DOWN = PipeBlock.DOWN;
	public static final BooleanProperty UP = PipeBlock.UP;
	public static final BooleanProperty NORTH = PipeBlock.NORTH;
	public static final BooleanProperty SOUTH = PipeBlock.SOUTH;
	public static final BooleanProperty WEST = PipeBlock.WEST;
	public static final BooleanProperty EAST = PipeBlock.EAST;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	protected final VoxelShape[] shapes;

	public ItemCable() {
		super(Block.Properties.of(Material.GLASS).strength(0.4F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL));
		this.registerDefaultState(stateDefinition.any().setValue(NORTH, Boolean.valueOf(false))
				.setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false))
				.setValue(WEST, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false))
				.setValue(UP, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)));
		this.shapes = this.makeShapes();

	}

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new ItemPipeTileEntity(pos, state);
	}

	// block behaviour

	@SuppressWarnings("deprecation")
	@Override

	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() == newState.getBlock()) {

		} else {
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean wat) {
		if (!world.isClientSide) {
			ItemPipeTileEntity.getTubeTEAt(world, pos).ifPresent(te -> te.onPossibleNetworkUpdateRequired());
		}
		super.neighborChanged(state, world, pos, blockIn, fromPos, wat);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
			ItemStack stack) {
		if (!world.isClientSide) {
			ItemPipeTileEntity.getTubeTEAt(world, pos).ifPresent(te -> te.onPossibleNetworkUpdateRequired());
		}
		super.setPlacedBy(world, pos, state, placer, stack);
	}

	/// connections and states

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockGetter world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		return super.getStateForPlacement(context).setValue(DOWN, this.canConnectTo(world, pos, Direction.DOWN))
				.setValue(UP, this.canConnectTo(world, pos, Direction.UP))
				.setValue(NORTH, this.canConnectTo(world, pos, Direction.NORTH))
				.setValue(SOUTH, this.canConnectTo(world, pos, Direction.SOUTH))
				.setValue(WEST, this.canConnectTo(world, pos, Direction.WEST))
				.setValue(EAST, this.canConnectTo(world, pos, Direction.EAST))
				.setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	protected boolean canConnectTo(BlockGetter world, BlockPos pos, Direction face) {
		BlockPos newPos = pos.relative(face);
		BlockState state = world.getBlockState(newPos);
		Block block = state.getBlock();

		if (block instanceof ColorCable) {
			return false;
		}

		if (block instanceof ColorItemCable) {
			return true;
		}

		if (block instanceof ItemCable)
			return this.isTubeCompatible((ItemCable) block);

		BlockEntity te = world.getBlockEntity(newPos);

		if (te == null)
			return false;

		if (te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, face.getOpposite()).isPresent()) {
			return true;
		}
		return false;
	}

	public boolean isTubeCompatible(ItemCable tube) {
		return true;
	}

	public static List<Direction> getConnectedDirections(BlockState state) {
		Block block = state.getBlock();
		ArrayList<Direction> dirs = new ArrayList<Direction>();
		if (block instanceof ItemCable) {
			for (Direction dir : Direction.values()) {
				if (state.getValue(PipeBlock.PROPERTY_BY_DIRECTION.get(dir))) {
					dirs.add(dir);
				}
			}
		}
		return dirs;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST, WATERLOGGED);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return stateIn.setValue(PipeBlock.PROPERTY_BY_DIRECTION.get(facing),
				Boolean.valueOf(this.canConnectTo(worldIn, currentPos, facing)));
	}

	/// model shapes

	protected VoxelShape[] makeShapes() {

		VoxelShape[] shapes = new VoxelShape[64];

		VoxelShape core = Block.box(7F, 7F, 7F, 9F, 9F, 9F);

		VoxelShape down = Block.box(7F, 0F, 7F, 9F, 7F, 9F);
		VoxelShape up = Block.box(7F, 9F, 7F, 9F, 16F, 9F);
		VoxelShape north = Block.box(7F, 7F, 0F, 9F, 9F, 7F);
		VoxelShape south = Block.box(7F, 7F, 9F, 9F, 9F, 16F);
		VoxelShape west = Block.box(0F, 7F, 7F, 7F, 9F, 9F);
		VoxelShape east = Block.box(9F, 7F, 7F, 16F, 9F, 9F);

		VoxelShape[] dunswe = { down, up, north, south, west, east };

		for (int i = 0; i < 64; i++) {
			shapes[i] = core;
			for (int j = 0; j < 6; j++) {
				if ((i & (1 << j)) != 0) {
					shapes[i] = Shapes.or(shapes[i], dunswe[j]);
				}
			}
		}

		return shapes;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.getShape(worldIn, pos);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return this.getShape(state, worldIn, pos, context);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return this.shapes[this.getShapeIndex(state)];
	}

	public int getShapeIndex(BlockState state) {
		int index = 0;

		for (int j = 0; j < FACING_VALUES.length; ++j) {
			if (state.getValue(PipeBlock.PROPERTY_BY_DIRECTION.get(FACING_VALUES[j]))) {
				index |= 1 << j;
			}
		}

		return index;
	}

	@Override
	public boolean canPlaceLiquid(BlockGetter p_54766_, BlockPos p_54767_, BlockState p_54768_,
			net.minecraft.world.level.material.Fluid p_54769_) {

		return !p_54768_.getValue(WATERLOGGED) && p_54769_ == Fluids.WATER;

	}

	@Override
	public boolean placeLiquid(LevelAccessor worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.getValue(WATERLOGGED) && fluidStateIn.getType() == Fluids.WATER) {
			if (!worldIn.isClientSide()) {
				worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.getLiquidTicks().scheduleTick(pos, fluidStateIn.getType(),
						fluidStateIn.getType().getTickDelay(worldIn));
			}

			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public ItemStack pickupBlock(LevelAccessor p_152719_, BlockPos p_152720_, BlockState p_152721_) {
		return null;
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		// TODO Auto-generated method stub
		return null;
	}

}