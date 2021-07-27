package ca.skynetcloud.cybercore.util.networking.handler;

import java.util.List;

import ca.skynetcloud.cybercore.item.CyberCoreCard;
import net.minecraft.world.item.ItemStack;

public class PlayerInventoryUtils {
	public static boolean enoughSpace(PlayerInventory inv, int neededSpace) {
		if (neededSpace <= 0) {
			return true;
		}

		int freeSlots = 0;
		for (int i = 0; i < 36; i++) {
			if (inv.getItem(i).isEmpty()) {
				freeSlots++;
				if (freeSlots >= neededSpace) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean hasList(PlayerInventory inv, List<ItemStack> stacks) {
		for (int i = 0; i < stacks.size(); i++) {
			ItemStack stack = stacks.get(i);
			if (!stack.isEmpty()) {
				if (inv.countItem(stack.getItem()) < stack.getCount()) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean addList(PlayerInventory inv, List<ItemStack> stacks) {
		boolean flag = true;
		for (ItemStack stack : stacks) {
			if (!inv.add(stack)) {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean removeList(PlayerInventory inv, List<ItemStack> stacks) {
		boolean flag = true;
		for (ItemStack stack : stacks) {
			if (!remove(inv, stack)) {
				flag = false;
			}
		}
		return flag;
	}

	public static boolean remove(PlayerInventory inv, ItemStack stack) {
		int count = stack.getCount();
		for (int j = 0; j < 36; j++) {
			ItemStack invstack = inv.getItem(j);
			if (!invstack.isEmpty()) {
				if (invstack.getItem() == stack.getItem()) {
					int k = Math.min(count, invstack.getCount());
					count -= k;
					invstack.shrink(k);
					if (invstack.isEmpty()) {
						inv.setItem(j, ItemStack.EMPTY);
					}

					if (count <= 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean enoughCredits(PlayerInventory inv, int amount) {
		if (amount <= 0) {
			return true;
		}

		int count = 0;
		for (int i = 0; i < 36; i++) {
			ItemStack invstack = inv.getItem(i);
			if (!invstack.isEmpty()) {
				if (invstack.getItem() instanceof CyberCoreCard) {
					count += CyberCoreCard.getCoins(invstack);
					if (count >= amount) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean removeCredits(PlayerInventory inv, int amount) {
		int count = amount;
		if (count <= 0) {
			return true;
		}
		for (int i = 0; i < 36; i++) {
			ItemStack invstack = inv.getItem(i);
			if (!invstack.isEmpty()) {
				if (invstack.getItem() instanceof CyberCoreCard) {
					int credits = CyberCoreCard.getCoins(invstack);
					credits -= CyberCoreCard.shrinkCoins(invstack, count);
					count -= credits;
					if (count <= 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean addCredits(PlayerInventory inv, int amount) {
		for (int i = 0; i < 36; i++) {
			ItemStack invstack = inv.getItem(i);
			if (!invstack.isEmpty()) {
				if (invstack.getItem() instanceof CyberCoreCard) {
					CyberCoreCard.addCoins(invstack, amount);
				}
			}
		}
		return false;
	}

}
