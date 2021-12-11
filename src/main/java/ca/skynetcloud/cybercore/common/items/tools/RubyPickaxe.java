package ca.skynetcloud.cybercore.common.items.tools;


import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.ItemInit;
import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class RubyPickaxe extends PickaxeItem {

	public RubyPickaxe(Tier material, float speed) {
		super(material, 1, speed,
				new Properties().tab(CyberCoreTab.MAIN));
	}

	public RubyPickaxe(Tier material, float speed, Properties properties) {
		super(material, 1, speed, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

		if (stack.sameItem(new ItemStack(ItemInit.RUBY_PICKAXE.get()))) {
			tooltip.add(new TextComponent(ChatFormatting.LIGHT_PURPLE + "Seem To Be Good"));

			tooltip.add(new TextComponent(
					ChatFormatting.GREEN + "NIGHT VISION" + " " + ChatFormatting.AQUA + "24 Sec"));

			tooltip.add(new TextComponent(
					ChatFormatting.GREEN + "HERO OF THE VILLAGE" + " " + ChatFormatting.AQUA + "12 Sec"));

		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

		playerIn.getCooldowns().addCooldown(this, 600);
		if (playerIn.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == ItemInit.RUBY_PICKAXE.get().asItem()) {
			if (worldIn.isClientSide) {
				playerIn.sendMessage(new TextComponent(ChatFormatting.GREEN + "[" + CyberCore.MODID + "] "
						+ ChatFormatting.RED + "This Tool Seem To Like You"), null);
			}
			playerIn.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 500, 1, false, false, true));
			playerIn.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 255, 1, false, false, true));
		}

		return super.use(worldIn, playerIn, handIn);
	}

}
