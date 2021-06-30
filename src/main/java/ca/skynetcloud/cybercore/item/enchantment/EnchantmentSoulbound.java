package ca.skynetcloud.cybercore.item.enchantment;

import javax.annotation.Nonnull;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.networking.config.CyberConfig;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentSoulbound extends Enchantment {

	public static final String ID = CyberCoreMain.MODID + ":soul_bound";

	public EnchantmentSoulbound() {
		super(Rarity.RARE, EnchantmentType.ARMOR,
				new EquipmentSlotType[] { EquipmentSlotType.MAINHAND, EquipmentSlotType.CHEST });
		this.setRegistryName("soul_bound");
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
		ResourceLocation rl = ench.getRegistryName();

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
