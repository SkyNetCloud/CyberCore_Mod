package ca.skynetcloud.cybercore.block.tech;

import java.util.function.Supplier;

import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TechBlockBaseSubCore extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	private final Supplier<? extends TileEntity> teCreator;

	public TechBlockBaseSubCore(Supplier<? extends TileEntity> teCreator) {
		super(Block.Properties.of(Material.METAL).strength(5.0f, 10.0f).noOcclusion());
		this.teCreator = teCreator;
	}

	public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos, int fortune) {
		return this;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return teCreator.get();
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (this == BlockInit.C_Changer_Block)
			return Block.box(0, 0, 0, 16, 9, 16);
		else
			return super.getShape(state, worldIn, pos, context);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		return new ItemStack(this);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		if (this == BlockInit.POWER_FURNACE_BLOCK) {
			builder.add(LIT);
		}
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult ray) {
		if (!world.isClientSide) {
			TileEntity te = world.getBlockEntity(pos);
			if (te instanceof CoreEnergyInventoryTileEntity) {
				player.openMenu((CoreEnergyInventoryTileEntity) te);
			}

		}

		return ActionResultType.SUCCESS;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

}
