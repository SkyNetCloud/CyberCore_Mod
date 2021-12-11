package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import ca.skynetcloud.cybercore.common.items.armor.CyberArmor;
import ca.skynetcloud.cybercore.common.items.armor.material.ArmorMaterialWrapper;
import ca.skynetcloud.cybercore.common.items.enchantment.EnchantmentSoulbound;
import ca.skynetcloud.cybercore.common.items.tools.*;
import ca.skynetcloud.cybercore.common.items.tools.material.ToolMaterialWrapper;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CyberCore.MODID);
    public static final DeferredRegister<Enchantment> Enchantments = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CyberCore.MODID);

    public static RegistryObject<Enchantment> SOUL_BOUND = Enchantments.register("soul_bound_book", () -> new EnchantmentSoulbound());

    public static RegistryObject<Item> CYBER_HELMET = ITEMS.register("cyber_armor_head", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_CHESTPLATE = ITEMS.register("cyber_armor_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_LEGGINGS = ITEMS.register("cyber_armor_leg", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> CYBER_BOOTS= ITEMS.register("cyber_armor_feet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> RUBY_HELMET = ITEMS.register("ruby_armor_head", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_CHESTPLATE = ITEMS.register("ruby_armor_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_LEGGINGS = ITEMS.register("ruby_armor_leg", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_BOOTS= ITEMS.register("ruby_armor_feet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.MAIN)));

    public static RegistryObject<Item> DARK_STEEL_HELMET = ITEMS.register("dark_steel_armor_head", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_CHESTPLATE = ITEMS.register("dark_steel_armor_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_LEGGINGS = ITEMS.register("dark_steel_armor_leg", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_BOOTS= ITEMS.register("dark_steel_armor_feet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.MAIN)));

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
    public static RegistryObject<Item> DARK_STEEL_PICKAXE = ITEMS.register("dark_steel_pickaxe", () -> new DarkSteelPickaxe(ToolMaterialWrapper.dark_steel_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_SHOVEL = ITEMS.register("dark_steel_shovel", () -> new DarkSteelShovel(ToolMaterialWrapper.dark_steel_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_SWORD = ITEMS.register("dark_steel_sword", () -> new DarkSteelSword(ToolMaterialWrapper.dark_steel_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_HOE = ITEMS.register("dark_steel_hoe", () -> new ca.skynetcloud.cybercore.common.item.tools.DarkSteelHoe(ToolMaterialWrapper.dark_steel_ingot, 3,
            new Item.Properties().tab(CyberCoreTab.MAIN)));


    public static RegistryObject<Item> TACO = ITEMS.register("taco", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.TACO)));
    public static RegistryObject<Item> CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.CHEESE)));
    public static RegistryObject<Item> TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.TOMATO)));
    public static RegistryObject<Item> LETTUCE = ITEMS.register("lettuce", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN).food(FoodInit.LETTUCE)));


    public static RegistryObject<Item> CYBER_INGOT = ITEMS.register("cyber_ingot", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> DARK_STEEL_INGOT= ITEMS.register("dark_steel_ingot", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN)));
    public static RegistryObject<Item> RUBY_GEM= ITEMS.register("ruby_gem", () -> new Item(new Item.Properties().tab(CyberCoreTab.MAIN)));


}
