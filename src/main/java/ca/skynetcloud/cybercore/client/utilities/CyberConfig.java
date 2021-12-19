package ca.skynetcloud.cybercore.client.utilities;

import ca.skynetcloud.cybercore.CyberCore;
import ca.weblite.objc.Proxy;
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

import static net.minecraft.world.item.enchantment.Enchantment.Rarity.RARE;

public class CyberConfig {

    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final Config CONFIG;
    private static final String CONFIG_PREFIX = "gui." + CyberCore.MODID + ".config.";

    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }


    public static void bake() {
        CyberConfig.bake();
    }

    public static class Config {

        //#region FarmingToolConfigInfo
        public static IntValue TILLING_RANGE;
        public static IntValue MOISTURE;
        public static IntValue PLANTER_RANGE;
        //endregion

        //#region PowerConfigInfo
        public static IntValue TICKS_PER_ITEM;
        public static IntValue Energy_PerAction;
        //endregion


        //#region enchantmentConfigInfo
        public static DoubleValue additiveDurabilityDrop;
        public static DoubleValue maximumDurabilityDrop;
        public static DoubleValue minimumDurabilityDrop;
        public static DoubleValue modeDurabilityDrop;
        public static DoubleValue dropLevel;
        public static DoubleValue additiveDropChance;
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
        //#endregion

        //#region OreConfigInfo

        public static IntValue cyberOreVeinSize;
        public static IntValue cyberOrePerChunk;
        public static IntValue cyberOreMinHeight;
        public static IntValue cyberOreMaxHeight;

        public static IntValue rubyOreVeinSize;
        public static IntValue rubyOrePerChunk;
        public static IntValue rubyOreMinHeight;
        public static IntValue rubyOreMaxHeight;


        public static IntValue darksteelOreVeinSize;
        public static IntValue darksteelOrePerChunk;
        public static IntValue darksteelOreMinHeight;
        public static IntValue darksteelOreMaxHeight;

        /*
        public static IntValue deepslate_cyberOreVeinSize;
        public static IntValue deepslate_cyberOrePerChunk;
        public static IntValue deepslate_cyberOreMinHeight;
        public static IntValue deepslate_cyberOreMaxHeight;

        public static IntValue deepslate_rubyOreVeinSize;
        public static IntValue deepslate_rubyOrePerChunk;
        public static IntValue deepslate_rubyOreMinHeight;
        public static IntValue deepslate_rubyOreMaxHeight;


        public static IntValue deepslate_darksteelOreVeinSize;
        public static IntValue deepslate_darksteelOrePerChunk;
        public static IntValue deepslate_darksteelOreMinHeight;
        public static IntValue deepslate_darksteelOreMaxHeight;


        public static BooleanValue deepslate_cyberOreGeneration;
        public static BooleanValue deepslate_rubyOreGeneration;
        public static BooleanValue deepslate_darksteelOreGeneration;
        */

        public static BooleanValue cyberOreGeneration;
        public static BooleanValue rubyOreGeneration;
        public static BooleanValue darksteelOreGeneration;

        //#endregion

        public Config(ForgeConfigSpec.Builder builder) {


            //#region FarmingToolConfigVaules
            builder.push("Ores Settings");

            cyberOreGeneration = builder.comment("Generate Cyber Ore").define("ore_generation.world_generation.cyber", true);
            rubyOreGeneration = builder.comment("Generate Ruby Ore").define("ore_generation.world_generation.ruby", true);
            darksteelOreGeneration = builder.comment("Generate Dark Steel Ore").define("ore_generation.world_generation.darksteel", true);


            cyberOreVeinSize =  builder.comment("Set Max Cyber Ore Vein Size (Default = 4)").defineInRange("ore_generation.cyber.cyberOreVeinSize", 10, 0, 36);
            cyberOrePerChunk = builder.comment("Set Cyber Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.cyber.cyberOreChance",  5, 0, 32);
            cyberOreMinHeight = builder.comment("Set Cyber Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.cyber.cyberOreMinHeight", -25, -65, 120);
            cyberOreMaxHeight = builder.comment("Set Cyber Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.cyber.cyberOreMaxHeight", 15, -65, 125);

            rubyOreVeinSize =  builder.comment("Set Max Ruby Ore Vein Size (Default = 4)").defineInRange("ore_generation.ruby.rubyOreVeinSize", 10, 0, 36);
            rubyOrePerChunk = builder.comment("Set Ruby Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.ruby.rubyOreChance", 5, 0, 32);
            rubyOreMinHeight = builder.comment("Set Ruby Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.ruby.rubyOreMinHeight", -15, -65, 120);
            rubyOreMaxHeight = builder.comment("Set Ruby Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.ruby.rubyOreMaxHeight", 25, -65, 125);

            darksteelOreVeinSize =  builder.comment("Set Max Dark Steel Ore Vein Size (Default = 4)").defineInRange("ore_generation.darksteel.darksteelOreVeinSize", 10, 0, 36);
            darksteelOrePerChunk = builder.comment("Set Dark Steel Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.darksteel.darksteelOreChance", 5, 0, 32);
            darksteelOreMinHeight = builder.comment("Set Dark Steel Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.darksteel.darksteelOreMinHeight", -15, -65, 120);
            darksteelOreMaxHeight = builder.comment("Set Dark Steel Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.darksteel.darksteelOreMaxHeight", 25, -65, 125);
/*

            deepslate_cyberOreGeneration = builder.comment("Generate Deepslate Cyber Ore").define("ore_generation.world_generation.deepslate_cyber", true);
            deepslate_rubyOreGeneration = builder.comment("Generate Deepslate Ruby Ore").define("ore_generation.world_generation.deepslate_ruby", true);
            deepslate_darksteelOreGeneration = builder.comment("Generate Deepslate Dark Steel Ore").define("ore_generation.world_generation.deepslate_darksteel", true);


            deepslate_cyberOreVeinSize =  builder.comment("Set Max Deepslate Cyber Ore Vein Size (Default = 4)").defineInRange("ore_generation.cyber.deepslate_cyberOreVeinSize", 15, 0, 36);
            deepslate_cyberOrePerChunk = builder.comment("Set Deepslate Cyber Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.cyber.deepslate_cyberOreChance",  10, 0, 32);
            deepslate_cyberOreMinHeight = builder.comment("Set Deepslate Cyber Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.cyber.deepslate_cyberOreMinHeight", 25, 0, 120);
            deepslate_cyberOreMaxHeight = builder.comment("Set Deepslate Cyber Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.cyber.deepslate_cyberOreMaxHeight", 55, 0, 125);

            deepslate_rubyOreVeinSize =  builder.comment("Set Max Deepslate Ruby Ore Vein Size (Default = 4)").defineInRange("ore_generation.ruby.deepslate_rubyOreVeinSize", 5, 0, 32);
            deepslate_rubyOrePerChunk = builder.comment("Set Deepslate Ruby Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.ruby.deepslate_rubyOreChance", 2, 0, 32);
            deepslate_rubyOreMinHeight = builder.comment("Set Deepslate Ruby Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.ruby.rdeepslate_ubyOreMinHeight", 25, 0, 120);
            deepslate_rubyOreMaxHeight = builder.comment("Set Deepslate Ruby Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.ruby.deepslate_rubyOreMaxHeight", 55, 0, 125);

            deepslate_darksteelOreVeinSize =  builder.comment("Set Max Deepslate Dark Steel Ore Vein Size (Default = 4)").defineInRange("ore_generation.darksteel.deepslate_darksteelOreVeinSize", 5, 0, 36);
            deepslate_darksteelOrePerChunk = builder.comment("Set Deepslate Dark Steel Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.darksteel.deepslate_darksteelOreChance", 10, 0, 32);
            deepslate_darksteelOreMinHeight = builder.comment("Set Deepslate Dark Steel Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.darksteel.deepslate_darksteelOreMinHeight", 25, 0, 120);
            deepslate_darksteelOreMaxHeight = builder.comment("Set Deepslate Dark Steel Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.darksteel.deepslate_darksteelOreMaxHeight", 55, 0, 125);
*/
            builder.pop();
            //endregion

            //#region FarmingToolConfigVaules
            builder.push("Farming Tools Settings");
            TILLING_RANGE = builder.comment("\r\nRange of cultivator item").translation(CONFIG_PREFIX + "cultivator_range").defineInRange("cultivator.range", 9, 2, 32);
            PLANTER_RANGE = builder.comment("\r\nRange of planter item").translation(CONFIG_PREFIX + "planter_range").defineInRange("planter.range", 9, 2, 32);
            MOISTURE = builder.comment("\r\nMoisture level set by cultivator").translation(CONFIG_PREFIX + "cultivator_moisture").defineInRange("cultivator.moisture", 7, 0, 7);
            builder.pop();
            //endregion

            //#region PowerConfig
            builder.push("Pyro Energy Settings");
            TICKS_PER_ITEM = builder.comment("The number of tick this block block will take").translation(CONFIG_PREFIX + "ticks_per_item").defineInRange("ticks_per_item", 5, 1, 25);
            Energy_PerAction = builder.comment("The Energy that's used peraction").translation(CONFIG_PREFIX + "energy_peraction").defineInRange("energy_peraction", 25, 1, 100);
            builder.pop();
            //endregion

            //#region PlaterConfigValues
            builder.push("Soul Bound Enchantment Values");
            PLANTER_RANGE = builder.comment("Range of planter item").defineInRange("planter.range", 9, 2, 32);
            builder.pop();
            //#endregion

            //#region BookSettings
            builder.push("Soul Bound Enchantment Values");
            rarity = builder.comment("The rarity of the enchantment").translation(CONFIG_PREFIX + "rarity").defineEnum("rarity", Rarity.RARE);
            levels = builder.comment("The number of levels of the enchantment").translation(CONFIG_PREFIX + "levels").defineInRange("levels", 1, 1, 5);
            isTreasure = builder.comment("Whether or not to consider this enchantment as a treasure").translation(CONFIG_PREFIX + "isTreasure").define("isTreasure", false);
            isVillagerTrade = builder.comment("Whether or not this enchantment can be offered by villagers for trade").translation(CONFIG_PREFIX + "isVillagerTrade").define("isVillagerTrade", true);
            isLootable = builder.comment("Whether or not this enchantment can generate in loot").translation(CONFIG_PREFIX + "isLootable").define("isLootable", true);
            canApplyAtEnchantingTable = builder.comment("Whether or not this enchantment can be applied at the enchanting table").translation(CONFIG_PREFIX + "canApplyAtEnchantingTable").define("canApplyAtEnchantingTable", true);
            canApplyOnBooks = builder.comment("Whether or not this enchantment can be applied on books").translation(CONFIG_PREFIX + "canApplyOnBooks").define("canApplyOnBooks", true);
            minEnchantabilityBase = builder.comment("The minimum enchantability requirement for the first enchantment level").translation(CONFIG_PREFIX + "minEnchantabilityBase").defineInRange("minEnchantabilityBase", 15, 1, 100);
            minEnchantabilityPerLevel = builder.comment("The additional enchantability requirement for each enchantment level after the first").translation(CONFIG_PREFIX + "minEnchantabilityPerLevel").defineInRange("minEnchantabilityPerLevel", 5, 1, 100);
            incompatibleEnchantments = builder.comment("List of enchantments that cannot be applied together with this enchantment").translation(CONFIG_PREFIX + "incompatibleEnchantments").defineList("incompatibleEnchantments", new ArrayList<>(), s -> s instanceof String);
            dropLevel = builder.comment("Chance for the enchant to drop down 1 level on death from 0.00 to 1.00.").translation("soulbound.config.drop_level").defineInRange("drop_level", 1.0, 0.0, 1.0);
            additiveDropChance = builder.comment("Chance for enchant to drop down 1 level with every added level\n" + "So if someone with Soulbound 3 dies the chance of the enchant being downgraded would be:\n" + "(dropLevel) - ((level - 1) * additiveDropChance)\n" + "Remember if you've set a lot of levels this could lead to the higher levels never dropping down").translation("soulbound.config.additive_drop_chance").defineInRange("additive_drop_chance", 0.0, 0.0, 1.0);
            builder.pop();
            //#endregion
        }

        public static int getPlantingRange() {
            return PLANTER_RANGE.get();
        }
        public static int getTillingRange() { return TILLING_RANGE.get();}
        public static int getMoisture() {
            return MOISTURE.get();
        }
    }

}
