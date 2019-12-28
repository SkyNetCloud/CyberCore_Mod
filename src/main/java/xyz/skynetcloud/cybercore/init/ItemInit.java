package xyz.skynetcloud.cybercore.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.item.ItemBaseCore;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;

public class ItemInit {

	public static List<ItemBaseCore> ITEMS = new ArrayList<ItemBaseCore>();

	public static ItemBaseCore CYBER_INGOT = new ItemBaseCore("cyber_ingot",
			new Item.Properties().group(CyberCoreTab.instance)),
			LUNAGEN_LEVEL_1 = new UpgradeLvl("lunagen_lvl_1", new Item.Properties().group(CyberCoreTab.instance), 1, 3),
			LUNAGEN_LEVEL_2 = new UpgradeLvl("lunagen_lvl_2", new Item.Properties().group(CyberCoreTab.instance), 2, 3),
			LUNAGEN_LEVEL_3 = new UpgradeLvl("lunagen_lvl_3", new Item.Properties().group(CyberCoreTab.instance), 3, 3),
			LUNAGEN_LEVEL_4 = new UpgradeLvl("lunagen_lvl_4", new Item.Properties().group(CyberCoreTab.instance), 4, 3),
			POWERSPEEDCARD_LEVEL_1 = new UpgradeLvl("speed_lvl_1", new Item.Properties().group(CyberCoreTab.instance), 1,
					3),
			POWERSPEEDCARD_LEVEL_2 = new UpgradeLvl("speed_lvl_2", new Item.Properties().group(CyberCoreTab.instance), 2,
					3),
			POWERSPEEDCARD_LEVEL_3 = new UpgradeLvl("speed_lvl_3", new Item.Properties().group(CyberCoreTab.instance), 3,
					3),
			POWERSPEEDCARD_LEVEL_4 = new UpgradeLvl("speed_lvl_4", new Item.Properties().group(CyberCoreTab.instance), 4,
					3);

	public static void register(IForgeRegistry<Item> registry) {
		for (ItemBaseCore item : ITEMS) {
			registry.register(item);
		}
	}

}
