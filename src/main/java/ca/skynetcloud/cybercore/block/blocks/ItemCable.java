package ca.skynetcloud.cybercore.block.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.block.blocks.color_cable.ColorCable;
import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
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
import net.minecraft.pathfinding.PathType;
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
		super(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.4F).harvestTool(ToolType.PICKAXE)
				.sound(SoundType.METAL));
		this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, Boolean.valueOf(false))
				.with(EAST, Boolean.valueOf(false)).with(SOUTH, Boolean.valueOf(false))
				.with(WEST, Boolean.valueOf(false)).with(DOWN, Boolean.valueOf(false)).with(UP, Boolean.valueOf(false))
				.with(WATERLOGGED, Boolean.valueOf(false)));
		this.shapes = this.makeShapes();

	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
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

	@Override
	@Deprecated
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() == newState.getBlock()) {

		} else {
			super.onReplaced(state, world, pos, newState, isMoving);
		}
	}

	@Override
	@Deprecated
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean wat) {
		if (!world.isRemote) {
			ItemPipeTileEntity.getTubeTEAt(world, pos).ifPresent(te -> te.onPossibleNetworkUpdateRequired());
		}
		super.neighborChanged(state, world, pos, blockIn, fromPos, wat);
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
			ItemStack stack) {
		if (!world.isRemote) {
			ItemPipeTileEntity.getTubeTEAt(world, pos).ifPresent(te -> te.onPossibleNetworkUpdateRequired());
		}
		super.onBlockPlacedBy(world, pos, state, placer, stack);
	}

	/// connections and states

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IBlockReader world = context.getWorld();
		BlockPos pos = context.getPos();
		FluidState fluidstate = context.getWorld().getFluidState(context.getPos());
		return super.getStateForPlacement(context).with(DOWN, this.canConnectTo(world, pos, Direction.DOWN))
				.with(UP, this.canConnectTo(world, pos, Direction.UP))
				.with(NORTH, this.canConnectTo(world, pos, Direction.NORTH))
				.with(SOUTH, this.canConnectTo(world, pos, Direction.SOUTH))
				.with(WEST, this.canConnectTo(world, pos, Direction.WEST))
				.with(EAST, this.canConnectTo(world, pos, Direction.EAST))
				.with(WATERLOGGED, Boolean.valueOf(fluidstate.getFluid() == Fluids.WATER));
	}

	protected boolean canConnectTo(IBlockReader world, BlockPos pos, Direction face) {
		BlockPos newPos = pos.offset(face);
		BlockState state = world.getBlockState(newPos);
		Block block = state.getBlock();

		if (block instanceof ColorCable) {
			return CyberCoreConfig.CanConnect.get();
		}

		if (block instanceof ItemCable)
			return this.isTubeCompatible((ItemCable) block);

		TileEntity te = world.getTileEntity(newPos);

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
				if (state.get(SixWayBlock.FACING_TO_PROPERTY_MAP.get(dir))) {
					dirs.add(dir);
				}
			}
		}
		return dirs;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST, WATERLOGGED);
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.get(WATERLOGGED)) {
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
		}

		return stateIn.with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(facing),
				Boolean.valueOf(this.canConnectTo(worldIn, currentPos, facing)));
	}

	/// model shapes

	protected VoxelShape[] makeShapes() {

		VoxelShape[] shapes = new VoxelShape[64];

		VoxelShape core = Block.makeCuboidShape(7F, 7F, 7F, 9F, 9F, 9F);

		VoxelShape down = Block.makeCuboidShape(7F, 0F, 7F, 9F, 7F, 9F);
		VoxelShape up = Block.makeCuboidShape(7F, 9F, 7F, 9F, 16F, 9F);
		VoxelShape north = Block.makeCuboidShape(7F, 7F, 0F, 9F, 9F, 7F);
		VoxelShape south = Block.makeCuboidShape(7F, 7F, 9F, 9F, 9F, 16F);
		VoxelShape west = Block.makeCuboidShape(0F, 7F, 7F, 7F, 9F, 9F);
		VoxelShape east = Block.makeCuboidShape(9F, 7F, 7F, 16F, 9F, 9F);

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
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
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
			if (state.get(SixWayBlock.FACING_TO_PROPERTY_MAP.get(FACING_VALUES[j]))) {
				index |= 1 << j;
			}
		}

		return index;
	}

	@Override
	public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
		return !state.get(WATERLOGGED) && fluidIn == Fluids.WATER;
	}

	@Override
	public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
		if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
			if (!worldIn.isRemote()) {
				worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
				worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(),
						fluidStateIn.getFluid().getTickRate(worldIn));
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
		if (state.get(WATERLOGGED)) {
			worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(false)), 3);
			return Fluids.WATER;
		} else {
			return Fluids.EMPTY;
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public FluidState getFluidState(BlockState state) {
		HashMap<Direction, Direction> map = new HashMap<>();
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}

}