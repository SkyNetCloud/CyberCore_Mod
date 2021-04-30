package ca.skynetcloud.cybercore.util.networking.config;

import ca.skynetcloud.cybercore.CyberCoreMain;
import mezz.jei.config.forge.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;


public class CyberCoreConfig {

	public static BooleanValue CanConnect;

	public static ConfigValue<Integer> MAX_ITEMS_IN_PIPE;

	public static ConfigValue<Integer> SOFT_CAP;

	public static ConfigValue<Integer> HARD_CAP;

	public static ConfigValue<Integer> TICKS_PIPE;

	public static ConfigValue<Integer> POWERLMIT;

	public static ConfigValue<Integer> creaitstartOff;

	public static ConfigValue<Integer> PerTick;

	public static ConfigValue<Integer> MaxTranfterCap;

	public static ConfigValue<Double> Max_Remote_Connection;

	public static ConfigValue<Integer> SpeedCardTick;

	public static ConfigValue<Double> attackdamage_Cyber;

	public static ConfigValue<Double> attackdamage_Ruby;

	public static ConfigValue<Double> attackdamage_Dark_Steel;

	public static ConfigValue<Integer> durability_Cyber;

	public static ConfigValue<Integer> durability_Ruby;

	public static ConfigValue<Integer> durability_Dark_Steel;

	public static ConfigValue<Integer> LabGenerateChance;

	public static ConfigValue<Integer> TILLING_RANGE;

	public static ConfigValue<Integer> IRRIG_RANGE;

	public static ConfigValue<Integer> MOISTURE;

	public static ConfigValue<Integer> PLANTER_RANGE;

	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {

		// Client
		client.comment("Client Side Configuration").push("client");

		TILLING_RANGE = client.comment("Range of cultivator item").defineInRange("cultivator.range", 9, 2, 32);

		PLANTER_RANGE = client.comment("Range of planter item").defineInRange("planter.range", 9, 2, 32);

		attackdamage_Cyber = client.comment("Cyber base attack damage").defineInRange("tool.attackdamage_cyber", 2.0,
				0.0, 999999999);
		attackdamage_Ruby = client.comment("Ruby base attack damage").defineInRange("tool.attackdamage_ruby", 3.5, 0.0,
				999999999);
		attackdamage_Dark_Steel = client.comment("Dark Steel base attack damage")
				.defineInRange("tool.attackdamage_dark_steel", 3.5, 0.0, 999999999);

		durability_Cyber = client.comment("Cyber durabilty (Default: 1000)").defineInRange("tool.cyber_tools_durabilty",
				1000, 0, 999999999);
		durability_Ruby = client.comment("Ruby durabilty (Default: 2000)").defineInRange("tool.ruby_tools_durabilty",
				2000, 0, 999999999);
		durability_Dark_Steel = client.comment("Dark_Steel durabilty (Default: 1561)")
				.defineInRange("cybercore.dark_steel_tools_durabilty", 1561, 0, 999999999);

		POWERLMIT = client.comment("Power Limit For All Blocks default:1000").define("block.cybercore_power_limit",
				1000);

		MAX_ITEMS_IN_PIPE = client.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
				.defineInRange("block.max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

		SOFT_CAP = client.comment(
				"Hard cap on how many tubes can exist in a contiguous network of tubes. If a player attempts to make a network of greater size from this value, not all tubes in the attempted network will become part of that network.")
				.translation("tubesreloaded.config.hard_tube_cap").defineInRange("hard_tube_cap", 500, 1, 10000);

		HARD_CAP = client.comment(
				"Hard cap on how many item cables can exist in a contiguous network of item cable. If a player attempts to make a network of greater size from this value, not all tubes in the attempted network will become part of that network.")
				.translation("tubesreloaded.config.hard_tube_cap").defineInRange("hard_tube_cap", 500, 1, 10000);

		creaitstartOff = client.comment("Creadit Player Start off with Default:100")
				.defineInRange("item.creait_start_off", 100, 1, 1000);

		PerTick = client.comment("How much power you get perTick default:10").defineInRange("other.perTick", 10, 1,
				100);

		MaxTranfterCap = client.comment("Cable maxTransferRate is how much cable can Tranfer default:20")
				.defineInRange("other.max_Transfer_Rate", 20, 20, 1000);

		Max_Remote_Connection = client.comment(
				"Maximum range at which tubes can be remotely connected to each other. This also affects how many nearby chunks are checked for longtube intersections when placing a block.")
				.translation("cybercore.config.max_remote_item_cable_connection_range")
				.defineInRange("max_remote_tube_connection_range", 16D, 0D, Double.MAX_VALUE);

		SpeedCardTick = client.comment("Speed Upgrade Tick default:15").define("other.speed_card_tick", 15);

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
