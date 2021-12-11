package ca.skynetcloud.cybercore.client.networking.util;

import net.minecraft.world.item.ItemStack;

public interface IItemChargeable {

	public int receiveEnergyLoad(ItemStack stack, int amount, boolean simulate);

	public int extractEnergyLoad(ItemStack stack, int amount, boolean simulate);

	public int maxEnergyLoad(ItemStack stack);

	public int currentEnergyLoad(ItemStack stack);
}
