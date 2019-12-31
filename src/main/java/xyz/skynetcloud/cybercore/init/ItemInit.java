package xyz.skynetcloud.cybercore.init;

import static xyz.skynetcloud.cybercore.api.items.ItemNames.cyber_axe;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.cyber_hoe;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.cyber_ingot;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.cyber_pickaxe;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.cyber_shovel;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.cyber_sword;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.lunar_upgrade_lvl_1;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.lunar_upgrade_lvl_2;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.lunar_upgrade_lvl_3;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.lunar_upgrade_lvl_4;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.speed_upgrade_lvl_1;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.speed_upgrade_lvl_2;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.speed_upgrade_lvl_3;
import static xyz.skynetcloud.cybercore.api.items.ItemNames.speed_upgrade_lvl_4;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		cyber_axe = registerItem(cyber_axe, "cyber_axe");

		cyber_pickaxe = registerItem(cyber_pickaxe, "cyber_pickaxe");

		cyber_shovel = registerItem(cyber_shovel, "cyber_shovel");

		cyber_sword = registerItem(cyber_sword, "cyber_sword");

		cyber_hoe = registerItem(cyber_hoe, "cyber_hoe");

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
