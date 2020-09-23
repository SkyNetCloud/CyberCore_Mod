package ca.skynetcloud.cybercore.init;

import java.util.function.Supplier;

import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.block.Blocks;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public class BasisToolMaterial {

	public static final IItemTier cyber_ingot = new ToolMaterial(3, CyberCoreConfig.durability_Cyber.get(), 6.0F,
			CyberCoreConfig.attackdamage_Cyber.get(), 14, () -> Ingredient.fromItems(Items.QUARTZ));
	public static final IItemTier ruby_gem = new ToolMaterial(3, CyberCoreConfig.durability_Ruby.get(), 7F,
			CyberCoreConfig.attackdamage_Ruby.get(), 10, () -> Ingredient.fromItems(Blocks.OBSIDIAN));
	public static final IItemTier dark_steel_ingot = new ToolMaterial(3, CyberCoreConfig.durability_Dark_Steel.get(),
			9.5F, CyberCoreConfig.attackdamage_Dark_Steel.get(), 10, () -> Ingredient.fromItems(Items.EMERALD));

	private static class ToolMaterial implements IItemTier {

		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantability;
		private final LazyValue<Ingredient> repair;

		public ToolMaterial(int harvestLevel, int maxUses, float efficiency, double attackDamage, int enchantability,
				Supplier<Ingredient> supplier) {
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = (float) attackDamage;
			this.enchantability = enchantability;
			this.repair = new LazyValue<Ingredient>(supplier);
		}

		@Override
		public int getMaxUses() {
			return maxUses;
		}

		@Override
		public float getEfficiency() {
			return efficiency;
		}

		@Override
		public float getAttackDamage() {
			return attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			return harvestLevel;
		}

		@Override
		public int getEnchantability() {
			return enchantability;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return repair.getValue();
		}
	}

}