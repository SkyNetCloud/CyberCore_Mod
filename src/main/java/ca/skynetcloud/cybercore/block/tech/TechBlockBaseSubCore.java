package ca.skynetcloud.cybercore.block.tech;

import java.util.function.Supplier;

import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.state.StateContainer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TechBlockBaseSubCore extends Block {

	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public final Supplier<? extends BlockEntity> teCreator;

	public TechBlockBaseSubCore(Supplier<? extends BlockEntity> teCreator) {
		super(Block.Properties.of(Material.METAL).strength(5.0f, 10.0f).noOcclusion());
		this.teCreator = teCreator;
	}

	public ItemLike getItemDropped(BlockState state, Level worldIn, BlockPos pos, int fortune) {
		return this;
	}


	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		if (this == BlockInit.C_Changer_Block)
			return Block.box(0, 0, 0, 16, 9, 16);
		else
			return super.getShape(state, worldIn, pos, context);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos,
			Player player) {
		return new ItemStack(this);
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		if (this == BlockInit.POWER_FURNACE_BLOCK) {
			builder.add(LIT);
		}
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult ray) {
		if (!world.isClientSide) {
			BlockEntity te = world.getBlockEntity(pos);
			if (te instanceof CoreEnergyInventoryTileEntity) {
				player.openMenu((CoreEnergyInventoryTileEntity) te);
			}

		}

		return InteractionResult.SUCCESS;
	}


}
