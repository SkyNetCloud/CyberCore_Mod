package ca.skynetcloud.cybercore.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class SoulReturn extends Enchantment {

	public SoulReturn(EquipmentSlotType[] slots) {
		super(Rarity.VERY_RARE, EnchantmentType.ALL, slots);

	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 30;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

}
