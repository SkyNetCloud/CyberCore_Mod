package ca.skynetcloud.cybercore.client.world.item.tools;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.MainInit;
import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.MAIN_TAB;


public class DarkSteelPickaxe extends PickaxeItem {

	public int coolDown = 0;
	public boolean canUse;

	public DarkSteelPickaxe(Tier material, float speed) {
		super(material, 1, speed,
				new Properties().tab(MAIN_TAB));
	}

	public DarkSteelPickaxe(Tier material, float speed, Properties properties) {
		super(material, 1, speed, properties);
	}

	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {

		if (stack.sameItem(new ItemStack(MainInit.DARK_STEEL_PICKAXE.get()))) {
			tooltip.add(
					Component.literal(ChatFormatting.DARK_PURPLE + "A shadow loom over you while you hold this tool"));

			tooltip.add(Component.literal(ChatFormatting.RED + "BLINDNESS" + " " + ChatFormatting.AQUA + "12 Sec"));

			tooltip.add(
					Component.literal(ChatFormatting.GREEN + "HEALTH BOOST" + " " + ChatFormatting.AQUA + "12 Sec"));

			tooltip.add(Component.literal(ChatFormatting.RED + "SLOWNESS" + " " + ChatFormatting.AQUA + "12 Sec"));
		}

		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {

		playerIn.getCooldowns().addCooldown(this, 600);
		if (playerIn.getItemBySlot(EquipmentSlot.MAINHAND).getItem() == MainInit.DARK_STEEL_PICKAXE.get().asItem()) {
			if (worldIn.isClientSide) {
				playerIn.sendSystemMessage(Component.literal(ChatFormatting.GREEN + "[" + CyberCore.MODID + "] "
						+ ChatFormatting.RED + "The Shadow Are Coming For You"));
			}
			playerIn.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 255, 1, false, false, true));
			playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 255, 1, false, false, true));
			playerIn.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 255, 1, false, false, true));
		}

		return super.use(worldIn, playerIn, handIn);
	}

}
