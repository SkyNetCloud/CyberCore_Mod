package xyz.skynetcloud.cybercore.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.api.blocks.BlockNames;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {

	public static void register(IForgeRegistry<Block> registry) {
		for (BlockBaseCore block : BlockNames.BLOCKS)
			registry.register((Block) block);

	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (BlockBaseCore block : BlockNames.BLOCKITEMS)
			registry.register((Item) block.createItemBlock());
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		BlockInit.register(event.getRegistry());

		CyberCoreMain.LOGGER.info("Loaded Blocks");
	}

}
