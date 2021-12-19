package ca.skynetcloud.cybercore.common.items.enchantment;

import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import javax.annotation.Nonnull;

public class EnchantmentSoulbound extends Enchantment {

	public EnchantmentSoulbound() {
		super(Rarity.RARE, EnchantmentCategory.ARMOR,
				new EquipmentSlot[] { EquipmentSlot.MAINHAND, EquipmentSlot.CHEST });
	}

	@Nonnull
	@Override
	public Rarity getRarity() {
		return CyberConfig.Config.rarity.get();
	}

	@Override
	public int getMaxLevel() {
		return CyberConfig.Config.levels.get();
	}

	@Override
	public int getMinCost(int enchantmentLevel) {
		return CyberConfig.Config.minEnchantabilityBase.get()
				+ CyberConfig.Config.minEnchantabilityPerLevel.get() * (enchantmentLevel - 1);
	}

	@Override
	public int getMaxCost(int enchantmentLevel) {
		return 50;
	}


	@Override
	public boolean isTreasureOnly() {
		return CyberConfig.Config.isTreasure.get();
	}

	@Override
	public boolean isTradeable() {
		return CyberConfig.Config.isVillagerTrade.get();
	}

	@Override
	public boolean isDiscoverable() {
		return CyberConfig.Config.isLootable.get();
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {
		ResourceLocation rl = ench.getRegistryName();

		if (rl != null && CyberConfig.Config.incompatibleEnchantments.get().contains(rl.toString())) {
			return false;
		}
		return super.checkCompatibility(ench);
	}

	@Override
	public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack) {
		return super.canApplyAtEnchantingTable(stack) && CyberConfig.Config.canApplyAtEnchantingTable.get();
	}

	@Override
	public boolean isAllowedOnBooks() {
		return CyberConfig.Config.canApplyOnBooks.get();
	}
}
