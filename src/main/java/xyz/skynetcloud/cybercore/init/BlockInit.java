package xyz.skynetcloud.cybercore.init;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.api.blocks.BlockNames;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		BlockNames.lunargen_block = registerBlock(BlockNames.lunargen_block, "lunarsolargenerator_block");
		BlockNames.cyber_ore = registerBlock(BlockNames.cyber_ore, "cyber_ore");
		BlockNames.power_cable = registerBlock(BlockNames.power_cable, "power_cable");
		BlockNames.power_box = registerBlock(BlockNames.power_box, "power_storage_block");
		BlockNames.cyber_block = registerBlock(BlockNames.cyber_block, "cyber_block");
		BlockNames.cyber_stair_block = registerBlock(BlockNames.cyber_stair_block, "cyber_stair_block");
		BlockNames.cyber_slab_block = registerBlock(BlockNames.cyber_slab_block, "cyber_slab_block");
	}

	public static Block registerBlock(Block block,) {
		BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(CyberCoreTab.instance));
		
		itemBlock.setRegistryName(name);
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(itemBlock);
		return block;
	}

	public static Block registerBlock(Block block, BlockItem itemBlock, String name) {
		block.setRegistryName(name);
		ForgeRegistries.BLOCKS.register(block);

		if (itemBlock != null) {
			itemBlock.setRegistryName(name);
			ForgeRegistries.ITEMS.register(itemBlock);
		}

		return block;
	}

}
