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

		event.getRegistry().registerAll(
		BlockInit.LETTUCE_CROP,
		BlockInit.TOMATO_CROP, 
		BlockInit.DARK_STEEL_BLOCK,
		BlockInit.DARK_STEEL_ORE,
		BlockInit.RUBY_BLOCK,
		BlockInit.RUBY_ORE,
		BlockInit.CABLE,
		BlockInit.CYBER_ORE, 
		BlockInit.CYBER_SLAB, 
		BlockInit.CYBER_STAIRS, 
		BlockInit.LUNAR_BLOCK,
		BlockInit.DimWorldLinkBlock, 
		BlockInit.POWER_BOX, 
		BlockInit.POWER_FURNACE_BLOCK);

		CyberCoreMain.LOGGER.info("Loaded Blocks");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().registerAll(ItemNames.power_furnace_block,
		ItemNames.dimworldlink_item,
		ItemNames.cable,
		ItemNames.ruby_ingot,
		ItemNames.ruby_ore,
		ItemNames.ruby_axe,
		ItemNames.ruby_hoe,
		ItemNames.ruby_pickaxe,
		ItemNames.ruby_shovel,
		ItemNames.ruby_sword,
		ItemNames.dark_steel_axe,
		ItemNames.dark_steel_hoe,
		ItemNames.dark_steel_shovel,
		ItemNames.dark_steel_sword,
		ItemNames.dark_steel_ingot,
		ItemNames.dark_steel_ore,
		ItemNames.cyber_ore,
		ItemNames.cyber_ingot,
		ItemNames.power_box,
		ItemNames.lunar,
		ItemNames.lettuce_crop,
		ItemNames.tomato_crop,
		ItemNames.cheese,
		ItemNames.tomato,
		ItemNames.tomato_seed,
		ItemNames.lettuce,
		ItemNames.lettuce_seed,
		ItemNames.cyber_axe,
		ItemNames.cyber_hoe,
		ItemNames.cyber_pickaxe,
		ItemNames.cyber_shovel,
		ItemNames.cyber_sword,
		ItemNames.lunar_upgrade_lvl_1,
		ItemNames.lunar_upgrade_lvl_2,
		ItemNames.lunar_upgrade_lvl_3,
		ItemNames.lunar_upgrade_lvl_4,
		ItemNames.speed_upgrade_lvl_1,
		ItemNames.speed_upgrade_lvl_2,
		ItemNames.speed_upgrade_lvl_3,
		ItemNames.speed_upgrade_lvl_4,
		ItemNames.power_upgrade_lvl_1,
		ItemNames.power_upgrade_lvl_2,
		ItemNames.power_upgrade_lvl_3);

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
