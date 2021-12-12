package ca.skynetcloud.cybercore.common.items.tools.farming;

import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class PlaterTool extends Item {

	private static final String FORGE_SEEDS = "forge:seeds";

	private static final String OTHER_SEEDS = "cybercore:seeds";

	public PlaterTool(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		Level world = context.getLevel();
		Player player = context.getPlayer();
		ItemStack seeds = ItemStack.EMPTY;
		Direction face = context.getHorizontalDirection();
		BlockPos center = context.getClickedPos().above();
		BlockPos blockpos = null;
		int countPlanted = 0;
		for (int dist = 0; dist < CyberConfig.Config.getPlantingRange(); dist++) {
			// get seed ready if any are left
			if (seeds.isEmpty()) {
				seeds = getSeed(player);
				if (seeds.isEmpty()) {
					break;// for sure done
				}
			}
			// advance position
			blockpos = center.relative(face, dist);
			// manage going above/below by 1 elevation
			boolean didPlant = false;
			if (world.isEmptyBlock(blockpos.below())) {
				// air here, went off an edge. try to go below 1
				blockpos = blockpos.below();
				if (world.isEmptyBlock(blockpos)) {
					if (tryPlantHere(world, seeds, blockpos)) {
						center = center.below();// go below the hill
						didPlant = true;
					}
				}
			} else if (world.isEmptyBlock(blockpos)) {
				// at my elevation
				if (tryPlantHere(world, seeds, blockpos)) {
					didPlant = true;
				}
			} else {
				// try going above by 1
				blockpos = blockpos.above();
				if (world.isEmptyBlock(blockpos.above())) {
					if (tryPlantHere(world, seeds, blockpos)) {
						center = center.above();// go above the hill
						didPlant = true;
					}
				}
			}
			if (didPlant) {
				countPlanted++;
				seeds.shrink(1);
			}
		}
		// loop is complete
		if (player != null && countPlanted > 0) {
			context.getItemInHand().hurtAndBreak(countPlanted, player, (p) -> {
				p.broadcastBreakEvent(context.getHand());
			});
		}
		return InteractionResult.SUCCESS;
	}

	private boolean tryPlantHere(Level world, ItemStack seeds, BlockPos blockpos) {
		boolean didPlant = false;
		if (world.getBlockState(blockpos.below()).getBlock() == Blocks.FARMLAND && world.isEmptyBlock(blockpos)) {
			// looks valid. try to plant
			if (world.setBlock(blockpos, Block.byItem(seeds.getItem()).defaultBlockState(), 0)) {
				didPlant = true;
			}
		}
		return didPlant;
	}

	private ItemStack getSeed(Player player) {
		ItemStack seeds = ItemStack.EMPTY;
		for (ItemStack s : player.inventoryMenu.getItems()) {
			if (!s.isEmpty()) {
				Item item = s.getItem();
				for (ResourceLocation st : item.getTags()) {
					if (st.toString().equalsIgnoreCase(FORGE_SEEDS) || st.toString().equalsIgnoreCase(OTHER_SEEDS)) {
						seeds = s;
						break;
					}
				}
			}
		}
		return seeds;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		TranslatableComponent t = new TranslatableComponent(getDescriptionId() + ".tooltip");

		tooltip.add(t);
	}
}
