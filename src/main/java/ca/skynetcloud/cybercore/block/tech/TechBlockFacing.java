package ca.skynetcloud.cybercore.block.tech;

import java.util.function.Supplier;

import com.sun.jdi.Mirror;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.data.client.model.VariantSettings.Rotation;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.tileentity.TileEntity;

public class TechBlockFacing extends TechBlockBaseSubCore {

	public static final DirectionProperty FACING = DirectionalBlock.FACING;

	public TechBlockFacing(Supplier<? extends TileEntity> teCreator) {
		super(teCreator);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate((Direction) state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation((Direction) state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

}
