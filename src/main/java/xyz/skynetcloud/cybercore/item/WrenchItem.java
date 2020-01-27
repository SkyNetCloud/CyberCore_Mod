package xyz.skynetcloud.cybercore.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.blocks.CustomWorldLink;
import xyz.skynetcloud.cybercore.block.blocks.CyberExtractorBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberLoaderBlock;
import xyz.skynetcloud.cybercore.block.tech.TechBlockFacing;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreItemPipe;

public class WrenchItem extends ItemBaseCore {

	public WrenchItem() {
		super(new Item.Properties().maxStackSize(1).group(CyberCoreTab.instance));
		this.setRegistryName("wrench");

	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		PlayerEntity player = context.getPlayer();
		if (!world.isRemote) {
			BlockState target = world.getBlockState(pos);
			ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
			if (!stack.isEmpty() && target.getBlock() instanceof TechBlockFacing) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.spawnAsEntity(world, pos, new ItemStack(target.getBlock()));
					return ActionResultType.SUCCESS;
				}
			} else if (!stack.isEmpty() && target.getBlock() instanceof CyberCoreCable) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.spawnAsEntity(world, pos, new ItemStack(target.getBlock()));
					return ActionResultType.SUCCESS;
				}
			} else if (!stack.isEmpty() && target.getBlock() instanceof CyberCoreItemPipe) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.spawnAsEntity(world, pos, new ItemStack(target.getBlock()));
					return ActionResultType.SUCCESS;
				}
			} else if (!stack.isEmpty() && target.getBlock() instanceof CyberExtractorBlock) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.spawnAsEntity(world, pos, new ItemStack(target.getBlock()));
					return ActionResultType.SUCCESS;
				} else if (!stack.isEmpty() && target.getBlock() instanceof CyberLoaderBlock) {
					if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
						world.removeBlock(pos, false);
						Block.spawnAsEntity(world, pos, new ItemStack(target.getBlock()));
						return ActionResultType.SUCCESS;
					}
				} else if (!stack.isEmpty() && target.getBlock() instanceof CustomWorldLink) {
					if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
						world.removeBlock(pos, false);
						Block.spawnAsEntity(world, pos, new ItemStack(target.getBlock()));
						return ActionResultType.SUCCESS;
					}
				}
			}
		}

		return super.onItemUse(context);
	}
}
