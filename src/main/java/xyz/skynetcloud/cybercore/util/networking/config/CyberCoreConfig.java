package xyz.skynetcloud.cybercore.util.networking.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class CyberCoreConfig {

	public static BooleanValue CanConnect;

	public static IntValue worldHight;

	public static BooleanValue GrassEnable;

	public static ConfigValue<Integer> MAX_ITEMS_IN_PIPE;

	public static ConfigValue<Integer> SOFT_CAP;

	public static ConfigValue<Integer> TICKS_PIPE;

	public static ConfigValue<Integer> POWERLMIT;

	public static ConfigValue<Integer> creaitstartOff;

	public static ConfigValue<Integer> PerTick;

	public static ConfigValue<Integer> MaxTranfterCap;

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

		IRRIG_RANGE = client.comment("Watering radius of irrigation block").defineInRange("irrigator.radius", 4, 1, 16);

		MOISTURE = client.comment("Moisture level set by cultivator").defineInRange("cultivator.moisture", 7, 0, 7);

		LabGenerateChance = client.comment(
				"The chance for a Lab to generate. This value is interpreted as \"1 out of X\" to generate a Lab")
				.defineInRange("LabGenerateChance", 25, 1, Integer.MAX_VALUE);

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

		CanConnect = client.comment("Can Connect this block default:true").define("block.canConnect", true);

		worldHight = client.comment("World High for new dim").defineInRange("world.worldHight", 256, 1, 256);

		GrassEnable = client.comment("Should the layers top of the world be grass").define("world.grass_enable", true);

		MAX_ITEMS_IN_PIPE = client.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
				.defineInRange("block.max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
		SOFT_CAP = client.comment(
				"Soft cap on how many tubes can exist in a contiguous network of Item Pipes. Items are transported slowlier in networks of greater size than this value.")
				.defineInRange("block.soft_item_pipe_cap", 400, 1, 10000);

		TICKS_PIPE = client.comment(
				"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
				.defineInRange("block.ticks_in_tube", 10, 1, 72000);

		POWERLMIT = client.comment("Power Limit For All Blocks default:1000").define("block.cybercore_power_limit",
				1000);

		creaitstartOff = client.comment("Creadit Player Start off with Default:100")
				.defineInRange("item.creait_start_off", 100, 1, 1000);

		PerTick = client.comment("How much power you get perTick default:10").defineInRange("other.perTick", 10, 1,
				100);

		MaxTranfterCap = client.comment("Cable maxTransferRate is how much cable can Tranfer default:20")
				.defineInRange("other.max_Transfer_Rate", 20, 20, 1000);

		SpeedCardTick = client.comment("Speed Upgrade Tick default:15").define("other.speed_card_tick", 15);

		// Server
		server.comment("Client Side Configuration").push("server");

		TILLING_RANGE = server.comment("Range of cultivator item").defineInRange("cultivator.range", 9, 2, 32);

		PLANTER_RANGE = server.comment("Range of planter item").defineInRange("planter.range", 9, 2, 32);

		IRRIG_RANGE = server.comment("Watering radius of irrigation block").defineInRange("irrigator.radius", 4, 1, 16);

		MOISTURE = server.comment("Moisture level set by cultivator").defineInRange("cultivator.moisture", 7, 0, 7);

		attackdamage_Cyber = server.comment("Cyber base attack damage").defineInRange("tool.attackdamage_cyber", 2.0,
				0.0, 999999999);
		attackdamage_Ruby = server.comment("Ruby base attack damage").defineInRange("tool.attackdamage_ruby", 3.5, 0.0,
				999999999);
		attackdamage_Dark_Steel = server.comment("Dark Steel base attack damage")
				.defineInRange("tool.attackdamage_dark_steel", 3.5, 0.0, 999999999);

		durability_Cyber = server.comment("Cyber durabilty (Default: 1000)").defineInRange("tool.cyber_tools_durabilty",
				1000, 0, 999999999);
		durability_Ruby = server.comment("Ruby durabilty (Default: 2000)").defineInRange("tool.ruby_tools_durabilty",
				2000, 0, 999999999);
		durability_Dark_Steel = server.comment("Dark Steel durabilty (Default: 1561)")
				.defineInRange("tool.dark_steel_tools_durabilty", 1561, 0, 999999999);

		CanConnect = server.comment("Can Connect this block default:true").define("block.canConnect", true);

		worldHight = server.comment("World High for new dim").defineInRange("world.worldHight", 256, 1, 256);

		GrassEnable = server.comment("Should the layers top of the world be grass").define("world.grass_enable", true);

		MAX_ITEMS_IN_PIPE = server.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
				.defineInRange("block.max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
		SOFT_CAP = server.comment(
				"Soft cap on how many tubes can exist in a contiguous network of Item Pipes. Items are transported slowlier in networks of greater size than this value.")
				.defineInRange("block.soft_item_pipe_cap", 400, 1, 10000);

		TICKS_PIPE = server.comment(
				"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
				.defineInRange("block.ticks_in_tube", 10, 1, 72000);

		POWERLMIT = server.comment("Power Limit For All Blocks default:1000").define("block.cybercore_power_limit",
				1000);

		creaitstartOff = server.comment("Creadit Player Start off with Default:100")
				.defineInRange("item.creait_start_off", 100, 1, 1000);

		PerTick = server.comment("How much power you get perTick default:10").defineInRange("other.perTick", 10, 1,
				100);

		MaxTranfterCap = server.comment("Cable maxTransferRate is how much cable can Tranfer default:20")
				.defineInRange("max_Transfer_Rate", 20, 20, 1000);

		SpeedCardTick = server.comment("Speed Upgrade Tick default:15").define("other.speed_card_tick", 15);

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
