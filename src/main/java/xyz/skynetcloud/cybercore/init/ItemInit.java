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
import xyz.skynetcloud.cybercore.api.items.ItemNames.ItemNamesInit;
import xyz.skynetcloud.cybercore.item.UpgradeLvl;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemInit {

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		cyber_axe = registerItem(cyber_axe, ItemNamesInit.CYBER_AXE);

		cyber_pickaxe = registerItem(cyber_pickaxe, ItemNamesInit.CYBER_PICKAXE);

		cyber_shovel = registerItem(cyber_shovel, ItemNamesInit.CYBER_SHOVEL);

		cyber_sword = registerItem(cyber_sword, ItemNamesInit.CYBER_SWORD);

		cyber_hoe = registerItem(cyber_hoe, ItemNamesInit.CYBER_HOE);

		lunar_upgrade_lvl_1 = registerItem(lunar_upgrade_lvl_1, ItemNamesInit.LUNAR_LVL_1);

		lunar_upgrade_lvl_2 = registerItem(lunar_upgrade_lvl_2, ItemNamesInit.LUNAR_LVL_2);

		lunar_upgrade_lvl_3 = registerItem(lunar_upgrade_lvl_3, ItemNamesInit.LUNAR_LVL_3);

		lunar_upgrade_lvl_4 = registerItem(lunar_upgrade_lvl_4, ItemNamesInit.LUNAR_LVL_4);

		speed_upgrade_lvl_1 = registerItem(speed_upgrade_lvl_1, ItemNamesInit.SPEED_LVL_1);

		speed_upgrade_lvl_2 = registerItem(speed_upgrade_lvl_2, ItemNamesInit.SPEED_LVL_2);

		speed_upgrade_lvl_3 = registerItem(speed_upgrade_lvl_3, ItemNamesInit.SPEED_LVL_3);

		speed_upgrade_lvl_4 = registerItem(speed_upgrade_lvl_4, ItemNamesInit.SPEED_LVL_4);

		cyber_ingot = registerItem(cyber_ingot, ItemNamesInit.CYBER_INGOT);

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
