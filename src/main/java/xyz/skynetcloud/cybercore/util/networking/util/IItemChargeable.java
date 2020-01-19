package xyz.skynetcloud.cybercore.util.networking.util;

import net.minecraft.item.ItemStack;

public interface IItemChargeable {

	public int receiveEnergyLoad(ItemStack stack, int amount, boolean simulate);

	public int extractEnergyLoad(ItemStack stack, int amount, boolean simulate);

	public int maxEnergyLoad(ItemStack stack);

	public int currentEnergyLoad(ItemStack stack);
}
