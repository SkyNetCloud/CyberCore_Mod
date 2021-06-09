package ca.skynetcloud.cybercore.init;

import static ca.skynetcloud.cybercore.CyberCoreMain.MODID;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CHEESE_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_AXE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_BOOTS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_HELMET_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_HOE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_INGOT;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_PICKAXE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_SHOVEL;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.CYBER_SWORD;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_AXE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_BOOTS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_HELMET_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_HOE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_INGOT;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_PICKAXE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_SHOVEL;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.DARK_STEEL_SWORD;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.LETTUCE_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.LETTUCE_SEEDS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.POWER_CARD_1;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.POWER_CARD_2;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.POWER_CARD_3;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_AXE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_BOOTS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_HELMET_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_HOE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_INGOT;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_PICKAXE;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_SHOVEL;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.RUBY_SWORD;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.SPEED_CARD_1;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.SPEED_CARD_2;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.SPEED_CARD_3;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.SPEED_CARD_4;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.TACO_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.TOMAO_SEEDS_NAME;
import static ca.skynetcloud.cybercore.util.networking.helper.Names.TOMATO_NAME;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.block.BlockItemCore;
import ca.skynetcloud.cybercore.init.material.BasisToolMaterial;
import ca.skynetcloud.cybercore.init.material.CustomArmorMaterial;
import ca.skynetcloud.cybercore.item.CyberCoreCard;
import ca.skynetcloud.cybercore.item.UpgradeLvl;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.item.WrenchItem;
import ca.skynetcloud.cybercore.item.armor.CyberArmor;
import ca.skynetcloud.cybercore.item.armor.DarkSteelArmor;
import ca.skynetcloud.cybercore.item.armor.RubyArmor;
import ca.skynetcloud.cybercore.item.enchantment.EnchantmentSoulbound;
import ca.skynetcloud.cybercore.item.tools.CyberAxe;
import ca.skynetcloud.cybercore.item.tools.CyberHoe;
import ca.skynetcloud.cybercore.item.tools.CyberPickaxe;
import ca.skynetcloud.cybercore.item.tools.CyberShovel;
import ca.skynetcloud.cybercore.item.tools.CyberSword;
import ca.skynetcloud.cybercore.item.tools.DarkSteelAxe;
import ca.skynetcloud.cybercore.item.tools.DarkSteelHoe;
import ca.skynetcloud.cybercore.item.tools.DarkSteelPickaxe;
import ca.skynetcloud.cybercore.item.tools.DarkSteelShovel;
import ca.skynetcloud.cybercore.item.tools.DarkSteelSword;
import ca.skynetcloud.cybercore.item.tools.ItemPlanter;
import ca.skynetcloud.cybercore.item.tools.RubyAxe;
import ca.skynetcloud.cybercore.item.tools.RubyHoe;
import ca.skynetcloud.cybercore.item.tools.RubyPickaxe;
import ca.skynetcloud.cybercore.item.tools.RubyShovel;
import ca.skynetcloud.cybercore.item.tools.RubySword;
import ca.skynetcloud.cybercore.item.tools.TillerItem;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {

	public static class FoodInit {
		public static Food taco = (new Food.Builder()).nutrition(12).saturationMod(0.5F).meat().build();
		public static Food cheese = (new Food.Builder()).nutrition(12).saturationMod(0.5F).build();
		public static Food tomato = (new Food.Builder()).nutrition(12).saturationMod(0.5F).build();
		public static Food lettuce = (new Food.Builder()).nutrition(12).saturationMod(0.5F).build();
	}

	@SuppressWarnings("unused")
	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[] { EquipmentSlotType.HEAD,
			EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET };

	public static BlockItem CABLE_PAINTER;

	public static Enchantment Soul_Bound = new EnchantmentSoulbound();

	public static BlockItem BlockExpItem = new BlockItemCore(BlockInit.BlockExp);

	public static Item planter = new ItemPlanter(new Properties().tab(CyberCoreTab.instance))
			.setRegistryName(MODID + ":planter");

	public static Item CyberCoreCard = new CyberCoreCard(new Properties().tab(CyberCoreTab.instance))
			.setRegistryName(MODID + ":cyber_core_card");

	public static Item tiller = new TillerItem(BasisToolMaterial.cyber_ingot, 1, 0,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(MODID + ":tiller");

	public static Item whrechItem = new WrenchItem();

	public static Item solarPanelPart = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("solor_panel_part");

	public static Item dark_steel_gear = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("dark_steel_gear");

	public static Item cyber_item_gear = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("cyber_ingot.gear");

	public static Item ruby_gem_gear = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("ruby_gem_gear");

	// public static Item cable = new BlockItemCore(BlockInit.CABLE);

	public static Item taco_shell = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("taco_shell");

	public static Item cyber_bits = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("cyber_bits");

	public static Item cyber_blend = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("cyber_blend");

	public static BlockItem ruby_block = new BlockItemCore(BlockInit.RUBY_BLOCK);

	public static BlockItem convter_item_block = new BlockItemCore(BlockInit.ItemConvterBlock);

	public static BlockItem power_cube = new BlockItemCore(BlockInit.Battery);

	public static BlockItem dark_steel_block = new BlockItemCore(BlockInit.DARK_STEEL_BLOCK);

	public static BlockItem lettuce_crop = new BlockItemCore(BlockInit.LETTUCE_CROP);

	public static BlockItem power_furnace_block = new BlockItemCore(BlockInit.POWER_FURNACE_BLOCK);

	public static BlockItem cyber_ore = new BlockItemCore(BlockInit.CYBER_ORE);

	public static BlockItem dark_steel_ore = new BlockItemCore(BlockInit.DARK_STEEL_ORE);

	public static BlockItem ruby_ore = new BlockItemCore(BlockInit.RUBY_ORE);

	public static BlockItem tomato_crop = new BlockItemCore(BlockInit.TOMATO_CROP);

	public static Item lettuce_seed = new SeedsInit(BlockInit.LETTUCE_CROP).setRegistryName(LETTUCE_SEEDS_NAME);

	public static Item tomato_seed = new SeedsInit(BlockInit.TOMATO_CROP).setRegistryName(TOMAO_SEEDS_NAME);

	// --------------- Foood ----------- \\

	public static Item tomato = new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodInit.lettuce))
			.setRegistryName(TOMATO_NAME);

	public static Item lettuce = new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodInit.lettuce))
			.setRegistryName(LETTUCE_NAME);

	public static Item taco = new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodInit.taco))
			.setRegistryName(TACO_NAME);

	public static Item cheese = new Item(new Item.Properties().tab(ItemGroup.TAB_FOOD).food(FoodInit.cheese))
			.setRegistryName(CHEESE_NAME);

	// ------------------ Cyber Tools -------------- \\

	public static Item cyber_pickaxe = new CyberPickaxe(BasisToolMaterial.cyber_ingot, 3,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_PICKAXE);

	public static Item cyber_axe = new CyberAxe(BasisToolMaterial.cyber_ingot, 5F,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_AXE);

	public static Item cyber_hoe = new CyberHoe(BasisToolMaterial.cyber_ingot, 0,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_HOE);

	public static Item cyber_shovel = new CyberShovel(BasisToolMaterial.cyber_ingot, 0.5F,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_SHOVEL);

	public static Item cyber_sword = new CyberSword(BasisToolMaterial.cyber_ingot, 5,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_SWORD);

	// ------------------ Ruby Tools -------------- \\

	public static Item ruby_pickaxe = new RubyPickaxe(BasisToolMaterial.ruby_gem, 1,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_PICKAXE);

	public static Item ruby_axe = new RubyAxe(BasisToolMaterial.ruby_gem, 3,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_AXE);

	public static Item ruby_hoe = new RubyHoe(BasisToolMaterial.ruby_gem, 0,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_HOE);

	public static Item ruby_shovel = new RubyShovel(BasisToolMaterial.ruby_gem, 0.5F,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_SHOVEL);

	public static Item ruby_sword = new RubySword(BasisToolMaterial.ruby_gem, 3,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_SWORD);

	// ------------------ Dark Steel Tools -------------- \\

	public static Item dark_steel_pickaxe = new DarkSteelPickaxe(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_PICKAXE);

	public static Item dark_steel_axe = new DarkSteelAxe(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_AXE);

	public static Item dark_steel_hoe = new DarkSteelHoe(BasisToolMaterial.dark_steel_ingot, 0,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_HOE);

	public static Item dark_steel_shovel = new DarkSteelShovel(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_SHOVEL);

	public static Item dark_steel_sword = new DarkSteelSword(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_SWORD);

	// ------------- Other Stuff ------------------- \\

	public static Item cyber_ingot = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName(CYBER_INGOT);

	public static Item ruby_ingot = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName(RUBY_INGOT);

	public static Item dark_steel_ingot = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName(DARK_STEEL_INGOT);

	public static Item speed_upgrade_card_1 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 1, ItemType.SPEED_UPGRADE)
					.setRegistryName(SPEED_CARD_1);

	public static Item speed_upgrade_card_2 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(2), 2, ItemType.SPEED_UPGRADE)
					.setRegistryName(SPEED_CARD_2);

	public static Item speed_upgrade_card_3 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(3), 3, ItemType.SPEED_UPGRADE)
					.setRegistryName(SPEED_CARD_3);

	public static Item speed_upgrade_card_4 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(4), 4, ItemType.SPEED_UPGRADE)
					.setRegistryName(SPEED_CARD_4);

	public static Item power_upgrade_card_1 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 1, ItemType.POWER_UPGRADE)
					.setRegistryName(POWER_CARD_1);

	public static Item power_upgrade_card_2 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 2, ItemType.POWER_UPGRADE)
					.setRegistryName(POWER_CARD_2);

	public static Item power_upgrade_card_3 = new UpgradeLvl(
			new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 3, ItemType.POWER_UPGRADE)
					.setRegistryName(POWER_CARD_3);

	public static Item DARK_STEEL_HELMET = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.HEAD, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_HELMET_NAME);

	public static Item DARK_STEEL_CHESTPLATE = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.CHEST, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_CHESTPLATE_NAME);

	public static Item DARK_STEEL_LEGGINGS = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.LEGS, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_LEGGINGS_NAME);

	public static Item DARK_STEEL_BOOTS = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlotType.FEET, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_BOOTS_NAME);

	public static Item RUBY_HELMET = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.HEAD,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_HELMET_NAME);

	public static Item RUBY_CHESTPLATE = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.CHEST,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_CHESTPLATE_NAME);

	public static Item RUBY_LEGGINGS = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.LEGS,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_LEGGINGS_NAME);

	public static Item RUBY_BOOTS = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlotType.FEET,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_BOOTS_NAME);

	public static Item CYBER_HELMET = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlotType.HEAD,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_HELMET_NAME);

	public static Item CYBER_CHESTPLATE = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber",
			EquipmentSlotType.CHEST, new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE))
					.setRegistryName(CYBER_CHESTPLATE_NAME);

	public static Item CYBER_LEGGINGS = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlotType.LEGS,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_LEGGINGS_NAME);

	public static Item CYBER_BOOTS = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlotType.FEET,
			new Item.Properties().tab(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_BOOTS_NAME);

	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registerItem(registry, new BlockItem(BlockInit.CABLE, new Item.Properties().tab(CyberCoreTab.power_cable)),
				Names.CABLE);

		registerItem(registry, new BlockItem(BlockInit.BLOCK_PIPE, new Item.Properties().tab(CyberCoreTab.item_cable)),
				Names.BLOCK_PIPE);

		IntStream.range(0, 16).forEach(i -> registerItem(registry,
				new BlockItem(
						ForgeRegistries.BLOCKS.getValue(
								new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Power_Cable_Names[i])),
						new Item.Properties().tab(CyberCoreTab.power_cable)),
				Names.COLORED_Power_Cable_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerItem(registry,
						new BlockItem(
								ForgeRegistries.BLOCKS.getValue(
										new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Item_TUBE_NAMES[i])),
								new Item.Properties().tab(CyberCoreTab.item_cable)),
						Names.COLORED_Item_TUBE_NAMES[i]));

		event.getRegistry().registerAll(ItemInit.CyberCoreCard, ItemInit.power_cube, ItemInit.convter_item_block,
				ItemInit.whrechItem, ItemInit.ruby_block, ItemInit.dark_steel_block, ItemInit.power_furnace_block,
				ItemInit.taco_shell, ItemInit.cyber_ore, ItemInit.dark_steel_ore, ItemInit.ruby_ore,
				ItemInit.cyber_ingot, ItemInit.cyber_blend, ItemInit.cyber_bits, ItemInit.dark_steel_ingot,
				ItemInit.ruby_ingot, ItemInit.tiller, ItemInit.planter, ItemInit.cheese, ItemInit.tomato, ItemInit.taco,
				ItemInit.tomato_seed, ItemInit.BlockExpItem, ItemInit.lettuce, ItemInit.lettuce_seed,
				ItemInit.cyber_axe, ItemInit.cyber_hoe, ItemInit.cyber_pickaxe, ItemInit.cyber_shovel,
				ItemInit.cyber_sword, ItemInit.ruby_axe, ItemInit.ruby_hoe, ItemInit.ruby_pickaxe, ItemInit.ruby_shovel,
				ItemInit.ruby_sword, ItemInit.dark_steel_axe, ItemInit.dark_steel_hoe, ItemInit.dark_steel_pickaxe,
				ItemInit.dark_steel_shovel, ItemInit.dark_steel_sword, ItemInit.speed_upgrade_card_1,
				ItemInit.speed_upgrade_card_2, ItemInit.speed_upgrade_card_3, ItemInit.speed_upgrade_card_4,
				ItemInit.power_upgrade_card_1, ItemInit.power_upgrade_card_2, ItemInit.power_upgrade_card_3,
				ItemInit.DARK_STEEL_HELMET, ItemInit.DARK_STEEL_CHESTPLATE, ItemInit.DARK_STEEL_LEGGINGS,
				ItemInit.DARK_STEEL_BOOTS, ItemInit.RUBY_HELMET, ItemInit.RUBY_CHESTPLATE, ItemInit.RUBY_LEGGINGS,
				ItemInit.RUBY_BOOTS, ItemInit.CYBER_HELMET, ItemInit.CYBER_CHESTPLATE, ItemInit.CYBER_LEGGINGS,
				ItemInit.CYBER_BOOTS, ItemInit.solarPanelPart, ItemInit.cyber_item_gear, ItemInit.dark_steel_gear,
				ItemInit.ruby_gem_gear);
	}

	private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name) {
		String prefixedName = CyberCoreMain.MODID + ":" + name;
		newItem.setRegistryName(prefixedName);
		registry.register(newItem);
		return newItem;
	}

}
