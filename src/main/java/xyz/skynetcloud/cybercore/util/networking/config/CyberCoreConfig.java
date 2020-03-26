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

	public static ConfigValue<Boolean> GivenOnFirstJoin;

	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {

		// Client
		client.comment("Client Side Configuration").push("client");

		CanConnect = client.comment("Can Connect this block default:true").define("canConnect", true);

		worldHight = client.comment("World High for new dim").defineInRange("worldHight", 256, 1, 256);

		GrassEnable = client.comment("Should the layers top of the world be grass").define("grass_enable", true);

		MAX_ITEMS_IN_PIPE = client.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
				.defineInRange("max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
		SOFT_CAP = client.comment(
				"Soft cap on how many tubes can exist in a contiguous network of Item Pipes. Items are transported slowlier in networks of greater size than this value.")
				.defineInRange("soft_item_pipe_cap", 400, 1, 10000);

		TICKS_PIPE = client.comment(
				"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
				.defineInRange("ticks_in_tube", 10, 1, 72000);

		POWERLMIT = client.comment("Power Limit For All Blocks default:1000").define("cybercore_power_limit", 1000);

		creaitstartOff = client.comment("Creadit Player Start off with Default:100").defineInRange("creait_start_off",
				100, 1, 1000);

		PerTick = client.comment("How much power you get perTick default:10").defineInRange("perTick", 10, 1, 100);
		
		MaxTranfterCap = client.comment("Cable maxTransferRate is how much cable can Tranfer default:20")
				.defineInRange("max_Transfer_Rate", 20, 20, 1000);
		
		SpeedCardTick = client.comment("Speed Upgrade Tick default:15").define("speed_card_tick", 15);
		

		GivenOnFirstJoin = server.comment("This allow player to login in with two of the modded seeds ServerSide:False ClientSide:True ")
				.define("give_on_first_join", true);

		// Server
		server.comment("Client Side Configuration").push("server");

		CanConnect = server.comment("Can Connect this block default:true").define("canConnect", true);

		worldHight = server.comment("World High for new dim").defineInRange("worldHight", 256, 1, 256);

		GrassEnable = server.comment("Should the layers top of the world be grass").define("grass_enable", true);

		MAX_ITEMS_IN_PIPE = server.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
				.defineInRange("max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);
		SOFT_CAP = server.comment(
				"Soft cap on how many tubes can exist in a contiguous network of Item Pipes. Items are transported slowlier in networks of greater size than this value.")
				.defineInRange("soft_item_pipe_cap", 400, 1, 10000);

		TICKS_PIPE = server.comment(
				"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
				.defineInRange("ticks_in_tube", 10, 1, 72000);

		POWERLMIT = server.comment("Power Limit For All Blocks default:1000").define("cybercore_power_limit", 1000);

		creaitstartOff = server.comment("Creadit Player Start off with Default:100").defineInRange("creait_start_off",
				100, 1, 1000);

		PerTick = server.comment("How much power you get perTick default:10").defineInRange("perTick", 10, 1, 100);
		
		MaxTranfterCap = server.comment("Cable maxTransferRate is how much cable can Tranfer default:20")
				.defineInRange("max_Transfer_Rate", 20, 20, 1000);
		
		SpeedCardTick = server.comment("Speed Upgrade Tick default:15").define("speed_card_tick", 15);
		

		GivenOnFirstJoin = server.comment("This allow player to login in with two of the modded seeds ServerSide:False ClientSide:True")
				.define("give_on_first_join", false);


	}

}
