package xyz.skynetcloud.cybercore.api.items;

import static xyz.skynetcloud.cybercore.api.Names.CYBER_AXE;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_HOE;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_INGOT;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_PICKAXE;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_SHOVEL;
import static xyz.skynetcloud.cybercore.api.Names.CYBER_SWORD;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_LVL_1;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_LVL_2;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_LVL_3;
import static xyz.skynetcloud.cybercore.api.Names.LUNAR_LVL_4;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_LVL_1;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_LVL_2;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_LVL_3;
import static xyz.skynetcloud.cybercore.api.Names.SPEED_LVL_4;
import static xyz.skynetcloud.cybercore.api.Names.cheese_name;
import static xyz.skynetcloud.cybercore.api.Names.lettuce_name;
import static xyz.skynetcloud.cybercore.api.Names.lettuce_seed_name;
import static xyz.skynetcloud.cybercore.api.Names.taco_name;
import static xyz.skynetcloud.cybercore.api.Names.tomato_name;
import static xyz.skynetcloud.cybercore.api.Names.tomato_seed_name;

import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.block.BlockItemCore;
import xyz.skynetcloud.cybercore.init.SeedsInit;
import xyz.skynetcloud.cybercore.init.ToolMaterialsInit;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;
import xyz.skynetcloud.cybercore.item.tools.CyberCorePickaxe;

public class ItemNames {
	
	public static class FoodInit {
		public static Food taco = (new Food.Builder()).hunger(12).saturation(0.5F).meat().build();
		public static Food cheese = (new Food.Builder()).hunger(12).saturation(0.5F).build();
		public static Food tomato = (new Food.Builder()).hunger(12).saturation(0.5F).build();
		public static Food lettuce = (new Food.Builder()).hunger(12).saturation(0.5F).build();
	}

	public static BlockItem lettuce_crop = new BlockItemCore(BlockInit.LETTUCE_CROP);
	
	public static BlockItem power_furnace_block = new BlockItemCore(BlockInit.POWER_FURNACE_BLOCK);

	public static BlockItem cyber_ore = new BlockItemCore(BlockInit.CYBER_ORE);

	public static BlockItem tomato_crop = new BlockItemCore(BlockInit.TOMATO_CROP);

	public static BlockItem lunar = new BlockItemCore(BlockInit.LUNAR_BLOCK);

	public static BlockItem dimworldlink_item = new BlockItemCore(BlockInit.DimWorldLinkBlock);

	public static BlockItem power_box = new BlockItemCore(BlockInit.POWER_BOX);

	public static BlockItem cable = new BlockItemCore(BlockInit.CABLE);

	public static Item lettuce_seed = new SeedsInit(BlockInit.LETTUCE_CROP).setRegistryName(lettuce_seed_name);

	public static Item tomato_seed = new SeedsInit(BlockInit.TOMATO_CROP).setRegistryName(tomato_seed_name);

	public static Item tomato = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.lettuce))
			.setRegistryName(tomato_name);

	public static Item lettuce = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.lettuce))
			.setRegistryName(lettuce_name);

	public static Item taco = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.taco))
			.setRegistryName(taco_name);

	public static Item cheese = new Item(new Item.Properties().group(ItemGroup.FOOD).food(FoodInit.cheese))
			.setRegistryName(cheese_name);

	public static Item cyber_pickaxe = new CyberCorePickaxe(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_PICKAXE);

	public static Item cyber_axe = new AxeItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_AXE);

	public static Item cyber_hoe = new HoeItem(ToolMaterialsInit.cyber_ingot_materials, 20,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_HOE);

	public static Item cyber_shovel = new ShovelItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_SHOVEL);

	public static Item cyber_sword = new SwordItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance)).setRegistryName(CYBER_SWORD);

	public static Item cyber_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance))
			.setRegistryName(CYBER_INGOT);

	public static Item lunar_upgrade_lvl_1 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 1, 0)
			.setRegistryName(LUNAR_LVL_1);

	public static Item lunar_upgrade_lvl_2 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 2, 0)
			.setRegistryName(LUNAR_LVL_2);

	public static Item lunar_upgrade_lvl_3 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 3, 0)
			.setRegistryName(LUNAR_LVL_3);

	public static Item lunar_upgrade_lvl_4 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 4, 0)
			.setRegistryName(LUNAR_LVL_4);

	public static Item speed_upgrade_lvl_1 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 1, 2)
			.setRegistryName(SPEED_LVL_1);

	public static Item speed_upgrade_lvl_2 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 2, 2)
			.setRegistryName(SPEED_LVL_2);

	public static Item speed_upgrade_lvl_3 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 3, 2)
			.setRegistryName(SPEED_LVL_3);

	public static Item speed_upgrade_lvl_4 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 4, 2)
			.setRegistryName(SPEED_LVL_4);

}
