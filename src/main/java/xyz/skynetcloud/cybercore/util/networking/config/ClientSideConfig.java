package xyz.skynetcloud.cybercore.util.networking.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ClientSideConfig {

	public static BooleanValue grass_enable;

	public static IntValue world_height;

	public static IntValue overworldId;

	public static ConfigValue<Integer> max_items_in_item_pipe;

	public static ConfigValue<Integer> soft_item_pipe_cap;

	public static ConfigValue<Integer> hard_item_pipe_cap;

	public static ConfigValue<Integer> ticks_in_item_pipe;

	public static ConfigValue<Integer> PowerLmit;

	public static ConfigValue<Integer> ClientInfo;

	public static ConfigValue<Integer> LunarGenPerTick;

	public static ConfigValue<Integer> SpeedLvlTick;

	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		client.comment("Client Side Configuration").push("client");

		max_items_in_item_pipe = client.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")

				.defineInRange("max_items_in_tube", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

		ticks_in_item_pipe = client.comment(
				"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
				.defineInRange("ticks_in_tube", 10, 1, 72000);

		hard_item_pipe_cap = client.comment("").defineInRange("hard_item_pipe_cap", 500, 1, 10000);

		soft_item_pipe_cap = client.comment(
				"Soft cap on how many tubes can exist in a contiguous network of Item Pipes. Items are transported slowlier in networks of greater size than this value.")
				.defineInRange("soft_item_pipe_cap", 400, 1, 10000);

		grass_enable = client.comment("World Has Grass").translation("cybercore.config.grass_enable")
				.define("grass_enable", true);

		overworldId = client.comment("Overworld dim ID").defineInRange("overworldId", 0, -1000, 1000);

		world_height = client.comment("Height of the world").defineInRange("world_height", 70, 5, 256);

		PowerLmit = client.comment("Power Limit For All Blocks default:1000").define("cybercore_power_limit", 1000);

		SpeedLvlTick = client.comment("Speed Upgrade Tick default:15").define("speed_lvl_tick", 15);

		LunarGenPerTick = client.comment("Power Per Tick LunarGen Block default:10").define("lunar_gen_per_tick", 10);

		server.comment("Server Side Configuration");

		max_items_in_item_pipe = server.comment(
				"Max items that can fit in a single tube. A tube block will break of the number of itemstacks contained with them is greater than this value, dropping their items on the ground")
				.defineInRange("max_items_in_item_pipe", Integer.MAX_VALUE, 1, Integer.MAX_VALUE);

		ticks_in_item_pipe = server.comment(
				"Base time in ticks that a moving itemstack spends in each individual tube block. Adjusted by other factors.")
				.defineInRange("ticks_in_tube", 10, 1, 72000);

		hard_item_pipe_cap = server.comment("").defineInRange("hard_item_pipe_cap", 500, 1, 10000);

		soft_item_pipe_cap = server.comment(
				"Soft cap on how many tubes can exist in a contiguous network of Item Pipes. Items are transported slowlier in networks of greater size than this value.")

				.defineInRange("soft_item_pipe_cap", 400, 1, 10000);

		grass_enable = server.comment("World Has Grass").define("grass_enable", true);

		overworldId = server.comment("Overworld dim ID").defineInRange("overworldId", 0, -1000, 1000);

		world_height = server.comment("Height of the world").defineInRange("world_height", 70, 5, 256);

		PowerLmit = server.comment("Power Limit For All Blocks default:1000").define("cybercore_power_limit", 1000);

		SpeedLvlTick = server.comment("Speed Upgrade Tick default:15").define("speed_lvl_tick", 15);

		LunarGenPerTick = server.comment("Power Per Tick LunarGen Block default:10").define("lunar_gen_per_tick", 10);

	}

	public static int getOverworldId() {
		return overworldId.get();
	}
}
