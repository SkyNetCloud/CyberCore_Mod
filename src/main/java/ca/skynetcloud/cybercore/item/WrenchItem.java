package ca.skynetcloud.cybercore.item;

import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.block.blocks.fences.BasicElecticFence;
import ca.skynetcloud.cybercore.block.blocks.fences.gate.ElecticFenceGate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WrenchItem extends ItemBaseCore {

	public WrenchItem() {
		super(new Item.Properties().stacksTo(1).tab(CyberCoreTab.instance));
		this.setRegistryName("wrench");

	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Player player = context.getPlayer();
		if (!world.isClientSide) {
			BlockState target = world.getBlockState(pos);
			ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);
			/*
			if (!stack.isEmpty() && target.getBlock() instanceof TechBlockFacing) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.popResource(world, pos, new ItemStack(target.getBlock()));
					return InteractionResult.SUCCESS;
				}
			}*/
			if (!stack.isEmpty() && target.getBlock() instanceof ElecticFenceGate) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.popResource(world, pos, new ItemStack(target.getBlock()));
					return InteractionResult.SUCCESS;
				}
			}

			if (!stack.isEmpty() && target.getBlock() instanceof BasicElecticFence) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.popResource(world, pos, new ItemStack(target.getBlock()));
					return InteractionResult.SUCCESS;
				}
			}

		}

		return super.useOn(context);
	}
}
