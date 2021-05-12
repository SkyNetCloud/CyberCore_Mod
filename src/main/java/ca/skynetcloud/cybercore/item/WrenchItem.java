package ca.skynetcloud.cybercore.item;

import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.block.tech.TechBlockFacing;
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

public class WrenchItem extends ItemBaseCore {

	public WrenchItem() {
		super(new Item.Properties().stacksTo(1).tab(CyberCoreTab.instance));
		this.setRegistryName("wrench");

	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		PlayerEntity player = context.getPlayer();
		if (!world.isClientSide) {
			BlockState target = world.getBlockState(pos);
			ItemStack stack = player.getItemInHand(Hand.MAIN_HAND);
			if (!stack.isEmpty() && target.getBlock() instanceof TechBlockFacing) {
				if (stack.getItem() instanceof WrenchItem && player.isCrouching()) {
					world.removeBlock(pos, false);
					Block.popResource(world, pos, new ItemStack(target.getBlock()));
					return ActionResultType.SUCCESS;
				}
			}
		}

		return super.useOn(context);
	}
}
