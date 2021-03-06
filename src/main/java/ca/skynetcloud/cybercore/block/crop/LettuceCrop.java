package ca.skynetcloud.cybercore.block.crop;

import ca.skynetcloud.cybercore.init.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LettuceCrop extends CropsBlock {

	public static final IntegerProperty AGE_3 = IntegerProperty.create("age", 0, 3);

	public LettuceCrop() {
		super(Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.CROP));
		this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));

	}

	@Override
	protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return state.getBlock() instanceof FarmlandBlock;
	}

	@Override
	public IntegerProperty getAgeProperty() {
		return AGE_3;
	}

	@Override
	public int getMaxAge() {
		return 3;
	}

	@OnlyIn(Dist.CLIENT)
	protected IItemProvider getSeedsItem() {
		return ItemInit.lettuce_seed;
	}

	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.getSeedsItem());
	}

	public boolean onBlockActivateds(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
			BlockRayTraceResult hit) {
		if (!world.isClientSide) {
			if (this.isMaxAge(state)) {
				world.addFreshEntity(
						new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.lettuce, 1)));
				world.setBlock(pos, this.getStateForAge(0), 0);
				return true;
			}
		}
		return false;
	}

	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE_3);
	}
}
