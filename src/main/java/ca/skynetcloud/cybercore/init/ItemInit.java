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
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class ItemInit {

	public static class FoodInit {
		public static FoodProperties taco = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).meat()
				.build();
		public static FoodProperties cheese = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
		public static FoodProperties tomato = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
		public static FoodProperties lettuce = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
	}

	@SuppressWarnings("unused")
	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST,
			EquipmentSlot.LEGS, EquipmentSlot.FEET };

	public static BlockItem CABLE_PAINTER;

	public static Enchantment Soul_Bound = new EnchantmentSoulbound();


	public static Item planter = new ItemPlanter(new Properties().tab(CyberCoreTab.instance))
			.setRegistryName(MODID + ":planter");

	public static Item whrechItem = new WrenchItem();

	// public static Item cable = new BlockItemCore(BlockInit.CABLE);

	public static Item taco_shell = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("taco_shell");

	public static Item cyber_bits = new Item(new Item.Properties().tab(CyberCoreTab.instance))
			.setRegistryName("cyber_bits");

	public static Item cyber_blend = new Item(new Item.Properties().tab(CyberCoreTab.instance))
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

	public static Item tomato = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.lettuce))
			.setRegistryName(TOMATO_NAME);

	public static Item lettuce = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.lettuce))
			.setRegistryName(LETTUCE_NAME);

	public static Item taco = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.taco))
			.setRegistryName(TACO_NAME);

	public static Item cheese = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.cheese))
			.setRegistryName(CHEESE_NAME);

	// ------------------ Cyber Tools -------------- \\

	public static Item cyber_pickaxe = new CyberPickaxe(BasisToolMaterial.cyber_ingot, 3,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_PICKAXE);

	public static Item cyber_axe = new CyberAxe(BasisToolMaterial.cyber_ingot, 5F,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_AXE);

	public static Item cyber_hoe = new CyberHoe(BasisToolMaterial.cyber_ingot, 0,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_HOE);

	public static Item cyber_shovel = new CyberShovel(BasisToolMaterial.cyber_ingot, 0.5F,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_SHOVEL);

	public static Item cyber_sword = new CyberSword(BasisToolMaterial.cyber_ingot, 5,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_SWORD);

	// ------------------ Ruby Tools -------------- \\

	public static Item ruby_pickaxe = new RubyPickaxe(BasisToolMaterial.ruby_gem, 1,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_PICKAXE);

	public static Item ruby_axe = new RubyAxe(BasisToolMaterial.ruby_gem, 3,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_AXE);

	public static Item ruby_hoe = new RubyHoe(BasisToolMaterial.ruby_gem, 0,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_HOE);

	public static Item ruby_shovel = new RubyShovel(BasisToolMaterial.ruby_gem, 0.5F,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_SHOVEL);

	public static Item ruby_sword = new RubySword(BasisToolMaterial.ruby_gem, 3,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_SWORD);

	// ------------------ Dark Steel Tools -------------- \\

	public static Item dark_steel_pickaxe = new DarkSteelPickaxe(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_PICKAXE);

	public static Item dark_steel_axe = new DarkSteelAxe(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_AXE);

	public static Item dark_steel_hoe = new DarkSteelHoe(BasisToolMaterial.dark_steel_ingot, 0,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_HOE);

	public static Item dark_steel_shovel = new DarkSteelShovel(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_SHOVEL);

	public static Item dark_steel_sword = new DarkSteelSword(BasisToolMaterial.dark_steel_ingot, 1,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_SWORD);

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
			EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_HELMET_NAME);

	public static Item DARK_STEEL_CHESTPLATE = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_CHESTPLATE_NAME);

	public static Item DARK_STEEL_LEGGINGS = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_LEGGINGS_NAME);

	public static Item DARK_STEEL_BOOTS = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
			EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.instance))
					.setRegistryName(DARK_STEEL_BOOTS_NAME);

	public static Item RUBY_HELMET = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.HEAD,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_HELMET_NAME);

	public static Item RUBY_CHESTPLATE = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.CHEST,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_CHESTPLATE_NAME);

	public static Item RUBY_LEGGINGS = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.LEGS,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_LEGGINGS_NAME);

	public static Item RUBY_BOOTS = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.FEET,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_BOOTS_NAME);

	public static Item CYBER_HELMET = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.HEAD,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_HELMET_NAME);

	public static Item CYBER_CHESTPLATE = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.CHEST,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_CHESTPLATE_NAME);

	public static Item CYBER_LEGGINGS = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.LEGS,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_LEGGINGS_NAME);

	public static Item CYBER_BOOTS = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.FEET,
			new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_BOOTS_NAME);

	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();

		registerItem(registry, new BlockItem(BlockInit.CABLE, new Item.Properties().tab(CyberCoreTab.power_cable)),
				Names.CABLE);

		registerItem(registry, new BlockItem(BlockInit.BLOCK_PIPE, new Item.Properties().tab(CyberCoreTab.item_cable)),
				Names.BLOCK_PIPE);

		registerItem(registry, new BlockItem(BlockInit.Fence_Block, new Item.Properties().tab(CyberCoreTab.other)),
				Names.Fence_Block);

		registerItem(registry, new BlockItem(BlockInit.Fence_Block_Top, new Item.Properties().tab(CyberCoreTab.other)),
				Names.Fence_Block_Top);

		registerItem(registry, new BlockItem(BlockInit.Fence_Gate_Block, new Item.Properties().tab(CyberCoreTab.other)),
				Names.Fence_Gate_Block);

		registerItem(registry, new BlockItem(BlockInit.Slab_Block, new Item.Properties().tab(CyberCoreTab.instance)),
				Names.Slab_Block);

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

		IntStream.range(0, 16)
				.forEach(i -> registerItem(registry,
						new BlockItem(
								ForgeRegistries.BLOCKS.getValue(
										new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Fence_Block_Names[i])),
								new Item.Properties().tab(CyberCoreTab.other)),
						Names.COLORED_Fence_Block_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerItem(registry, new BlockItem(
						ForgeRegistries.BLOCKS.getValue(
								new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Fence_Top_Block_Names[i])),
						new Item.Properties().tab(CyberCoreTab.other)), Names.COLORED_Fence_Top_Block_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerItem(registry,
						new BlockItem(
								ForgeRegistries.BLOCKS.getValue(
										new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Slab_Block_Names[i])),
								new Item.Properties().tab(CyberCoreTab.instance)),
						Names.COLORED_Slab_Block_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerItem(registry, new BlockItem(
						ForgeRegistries.BLOCKS.getValue(
								new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Fence_Gate_Block_Names[i])),
						new Item.Properties().tab(CyberCoreTab.other)), Names.COLORED_Fence_Gate_Block_Names[i]));

		event.getRegistry().register(ItemInit.power_cube);
		event.getRegistry().register(ItemInit.color_changer);
		event.getRegistry().register(ItemInit.whrechItem);
		event.getRegistry().register(ItemInit.ruby_block);
		event.getRegistry().register(ItemInit.dark_steel_block);
		event.getRegistry().register(ItemInit.power_furnace_block);
		event.getRegistry().register(ItemInit.taco_shell);
		event.getRegistry().register(ItemInit.cyber_ore);
		event.getRegistry().register(ItemInit.dark_steel_ore);
		event.getRegistry().register(ItemInit.ruby_ore);
		event.getRegistry().register(ItemInit.cyber_ingot);
		event.getRegistry().register(ItemInit.cyber_blend);
		event.getRegistry().register(ItemInit.cyber_bits);
		event.getRegistry().register(ItemInit.dark_steel_ingot);
		event.getRegistry().register(ItemInit.ruby_ingot);
		event.getRegistry().register(ItemInit.planter);
		event.getRegistry().register(ItemInit.cheese);
		event.getRegistry().register(ItemInit.tomato);
		event.getRegistry().register(ItemInit.taco);
		event.getRegistry().register(ItemInit.tomato_seed);
		event.getRegistry().register(ItemInit.lettuce);
		event.getRegistry().register(ItemInit.lettuce_seed);
		event.getRegistry().register(ItemInit.cyber_axe);
		event.getRegistry().register(ItemInit.cyber_hoe);
		event.getRegistry().register(ItemInit.cyber_pickaxe);
		event.getRegistry().register(ItemInit.cyber_shovel);
		event.getRegistry().register(ItemInit.cyber_sword);
		event.getRegistry().register(ItemInit.ruby_axe);
		event.getRegistry().register(ItemInit.ruby_hoe);
		event.getRegistry().register(ItemInit.ruby_pickaxe);
		event.getRegistry().register(ItemInit.ruby_shovel);
		event.getRegistry().register(ItemInit.ruby_sword);
		event.getRegistry().register(ItemInit.dark_steel_axe);
		event.getRegistry().register(ItemInit.dark_steel_hoe);
		event.getRegistry().register(ItemInit.dark_steel_pickaxe);
		event.getRegistry().register(ItemInit.dark_steel_shovel);
		event.getRegistry().register(ItemInit.dark_steel_sword);
		event.getRegistry().register(ItemInit.speed_upgrade_card_1);
		event.getRegistry().register(ItemInit.speed_upgrade_card_2);
		event.getRegistry().register(ItemInit.speed_upgrade_card_3);
		event.getRegistry().register(ItemInit.speed_upgrade_card_4);
		event.getRegistry().register(ItemInit.power_upgrade_card_1);
		event.getRegistry().register(ItemInit.power_upgrade_card_2);
		event.getRegistry().register(ItemInit.power_upgrade_card_3);

		event.getRegistry().register(ItemInit.DARK_STEEL_HELMET);
		event.getRegistry().register(ItemInit.DARK_STEEL_CHESTPLATE);
		event.getRegistry().register(ItemInit.DARK_STEEL_LEGGINGS);
		event.getRegistry().register(ItemInit.DARK_STEEL_BOOTS);

		event.getRegistry().register(ItemInit.RUBY_HELMET);
		event.getRegistry().register(ItemInit.RUBY_CHESTPLATE);
		event.getRegistry().register(ItemInit.RUBY_LEGGINGS);
		event.getRegistry().register(ItemInit.RUBY_BOOTS);

		event.getRegistry().register(ItemInit.CYBER_HELMET);
		event.getRegistry().register(ItemInit.CYBER_CHESTPLATE);
		event.getRegistry().register(ItemInit.CYBER_LEGGINGS);
		event.getRegistry().register(ItemInit.CYBER_BOOTS);
	}

	private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name) {
		String prefixedName = CyberCoreMain.MODID + ":" + name;
		newItem.setRegistryName(prefixedName);
		registry.register(newItem);
		return newItem;
	}

}
