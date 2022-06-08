package ca.skynetcloud.cybercore.common.items.enchantment;

import javax.annotation.Nonnull;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.client.init.ItemInit;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentSoulbound extends Enchantment {

	public EnchantmentSoulbound() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR,
				new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.CHEST });
	}

	@Nonnull
	@Override
	public Rarity getRarity() {
		return CyberConfig.Enchantment.rarity;
	}

	@Override
	public int getMaxLevel() {
		return CyberConfig.Enchantment.levels;
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return CyberConfig.Enchantment.minEnchantabilityBase
				+ CyberConfig.Enchantment.minEnchantabilityPerLevel * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 50;
	}


	@Override
	public boolean isTreasureOnly() {
		return CyberConfig.Enchantment.isTreasure;
	}

	@Override
	public boolean isTradeable() {
		return CyberConfig.Enchantment.isVillagerTrade;
	}

	@Override
	public boolean isDiscoverable() {
		return CyberConfig.Enchantment.isLootable;
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		ResourceLocation rl = ItemInit.SOUL_BOUND.getId();

		if (rl != null && CyberConfig.Enchantment.incompatibleEnchantments.contains(rl.toString())) {
			return false;
		}
		return super.checkCompatibility(ench);
	}

	@Override
	public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack) {
		return super.canApplyAtEnchantingTable(stack) && CyberConfig.Enchantment.canApplyAtEnchantingTable;
	}

	@Override
	public boolean isAllowedOnBooks() {
		return CyberConfig.Enchantment.canApplyOnBooks;
	}
}
