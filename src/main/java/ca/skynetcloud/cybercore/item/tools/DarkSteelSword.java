package ca.skynetcloud.cybercore.item.tools;

import ca.skynetcloud.cybercore.CyberCoreClient.CyberCoreTab;
import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class DarkSteelSword extends SwordItem {

	private static boolean HasSwordBeenMade = false;

	public DarkSteelSword(IItemTier material, float speed) {
		super(material, 3, speed, new Properties().tab(CyberCoreTab.instance));

	}

	public DarkSteelSword(IItemTier material, float speed, Properties properties) {
		super(material, 3, speed, properties);
	}

	@Override
	public void onCraftedBy(ItemStack stack, World p_77622_2_, PlayerEntity p_77622_3_) {
		if (!p_77622_2_.isClientSide()) {
			if (stack.sameItem(new ItemStack(ItemInit.cyber_sword))) {
				stack.enchant(Enchantments.SHARPNESS, 5);
				if (!HasSwordBeenMade) {
					p_77622_3_.sendMessage(new StringTextComponent(TextFormatting.GREEN + "[" + CyberCoreMain.NAME
							+ "] " + TextFormatting.RED + "The Item you made is now been given an Enchant :D"), null);
				}
			}
		}
		super.onCraftedBy(stack, p_77622_2_, p_77622_3_);
	}

}
