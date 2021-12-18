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
    //public static Proxy rubyOrePerChunk;

    static {
        final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }



    public static void bake() {
        Enchantment.bake();
        CyberConfig.bake();
    }

    public static class OreConfig {
        public static int cyberOreVeinSize;
        public static int cyberOrePerChunk;
        public static int cyberOreMinHeight;
        public static int cyberOreMaxHeight;

        public static int rubyOreVeinSize;
        public static int rubyOrePerChunk;
        public static int rubyOreMinHeight;
        public static int rubyOreMaxHeight;


        public static int darksteelOreVeinSize;
        public static int darksteelOrePerChunk;
        public static int darksteelOreMinHeight;
        public static int darksteelOreMaxHeight;

        public static boolean cyberOreGeneration;
        public static boolean rubyOreGeneration;
        public static boolean darksteelOreGeneration;

        public static void bake() {
            cyberOrePerChunk = Config.cyberOrePerChunk.get();
            cyberOreVeinSize = Config.cyberOreVeinSize.get();
            cyberOreMinHeight = Config.cyberOreMinHeight.get();
            cyberOreMinHeight = Config.cyberOreMaxHeight.get();

            rubyOreVeinSize = Config.rubyOreVeinSize.get();
            rubyOrePerChunk = Config.rubyOrePerChunk.get();
            rubyOreMinHeight = Config.rubyOreMinHeight.get();
            rubyOreMaxHeight = Config.rubyOreMaxHeight.get();

            darksteelOreVeinSize = Config.darksteelOreVeinSize.get();
            darksteelOrePerChunk = Config.darksteelOrePerChunk.get();
            darksteelOreMinHeight = Config.darksteelOreMinHeight.get();
            darksteelOreMaxHeight = Config.darksteelOreMaxHeight.get();

            cyberOreGeneration = Config.cyberOreGeneration.get();
            rubyOreGeneration = Config.rubyOreGeneration.get();
            darksteelOreGeneration = Config.darksteelOreGeneration.get();

        }
    }

    public static class Enchantment {
        public static Rarity rarity = RARE;

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

            cyberOreVeinSize =  builder.comment("Set Max Cyber Ore Vein Size (Default = 4)").defineInRange("ore_generation.cyber.cyberOreVeinSize", 4, 0, 36);
            cyberOrePerChunk = builder.comment("Set Cyber Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.cyber.cyberOreChance", 1, 0, 32);
            cyberOreMinHeight = builder.comment("Set Cyber Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.cyber.cyberOreMinHeight", 25, 0, 120);
            cyberOreMaxHeight = builder.comment("Set Cyber Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.cyber.cyberOreMaxHeight", 55, 0, 125);

            rubyOreVeinSize =  builder.comment("Set Max Ruby Ore Vein Size (Default = 4)").defineInRange("ore_generation.ruby.rubyOreVeinSize", 4, 0, 36);
            rubyOrePerChunk = builder.comment("Set Ruby Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.ruby.rubyOreChance", 1, 0, 32);
            rubyOreMinHeight = builder.comment("Set Ruby Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.ruby.rubyOreMinHeight", 25, 0, 120);
            rubyOreMaxHeight = builder.comment("Set Ruby Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.ruby.rubyOreMaxHeight", 55, 0, 125);

            darksteelOreVeinSize =  builder.comment("Set Max Dark Steel Ore Vein Size (Default = 4)").defineInRange("ore_generation.darksteel.darksteelOreVeinSize", 4, 0, 36);
            darksteelOrePerChunk = builder.comment("Set Dark Steel Ore Spawn Chance (Default = 2)").defineInRange("ore_generation.darksteel.darksteelOreChance", 1, 0, 32);
            darksteelOreMinHeight = builder.comment("Set Dark Steel Ore Min Spawn Height (Default = 25)").defineInRange("ore_generation.darksteel.darksteelOreMinHeight", 25, 0, 120);
            darksteelOreMaxHeight = builder.comment("Set Dark Steel Ore Max Spawn Height (Default = 55)").defineInRange("ore_generation.darksteel.darksteelOreMaxHeight", 55, 0, 125);
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
