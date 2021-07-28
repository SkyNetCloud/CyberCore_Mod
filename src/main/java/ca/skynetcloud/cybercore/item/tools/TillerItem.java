package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.util.networking.config.CyberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

public class TillerItem extends HoeItem {

	public TillerItem(Tier tier, float attackSpeedIn, int attackDamage, Properties builder) {
		super(tier, attackDamage, attackSpeedIn, builder);

	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		// ActionResultType succ = super.onItemUse(context);
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.FAIL;
		}
		// so we got a success from the initial block
		Level world = context.getLevel();
		BlockPos center = context.getClickedPos();
		// context.getPlayer().getHorizontalFacing()
		Direction face = context.getHorizontalDirection();
		BlockPos blockpos = null;
		for (int dist = 0; dist < CyberConfig.Config.getTillingRange(); dist++) {
			blockpos = center.relative(face, dist);
			if (world.isEmptyBlock(blockpos)) {
				// air here, went off an edge. try to go down 1
				blockpos = blockpos.below();
				if (world.isEmptyBlock(blockpos.above())) {
					if (hoeBlock(context, blockpos)) {
						center = center.below();// go down the hill
					}
				}
			} else if (world.isEmptyBlock(blockpos.above())) {
				// at my elevation
				hoeBlock(context, blockpos);
			} else {
				// try going up by 1
				blockpos = blockpos.above();
				if (world.isEmptyBlock(blockpos.above())) {
					if (hoeBlock(context, blockpos)) {
						center = center.above();// go up the hill
					}
				}
			}
		}
		return InteractionResult.SUCCESS;
	}


	private boolean hoeBlock(BlockPlaceContext context, BlockPos blockpos) {
		Level world = context.getLevel();
		Block blockHere = world.getBlockState(blockpos).getBlock();
		BlockState blockstate = TILLABLES.get(blockHere);
		if (blockstate != null) {
			blockstate = this.moisturize(blockstate);
			if (world.setBlock(blockpos, blockstate, 11)) {
				Player playerentity = context.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (playerentity != null) {
					context.getItemInHand().hurtAndBreak(1, playerentity, (p) -> {
						p.broadcastBreakEvent(context.getHand());
					});
				}
				return true;
			}
		}
		return false;
	}

	private BlockState moisturize(BlockState blockstate) {
		try {
			if (blockstate.getBlock() == Blocks.FARMLAND && CyberConfig.Config.getMoisture() > 0) {
				blockstate = blockstate.setValue(FarmBlock.MOISTURE, CyberConfig.Config.getMoisture());
			}
		} catch (Exception e) {
			// CyberCoreMain.LOGGER.error("Tiller Item Moisturize error", e);
		}
		return blockstate;
	}

}
