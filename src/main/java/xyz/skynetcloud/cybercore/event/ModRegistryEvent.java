package xyz.skynetcloud.cybercore.event;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
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
import xyz.skynetcloud.cybercore.api.items.ItemInit;
import xyz.skynetcloud.cybercore.api.tileentity.TileEntityNames;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryEvent {

	@SubscribeEvent
	public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().register(ItemInit.soulbond.setRegistryName("soulbond"));

	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		event.getRegistry().register(BlockInit.BLOCK_LOADER);
		event.getRegistry().register(BlockInit.BLOCK_EXTRACTOR);
		event.getRegistry().register(BlockInit.LETTUCE_CROP);
		event.getRegistry().register(BlockInit.CYBERLAND);
		event.getRegistry().register(BlockInit.ITEM_PIPE);
		event.getRegistry().register(BlockInit.TOMATO_CROP);
		event.getRegistry().register(BlockInit.CABLE);
		event.getRegistry().register(BlockInit.CYBER_ORE);
		event.getRegistry().register(BlockInit.RUBY_ORE);
		event.getRegistry().register(BlockInit.DARK_STEEL_ORE);
		event.getRegistry().register(BlockInit.RUBY_BLOCK);
		event.getRegistry().register(BlockInit.DARK_STEEL_BLOCK);
		event.getRegistry().register(BlockInit.RUBY_SLAB);
		event.getRegistry().register(BlockInit.RUBY_STAIRS);
		event.getRegistry().register(BlockInit.LUNAR_BLOCK);
		event.getRegistry().register(BlockInit.POWER_BOX);
		event.getRegistry().register(BlockInit.POWER_FURNACE_BLOCK);

		CyberCoreMain.LOGGER.info("Loaded Blocks");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		
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
		
		event.getRegistry().register(ItemInit.card);
		event.getRegistry().register(ItemInit.block_loader);
		event.getRegistry().register(ItemInit.whrechItem);
		event.getRegistry().register(ItemInit.block_extractor);
		event.getRegistry().register(ItemInit.ruby_block);
		event.getRegistry().register(ItemInit.cyberland_land);
		event.getRegistry().register(ItemInit.dark_steel_block);
		event.getRegistry().register(ItemInit.power_furnace_block);
		event.getRegistry().register(ItemInit.taco_shell);
		event.getRegistry().register(ItemInit.item_cable);
		event.getRegistry().register(ItemInit.cable);
		event.getRegistry().register(ItemInit.cyber_ore);
		event.getRegistry().register(ItemInit.dark_steel_ore);
		event.getRegistry().register(ItemInit.ruby_ore);
		event.getRegistry().register(ItemInit.cyber_ingot);
		event.getRegistry().register(ItemInit.ruby_slabs);
		event.getRegistry().register(ItemInit.ruby_stairs);
		event.getRegistry().register(ItemInit.dark_steel_ingot);
		event.getRegistry().register(ItemInit.ruby_ingot);
		event.getRegistry().register(ItemInit.power_box);
		event.getRegistry().register(ItemInit.lunar);
		event.getRegistry().register(ItemInit.lettuce_crop);
		event.getRegistry().register(ItemInit.tomato_crop);
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
		event.getRegistry().register(ItemInit.lunar_upgrade_card_1);
		event.getRegistry().register(ItemInit.lunar_upgrade_card_2);
		event.getRegistry().register(ItemInit.lunar_upgrade_card_3);
		event.getRegistry().register(ItemInit.lunar_upgrade_card_4);
		event.getRegistry().register(ItemInit.speed_upgrade_card_1);
		event.getRegistry().register(ItemInit.speed_upgrade_card_2);
		event.getRegistry().register(ItemInit.speed_upgrade_card_3);
		event.getRegistry().register(ItemInit.speed_upgrade_card_4);
		event.getRegistry().register(ItemInit.power_upgrade_card_1);
		event.getRegistry().register(ItemInit.power_upgrade_card_2);
		event.getRegistry().register(ItemInit.power_upgrade_card_3);

		CyberCoreMain.LOGGER.info("Item Loaded");
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<TileEntityType<?>> event) {

		event.getRegistry().register(TileEntityNames.LUNAR_GEN_MACHINE_TE.setRegistryName("tileentitylunar"));
		event.getRegistry().register(TileEntityNames.CABLE_TE.setRegistryName("tileentitycable"));
		event.getRegistry().register(TileEntityNames.POWER_BOX_TE.setRegistryName("tileentitypowerbox"));
		event.getRegistry().register(TileEntityNames.POWER_FURNACE_TE.setRegistryName("tileentitypoweredfrnace"));
		event.getRegistry().register(TileEntityNames.TE_TYPE_ITEM_PIPE.setRegistryName("itempipe_te"));

		CyberCoreMain.LOGGER.info("TileEntityType Loaded");
	}

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
		// EntitesNameType.registerAll(event);
	}

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(ContainerNames.LUNARGEN_CON.setRegistryName(Names.LUNARGEN_CON));
		event.getRegistry().register(ContainerNames.POWER_BOX_CON.setRegistryName(Names.POWER_BOX_CON));
		event.getRegistry().register(ContainerNames.POWER_FURNCAE_CON.setRegistryName(Names.POWERED_FURNACE_CON));

		CyberCoreMain.LOGGER.info("ContainerType Loaded");
	}

}