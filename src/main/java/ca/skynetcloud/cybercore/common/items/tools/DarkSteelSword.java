package ca.skynetcloud.cybercore.common.items.tools;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.ItemInit;
import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class DarkSteelSword extends SwordItem {

	private static boolean HasSwordBeenMade = false;

	public DarkSteelSword(Tier material, float speed) {
		super(material, 3, speed, new Properties().tab(CyberCoreTab.MAIN));

	}

	public DarkSteelSword(Tier material, float speed, Properties properties) {
		super(material, 3, speed, properties);
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level p_77622_2_, Player p_77622_3_) {
		if (!p_77622_2_.isClientSide()) {
			if (stack.sameItem(new ItemStack(ItemInit.DARK_STEEL_SWORD.get()))) {
				stack.enchant(Enchantments.SHARPNESS, 5);
				if (!HasSwordBeenMade) {
					p_77622_3_
							.sendSystemMessage(
									Component.literal(ChatFormatting.GREEN + "[" + CyberCore.MODID + "] "
											+ ChatFormatting.RED + "The Item you made is now been given an Enchant :D"));
				}
			}
		}
		super.onCraftedBy(stack, p_77622_2_, p_77622_3_);
	}

}
