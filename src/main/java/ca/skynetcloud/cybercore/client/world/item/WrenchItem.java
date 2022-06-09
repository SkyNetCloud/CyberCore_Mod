package ca.skynetcloud.cybercore.client.world.item;


import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import ca.skynetcloud.cybercore.client.world.level.block.tech.cable.ItemCable;
import ca.skynetcloud.cybercore.client.world.level.block.tech.cable.PowerCable;
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

import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.MAIN_TAB;

public class WrenchItem extends Item {

	public WrenchItem()
	{
		super(new Properties().stacksTo(1).tab(MAIN_TAB));
	}

	@Override
	public InteractionResult useOn(UseOnContext ctx)
	{
		Level level = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();
		Player player = ctx.getPlayer();
		if (!level.isClientSide)
		{
			BlockState target = level.getBlockState(pos);
			ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);

			if (stack.getItem() instanceof WrenchItem && player.isCrouching())
			{
				Block block = target.getBlock();
				if (removeIfValid(block, level, pos))
				{
					Block.popResource(level, pos, new ItemStack(target.getBlock()));
					return InteractionResult.SUCCESS;
				}
			}
		}
		return super.useOn(ctx);
	}

	private boolean removeIfValid(Block block, Level world, BlockPos pos)
	{
		if ( block instanceof PowerCable || block instanceof ItemCable)
		{
			world.removeBlock(pos, false);
			return true;
		}
		return false;
	}

	 /*@Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flagIn)
	{

		tooltip.add(new TextComponent(new TranslatableComponent("info.wrench_cable").getString()));
		tooltip.add(new TextComponent(""));
		tooltip.add(new TextComponent(new TranslatableComponent("info.wrench_dismantle").getString()));

	}
		*/





}