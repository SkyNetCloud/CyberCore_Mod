package ca.skynetcloud.cybercore.api.items;

import static ca.skynetcloud.cybercore.CyberCoreMain.MODID;
import static ca.skynetcloud.cybercore.api.Names.CHEESE_NAME;
import static ca.skynetcloud.cybercore.api.Names.CYBER_AXE;
import static ca.skynetcloud.cybercore.api.Names.CYBER_BOOTS_NAME;
import static ca.skynetcloud.cybercore.api.Names.CYBER_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.api.Names.CYBER_HELMET_NAME;
import static ca.skynetcloud.cybercore.api.Names.CYBER_HOE;
import static ca.skynetcloud.cybercore.api.Names.CYBER_INGOT;
import static ca.skynetcloud.cybercore.api.Names.CYBER_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.api.Names.CYBER_PICKAXE;
import static ca.skynetcloud.cybercore.api.Names.CYBER_SHOVEL;
import static ca.skynetcloud.cybercore.api.Names.CYBER_SWORD;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_AXE;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_BOOTS_NAME;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_HELMET_NAME;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_HOE;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_INGOT;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_PICKAXE;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_SHOVEL;
import static ca.skynetcloud.cybercore.api.Names.DARK_STEEL_SWORD;
import static ca.skynetcloud.cybercore.api.Names.LETTUCE_NAME;
import static ca.skynetcloud.cybercore.api.Names.LETTUCE_SEEDS_NAME;
import static ca.skynetcloud.cybercore.api.Names.LUNAR_CARD_1;
import static ca.skynetcloud.cybercore.api.Names.LUNAR_CARD_2;
import static ca.skynetcloud.cybercore.api.Names.LUNAR_CARD_3;
import static ca.skynetcloud.cybercore.api.Names.LUNAR_CARD_4;
import static ca.skynetcloud.cybercore.api.Names.POWER_CARD_1;
import static ca.skynetcloud.cybercore.api.Names.POWER_CARD_2;
import static ca.skynetcloud.cybercore.api.Names.POWER_CARD_3;
import static ca.skynetcloud.cybercore.api.Names.RUBY_AXE;
import static ca.skynetcloud.cybercore.api.Names.RUBY_BOOTS_NAME;
import static ca.skynetcloud.cybercore.api.Names.RUBY_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.api.Names.RUBY_HELMET_NAME;
import static ca.skynetcloud.cybercore.api.Names.RUBY_HOE;
import static ca.skynetcloud.cybercore.api.Names.RUBY_INGOT;
import static ca.skynetcloud.cybercore.api.Names.RUBY_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.api.Names.RUBY_PICKAXE;
import static ca.skynetcloud.cybercore.api.Names.RUBY_SHOVEL;
import static ca.skynetcloud.cybercore.api.Names.RUBY_SWORD;
import static ca.skynetcloud.cybercore.api.Names.SPEED_CARD_1;
import static ca.skynetcloud.cybercore.api.Names.SPEED_CARD_2;
import static ca.skynetcloud.cybercore.api.Names.SPEED_CARD_3;
import static ca.skynetcloud.cybercore.api.Names.SPEED_CARD_4;
import static ca.skynetcloud.cybercore.api.Names.TACO_NAME;
import static ca.skynetcloud.cybercore.api.Names.TOMAO_SEEDS_NAME;
import static ca.skynetcloud.cybercore.api.Names.TOMATO_NAME;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.block.BlockItemCore;
import ca.skynetcloud.cybercore.init.BasisToolMaterial;
import ca.skynetcloud.cybercore.init.CustomArmorMaterial;
import ca.skynetcloud.cybercore.init.SeedsInit;
import ca.skynetcloud.cybercore.item.ArmorItemBase;
import ca.skynetcloud.cybercore.item.CyberCoreCardItem;
import ca.skynetcloud.cybercore.item.UpgradeLvl;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.item.WrenchItem;
import ca.skynetcloud.cybercore.item.tools.CyberCoreAxe;
import ca.skynetcloud.cybercore.item.tools.CyberCoreHoe;
import ca.skynetcloud.cybercore.item.tools.CyberCorePickaxe;
import ca.skynetcloud.cybercore.item.tools.CyberCoreShovel;
import ca.skynetcloud.cybercore.item.tools.CyberCoreSword;
import ca.skynetcloud.cybercore.item.tools.ItemPlanter;
import ca.skynetcloud.cybercore.item.tools.TillerItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {

	public static class FoodInit {
		public static Food taco = (new Food.Builder()).hunger(12).saturation(0.5F).meat().build();
		public static Food cheese = (new Food.Builder()).hunger(12).saturation(0.5F).build();
		public static Food tomato = (new Food.Builder()).hunger(12).saturation(0.5F).build();
		public static Food lettuce = (new Food.Builder()).hunger(12).saturation(0.5F).build();
	}

	@SuppressWarnings("unused")
	private static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[] { EquipmentSlotType.HEAD,
			EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET };

	public static BlockItem CABLE_PAINTER;

	public static Item card = new CyberCoreCardItem().setRegistryName(MODID + ":card");

	public static Item planter = new ItemPlanter(new Properties().group(CyberCoreTab.instance))
			.setRegistryName(MODID + ":planter");

	public static Item tiller = new TillerItem(BasisToolMaterial.cyber_ingot, 1, 0,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON))
					.setRegistryName(MODID + ":tiller");

	public static Item whrechItem = new WrenchItem();

	// public static Item cable = new BlockItemCore(BlockInit.CABLE);

	public static Item taco_shell = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName("taco_shell");

	public static Item cyber_bits = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName("cyber_bits");

	public static Item cyber_blend = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName("cyber_blend");

	public static BlockItem ruby_block = new BlockItemCore(BlockInit.RUBY_BLOCK);

	public static BlockItem color_changer = new BlockItemCore(BlockInit.C_Changer_Block);

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

	public static Item tomato = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.lettuce))
			.setRegistryName(TOMATO_NAME);

	public static Item lettuce = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.lettuce))
			.setRegistryName(LETTUCE_NAME);

	public static Item taco = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.taco))
			.setRegistryName(TACO_NAME);

	public static Item cheese = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.cheese))
			.setRegistryName(CHEESE_NAME);

	// ------------------ Cyber Tools -------------- \\

	public static Item cyber_pickaxe = new CyberCorePickaxe(BasisToolMaterial.cyber_ingot, 3,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_PICKAXE);

	public static Item cyber_axe = new CyberCoreAxe(BasisToolMaterial.cyber_ingot, 5F,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_AXE);

	public static Item cyber_hoe = new CyberCoreHoe(BasisToolMaterial.cyber_ingot, 0,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_HOE);

	public static Item cyber_shovel = new CyberCoreShovel(BasisToolMaterial.cyber_ingot, 0.5F,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_SHOVEL);

	public static Item cyber_sword = new CyberCoreSword(BasisToolMaterial.cyber_ingot, 5,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.RARE)).setRegistryName(CYBER_SWORD);

	// ------------------ Ruby Tools -------------- \\

	public static Item ruby_pickaxe = new CyberCorePickaxe(BasisToolMaterial.ruby_gem, 1,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_PICKAXE);

	public static Item ruby_axe = new CyberCoreAxe(BasisToolMaterial.ruby_gem, 3,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_AXE);

	public static Item ruby_hoe = new CyberCoreHoe(BasisToolMaterial.ruby_gem, 0,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_HOE);

	public static Item ruby_shovel = new CyberCoreShovel(BasisToolMaterial.ruby_gem, 0.5F,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_SHOVEL);

	public static Item ruby_sword = new CyberCoreSword(BasisToolMaterial.ruby_gem, 3,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.COMMON)).setRegistryName(RUBY_SWORD);

	// ------------------ Dark Steel Tools -------------- \\

	public static Item dark_steel_pickaxe = new CyberCorePickaxe(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_PICKAXE);

	public static Item dark_steel_axe = new CyberCoreAxe(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_AXE);

	public static Item dark_steel_hoe = new CyberCoreHoe(BasisToolMaterial.dark_steel_ingot, 0,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_HOE);

	public static Item dark_steel_shovel = new CyberCoreShovel(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_SHOVEL);

	public static Item dark_steel_sword = new CyberCoreSword(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().group(CyberCoreTab.instance).rarity(Rarity.EPIC)).setRegistryName(DARK_STEEL_SWORD);

	// ------------- Other Stuff ------------------- \\

	public static Item cyber_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(CYBER_INGOT);

	public static Item ruby_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(RUBY_INGOT);

	public static Item dark_steel_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(DARK_STEEL_INGOT);

	public static Item power_up_card_1 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 1, ItemType.SOLAR_FOCUS)
					.setRegistryName(LUNAR_CARD_1);

	public static Item power_up_card_2 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 2, ItemType.SOLAR_FOCUS)
					.setRegistryName(LUNAR_CARD_2);

	public static Item power_up_card_3 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 3, ItemType.SOLAR_FOCUS)
					.setRegistryName(LUNAR_CARD_3);

	public static Item power_up_card_4 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 4, ItemType.SOLAR_FOCUS)
					.setRegistryName(LUNAR_CARD_4);

	public static Item speed_upgrade_card_1 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 1,
			ItemType.SPEED_UPGRADE).setRegistryName(SPEED_CARD_1);

	public static Item speed_upgrade_card_2 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 2,
			ItemType.SPEED_UPGRADE).setRegistryName(SPEED_CARD_2);

	public static Item speed_upgrade_card_3 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 3,
			ItemType.SPEED_UPGRADE).setRegistryName(SPEED_CARD_3);

	public static Item speed_upgrade_card_4 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 4,
			ItemType.SPEED_UPGRADE).setRegistryName(SPEED_CARD_4);

	public static Item power_upgrade_card_1 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 1, ItemType.POWER_UPGRADE)
					.setRegistryName(POWER_CARD_1);

	public static Item power_upgrade_card_2 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 2, ItemType.POWER_UPGRADE)
					.setRegistryName(POWER_CARD_2);

	public static Item power_upgrade_card_3 = new UpgradeLvl(
			new Item.Properties().group(CyberCoreTab.instance).maxStackSize(1), 3, ItemType.POWER_UPGRADE)
					.setRegistryName(POWER_CARD_3);

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

	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registerItem(registry, new BlockItem(BlockInit.CABLE, new Item.Properties().group(CyberCoreTab.power_cable)),
				Names.CABLE);

		registerItem(registry,
				new BlockItem(BlockInit.BLOCK_PIPE, new Item.Properties().group(CyberCoreTab.item_cable)),
				Names.BLOCK_PIPE);

		IntStream.range(0, 16).forEach(i -> registerItem(registry,
				new BlockItem(
						ForgeRegistries.BLOCKS.getValue(
								new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Power_Cable_Names[i])),
						new Item.Properties().group(CyberCoreTab.power_cable)),
				Names.COLORED_Power_Cable_Names[i]));

		IntStream.range(0, 16).forEach(i -> registerItem(registry,
				new BlockItem(
						ForgeRegistries.BLOCKS
								.getValue(new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Item_TUBE_NAMES[i])),
						new Item.Properties().group(CyberCoreTab.item_cable)),
				Names.COLORED_Item_TUBE_NAMES[i]));
	}

	private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name) {
		String prefixedName = CyberCoreMain.MODID + ":" + name;
		newItem.setRegistryName(prefixedName);
		registry.register(newItem);
		return newItem;
	}

}
