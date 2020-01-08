package xyz.skynetcloud.cybercore.init;

import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.cyber_block;
import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.cyber_ore;
import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.cyber_slab_block;
import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.cyber_stair_block;
import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.lunargen_block;
import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.power_box;
import static xyz.skynetcloud.cybercore.api.blocks.BlockNames.power_cable;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.skynetcloud.cybercore.api.blocks.BlockNames;
import xyz.skynetcloud.cybercore.api.blocks.BlockNames.BlockNamesInit;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		lunargen_block = BlockNames.registerBlock(BlockNames.lunargen_block, BlockNamesInit.LUNAR_BLOCK);
		cyber_ore = BlockNames.registerBlock(BlockNames.cyber_ore, BlockNamesInit.CYBER_ORE_BLOCK);
		power_cable = BlockNames.registerBlock(BlockNames.power_cable, BlockNamesInit.POWER_CABLE_BLOCK);
		power_box = BlockNames.registerBlock(BlockNames.power_box, BlockNamesInit.POWER_BOX_BLOCK);
		cyber_block = BlockNames.registerBlock(BlockNames.cyber_block, BlockNamesInit.CYBER_BLOCK);
		cyber_stair_block = BlockNames.registerBlock(BlockNames.cyber_stair_block, BlockNamesInit.CYBER_STAIR_BLOCK);
		cyber_slab_block = BlockNames.registerBlock(BlockNames.cyber_slab_block, BlockNamesInit.CYBER_SLAB_BLOCK);
	}

}
