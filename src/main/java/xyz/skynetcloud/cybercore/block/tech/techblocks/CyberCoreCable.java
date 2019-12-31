package xyz.skynetcloud.cybercore.block.tech.techblocks;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import xyz.skynetcloud.cybercore.init.BlockInit;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;

public class CyberCoreCable extends TechBlockBaseSubCore {

	public static final IntegerProperty NORTH = IntegerProperty.create("north", 0, 3),
			EAST = IntegerProperty.create("east", 0, 3), SOUTH = IntegerProperty.create("south", 0, 3),
			WEST = IntegerProperty.create("west", 0, 3), UP = IntegerProperty.create("up", 0, 3),
			DOWN = IntegerProperty.create("down", 0, 3);
	public static final Map<Direction, IntegerProperty> directions = new HashMap<Direction, IntegerProperty>() {
		private static final long serialVersionUID = 1L;

		{
			put(Direction.NORTH, NORTH);
			put(Direction.EAST, EAST);
			put(Direction.SOUTH, SOUTH);
			put(Direction.WEST, WEST);
			put(Direction.UP, UP);
			put(Direction.DOWN, DOWN);
		}
	};

	private static final VoxelShape core_voxel = Block.makeCuboidShape(7F, 7F, 7F, 9F, 9F, 9F);
	private static final Map<Direction, VoxelShape> connection_voxels = new HashMap<Direction, VoxelShape>() {
		private static final long serialVersionUID = 1L;
		{
			put(Direction.DOWN, Block.makeCuboidShape(6F, 0F, 6F, 10F, 2F, 10F)); // DOWN
			put(Direction.UP, Block.makeCuboidShape(6F, 14F, 6F, 10F, 16F, 10F)); // UP
			put(Direction.NORTH, Block.makeCuboidShape(6F, 6F, 0F, 10F, 10F, 2F)); // NORTH
			put(Direction.SOUTH, Block.makeCuboidShape(6F, 6F, 14F, 10F, 10F, 16F)); // SOUTH
			put(Direction.WEST, Block.makeCuboidShape(0F, 6F, 6F, 2F, 10F, 10F)); // WEST
			put(Direction.EAST, Block.makeCuboidShape(14F, 6F, 6F, 16F, 10F, 10F));// EAST
		}
	};

	private static final Map<Direction, VoxelShape> cable_voxels = new HashMap<Direction, VoxelShape>() {
		private static final long serialVersionUID = 1L;
		{
			put(Direction.DOWN, Block.makeCuboidShape(7F, 0F, 7F, 9F, 7F, 9F)); // DOWN
			put(Direction.UP, Block.makeCuboidShape(7F, 9F, 7F, 9F, 16F, 9F)); // UP
			put(Direction.NORTH, Block.makeCuboidShape(7F, 7F, 0F, 9F, 9F, 7F)); // NORTH
			put(Direction.SOUTH, Block.makeCuboidShape(7F, 7F, 9F, 9F, 9F, 16F)); // SOUTH
			put(Direction.WEST, Block.makeCuboidShape(0F, 7F, 7F, 7F, 9F, 9F)); // WEST
			put(Direction.EAST, Block.makeCuboidShape(9F, 7F, 7F, 16F, 9F, 9F));// EAST
		}
	};

