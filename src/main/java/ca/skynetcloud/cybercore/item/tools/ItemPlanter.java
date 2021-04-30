package ca.skynetcloud.cybercore.item.tools;

import java.util.List;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemPlanter extends Item {

	private static final String FORGE_SEEDS = "forge:seeds";

	private static final String OTHER_SEEDS = "cybercore:seeds";

	public ItemPlanter(Properties properties) {
		super(properties);
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		super.useOn(context);
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();
		ItemStack seeds = ItemStack.EMPTY;
		Direction face = context.getHorizontalDirection();
		BlockPos center = context.getClickedPos().above();
		BlockPos blockpos = null;
		int countPlanted = 0;
		for (int dist = 0; dist < CyberCoreConfig.getPlantingRange(); dist++) {
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
		return ActionResultType.SUCCESS;
	}

	private boolean tryPlantHere(World world, ItemStack seeds, BlockPos blockpos) {
		boolean didPlant = false;
		if (world.getBlockState(blockpos.below()).getBlock() == Blocks.FARMLAND && world.isEmptyBlock(blockpos)) {
			// looks valid. try to plant
			if (world.setBlock(blockpos, Block.byItem(seeds.getItem()).defaultBlockState(), 0)) {
				didPlant = true;
			}
		}
		return didPlant;
	}

	private ItemStack getSeed(PlayerEntity player) {
		ItemStack seeds = ItemStack.EMPTY;
		for (ItemStack s : player.inventory.items) {
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
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		TranslationTextComponent t = new TranslationTextComponent(getDescriptionId() + ".tooltip");

		tooltip.add(t);
	}
}
