package ca.skynetcloud.cybercore.event;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.BlockEntityInit;
import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.init.ItemInit;
import ca.skynetcloud.cybercore.init.SoundInit;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeSerializers;
import ca.skynetcloud.cybercore.world.biome.DecayedBiome;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryEvent {

	@SubscribeEvent
	public static void registerEnchantment(RegistryEvent.Register<Enchantment> event) {
		event.getRegistry().register(ItemInit.Soul_Bound);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Loaded Enchantment");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

		event.getRegistry().register(BlockInit.LETTUCE_CROP);
		event.getRegistry().register(BlockInit.TOMATO_CROP);

		event.getRegistry().register(BlockInit.CYBER_ORE);
		event.getRegistry().register(BlockInit.RUBY_ORE);
		event.getRegistry().register(BlockInit.DARK_STEEL_ORE);

		event.getRegistry().register(BlockInit.CYBER_DeepSlate_ORE);
		event.getRegistry().register(BlockInit.RUBY_DeepSlate_ORE);
		event.getRegistry().register(BlockInit.DARK_STEEL_DeepSlate_ORE);

		event.getRegistry().register(BlockInit.RUBY_BLOCK);
		event.getRegistry().register(BlockInit.DARK_STEEL_BLOCK);

		BlockInit.registerBlocks(event);

		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Blocks Loaded");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {

		ItemInit.registerItems(event);

		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Items Loaded");
	}

	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<BlockEntityType<?>> event) {

		BlockEntityInit.registerTileEntities(event);

		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "TileEntityTypes Loaded");
	}

	@SubscribeEvent
	public static void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
		event.getRegistry().register(ModedRecipeSerializers.COLORCHNAGER.setRegistryName("coloring"));
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "RecipeSerializers Loaded");
	}

	@SubscribeEvent
	public static void registerBiome(RegistryEvent.Register<Biome> event) {
		DecayedBiome.registerBiomes(event);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Biomes Loaded");
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		SoundInit.registerAll(event.getRegistry());
		CyberCoreMain.LOGGER.info(ChatFormatting.RED + "Sound Event Loaded");
	}

}