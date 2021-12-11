package ca.skynetcloud.cybercore.common.item.tools;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.client.init.CoreInit;
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
import net.minecraftforge.common.ToolType;

import java.util.List;

public class CyberPickaxe extends PickaxeItem {

	public CyberPickaxe(Tier material, float speed) {
		super(material, 1, speed,
				new Properties().tab(CyberCoreTab.instance).addToolType(ToolType.PICKAXE, material.getLevel()));
	}

	public CyberPickaxe(Tier material, float speed, Properties properties) {
		super(material, 1, speed, properties.addToolType(ToolType.PICKAXE, material.getLevel()));
	}

	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

		if (stack.sameItem(new ItemStack(CoreInit.ItemInit.cyber_pickaxe))) {
			tooltip.add(new TextComponent("Hmm someone is watching you"));

			tooltip.add(
					new TextComponent(ChatFormatting.GREEN + "NIGHT VISION" + " " + ChatFormatting.AQUA + "24 Sec"));

			tooltip.add(new TextComponent(ChatFormatting.GREEN + "HASTE" + " " + ChatFormatting.AQUA + "12 Sec"));

		}

		if (stack.sameItem(new ItemStack(CoreInit.ItemInit.ruby_pickaxe))) {
			tooltip.add(new TextComponent(ChatFormatting.LIGHT_PURPLE + "Seem To Be Good"));

			tooltip.add(
					new TextComponent(ChatFormatting.GREEN + "NIGHT VISION" + " " + ChatFormatting.AQUA + "24 Sec"));

			tooltip.add(new TextComponent(
					ChatFormatting.GREEN + "HERO OF THE VILLAGE" + " " + ChatFormatting.AQUA + "12 Sec"));

		}

		if (stack.sameItem(new ItemStack(CoreInit.ItemInit.dark_steel_pickaxe))) {
			tooltip.add(
					new TextComponent(ChatFormatting.DARK_PURPLE + "A shadow loom over you while you hold this tool"));

			tooltip.add(new TextComponent(ChatFormatting.RED + "BLINDNESS" + " " + ChatFormatting.AQUA + "12 Sec"));

			tooltip.add(
					new TextComponent(ChatFormatting.GREEN + "HEALTH BOOST" + " " + ChatFormatting.AQUA + "12 Sec"));

			tooltip.add(new TextComponent(ChatFormatting.RED + "SLOWNESS" + " " + ChatFormatting.AQUA + "12 Sec"));
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

		playerIn.getCooldowns().addCooldown(this, 600);
		if (playerIn.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == CoreInit.ItemInit.cyber_pickaxe.asItem()) {
			if (worldIn.isClientSide) {
				playerIn.sendMessage(new TextComponent(ChatFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
						+ ChatFormatting.RED + "Hmm What Did It added to you"), null);
			}

			playerIn.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 500, 1, false, false, true));
			playerIn.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 500, 1, false, false, true));
		}

		return super.use(worldIn, playerIn, handIn);
	}

}
