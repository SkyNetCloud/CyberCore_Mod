package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.ContainerInit;
import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.init.TileEntityInit;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeSerializers;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryEvent {

	@SubscribeEvent
	public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().register(ItemInit.Soul_Bound);
		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "Loaded Enchantment");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		;
		event.getRegistry().register(BlockInit.LETTUCE_CROP);
		event.getRegistry().register(BlockInit.BlockExp);
		event.getRegistry().register(BlockInit.TOMATO_CROP);
		event.getRegistry().register(BlockInit.CYBER_ORE);
		event.getRegistry().register(BlockInit.RUBY_ORE);
		event.getRegistry().register(BlockInit.DARK_STEEL_ORE);
		event.getRegistry().register(BlockInit.RUBY_BLOCK);
		event.getRegistry().register(BlockInit.DARK_STEEL_BLOCK);
		event.getRegistry().register(BlockInit.POWER_FURNACE_BLOCK);
		event.getRegistry().register(BlockInit.Battery);
		event.getRegistry().register(BlockInit.C_Changer_Block);
		BlockInit.registerBlocks(event);

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "Blocks Loaded");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		ItemInit.registerItems(event);

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "Item Loaded");
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<TileEntityType<?>> event) {

		event.getRegistry().register(TileEntityInit.POWER_FURNACE_TE.setRegistryName(Names.POWER_FURNACE_BLOCK));
		event.getRegistry().register(TileEntityInit.POWER_CUBE_TE.setRegistryName(Names.POWER_BOX));
		event.getRegistry().register(TileEntityInit.COLOR_Changer_TE.setRegistryName(Names.CABLE_PAINTER));

		TileEntityInit.registerTileEntities(event);

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "TileEntityType Loaded");
	}

	@SubscribeEvent
	public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		event.getRegistry().register(ModedRecipeSerializers.COLORCHNAGER.setRegistryName("coloring"));
		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "RecipeSerializers Loaded");
	}

	@SubscribeEvent
	public static void registerContainerType(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(ContainerInit.POWER_FURNCAE_CON.setRegistryName(Names.POWERED_FURNACE_CON));
		event.getRegistry().register(ContainerInit.POWER_CUBE_CON.setRegistryName(Names.POWER_BOX_CON));
		event.getRegistry().register(ContainerInit.c_changer_CON.setRegistryName("color_changer"));

		CyberCoreMain.LOGGER.info(TextFormatting.BLUE + "ContainerType Loaded");
	}

	@SubscribeEvent
	public static void registerEnitiesType(RegistryEvent.Register<EntityType<?>> event) {

		CyberCoreMain.LOGGER.info(TextFormatting.RED + "Entites Loaded");
	}

}