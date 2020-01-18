package xyz.skynetcloud.cybercore.block.tech.blocks;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.api.items.ItemNames;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;

public class CyberCoreItemPipe extends BlockBaseCore {

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

	public CyberCoreItemPipe(Properties property, String name, ItemGroup group, boolean hasItem) {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5F), name, group, true);
		this.setDefaultState(stateContainer.getBaseState().with(NORTH, 0).with(EAST, 0).with(SOUTH, 0).with(WEST, 0)
				.with(UP, 0).with(DOWN, 0));

	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult ray) {
		if (!worldIn.isRemote && hand.equals(Hand.MAIN_HAND)
				&& player.getHeldItemMainhand().getItem().equals(ItemNames.cyber_ingot)) {
			Vec3d hitvec = ray.getHitVec();
			hitvec = hitvec.add(-pos.getX(), -pos.getY(), -pos.getZ());
			VoxelShape tempshape;
			for (Direction dir : Direction.values()) {
				tempshape = connection_voxels.get(dir);
				if (tempshape.getStart(Axis.X) <= hitvec.x && tempshape.getEnd(Axis.X) >= hitvec.x) {
					if (tempshape.getStart(Axis.Y) <= hitvec.y && tempshape.getEnd(Axis.Y) >= hitvec.y) {
						if (tempshape.getStart(Axis.Z) <= hitvec.z && tempshape.getEnd(Axis.Z) >= hitvec.z) {

						}
					}
				}
			}

		}
		return super.func_225533_a_(state, worldIn, pos, player, hand, ray);
	}

}
