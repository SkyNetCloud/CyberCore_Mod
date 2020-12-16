package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryEvent {

	@SubscribeEvent
	public void onBiomeLoading(final BiomeLoadingEvent biome) {
		if (biome.getCategory() == Biome.Category.NETHER || biome.getCategory() == Biome.Category.THEEND)
			return;

		biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
				.add(() -> ModFeatures.CYBER_ORE_CONFIG);

		biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
				.add(() -> ModFeatures.DARK_STEEL_ORE_CONFIG);

		biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
				.add(() -> ModFeatures.RUBY_ORE_CONFIG);
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		;
		event.getRegistry().register(BlockInit.LETTUCE_CROP);
		event.getRegistry().register(BlockInit.TOMATO_CROP);
		event.getRegistry().register(BlockInit.CYBER_ORE);
		event.getRegistry().register(BlockInit.RUBY_ORE);
		event.getRegistry().register(BlockInit.DARK_STEEL_ORE);
		event.getRegistry().register(BlockInit.RUBY_BLOCK);
		event.getRegistry().register(BlockInit.DARK_STEEL_BLOCK);
		event.getRegistry().register(BlockInit.POWER_FURNACE_BLOCK);

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "Loaded Blocks");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		event.getRegistry().register(ItemInit.whrechItem);
		event.getRegistry().register(ItemInit.ruby_block);
		event.getRegistry().register(ItemInit.dark_steel_block);
		event.getRegistry().register(ItemInit.power_furnace_block);
		event.getRegistry().register(ItemInit.taco_shell);
		event.getRegistry().register(ItemInit.cyber_ore);
		event.getRegistry().register(ItemInit.dark_steel_ore);
		event.getRegistry().register(ItemInit.ruby_ore);
		event.getRegistry().register(ItemInit.cyber_ingot);
		event.getRegistry().register(ItemInit.dark_steel_ingot);
		event.getRegistry().register(ItemInit.ruby_ingot);
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

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "Item Loaded");
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<TileEntityType<?>> event) {

		event.getRegistry().register(TileEntityNames.POWER_FURNACE_TE.setRegistryName(Names.POWER_FURNACE_BLOCK));

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "TileEntityType Loaded");
	}

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(ContainerNames.POWER_FURNCAE_CON.setRegistryName(Names.POWERED_FURNACE_CON));

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "ContainerType Loaded");
	}

}