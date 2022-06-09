package ca.skynetcloud.cybercore.client.world.item.tools.farming;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Predicate;


public class TillerTool extends HoeItem {

     public TillerTool(Tier tier, Properties builder) {
         super(tier, -4, 0.0F, builder);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (context.getHorizontalDirection() == Direction.DOWN) {
            return InteractionResult.FAIL;
        }

        Level level = context.getLevel();
        BlockPos center = context.getClickedPos();

        Direction face = context.getHorizontalDirection();
        BlockPos blockpos = null;
        for (int dist = 0; dist < CyberConfig.CONFIG.getTillingRange(); dist++) {
            blockpos = center.relative(face, dist);
            if (level.isEmptyBlock(blockpos)) {
                // air here, went off an edge. try to go down 1
                blockpos = blockpos.below();
                if (level.isEmptyBlock(blockpos.above())) {
                    if (hoeBlock(context, blockpos)) {
                        center = center.below();// go down the hill
                    }
                }
            } else if (level.isEmptyBlock(blockpos.above())) {
                // at my elevation
                hoeBlock(context, blockpos);
            } else {
                // try going up by 1
                blockpos = blockpos.above();
                if (level.isEmptyBlock(blockpos.above())) {
                    if (hoeBlock(context, blockpos)) {
                        center = center.above();// go up the hill
                    }
                }
            }
        }


        return InteractionResult.SUCCESS;
    }

    private boolean hoeBlock(UseOnContext context, BlockPos blockpos) {
        Level world = context.getLevel();
        Block blockHere = world.getBlockState(blockpos).getBlock();
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = HoeItem.TILLABLES.get(blockHere);
        if (pair == null) {
            return false;
        }
        Predicate<UseOnContext> predicate = pair.getFirst();
        Consumer<UseOnContext> consumer = pair.getSecond();
        if (predicate.test(context)) {
            Player player = context.getPlayer();
            player.level.playSound(player, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            consumer.accept(context);
            this.moisturize(context.getLevel(), blockpos, context.getLevel().getBlockState(blockpos));
            Player playerentity = context.getPlayer();
            world.playSound(playerentity, blockpos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (playerentity != null) {
                context.getItemInHand().hurtAndBreak(1, playerentity, (p) -> {
                    p.broadcastBreakEvent(context.getHand());
                });
            }
            return true;
        }
        return false;
    }

    private void moisturize(Level world, BlockPos pos, BlockState blockstate) {
        try {
            if (15 > 0) {
                //        blockstate = blockstate.setValue(FarmBlock.MOISTURE, GardenMod.CONFIG.getMoisture());
                world.setBlock(pos, Blocks.FARMLAND.defaultBlockState().setValue(FarmBlock.MOISTURE, CyberConfig.CONFIG.getMoisture()), 3);
            }
        }
        catch (Exception e) {
            CyberCore.LOGGER.error("Tiller Tool Moisturize error", e);
        }
    }


}
