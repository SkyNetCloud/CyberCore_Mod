package xyz.skynetcloud.cybercore.entities.tradesandjobs;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.text.TranslationTextComponent;

public class BaseVillagerTrade {
	private final String name;
	private final List<ItemStack> inputs;
	private final List<ItemStack> outputs;
	private final int creditsBuy, creditsSell;

	public BaseVillagerTrade(String name, List<ItemStack> inputs, List<ItemStack> outputs, int creditsBuy,
			int creditsSell) {
		this.name = name;
		this.inputs = inputs;
		this.outputs = outputs;
		this.creditsBuy = creditsBuy;
		this.creditsSell = creditsSell;
	}

	public String getName() {
		return new TranslationTextComponent(name).getUnformattedComponentText();
	}

	public List<ItemStack> getInputs() {
		return inputs;
	}

	public List<ItemStack> getOutputs() {
		return outputs;
	}

	public int getCreditsBuy() {
		return creditsBuy;
	}

	public int getCreditsSell() {
		return creditsSell;
	}

	public CompoundNBT toNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putString("name", name);
		nbt.putInt("length_inputs", inputs.size());
		for (int i = 0; i < inputs.size(); i++) {
			nbt.put("input_" + i, inputs.get(i).serializeNBT());
		}

		nbt.putInt("length_outputs", outputs.size());
		for (int i = 0; i < outputs.size(); i++) {
			nbt.put("output_" + i, outputs.get(i).serializeNBT());
		}
		nbt.putInt("creditsbuy", creditsBuy);
		nbt.putInt("creditssell", creditsSell);
		return nbt;
	}

	public static BaseVillagerTrade fromNBT(CompoundNBT nbt) {
		List<ItemStack> inputs = new ArrayList<>(), outputs = new ArrayList<>();
		for (int i = 0; i < nbt.getInt("length_inputs"); i++) {
			inputs.add(ItemStack.read(nbt.getCompound("input_" + i)));
		}

		for (int i = 0; i < nbt.getInt("length_outputs"); i++) {
			outputs.add(ItemStack.read(nbt.getCompound("output_" + i)));
		}

		return new BaseVillagerTrade(nbt.getString("name"), inputs, outputs, nbt.getInt("creditsbuy"),
				nbt.getInt("creditssell"));
	}

	public PacketBuffer toBuffer(PacketBuffer buf) {
		buf.writeString(name);
		buf.writeInt(inputs.size());
		for (int i = 0; i < inputs.size(); i++) {
			buf.writeItemStack(inputs.get(i));
		}

		buf.writeInt(outputs.size());
		for (int i = 0; i < outputs.size(); i++) {
			buf.writeItemStack(outputs.get(i));
		}
		buf.writeInt(creditsBuy);
		buf.writeInt(creditsSell);
		return buf;
	}

	public static BaseVillagerTrade fromBuffer(PacketBuffer buf) {
		List<ItemStack> inputs = new ArrayList<>(), outputs = new ArrayList<>();
		String name = buf.readString();
		int size = buf.readInt();
		for (int i = 0; i < size; i++) {
			inputs.add(buf.readItemStack());
		}

		size = buf.readInt();
		for (int i = 0; i < size; i++) {
			outputs.add(buf.readItemStack());
		}
		return new BaseVillagerTrade(name, inputs, outputs, buf.readInt(), buf.readInt());
	}
}