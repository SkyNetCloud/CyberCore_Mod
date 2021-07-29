package ca.skynetcloud.cybercore.block.crop;

import ca.skynetcloud.cybercore.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LettuceCrop extends CropBlock {

	public static final IntegerProperty AGE_3 = IntegerProperty.create("age", 0, 3);

	public LettuceCrop() {
		super(Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.CROP));
		this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), Integer.valueOf(0)));

	}

	@Override
	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.getBlock() instanceof FarmBlock;
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
	protected ItemLike getSeedsItem() {
		return ItemInit.lettuce_seed;
	}

	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem(BlockGetter worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.getSeedsItem());
	}

	public boolean onBlockActivateds(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult hit) {
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

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE_3);
	}
}