	public CyberCoreCable() {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5F), CyberCoreTab.instance, true);
		this.setDefaultState(stateContainer.getBaseState().with(NORTH, 0).with(EAST, 0).with(SOUTH, 0).with(WEST, 0)
				.with(UP, 0).with(DOWN, 0));
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult ray) {
		if (!worldIn.isRemote && hand.equals(Hand.MAIN_HAND)
				&& player.getHeldItemMainhand().getItem().equals(Items.REDSTONE)) {
			Vec3d hitvec = ray.getHitVec();
			hitvec = hitvec.add(-pos.getX(), -pos.getY(), -pos.getZ());
			VoxelShape tempshape;
			for (Direction dir : Direction.values()) {
				tempshape = connection_voxels.get(dir);
				if (tempshape.getStart(Axis.X) <= hitvec.x && tempshape.getEnd(Axis.X) >= hitvec.x) {
					if (tempshape.getStart(Axis.Y) <= hitvec.y && tempshape.getEnd(Axis.Y) >= hitvec.y) {
						if (tempshape.getStart(Axis.Z) <= hitvec.z && tempshape.getEnd(Axis.Z) >= hitvec.z) {
							CableTileEntity te = getTECable(worldIn, pos);
							if (te != null) {
								te.rotateConnection(dir);
								worldIn.setBlockState(pos, getCurrentState(state, worldIn, pos));
							}
						}
					}
				}
			}
		}
		return super.onBlockActivated(state, worldIn, pos, player, hand, ray);
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(BlockInit.power_cable);
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldstate, boolean bool) {
		CableTileEntity te = getTECable(world, pos);
		if (te != null) {
			te.initCable(state);
			world.setBlockState(pos, getCurrentState(state, world, pos));
		}

	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new CableTileEntity();
	}

	public Item createItemBlock() {
		return new BlockItem(this, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS))
				.setRegistryName("power_cable");
	}

	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null) {
			if (te instanceof CableTileEntity) {
				((CableTileEntity) te).deleteCable();
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}



	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@Nullable
	protected HashMap<Integer, RayTraceResult> rayTraceList(BlockPos pos, Vec3d start, Vec3d end,
			HashMap<Integer, AxisAlignedBB> boxes) {
		HashMap<Integer, RayTraceResult> list = new HashMap<Integer, RayTraceResult>();
		return list;
	}

	@Override
	public void onNeighborChange(BlockState state, IWorldReader world, BlockPos pos, BlockPos neighbor) {

		TileEntity te = world.getTileEntity(pos);
		if (te != null) {
			if (te instanceof CableTileEntity || te.getCapability(CapabilityEnergy.ENERGY).isPresent()) {
				((CableTileEntity) te).checkConnections();
			}
		}
		super.onNeighborChange(state, world, pos, neighbor);
	}

	private CableTileEntity getTECable(World world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te != null) {
			if (te instanceof CableTileEntity) {
				return (CableTileEntity) te;
			}
		}
		return null;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IBlockReader world = context.getWorld();
		BlockPos pos = context.getPos();
		BlockState state = getDefaultState();
		for (Direction facing : Direction.values()) {

			BlockState stateDirection = world.getBlockState(pos.offset(facing));
			TileEntity te = world.getTileEntity(pos.offset(facing));

			if (stateDirection.getBlock() instanceof CyberCoreCable) {
				state.with(directions.get(facing), 1);
			} else if (te != null) {
				if (te.getCapability(CapabilityEnergy.ENERGY).isPresent()) {

					state.with(directions.get(facing), 2);
				}
			} else {
				state.with(directions.get(facing), 0);
			}
		}

		return state;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world,
			BlockPos currentPos, BlockPos facingPos) {
		return getCurrentState(state, world, currentPos);
	}

	public BlockState getCurrentState(BlockState state, IWorld world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof CableTileEntity) {
			CableTileEntity tec = (CableTileEntity) te;
			((CableTileEntity) te).checkConnections();
			return state.with(UP, tec.getConnection(Direction.UP)).with(DOWN, tec.getConnection(Direction.DOWN))
					.with(EAST, tec.getConnection(Direction.EAST)).with(WEST, tec.getConnection(Direction.WEST))
					.with(NORTH, tec.getConnection(Direction.NORTH)).with(SOUTH, tec.getConnection(Direction.SOUTH));
		}
		return state;
	}

	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getCombinedShape(state);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos,
			ISelectionContext context) {
		return getCombinedShape(state);
	}

	private VoxelShape getCombinedShape(BlockState state) {
		VoxelShape shape = core_voxel;
		for (Direction dir : Direction.values()) {
			int value = state.get(directions.get(dir));
			if (value > 0) {
				shape = VoxelShapes.or(shape, cable_voxels.get(dir));
				if (value > 1) {
					shape = VoxelShapes.or(shape, connection_voxels.get(dir));
				}
			}
		}
		return shape;
	}
}