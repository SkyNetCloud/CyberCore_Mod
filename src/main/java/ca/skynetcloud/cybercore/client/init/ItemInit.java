package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import ca.skynetcloud.cybercore.common.blocks.NewOreBlock;
import ca.skynetcloud.cybercore.common.blocks.crops.LettuceCrop;
import ca.skynetcloud.cybercore.common.blocks.crops.TomatoCrop;
import ca.skynetcloud.cybercore.common.blocks.tech.PowerCube;
import ca.skynetcloud.cybercore.common.blocks.tech.TechBaseBlockFacing;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.ItemCable;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.PowerCable;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PoweredFurnaceBlockEntity;
import ca.skynetcloud.cybercore.common.items.ItemTier;
import ca.skynetcloud.cybercore.common.items.armor.CyberArmor;
import ca.skynetcloud.cybercore.common.items.armor.material.ArmorMaterialWrapper;
import ca.skynetcloud.cybercore.common.items.enchantment.EnchantmentSoulbound;
import ca.skynetcloud.cybercore.common.items.tools.*;
import ca.skynetcloud.cybercore.common.items.tools.farming.PlaterTool;
import ca.skynetcloud.cybercore.common.items.tools.farming.TillerTool;
import ca.skynetcloud.cybercore.common.items.tools.material.ToolMaterialWrapper;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CyberCore.MODID);
    public static final DeferredRegister<Enchantment> Enchantments = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CyberCore.MODID);

    public static RegistryObject<Enchantment> SOUL_BOUND = Enchantments.register("soul_bound_book", () -> new EnchantmentSoulbound());

    public static RegistryObject<Item> POWERED_FURNACE = ITEMS.register("powered_furnace", () -> new BlockItem(BlockInit.POWERED_FURNACE.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> SOLAR_GEN = ITEMS.register("powered_furnace", () -> new BlockItem(BlockInit.POWERED_FURNACE.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> POWER_CUBE = ITEMS.register("power_cube", () -> new BlockItem(BlockInit.POWER_CUBE.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));

    public static RegistryObject<Item> CYBER_ORE_BLOCK = ITEMS.register("cyber_ore", () -> new BlockItem(BlockInit.CYBER_ORE_BLOCK.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> DARK_STEEL_ORE_BLOCK = ITEMS.register("dark_steel_ore", () -> new BlockItem(BlockInit.DARK_STEEL_ORE_BLOCK.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> RUBY_ORE_BLOCK = ITEMS.register("ruby_ore", () -> new BlockItem(BlockInit.RUBY_ORE_BLOCK.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));;

    public static RegistryObject<Item> DEEPSLATE_CYBER_ORE_BLOCK = ITEMS.register("deepslate_cyber_ore", () -> new BlockItem(BlockInit.DEEPSLATE_CYBER_ORE_BLOCK.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> DEEPSLATE_DARK_STEEL_ORE_BLOCK = ITEMS.register("deepslate_dark_steel_ore", () -> new BlockItem(BlockInit.DEEPSLATE_DARK_STEEL_ORE_BLOCK.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> DEEPSLATE_RUBY_ORE_BLOCK = ITEMS.register("deepslate_ruby_ore", () -> new BlockItem(BlockInit.DEEPSLATE_RUBY_ORE_BLOCK.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));;


    public static RegistryObject<Item> POWER_CABLE = ITEMS.register("power_cable_block", () -> new BlockItem(BlockInit.POWER_CABLE.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));
    public static RegistryObject<Item> ITEM_CABLE = ITEMS.register("item_cable_block", () -> new BlockItem(BlockInit.ITEM_CABLE.get(), new Item.Properties().tab(CyberCoreTab.BLOCKS)));


    public static RegistryObject<Item> TOMATO_SEED = ITEMS.register("tomato_seed", () -> new SeedInit(BlockInit.TOMATO_CROP.get()));
    public static RegistryObject<Item> LETTUCE_SEED = ITEMS.register("lettuce_seed", () -> new SeedInit(BlockInit.LETTUCE_CROP.get()));

    public static RegistryObject<Item> TILLER_TOOL = ITEMS.register("tiller_tool", () -> new TillerTool(Tiers.GOLD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> PLATER_TOOL = ITEMS.register("plater_tool", () -> new PlaterTool(new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> CYBER_HELMET = ITEMS.register("cyber_helmet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_CHESTPLATE = ITEMS.register("cyber_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_LEGGINGS = ITEMS.register("cyber_leggings", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_BOOTS= ITEMS.register("cyber_boots", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_BOOTS= ITEMS.register("ruby_boots", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> DARK_STEEL_HELMET = ITEMS.register("dark_steel_helmet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_CHESTPLATE = ITEMS.register("dark_steel_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_LEGGINGS = ITEMS.register("dark_steel_leggings", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_BOOTS= ITEMS.register("dark_steel_boots", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> CYBER_AXE = ITEMS.register("cyber_axe", () -> new CyberAxe(ToolMaterialWrapper.cyber_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_PICKAXE = ITEMS.register("cyber_pickaxe", () -> new CyberPickaxe(ToolMaterialWrapper.cyber_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_SHOVEL = ITEMS.register("cyber_shovel", () -> new CyberShovel(ToolMaterialWrapper.cyber_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_SWORD = ITEMS.register("cyber_sword", () -> new CyberSword(ToolMaterialWrapper.cyber_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_HOE = ITEMS.register("cyber_hoe", () -> new CyberHoe(ToolMaterialWrapper.cyber_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> RUBY_AXE = ITEMS.register("ruby_axe", () -> new RubyAxe(ToolMaterialWrapper.ruby_gem, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new RubyPickaxe(ToolMaterialWrapper.ruby_gem, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new RubyShovel(ToolMaterialWrapper.ruby_gem, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new RubySword(ToolMaterialWrapper.ruby_gem, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new RubyHoe(ToolMaterialWrapper.ruby_gem, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> DARK_STEEL_AXE = ITEMS.register("dark_steel_axe", () -> new DarkSteelAxe(ToolMaterialWrapper.dark_steel_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_PICKAXE = ITEMS.register("dark_steel_pickaxe", () -> new DarkSteelPickaxe(ToolMaterialWrapper.dark_steel_ingot, 3, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_SHOVEL = ITEMS.register("dark_steel_shovel", () -> new DarkSteelShovel(ToolMaterialWrapper.dark_steel_ingot, 3, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_SWORD = ITEMS.register("dark_steel_sword", () -> new DarkSteelSword(ToolMaterialWrapper.dark_steel_ingot, 3, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_HOE = ITEMS.register("dark_steel_hoe", () -> new DarkSteelHoe(ToolMaterialWrapper.dark_steel_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> SOLARLENS_TIER_1 = ITEMS.register("solarlens_1", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 1, ItemTier.ItemType.SOLAR_LENS));
    public static RegistryObject<Item> SOLARLENS_TIER_2 = ITEMS.register("solarlens_2", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 2, ItemTier.ItemType.SOLAR_LENS));
    public static RegistryObject<Item> SOLARLENS_TIER_3 = ITEMS.register("solarlens_3", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 3, ItemTier.ItemType.SOLAR_LENS));
    public static RegistryObject<Item> SOLARLENS_TIER_4 = ITEMS.register("solarlens_4", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 4, ItemTier.ItemType.SOLAR_LENS));

    public static RegistryObject<Item> SPEED_UP_TIER_1 = ITEMS.register("speed_up_1", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 1, ItemTier.ItemType.SPEED_UP));
    public static RegistryObject<Item> SPEED_UP_TIER_2 = ITEMS.register("speed_up_2", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 2, ItemTier.ItemType.SPEED_UP));
    public static RegistryObject<Item> SPEED_UP_TIER_3 = ITEMS.register("speed_up_3", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 3, ItemTier.ItemType.SPEED_UP));
    public static RegistryObject<Item> SPEED_UP_TIER_4 = ITEMS.register("speed_up_4", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 4, ItemTier.ItemType.SPEED_UP));

    public static RegistryObject<Item> POWER_STORAGE_UP_TIER_1 = ITEMS.register("power_storage_up_1", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 1, ItemTier.ItemType.POWER_STORAGE_UP));
    public static RegistryObject<Item> POWER_STORAGE_UP_TIER_2 = ITEMS.register("power_storage_up_2", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 2, ItemTier.ItemType.POWER_STORAGE_UP));
    public static RegistryObject<Item> POWER_STORAGE_UP_TIER_3 = ITEMS.register("power_storage_up_3", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 3, ItemTier.ItemType.POWER_STORAGE_UP));
    public static RegistryObject<Item> POWER_STORAGE_UP_TIER_4 = ITEMS.register("power_storage_up_4", () -> new ItemTier(new Item.Properties().tab(CyberCoreTab.MAIN), 4, ItemTier.ItemType.POWER_STORAGE_UP));


    public static RegistryObject<Item> TACO = ITEMS.register("taco", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.TACO)));
    public static RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.CHEESE)));
    public static RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.TOMATO)));
    public static RegistryObject<Item> LETTUCE = ITEMS.register("lettuce", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.LETTUCE)));


    public static RegistryObject<Item> CYBER_INGOT = ITEMS.register("cyber_ingot", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_INGOT= ITEMS.register("dark_steel_ingot", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_GEM= ITEMS.register("ruby_gem", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN)));


}
