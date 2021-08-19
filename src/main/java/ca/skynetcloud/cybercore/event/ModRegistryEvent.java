package ca.skynetcloud.cybercore.event;



import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.CoreInit;
import ca.skynetcloud.cybercore.util.crafting.ModedRecipeSerializers;
import ca.skynetcloud.cybercore.world.biome.DecayedBiome;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.inventory.MenuType;
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
		CoreInit.EnchantmentInit.EnchantmentLoad(event);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Loaded Enchantment");
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		CoreInit.BlockInit.registerBlocks(event);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Blocks Loaded");
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		CoreInit.ItemInit.registerItems(event);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "Items Loaded");
	}

	@SubscribeEvent
	public static void registerContainer(RegistryEvent.Register<MenuType<?>> event)
	{
		CoreInit.ContainersInit.registerAll(event);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "MenuTypes Loaded");
	}


	@SubscribeEvent
	public static void registerTileEntityType(RegistryEvent.Register<BlockEntityType<?>> event) {
		CoreInit.BlockEntityInit.registerTileEntities(event);
		CyberCoreMain.LOGGER.info(ChatFormatting.BLUE + "BlockEntityTypes Loaded");
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
		CoreInit.SoundInit.registerAll(event.getRegistry());
		CyberCoreMain.LOGGER.info(ChatFormatting.RED + "Sound Event Loaded");
	}

}