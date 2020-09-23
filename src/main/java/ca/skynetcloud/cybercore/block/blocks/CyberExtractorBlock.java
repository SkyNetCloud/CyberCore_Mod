package ca.skynetcloud.cybercore.block.blocks;

import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.util.networking.helper.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class CyberExtractorBlock extends BlockBaseCore {

	// output is current facing, input is face.getOpposite()
	public static final DirectionProperty FACING = DirectionalBlock.FACING;

	public static World worldIn;

	protected final VoxelShape[] shapes;

	public CyberExtractorBlock(Properties properties) {
		super(properties, "block_extractor", CyberCoreTab.instance, true);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.UP));
		this.shapes = this.makeShapes();
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		if (!worldIn.isRemote) {
			this.transferItem(state, pos, worldIn);
		}
		worldIn.setBlockState(pos, state, 2);
	}

	private void transferItem(BlockState state, BlockPos pos, World world) {
		Direction output_dir = state.get(FACING);
		BlockPos output_pos = pos.offset(output_dir);
		Direction input_dir = output_dir.getOpposite();
		BlockPos input_pos = pos.offset(input_dir);

		LazyOptional<IItemHandler> inputCap = WorldHelper.getTEItemHandlerAt(world, input_pos, output_dir);
		if (inputCap.isPresent()) {
			LazyOptional<IItemHandler> outputCap = WorldHelper.getTEItemHandlerAt(world, output_pos, input_dir);

			if (outputCap.isPresent() || !world.getBlockState(output_pos).isOpaqueCube(world, output_pos)) {
				ItemStack stack = inputCap.map(inputHandler -> this.extractNextStack(inputHandler))
						.orElse(ItemStack.EMPTY);
				if (stack.getCount() > 0) {
					ItemStack remaining = outputCap.map(outputHandler -> this.putStackInHandler(stack, outputHandler))
							.orElse(stack.copy());
					WorldHelper.ejectItemstack(world, pos, output_dir, remaining);
				}
			}
		}
	}

	private ItemStack extractNextStack(IItemHandler handler)
	{
		int slots = handler.getSlots();
		for (int i=0; i<slots; i++)
		{
			ItemStack stack = handler.extractItem(i, 64, false);
			if (stack.getCount() > 0)
			{
				return stack.copy();
			}
		}
		return ItemStack.EMPTY;
	}

	private ItemStack putStackInHandler(ItemStack stack, IItemHandler handler) {
		ItemStack remaining = stack.copy();
		int slots = handler.getSlots();
		for (int i = 0; i < slots; i++) {
			remaining = handler.insertItem(i, remaining, false);
			if (remaining.getCount() <= 0) {
				return ItemStack.EMPTY;
			}
		}
		return remaining;
	}

	//// facing and blockstate boilerplate

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction direction = context.getFace().getOpposite();
		return this.getDefaultState().with(FACING,
				direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	// model shapes

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
		return state.get(FACING).getIndex();
	}

	protected VoxelShape[] makeShapes() {
		VoxelShape[] shapes = new VoxelShape[6];

		for (int face = 0; face < 6; face++) // dunswe
		{
			boolean DOWN = face == 0;
			boolean UP = face == 1;
			boolean NORTH = face == 2;
			boolean SOUTH = face == 3;
			boolean WEST = face == 4;
			boolean EAST = face == 5;

			// north==0, south==16

			double input_x_min = WEST ? 10D : 0D;
			double input_x_max = EAST ? 6D : 16D;
			double input_y_min = DOWN ? 10D : 0D;
			double input_y_max = UP ? 6D : 16D;
			double input_z_min = NORTH ? 10D : 0D;
			double input_z_max = SOUTH ? 6D : 16D;

			double mid_x_min = EAST ? 6D : 4D;
			double mid_x_max = WEST ? 10D : 12D;
			double mid_y_min = UP ? 6D : 4D;
			double mid_y_max = DOWN ? 10D : 12D;
			double mid_z_min = SOUTH ? 6D : 4D;
			double mid_z_max = NORTH ? 10D : 12D;

			double output_x_min = WEST ? 0D : EAST ? 12D : 6D;
			double output_x_max = WEST ? 4D : EAST ? 16D : 10D;
			double output_y_min = DOWN ? 0D : UP ? 12D : 6D;
			double output_y_max = DOWN ? 4D : UP ? 16D : 10D;
			double output_z_min = SOUTH ? 12D : NORTH ? 0D : 6D;
			double output_z_max = SOUTH ? 16D : NORTH ? 4D : 10D;

			VoxelShape input = Block.makeCuboidShape(input_x_min, input_y_min, input_z_min, input_x_max, input_y_max,
					input_z_max);
			VoxelShape mid = Block.makeCuboidShape(mid_x_min, mid_y_min, mid_z_min, mid_x_max, mid_y_max, mid_z_max);
			VoxelShape output = Block.makeCuboidShape(output_x_min, output_y_min, output_z_min, output_x_max,
					output_y_max, output_z_max);

			shapes[face] = VoxelShapes.or(input, mid, output);
		}

		return shapes;
	}
}
