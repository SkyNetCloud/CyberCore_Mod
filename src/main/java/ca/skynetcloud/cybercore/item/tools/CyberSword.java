package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.init.CoreInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class CyberSword extends SwordItem {

	private static boolean HasSwordBeenMade = false;

	public CyberSword(Tier material, float speed) {
		super(material, 3, speed, new Properties().tab(CyberCoreTab.instance));

	}

	public CyberSword(Tier material, float speed, Properties properties) {
		super(material, 3, speed, properties);
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level p_77622_2_, Player p_77622_3_) {
		if (!p_77622_2_.isClientSide()) {
			if (stack.sameItem(new ItemStack(CoreInit.ItemInit.cyber_sword))) {
				stack.enchant(Enchantments.SHARPNESS, 5);
				if (!HasSwordBeenMade) {
					p_77622_3_.sendMessage(new TextComponent(ChatFormatting.GREEN + "[" + CyberCoreMain.NAME + "] "
							+ ChatFormatting.RED + "The Item you has been give a cool Enchant"), null);
				}
			}
		}
		super.onCraftedBy(stack, p_77622_2_, p_77622_3_);
	}

}
