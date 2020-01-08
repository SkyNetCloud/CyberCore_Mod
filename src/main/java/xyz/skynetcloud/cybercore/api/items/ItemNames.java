package xyz.skynetcloud.cybercore.api.items;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;

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

	public static class ItemNamesInit {
		public static final String SPEED_LVL_1 = (MODID + ":speed_lvl_1");
		public static final String SPEED_LVL_2 = (MODID + ":speed_lvl_2");
		public static final String SPEED_LVL_3 = (MODID + ":speed_lvl_3");
		public static final String SPEED_LVL_4 = (MODID + ":speed_lvl_4");
		public static final String LUNAR_LVL_1 = (MODID + ":lunar_lvl_1");
		public static final String LUNAR_LVL_2 = (MODID + ":lunar_lvl_2");
		public static final String LUNAR_LVL_3 = (MODID + ":lunar_lvl_3");
		public static final String LUNAR_LVL_4 = (MODID + ":lunar_lvl_4");
		public static final String CYBER_INGOT = (MODID + ":cyber_ingot");
		public static final String CYBER_AXE = (MODID + ":cyber_axe");
		public static final String CYBER_PICKAXE = (MODID + ":cyber_pickaxe");
		public static final String CYBER_SHOVEL = (MODID + ":cyber_shovel");
		public static final String CYBER_SWORD = (MODID + ":cyber_sword");
		public static final String CYBER_HOE = (MODID + ":cyber_hoe");
	}

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

	public static final Item WRENCH = new Item(new Item.Properties().group(CyberCoreTab.instance));

}
