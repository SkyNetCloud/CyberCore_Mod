package ca.skynetcloud.cybercore.block.blocks;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;

public class FencePowerGrid extends TechBlockBaseSubCore {

	public static final BooleanProperty SUPPLYING = BooleanProperty.create("supplying");

	public FencePowerGrid(Supplier<? extends TileEntity> teCreator) {
		super(teCreator);

		registerDefaultState(defaultBlockState().setValue(SUPPLYING, false));

	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return defaultBlockState().setValue(SUPPLYING, false);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(SUPPLYING);
	}

}
