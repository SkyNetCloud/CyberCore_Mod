package xyz.skynetcloud.cybercore.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

	public static Item cyber_ingot;

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

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		lunar_upgrade_lvl_1 = registerItem(lunar_upgrade_lvl_1, "lunar_upgrade_lvl_1");

		lunar_upgrade_lvl_2 = registerItem(lunar_upgrade_lvl_2, "lunar_upgrade_lvl_2");

		lunar_upgrade_lvl_3 = registerItem(lunar_upgrade_lvl_3, "lunar_upgrade_lvl_3");

		lunar_upgrade_lvl_4 = registerItem(lunar_upgrade_lvl_4, "lunar_upgrade_lvl_4");

		speed_upgrade_lvl_1 = registerItem(speed_upgrade_lvl_1, "speed_upgrade_lvl_1");

		speed_upgrade_lvl_2 = registerItem(speed_upgrade_lvl_2, "speed_upgrade_lvl_2");

		speed_upgrade_lvl_3 = registerItem(speed_upgrade_lvl_3, "speed_upgrade_lvl_3");

		speed_upgrade_lvl_4 = registerItem(speed_upgrade_lvl_4, "speed_upgrade_lvl_4");

		cyber_ingot = registerItem(cyber_ingot, "cyber_ingot");

		CyberCoreMain.LOGGER.info("Items Loaded");

	}

	public static Item registerItem(Item item, String name) {
		item.setRegistryName(name);
		ForgeRegistries.ITEMS.register(item);
		return item;
	}

	public static UpgradeLvl registerItem(UpgradeLvl item, String name) {
		item.setRegistryName(name);
		ForgeRegistries.ITEMS.register(item);
		return item;
	}

}
