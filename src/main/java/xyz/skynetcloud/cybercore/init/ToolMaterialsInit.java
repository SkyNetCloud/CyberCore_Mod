package xyz.skynetcloud.cybercore.init;

import static xyz.skynetcloud.cybercore.api.items.ItemNames.*;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;

public enum ToolMaterialsInit implements IItemTier {
	cyber_ingot_materials(10.0f, 9.0f, 800, 3, 25, cyber_ingot),
	ruby_ingot_materials(10.0f, 9.0f, 800, 3, 25, ruby_ingot),
	dark_steel_ingot_materials(10.0f, 9.0f, 800, 3, 25, dark_steel_ingot);

	private float attackDamage, efficiency;
	private int durability, harvestLevel, enchantability;
	private Item repairMaterial;

	private ToolMaterialsInit(float attackDamage, float efficiency, int durability, int harvestLevel,
			int enchantability, Item repairMaterial) {
		this.attackDamage = attackDamage;
		this.efficiency = efficiency;
		this.durability = durability;
		this.harvestLevel = harvestLevel;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getMaxUses() {
		return this.durability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(this.repairMaterial);
	}
}