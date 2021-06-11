package ca.skynetcloud.cybercore.block.blocks.fences;

import java.util.function.Supplier;

import ca.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;

public class ElecticFenceGrid extends TechBlockBaseSubCore {

	public static final BooleanProperty SUPPLYING = BooleanProperty.create("supplying");

	public ElecticFenceGrid(Supplier<? extends TileEntity> teCreator) {
		super(teCreator);
		this.registerDefaultState(stateDefinition.any().setValue(SUPPLYING, false));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(SUPPLYING);
	}

}
