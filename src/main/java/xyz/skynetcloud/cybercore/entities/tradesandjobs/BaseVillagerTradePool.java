package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;

public class BaseVillagerTradePool {
	private final String name;
	private final List<ItemStackWithRandomSize> inputs;
	private final List<ItemStackWithRandomSize> outputs;
	private final int creditsBuy, creditsSell;

	public BaseVillagerTradePool(String name, List<ItemStackWithRandomSize> inputs,
			List<ItemStackWithRandomSize> outputs, int creditsBuy, int creditsSell) {
		this.name = name;
		this.inputs = inputs;
		this.outputs = outputs;
		this.creditsBuy = creditsBuy;
		this.creditsSell = creditsSell;
	}

	public BaseVillagerTrade generateVillagerTrade() {
		List<ItemStack> inputstacks = new ArrayList<>(), outputstacks = new ArrayList<>();
		Random rand = new Random();
		for (ItemStackWithRandomSize template : inputs) {
			inputstacks.add(template.getRandomSizeStack(rand));
		}

		for (ItemStackWithRandomSize template : outputs) {
			outputstacks.add(template.getRandomSizeStack(rand));
		}
		return new BaseVillagerTrade(name, inputstacks, outputstacks, creditsBuy, creditsSell);
	}

}
