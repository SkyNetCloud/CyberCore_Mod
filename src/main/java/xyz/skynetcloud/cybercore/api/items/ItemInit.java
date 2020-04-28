package xyz.skynetcloud.cybercore.api.items;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;
import static xyz.skynetcloud.cybercore.api.Names.CHEESE_NAME;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_AXE;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_HOE;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_INGOT;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_PICKAXE;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_SHOVEL;
import static xyz.skynetcloud.cybercore.api.Names.*;
import static xyz.skynetcloud.cybercore.api.Names.DARK_STEEL_AXE;
import static xyz.skynetcloud.cybercore.api.Names.DARK_STEEL_HOE;
import static xyz.skynetcloud.cybercore.api.Names.DARK_STEEL_INGOT;
import static xyz.skynetcloud.cybercore.api.Names.DARK_STEEL_PICKAXE;
import static xyz.skynetcloud.cybercore.api.Names.DARK_STEEL_SHOVEL;
import static xyz.skynetcloud.cybercore.api.Names.DARK_STEEL_SWORD;
import static xyz.skynetcloud.cybercore.api.Names.LETTUCE_NAME;
import static xyz.skynetcloud.cybercore.api.Names.LETTUCE_SEEDS_NAME;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_CARD_1;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_CARD_2;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_CARD_3;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_CARD_4;
import static xyz.skynetcloud.cybercore.api.Names.POWER_CARD_1;
import static xyz.skynetcloud.cybercore.api.Names.POWER_CARD_2;
import static xyz.skynetcloud.cybercore.api.Names.POWER_CARD_3;
import static xyz.skynetcloud.cybercore.api.Names.RUBY_AXE;
import static xyz.skynetcloud.cybercore.api.Names.RUBY_HOE;
import static xyz.skynetcloud.cybercore.api.Names.RUBY_INGOT;
import static xyz.skynetcloud.cybercore.api.Names.RUBY_PICKAXE;
import static xyz.skynetcloud.cybercore.api.Names.RUBY_SHOVEL;
import static xyz.skynetcloud.cybercore.api.Names.RUBY_SWORD;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_CARD_1;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_CARD_2;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_CARD_3;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_CARD_4;
import static xyz.skynetcloud.cybercore.api.Names.TACO_NAME;
import static xyz.skynetcloud.cybercore.api.Names.TOMAO_SEEDS_NAME;
import static xyz.skynetcloud.cybercore.api.Names.TOMATO_NAME;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;

import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item.Properties;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.block.BlockItemCore;
import xyz.skynetcloud.cybercore.init.CustomArmorMaterial;
import xyz.skynetcloud.cybercore.init.SeedsInit;
import xyz.skynetcloud.cybercore.item.ArmorItemBase;
import xyz.skynetcloud.cybercore.init.ToolMaterialsInit;
import xyz.skynetcloud.cybercore.item.CyberCoreCardItem;
import xyz.skynetcloud.cybercore.item.SoulReturn;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;
import xyz.skynetcloud.cybercore.item.WrenchItem;
import xyz.skynetcloud.cybercore.item.tools.CyberCorePickaxe;

public class ItemInit {

	public static class FoodInit {
		public static Food taco = (new Food.Builder()).hunger(12).saturation(0.5F).meat().build();
		public static Food cheese = (new Food.Builder()).hunger(12).saturation(0.5F).build();
		public static Food tomato = (new Food.Builder()).hunger(12).saturation(0.5F).build();
		public static Food lettuce = (new Food.Builder()).hunger(12).saturation(0.5F).build();
	}

	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[] { EquipmentSlotType.HEAD,
			EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET };

	public static Enchantment soulbond = new SoulReturn(ARMOR_SLOTS);

	public static Item card = new CyberCoreCardItem().setRegistryName(MODID + ":card");

	public static Item whrechItem = new WrenchItem();

