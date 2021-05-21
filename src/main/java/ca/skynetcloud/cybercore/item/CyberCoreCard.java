package ca.skynetcloud.cybercore.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class CyberCoreCard extends Item {

	public CyberCoreCard(Properties property) {
		super(property);
	}

	public static boolean hasCoins(ItemStack stack) {
		CompoundNBT nbt = getNBT(stack);
		if (nbt.contains("coins")) {
			return true;
		}
		return false;
	}

	public static int getCoins(ItemStack stack) {
		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof CyberCoreCard) {
				if (hasCoins(stack)) {
					return getNBT(stack).getInt("coins");
				}
			}
		}
		return 100;
	}

	public static CompoundNBT getNBT(ItemStack stack) {
		CompoundNBT nbt = stack.getTag();
		if (nbt != null) {
			return nbt;
		}
		return new CompoundNBT();
	}

	public static int addCoins(ItemStack stack, int amount) {
		int newValue = getCoins(stack) + amount;
		setCoins(stack, newValue);
		return newValue;
	}

	public static int shrinkCoins(ItemStack stack, int amount) {
		int newValue = Math.max(getCoins(stack) - amount, 0);
		setCoins(stack, newValue);
		return newValue;
	}

	public static void setCoins(ItemStack stack, int amount) {
		CompoundNBT nbt = getNBT(stack);
		nbt.putInt("Coins", amount);
		stack.setTag(nbt);
	}

	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Cybercoins: " + getCoins(stack)));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}

}
