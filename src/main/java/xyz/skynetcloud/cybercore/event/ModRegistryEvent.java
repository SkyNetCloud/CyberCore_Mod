package xyz.skynetcloud.cybercore.event;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.api.Names;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.api.containers.ContainerNames;
import xyz.skynetcloud.cybercore.api.items.ItemNames;
import xyz.skynetcloud.cybercore.api.tileentity.TileEntityNames;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryEvent {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		event.getRegistry().register(BlockInit.LETTUCE_CROP);
		event.getRegistry().register(BlockInit.TOMATO_CROP);
		event.getRegistry().register(BlockInit.CABLE);
		event.getRegistry().register(BlockInit.CYBER_ORE);
		event.getRegistry().register(BlockInit.CYBER_SLAB);
		event.getRegistry().register(BlockInit.CYBER_STAIRS);
		event.getRegistry().register(BlockInit.LUNAR_BLOCK);
		event.getRegistry().register(BlockInit.DimWorldLinkBlock);
		event.getRegistry().register(BlockInit.POWER_BOX);
		event.getRegistry().register(BlockInit.POWER_FURNACE_BLOCK);

		CyberCoreMain.LOGGER.info("Loaded Blocks");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().register(ItemNames.power_furnace_block);
		event.getRegistry().register(ItemNames.cable);
		event.getRegistry().register(ItemNames.cyber_ore);
		event.getRegistry().register(ItemNames.cyber_ingot);
		event.getRegistry().register(ItemNames.power_box);
		event.getRegistry().register(ItemNames.lunar);
		event.getRegistry().register(ItemNames.lettuce_crop);
		event.getRegistry().register(ItemNames.tomato_crop);
		event.getRegistry().register(ItemNames.cheese);
		event.getRegistry().register(ItemNames.tomato);
		event.getRegistry().register(ItemNames.tomato_seed);
		event.getRegistry().register(ItemNames.lettuce);
		event.getRegistry().register(ItemNames.lettuce_seed);
		event.getRegistry().register(ItemNames.cyber_axe);
		event.getRegistry().register(ItemNames.cyber_hoe);
		event.getRegistry().register(ItemNames.cyber_pickaxe);
		event.getRegistry().register(ItemNames.cyber_shovel);
		event.getRegistry().register(ItemNames.cyber_sword);
		event.getRegistry().register(ItemNames.lunar_upgrade_lvl_1);
		event.getRegistry().register(ItemNames.lunar_upgrade_lvl_2);
		event.getRegistry().register(ItemNames.lunar_upgrade_lvl_3);
		event.getRegistry().register(ItemNames.lunar_upgrade_lvl_4);
		event.getRegistry().register(ItemNames.speed_upgrade_lvl_1);
		event.getRegistry().register(ItemNames.speed_upgrade_lvl_2);
		event.getRegistry().register(ItemNames.speed_upgrade_lvl_3);
		event.getRegistry().register(ItemNames.speed_upgrade_lvl_4);

		CyberCoreMain.LOGGER.info("Item Loaded");
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<TileEntityType<?>> event) {

		event.getRegistry().register(TileEntityNames.LUNAR_GEN_MACHINE_TE.setRegistryName("tileentitylunar"));
		event.getRegistry().register(TileEntityNames.CABLE_TE.setRegistryName("tileentitycable"));
		event.getRegistry().register(TileEntityNames.POWER_BOX_TE.setRegistryName("tileentitypowerbox"));
		event.getRegistry().register(TileEntityNames.POWER_FURNACE_TE.setRegistryName("tileentitypoweredfrnace"));
		CyberCoreMain.LOGGER.info("TileEntityType Loaded");
	}

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {

		event.getRegistry().register(ContainerNames.LUNARGEN_CON.setRegistryName(Names.LUNARGEN_CON));
		event.getRegistry().register(ContainerNames.POWER_BOX_CON.setRegistryName(Names.POWER_BOX_CON));
		event.getRegistry().register(ContainerNames.POWER_FURNCAE_CON.setRegistryName(Names.POWERED_FURNACE_CON));
		CyberCoreMain.LOGGER.info("ContainerType Loaded");
	}

}