	public static BlockItem Item_Irrigation = new BlockItemCore(BlockInit.IRRIGATION_BLOCK);

	public static Item block_loader = new BlockItemCore(BlockInit.BLOCK_LOADER);

	public static Item block_extractor = new BlockItemCore(BlockInit.BLOCK_EXTRACTOR);

	public static Item taco_shell = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName("taco_shell");

	public static Item cyber_bits = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName("cyber_bits");

	public static Item cyber_blend = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName("cyber_blend");

	public static BlockItem ruby_block = new BlockItemCore(BlockInit.RUBY_BLOCK);

	public static BlockItem ruby_slabs = new BlockItemCore(BlockInit.RUBY_SLAB);

	public static BlockItem ruby_stairs = new BlockItemCore(BlockInit.RUBY_STAIRS);

	public static BlockItem dark_steel_block = new BlockItemCore(BlockInit.DARK_STEEL_BLOCK);

	public static BlockItem lettuce_crop = new BlockItemCore(BlockInit.LETTUCE_CROP);

	public static BlockItem power_furnace_block = new BlockItemCore(BlockInit.POWER_FURNACE_BLOCK);

	public static BlockItem cyber_ore = new BlockItemCore(BlockInit.CYBER_ORE);

	public static BlockItem dark_steel_ore = new BlockItemCore(BlockInit.DARK_STEEL_ORE);

	public static BlockItem ruby_ore = new BlockItemCore(BlockInit.RUBY_ORE);

	public static BlockItem tomato_crop = new BlockItemCore(BlockInit.TOMATO_CROP);

	public static BlockItem lunar = new BlockItemCore(BlockInit.LUNAR_BLOCK);

	public static BlockItem cyberland_land = new BlockItemCore(BlockInit.CYBERLAND);

	public static BlockItem power_box = new BlockItemCore(BlockInit.POWER_BOX);

	public static BlockItem cable = new BlockItemCore(BlockInit.CABLE);

	public static BlockItem item_cable = new BlockItemCore(BlockInit.ITEM_PIPE);

	public static Item lettuce_seed = new SeedsInit(BlockInit.LETTUCE_CROP).setRegistryName(LETTUCE_SEEDS_NAME);

	public static Item tomato_seed = new SeedsInit(BlockInit.TOMATO_CROP).setRegistryName(TOMAO_SEEDS_NAME);

