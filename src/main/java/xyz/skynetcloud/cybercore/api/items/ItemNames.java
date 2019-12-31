package xyz.skynetcloud.cybercore.api.items;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.init.ToolMaterialsInit;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;
import xyz.skynetcloud.cybercore.item.tools.CyberCorePickaxe;

public class ItemNames {

	public static Item cyber_pickaxe = new CyberCorePickaxe(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance));

	public static Item cyber_axe = new AxeItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance));

	public static Item cyber_hoe = new HoeItem(ToolMaterialsInit.cyber_ingot_materials, 20,
			new Item.Properties().group(CyberCoreTab.instance));

	public static Item cyber_shovel = new ShovelItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance));

	public static Item cyber_sword = new SwordItem(ToolMaterialsInit.cyber_ingot_materials, 10, 10,
			new Item.Properties().group(CyberCoreTab.instance));

	public static Item cyber_ingot = new Item(new Item.Properties().group(CyberCoreTab.instance));

	public static UpgradeLvl lunar_upgrade_lvl_1 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 1,
			3);

	public static UpgradeLvl lunar_upgrade_lvl_2 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 2,
			3);

	public static UpgradeLvl lunar_upgrade_lvl_3 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 3,
			3);

	public static UpgradeLvl lunar_upgrade_lvl_4 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 4,
			3);

	public static UpgradeLvl speed_upgrade_lvl_1 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 1,
			3);

	public static UpgradeLvl speed_upgrade_lvl_2 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 2,
			3);

	public static UpgradeLvl speed_upgrade_lvl_3 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 3,
			3);

	public static UpgradeLvl speed_upgrade_lvl_4 = new UpgradeLvl(new Item.Properties().group(CyberCoreTab.instance), 4,
			3);

}
