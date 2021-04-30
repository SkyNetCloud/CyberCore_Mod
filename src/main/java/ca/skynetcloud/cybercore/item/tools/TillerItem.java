package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TillerItem extends HoeItem {

	public TillerItem(IItemTier tier, float attackSpeedIn, int attackDamage, Properties builder) {
		super(tier, attackDamage, attackSpeedIn, builder);

	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		// ActionResultType succ = super.onItemUse(context);
		if (context.getClickedFace() == Direction.DOWN) {
			return ActionResultType.FAIL;
		}
		// so we got a success from the initial block
		World world = context.getLevel();
		BlockPos center = context.getClickedPos();
		// context.getPlayer().getHorizontalFacing()
		Direction face = context.getHorizontalDirection();
		BlockPos blockpos = null;
		for (int dist = 0; dist < CyberCoreConfig.getTillingRange(); dist++) {
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
		return ActionResultType.SUCCESS;
	}

	private boolean hoeBlock(ItemUseContext context, BlockPos blockpos) {
		World world = context.getLevel();
		Block blockHere = world.getBlockState(blockpos).getBlock();
		BlockState blockstate = TILLABLES.get(blockHere);
		if (blockstate != null) {
			blockstate = this.moisturize(blockstate);
			if (world.setBlock(blockpos, blockstate, 11)) {
				PlayerEntity playerentity = context.getPlayer();
				world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
			if (blockstate.getBlock() == Blocks.FARMLAND && CyberCoreConfig.getMoisture() > 0) {
				blockstate = blockstate.setValue(FarmlandBlock.MOISTURE, CyberCoreConfig.getMoisture());
			}
		} catch (Exception e) {
			//CyberCoreMain.LOGGER.error("Tiller Item Moisturize error", e);
		}
		return blockstate;
	}

}