	public static Item tomato = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.lettuce))
			.setRegistryName(TOMATO_NAME);

	public static Item lettuce = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.lettuce))
			.setRegistryName(LETTUCE_NAME);

	public static Item taco = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.taco))
			.setRegistryName(TACO_NAME);

	public static Item cheese = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.cheese))
			.setRegistryName(CHEESE_NAME);

	public static Item cyber_pickaxe = new CyberCorePickaxe(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_PICKAXE);

	public static Item cyber_axe = new AxeItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_AXE);

	public static Item cyber_hoe = new HoeItem(ToolMaterialsInit.cyber_ingot_materials, 20,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_HOE);

	public static Item cyber_shovel = new ShovelItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_SHOVEL);

	public static Item cyber_sword = new SwordItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_SWORD);

	public static Item ruby_pickaxe = new CyberCorePickaxe(ToolMaterialsInit.ruby_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_PICKAXE);

	public static Item ruby_axe = new AxeItem(ToolMaterialsInit.ruby_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_AXE);

	public static Item ruby_hoe = new HoeItem(ToolMaterialsInit.ruby_ingot_materials, 20,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_HOE);

	public static Item ruby_shovel = new ShovelItem(ToolMaterialsInit.ruby_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_SHOVEL);

	public static Item ruby_sword = new SwordItem(ToolMaterialsInit.ruby_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_SWORD);

	public static Item dark_steel_pickaxe = new CyberCorePickaxe(ToolMaterialsInit.dark_steel_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_PICKAXE);

	public static Item dark_steel_axe = new AxeItem(ToolMaterialsInit.dark_steel_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_AXE);

	public static Item dark_steel_hoe = new HoeItem(ToolMaterialsInit.dark_steel_ingot_materials, 20,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_HOE);

	public static Item dark_steel_shovel = new ShovelItem(ToolMaterialsInit.dark_steel_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_SHOVEL);

	public static Item dark_steel_sword = new SwordItem(ToolMaterialsInit.dark_steel_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_SWORD);

	public static Item cyber_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(CYBER_INGOT);

	public static Item ruby_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(RUBY_INGOT);

	public static Item dark_steel_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(DARK_STEEL_INGOT);

	public static Item lunar_upgrade_card_1 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 1, 0).setRegistryName(LUNAR_CARD_1);

	public static Item lunar_upgrade_card_2 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 2, 0).setRegistryName(LUNAR_CARD_2);

	public static Item lunar_upgrade_card_3 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 3, 0).setRegistryName(LUNAR_CARD_3);

	public static Item lunar_upgrade_card_4 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 4, 0).setRegistryName(LUNAR_CARD_4);

	public static Item speed_upgrade_card_1 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 1, 2)
			.setRegistryName(SPEED_CARD_1);

	public static Item speed_upgrade_card_2 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 2, 2)
			.setRegistryName(SPEED_CARD_2);

	public static Item speed_upgrade_card_3 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 3, 2)
			.setRegistryName(SPEED_CARD_3);

	public static Item speed_upgrade_card_4 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 4, 2)
			.setRegistryName(SPEED_CARD_4);

	public static Item power_upgrade_card_1 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 1, 3).setRegistryName(POWER_CARD_1);

	public static Item power_upgrade_card_2 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 2, 3).setRegistryName(POWER_CARD_2);

	public static Item power_upgrade_card_3 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 3, 3).setRegistryName(POWER_CARD_3);

	public static Item DARK_STEEL_HELMET = new ArmorItemBase(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.HEAD, new Item.Properties().group(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_HELMET_NAME);

	public static Item DARK_STEEL_CHESTPLATE = new ArmorItemBase(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.CHEST, new Item.Properties().group(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_CHESTPLATE_NAME);

	public static Item DARK_STEEL_LEGGINGS = new ArmorItemBase(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.LEGS, new Item.Properties().group(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_LEGGINGS_NAME);

	public static Item DARK_STEEL_BOOTS = new ArmorItemBase(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.FEET, new Item.Properties().group(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_BOOTS_NAME);

	public static Item RUBY_HELMET = new ArmorItemBase(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.HEAD,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(RUBY_HELMET_NAME);

	public static Item RUBY_CHESTPLATE = new ArmorItemBase(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.CHEST,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(RUBY_CHESTPLATE_NAME);

	public static Item RUBY_LEGGINGS = new ArmorItemBase(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.LEGS,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(RUBY_LEGGINGS_NAME);

	public static Item RUBY_BOOTS = new ArmorItemBase(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.FEET,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(RUBY_BOOTS_NAME);

	public static Item CYBER_HELMET = new ArmorItemBase(CustomArmorMaterial.Cyber_Ingot, "cyber",
			EquipmentSlotType.HEAD, new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE))
					.setRegistryName(CYBER_HELMET_NAME);

	public static Item CYBER_CHESTPLATE = new ArmorItemBase(CustomArmorMaterial.Cyber_Ingot, "cyber",
			EquipmentSlotType.CHEST, new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE))
					.setRegistryName(CYBER_CHESTPLATE_NAME);

	public static Item CYBER_LEGGINGS = new ArmorItemBase(CustomArmorMaterial.Cyber_Ingot, "cyber",
			EquipmentSlotType.LEGS, new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE))
					.setRegistryName(CYBER_LEGGINGS_NAME);

	public static Item CYBER_BOOTS = new ArmorItemBase(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlotType.FEET,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_BOOTS_NAME);

}
