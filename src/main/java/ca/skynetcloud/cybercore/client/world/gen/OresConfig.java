package ca.skynetcloud.cybercore.client.world.gen;

import net.minecraftforge.common.ForgeConfigSpec;

public class OresConfig {

    public static ForgeConfigSpec.IntValue OVERWORLD_VEINSIZE;
    public static ForgeConfigSpec.IntValue OVERWORLD_AMOUNT;


    public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
        OVERWORLD_VEINSIZE = COMMON_BUILDER
                .comment("Veinsize of our ore in the overworld dimension")
                .defineInRange("overworldVeinsize", 5, 1, Integer.MAX_VALUE);
    }

}
