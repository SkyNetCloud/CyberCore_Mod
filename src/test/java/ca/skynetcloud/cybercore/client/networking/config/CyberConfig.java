package ca.skynetcloud.cybercore.client.networking.config;

import ca.skynetcloud.cybercore.CyberCoreMain;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CyberConfig {

	public static final ForgeConfigSpec CONFIG_SPEC;
	public static final Config CONFIG;
	private static final String CONFIG_PREFIX = "gui." + CyberCoreMain.MODID + ".config.";

	static {
		final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
		CONFIG_SPEC = specPair.getRight();
		CONFIG = specPair.getLeft();
	}

	public static void bake() {
		Enchantment.bake();
		CyberConfig.bake();
	}

	public static class Enchantment {
		public static Rarity rarity = Rarity.RARE;

		public static int levels = 1;
		public static boolean isTreasure = false;
		public static boolean isVillagerTrade = true;
		public static boolean isLootable = true;
		public static boolean canApplyAtEnchantingTable = true;
		public static boolean canApplyOnBooks = true;
		public static int minEnchantabilityBase = 15;
		public static int minEnchantabilityPerLevel = 5;
		public static Set<String> incompatibleEnchantments = new HashSet<>();

		public static void bake() {
			rarity = Config.rarity.get();
			levels = Config.levels.get();
			isTreasure = Config.isTreasure.get();
			isVillagerTrade = Config.isVillagerTrade.get();
			isLootable = Config.isLootable.get();
			canApplyAtEnchantingTable = Config.canApplyAtEnchantingTable.get();
			canApplyOnBooks = Config.canApplyOnBooks.get();
			minEnchantabilityBase = Config.minEnchantabilityBase.get();
			minEnchantabilityPerLevel = Config.minEnchantabilityPerLevel.get();
			incompatibleEnchantments.clear();

			for (String enchantment : Config.incompatibleEnchantments.get()) {

				if (ForgeRegistries.ENCHANTMENTS.containsKey(new ResourceLocation(enchantment))) {
					incompatibleEnchantments.add(enchantment);
				}
			}
		}
	}

	public static class Config {

		public static BooleanValue playMoveSound;
		public static BooleanValue playHurtSound;
		public static BooleanValue playDeathSound;
		public static BooleanValue NikoNeekoMobsSetting;
		public static BooleanValue SkyNetCloudMobsSetting;
		public static IntValue MAX_ITEMS_IN_PIPE;
		public static IntValue SOFT_CAP;
		public static IntValue HARD_CAP;
		public static IntValue TICKS_PIPE;
		public static IntValue POWERLMIT;
		public static IntValue creaitstartOff;
		public static IntValue PerTick;
		public static IntValue MaxTranfterCap;
		public static DoubleValue Max_Remote_Connection;
		public static DoubleValue additiveDurabilityDrop;
		public static DoubleValue maximumDurabilityDrop;
		public static DoubleValue minimumDurabilityDrop;
		public static DoubleValue modeDurabilityDrop;
		public static DoubleValue dropLevel;
		public static DoubleValue saveChance;
		public static DoubleValue additiveSaveChance;
		public static DoubleValue additiveDropChance;
		public static IntValue SpeedCardTick;
		public static IntValue LabGenerateChance;
		public static IntValue TILLING_RANGE;
		public static IntValue IRRIG_RANGE;
		public static IntValue MOISTURE;
		public static IntValue PLANTER_RANGE;
		public static IntValue ITEM_OUT_SIZE;
		public static BooleanValue durabilityDrop;
		public static BooleanValue breakItemOnZeroDurability;
		public static EnumValue<Rarity> rarity;
		public static IntValue levels;
		public static BooleanValue isTreasure;
		public static BooleanValue isVillagerTrade;
		public static BooleanValue isLootable;
		public static BooleanValue canApplyAtEnchantingTable;
		public static BooleanValue canApplyOnBooks;
		public static IntValue minEnchantabilityBase;
		public static IntValue minEnchantabilityPerLevel;
		public static ForgeConfigSpec.ConfigValue<List<? extends String>> incompatibleEnchantments;

		public Config(ForgeConfigSpec.Builder builder) {
			builder.push("enchantment");

			TICKS_PIPE = builder.comment(
					"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
					.translation("cybercore.ticks.all").defineInRange("ticks_in_tube", 10, 1, 72000);

			TILLING_RANGE = builder.comment("Range of cultivator item").defineInRange("cultivator.range", 9, 2, 32);

			PLANTER_RANGE = builder.comment("Range of planter item").defineInRange("planter.range", 9, 2, 32);

			ITEM_OUT_SIZE = builder.comment("Item pulled out of a another block").defineInRange("item.out.size", 64, 1,
					64);

			POWERLMIT = builder.comment("Power Limit For All Blocks default:1000")
					.defineInRange("block.cybercore_power_limit", 1000, 1, 2500);

			MAX_ITEMS_IN_PIPE = builder.comment(
					"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
					.defineInRange("block.max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

			SOFT_CAP = builder.comment(
					"Hard cap on how many tubes can exist in a contiguous network of tubes. If a player attempts to make a network of greater size from this value, not all tubes in the attempted network will become part of that network.")
					.translation("tubesreloaded.config.hard_tube_cap").defineInRange("hard_tube_cap", 500, 1, 10000);

			HARD_CAP = builder.comment(
					"Hard cap on how many item cables can exist in a contiguous network of item cable. If a player attempts to make a network of greater size from this value, not all tubes in the attempted network will become part of that network.")
					.translation("tubesreloaded.config.hard_tube_cap").defineInRange("hard_tube_cap", 500, 1, 10000);

			creaitstartOff = builder.comment("Creadit Player Start off with Default:100")
					.defineInRange("item.creait_start_off", 100, 1, 1000);

			PerTick = builder.comment("How much power you get perTick default:10").defineInRange("other.perTick", 10, 1,
					100);

			MaxTranfterCap = builder.comment("Cable maxTransferRate is how much cable can Tranfer default:20")
					.defineInRange("other.max_Transfer_Rate", 20, 20, 1000);

			Max_Remote_Connection = builder.comment(
					"Maximum range at which tubes can be remotely connected to each other. This also affects how many nearby chunks are checked for longtube intersections when placing a block.")
					.translation("cybercore.config.max_remote_item_cable_connection_range")
					.defineInRange("max_remote_tube_connection_range", 16D, 0D, Double.MAX_VALUE);

			SpeedCardTick = builder.comment("Speed Upgrade Tick default:15").defineInRange("other.speed_card_tick", 15,
					15, 25);

			rarity = builder.comment("The rarity of the enchantment").translation(CONFIG_PREFIX + "rarity")
					.defineEnum("rarity", Rarity.RARE);

			levels = builder.comment("The number of levels of the enchantment").translation(CONFIG_PREFIX + "levels")
					.defineInRange("levels", 1, 1, 5);

			isTreasure = builder.comment("Whether or not to consider this enchantment as a treasure")
					.translation(CONFIG_PREFIX + "isTreasure").define("isTreasure", false);

			isVillagerTrade = builder.comment("Whether or not this enchantment can be offered by villagers for trade")
					.translation(CONFIG_PREFIX + "isVillagerTrade").define("isVillagerTrade", true);

			isLootable = builder.comment("Whether or not this enchantment can generate in loot")
					.translation(CONFIG_PREFIX + "isLootable").define("isLootable", true);

			canApplyAtEnchantingTable = builder
					.comment("Whether or not this enchantment can be applied at the enchanting table")
					.translation(CONFIG_PREFIX + "canApplyAtEnchantingTable").define("canApplyAtEnchantingTable", true);

			canApplyOnBooks = builder.comment("Whether or not this enchantment can be applied on books")
					.translation(CONFIG_PREFIX + "canApplyOnBooks").define("canApplyOnBooks", true);

			minEnchantabilityBase = builder
					.comment("The minimum enchantability requirement for the first enchantment level")
					.translation(CONFIG_PREFIX + "minEnchantabilityBase")
					.defineInRange("minEnchantabilityBase", 15, 1, 100);

			minEnchantabilityPerLevel = builder
					.comment("The additional enchantability requirement for each enchantment level after the first")
					.translation(CONFIG_PREFIX + "minEnchantabilityPerLevel")
					.defineInRange("minEnchantabilityPerLevel", 5, 1, 100);

			incompatibleEnchantments = builder
					.comment("List of enchantments that cannot be applied together with this enchantment")
					.translation(CONFIG_PREFIX + "incompatibleEnchantments")
					.defineList("incompatibleEnchantments", new ArrayList<>(), s -> s instanceof String);

			dropLevel = builder.comment("Chance for the enchant to drop down 1 level on death from 0.00 to 1.00.")
					.translation("soulbound.config.drop_level").defineInRange("drop_level", 1.0, 0.0, 1.0);

			additiveDropChance = builder.comment("Chance for enchant to drop down 1 level with every added level\n"
					+ "So if someone with Soulbound 3 dies the chance of the enchant being downgraded would be:\n"
					+ "(dropLevel) - ((level - 1) * additiveDropChance)\n"
					+ "Remember if you've set a lot of levels this could lead to the higher levels never dropping down")
					.translation("soulbound.config.additive_drop_chance")
					.defineInRange("additive_drop_chance", 0.0, 0.0, 1.0);

			builder.pop();

			builder.push("Save Chance Values");

			additiveSaveChance = builder
					.comment("Chance for item to be kept from 0.00 to 1.00 with every added level.\n"
							+ "So if someone with Soulbound 3 dies their chances of keeping the item would be:\n"
							+ ("(saveChance) + ((level - 1) * additiveSaveChance)\n"
									+ "Remember if you've set a lot of levels this could lead to the higher levels always being saved."))
					.translation("soulbound.config.additive_save_chance")
					.defineInRange("additive_save_chance", 0.0, 0.0, 1.0);

			saveChance = builder // done
					.comment("Chance for item with enchant to be kept from 0.00 to 1.00.")
					.translation("soulbound.config.save_chance").defineInRange("save_chance", 1.0, 0.0, 1.0);

			builder.pop();

			builder.push("Robot Stuff");

			playMoveSound = builder.comment("Whether or not Robot move sound is on or off Default: true")
					.translation(CONFIG_PREFIX + "playMoveSound").define("playMoveSound", true);

			playHurtSound = builder.comment("Whether or not Robot hurt sound is on or off Default: true")
					.translation(CONFIG_PREFIX + "playHurtSound").define("playHurtSound", true);

			playDeathSound = builder.comment("Whether or not Robot death sound is on or off Default: true")
					.translation(CONFIG_PREFIX + "playDeathSound").define("playDeathSound", true);
			/*
			 * SkyNetCloudMobsSetting = builder.comment(
			 * "Mobs Setting for the User [SkyNetCloud]  [This doesn't aply to normal player unless your in the same server] Default: true"
			 * ) .translation(CONFIG_PREFIX +
			 * "SkyNetCloudMobsSetting").define("SkyNetCloudMobsSetting", true);
			 * 
			 * NikoNeekoMobsSetting = builder.comment(
			 * "Mobs Setting for the User [NikoNeeko] [This doesn't aply to normal player unless your in the same server] Default: true"
			 * ) .translation(CONFIG_PREFIX +
			 * "NikoNeekoMobsSetting").define("NikoNeekoMobsSetting", true);
			 */
			builder.pop();

			builder.push("Durability Drop Values");

			durabilityDrop = builder.comment(
					"Set whether durability drop is enabled or not. Durability drop is calculated with the min and max \n"
							+ "variables. The values chosen will most likely be in the middle and get rarer towards the ends. (triangular distribution)")
					.translation("soulbound.config.durability_drop").define("durability_drop", false);

			breakItemOnZeroDurability = builder.comment(
					"If set to true, the item will be broken if the durability reaches 0 on it's durabilityDrop")
					.translation("soulbound.config.break_item_on_zero_durability")
					.define("break_item_on_zero_durability", false);

			additiveDurabilityDrop = builder.comment(
					"Subtracts this number from the max, min, and mode each level effectively making the durability \n"
							+ "drop go down the higher the level")
					.translation("soulbound.config.additive_durability_drop")
					.defineInRange("additive_durability_drop", 0.05, 0.0, 1.0);

			maximumDurabilityDrop = builder.comment(
					"Defines the minimum percentage that the durability goes down when returned (this percentage is of\n"
							+ "the items max durability NOT the actual durability)")
					.translation("soulbound.config.maximum_durability_drop")
					.defineInRange("maximum_durability_drop", 0.35, 0.0, 1.0);

			minimumDurabilityDrop = builder.comment(
					"Defines the maximum percentage that the durability goes down when returned (this percentage is of\n"
							+ "the items max durability NOT the actual durability)")
					.translation("soulbound.config.minimum_durability_drop")
					.defineInRange("minimum_durability_drop", 0.20, 0.0, 1.0);

			modeDurabilityDrop = builder.comment(
					"Defines the mode (average value) percentage that the durability goes down when returned. This value\n"
							+ "cannot be more than the maximum or less than the minimum. (this percentage is of\n"
							+ "the items max durability NOT the actual durability)")
					.translation("soulbound.config.mode_durability_drop")
					.defineInRange("mode_durability_drop", 0.25, 0.0, 1.0);

		}

		public static int getTillingRange() {
			return TILLING_RANGE.get();
		}

		public static int getPlantingRange() {
			return PLANTER_RANGE.get();
		}

		public static int getIrrigationRange() {
			return IRRIG_RANGE.get();
		}

		public static int getMoisture() {
			return MOISTURE.get();
		}
	}

	public enum PermissionType {
		BLACKLIST, WHITELIST
	}

	public enum ActivationState {
		STANDING, CROUCHING, KEYBINDING
	}
}