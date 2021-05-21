package ca.skynetcloud.cybercore.block.blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.block.blocks.color_cable.ColorCable;
import ca.skynetcloud.cybercore.block.blocks.color_cable.ColorItemCable;
import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IBucketPickupHandler;
import net.minecraft.block.ILiquidContainer;
import net.minecraft.block.SixWayBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;

public class ItemCable extends Block implements IBucketPickupHandler, ILiquidContainer {
	public static final Direction[] FACING_VALUES = Direction.values();

	public static final BooleanProperty DOWN = SixWayBlock.DOWN;
	public static final BooleanProperty UP = SixWayBlock.UP;
	public static final BooleanProperty NORTH = SixWayBlock.NORTH;
	public static final BooleanProperty SOUTH = SixWayBlock.SOUTH;
	public static final BooleanProperty WEST = SixWayBlock.WEST;
	public static final BooleanProperty EAST = SixWayBlock.EAST;
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
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new ItemPipeTileEntity();
	}

	// block behaviour

	@SuppressWarnings("deprecation")
	@Override

	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() == newState.getBlock()) {

		} else {
			super.onRemove(state, world, pos, newState, isMoving);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean wat) {
		if (!world.isClientSide) {
			ItemPipeTileEntity.getTubeTEAt(world, pos).ifPresent(te -> te.onPossibleNetworkUpdateRequired());
		}
		super.neighborChanged(state, world, pos, blockIn, fromPos, wat);
	}

	@Override
	public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
			ItemStack stack) {
		if (!world.isClientSide) {
			ItemPipeTileEntity.getTubeTEAt(world, pos).ifPresent(te -> te.onPossibleNetworkUpdateRequired());
		}
		super.setPlacedBy(world, pos, state, placer, stack);
	}

	/// connections and states

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IBlockReader world = context.getLevel();
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

	protected boolean canConnectTo(IBlockReader world, BlockPos pos, Direction face) {
		BlockPos newPos = pos.relative(face);
		BlockState state = world.getBlockState(newPos);
		Block block = state.getBlock();

		if (block instanceof ColorCable) {
			return false;
		}

		if (block instanceof ColorItemCable) {
			return true;
		}

		if (block instanceof ExtractorBlock && state.getValue(ExtractorBlock.FACING).equals(face.getOpposite()))
			return true;

		if (block instanceof ItemCable)
			return this.isTubeCompatible((ItemCable) block);

		TileEntity te = world.getBlockEntity(newPos);

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
				if (state.getValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(dir))) {
					dirs.add(dir);
				}
			}
		}
		return dirs;
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST, WATERLOGGED);
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.getLiquidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return stateIn.setValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(facing),
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
					shapes[i] = VoxelShapes.or(shapes[i], dunswe[j]);
				}
			}
		}

		return shapes;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getShape(worldIn, pos);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return this.getShape(state, worldIn, pos, context);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.shapes[this.getShapeIndex(state)];
	}

	public int getShapeIndex(BlockState state) {
		int index = 0;

		for (int j = 0; j < FACING_VALUES.length; ++j) {
			if (state.getValue(SixWayBlock.PROPERTY_BY_DIRECTION.get(FACING_VALUES[j]))) {
				index |= 1 << j;
			}
		}

		return index;
	}

	@Override
	public boolean canPlaceLiquid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.getValue(WATERLOGGED) && fluidIn == Fluids.WATER;
	}

	@Override
	public boolean placeLiquid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
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

	@Override
	public Fluid takeLiquid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.getValue(WATERLOGGED)) {
			worldIn.setBlock(pos, state.setValue(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

}