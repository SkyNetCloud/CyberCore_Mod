package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.recipes.recipeclasses.PainterRecipe;
import ca.skynetcloud.cybercore.world.gen.ModFeatures;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
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

		BlockInit.registerBlocks(event);

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "Loaded Blocks");
	}

	@SubscribeEvent
	public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		event.getRegistry().register(new PainterRecipe.Serializer().setRegistryName("painter"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		ItemInit.registerItems(event);
		event.getRegistry().register(ItemInit.cyber_bits);
		event.getRegistry().register(ItemInit.cyber_blend);

		event.getRegistry().register(ItemInit.planter);
		event.getRegistry().register(ItemInit.tiller);

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

		event.getRegistry().register(ItemInit.whrechItem);

		event.getRegistry().register(ItemInit.dark_steel_block);

		event.getRegistry().register(ItemInit.taco_shell);

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

		event.getRegistry().register(TileEntityNames.LUNAR_GEN_MACHINE_TE.setRegistryName("lunarsolargenerator_block"));
		event.getRegistry().register(TileEntityNames.CABLE_PAINTER_TE.setRegistryName("cable_painter"));
		event.getRegistry().register(TileEntityNames.POWER_BOX_TE.setRegistryName("power_storage"));
		event.getRegistry().register(TileEntityNames.POWER_FURNACE_TE.setRegistryName("powered_furnace"));
		event.getRegistry().register(TileEntityNames.IrrigationTile.setRegistryName("block_irrigation"));
		event.getRegistry().register(TileEntityNames.CABLE_TE.setRegistryName(Names.CABLE));

		TileEntityNames.registerTileEntities(event);

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "TileEntityType Loaded");
	}

	@SubscribeEvent
	public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event) {
		// EntitesNameType.registerAll(event);
	}

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(ContainerNames.LUNARGEN_CON.setRegistryName(Names.LUNARGEN_CON));
		event.getRegistry().register(ContainerNames.CABLE_PAINTER_CON.setRegistryName("cable_painter"));
		event.getRegistry().register(ContainerNames.POWER_BOX_CON.setRegistryName(Names.POWER_BOX_CON));
		event.getRegistry().register(ContainerNames.POWER_FURNCAE_CON.setRegistryName(Names.POWERED_FURNACE_CON));

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "ContainerType Loaded");
	}

}