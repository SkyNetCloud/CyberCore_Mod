package xyz.skynetcloud.cybercore.util;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ClientSideConfig {

	public BooleanValue grass_enable;

	public static IntValue world_height;

	public static IntValue overworldId;

	public static ConfigValue<Integer> PowerLmit;

	public static ConfigValue<Integer> ClientInfo;

	public static ConfigValue<Integer> LunarGenPerTick;

	public static ConfigValue<Integer> SpeedLvlTick;

	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		client.comment("Client Side Configuration").push("common");

		overworldId = client.comment("Overworld dim ID").defineInRange("overworldId", 0, -1000, 1000);

		world_height = client.comment("Height of the world").defineInRange("world_height", 70, 5, 256);

		PowerLmit = client.comment("Power Limit For All Blocks default:1000").define("cybercore_power_limit", 1000);

		SpeedLvlTick = client.comment("Speed Upgrade Tick default:15").define("speed_lvl_tick", 15);

		LunarGenPerTick = client.comment("Power Per Tick LunarGen Block default:10").define("lunar_gen_per_tick", 10);

		server.comment("Server Side Configuration").push("common");

		overworldId = server.comment("Overworld dim ID").defineInRange("overworldId", 0, -1000, 1000);

		world_height = server.comment("Height of the world").defineInRange("world_height", 70, 5, 256);

		PowerLmit = server.comment("Power Limit For All Blocks default:1000").define("cybercore_power_limit", 1000);

		SpeedLvlTick = server.comment("Speed Upgrade Tick default:15").define("speed_lvl_tick", 15);

		LunarGenPerTick = server.comment("Power Per Tick LunarGen Block default:80").define("lunar_gen_per_tick", 80);

	}

	public static int getOverworldId() {
		return overworldId.get();
	}
}
