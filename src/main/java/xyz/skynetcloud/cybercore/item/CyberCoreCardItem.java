package xyz.skynetcloud.cybercore.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;

public class CyberCoreCardItem extends ItemBaseCore {

	public CyberCoreCardItem() {
		super(new Item.Properties().maxStackSize(1).group(CyberCoreTab.instance));
	}

	public static boolean hasCredits(ItemStack stack) {
		CompoundNBT nbt = getNBT(stack);
		if (nbt.contains("credits")) {
			return true;
		}
		return false;
	}

	public static int getCredits(ItemStack stack) {
		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof CyberCoreCardItem) {
				if (hasCredits(stack)) {
					return getNBT(stack).getInt("credits");
				}
			}
		}
		return CyberCoreConfig.creaitstartOff.get();
	}

	public static CompoundNBT getNBT(ItemStack stack) {
		CompoundNBT nbt = stack.getTag();
		if (nbt != null) {
			return nbt;
		}
		return new CompoundNBT();
	}

	public static int addCredits(ItemStack stack, int amount) {
		int newValue = getCredits(stack) + amount;
		setCredits(stack, newValue);
		return newValue;
	}

	public static int shrinkCredits(ItemStack stack, int amount) {
		int newValue = Math.max(getCredits(stack) - amount, 0);
		setCredits(stack, newValue);
		return newValue;
	}

	public static void setCredits(ItemStack stack, int amount) {
		CompoundNBT nbt = getNBT(stack);
		nbt.putInt("credits", amount);
		stack.setTag(nbt);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("CyberCoreDollars: " + getCredits(stack)));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
