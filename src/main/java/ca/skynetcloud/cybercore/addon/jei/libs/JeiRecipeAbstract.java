package ca.skynetcloud.cybercore.addon.jei.libs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class JeiRecipeAbstract {

	protected List<ItemStack> inputs = new ArrayList<ItemStack>();
	protected List<ItemStack> outputs = new ArrayList<ItemStack>();

	public int getInputLength() {
		return inputs.size();
	}

	public int getOutputLength() {
		return outputs.size();
	}

	public ItemStack getInput(int i) {
		if (i < getInputLength()) {
			return inputs.get(i);
		}
		return ItemStack.EMPTY;
	}

	public List<ItemStack> getInputs() {
		return inputs;
	}

	public ItemStack getOutput(int i) {
		if (i < getOutputLength()) {
			return outputs.get(i);
		}
		return ItemStack.EMPTY;

	}

	public List<ItemStack> getOutputs() {
		return outputs;
	}
}
