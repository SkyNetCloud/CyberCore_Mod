package ca.skynetcloud.cybercore.block.blocks;

import ca.skynetcloud.cybercore.util.networking.config.CyberConfig.Config;
import ca.skynetcloud.cybercore.util.networking.helper.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class ExtractorBlock extends Block {

	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	// public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	protected final VoxelShape[] shapes;

	public ExtractorBlock(Properties properties) {
		super(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f));
		this.registerDefaultState(
				stateDefinition.any().setValue(FACING, Direction.UP).setValue(BlockStateProperties.POWERED, false));
		this.shapes = this.makeShapes();

	}

	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		if (!worldIn.isClientSide) {
			boolean isReceivingPower = worldIn.hasNeighborSignal(pos);
			boolean isStatePowered = state.getValue(BlockStateProperties.POWERED);
			if (isReceivingPower != isStatePowered) {
				if (isReceivingPower) {
					this.ejectItems(state, pos, worldIn);
					worldIn.playSound(null, pos, SoundEvents.PISTON_CONTRACT, SoundCategory.BLOCKS, 0.3F,
							worldIn.random.nextFloat() * 0.1F + 0.8F);
				} else {
					worldIn.playSound(null, pos, SoundEvents.PISTON_CONTRACT, SoundCategory.BLOCKS, 0.1F,
							worldIn.random.nextFloat() * 0.1F + 0.9F);
				}
				worldIn.setBlock(pos, state.setValue(BlockStateProperties.POWERED, Boolean.valueOf(isReceivingPower)),
						2);
			}

		}
	}

	private void ejectItems(BlockState state, BlockPos pos, World world) {
		Direction output_dir = state.getValue(FACING);
		BlockPos output_pos = pos.relative(output_dir);
		Direction input_dir = output_dir.getOpposite();
		BlockPos input_pos = pos.relative(input_dir);

		LazyOptional<IItemHandler> inputCap = WorldHelper.getTEItemHandlerAt(world, input_pos, output_dir);
		if (inputCap.isPresent()) {
			LazyOptional<IItemHandler> outputCap = WorldHelper.getTEItemHandlerAt(world, output_pos, input_dir);

			if (outputCap.isPresent() || !world.getBlockState(output_pos).isSolidRender(world, output_pos)) {
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

	private ItemStack extractNextStack(IItemHandler handler) {

		int slots = handler.getSlots();

		for (int i = 0; i < slots; i++) {

			ItemStack stack = handler.extractItem(i, Config.ITEM_OUT_SIZE.get(), false);

			if (stack.getCount() > 0) {
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
		Direction direction = context.getClickedFace().getOpposite();
		return this.defaultBlockState().setValue(FACING,
				direction.getAxis() == Direction.Axis.Y ? Direction.DOWN : direction);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, BlockStateProperties.POWERED);
	}

	// model shapes

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
		return state.getValue(FACING).get3DDataValue();
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

			VoxelShape input = Block.box(input_x_min, input_y_min, input_z_min, input_x_max, input_y_max, input_z_max);
			VoxelShape mid = Block.box(mid_x_min, mid_y_min, mid_z_min, mid_x_max, mid_y_max, mid_z_max);
			VoxelShape output = Block.box(output_x_min, output_y_min, output_z_min, output_x_max, output_y_max,
					output_z_max);

			shapes[face] = VoxelShapes.or(input, mid, output);
		}

		return shapes;
	}

}
