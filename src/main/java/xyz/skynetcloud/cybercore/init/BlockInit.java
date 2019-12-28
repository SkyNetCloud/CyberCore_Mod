package xyz.skynetcloud.cybercore.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import xyz.skynetcloud.cybercore.block.tech.techblocks.CyberCoreCable;
import xyz.skynetcloud.cybercore.block.tech.techblocks.LunarGenBlock;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
	static ItemGroup group = CyberCoreTab.instance;

	public static Block lunargen_block = new LunarGenBlock();

	public static Block power_box = new TechBlockBaseSubCore(Block.Properties.create(Material.IRON), group, true);

	public static Block cyber_ore = new Block(Block.Properties.create(Material.GOURD));

	public static Block power_cable = new CyberCoreCable();

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		lunargen_block = registerBlock(lunargen_block, "lunarsolargenerator_block");
		cyber_ore = registerBlock(cyber_ore, "cyber_ore");
		power_cable = registerBlock(power_cable, "power_cable");
		power_box = registerBlock(power_box, "power_storage_block");

	}

	public static Block registerBlock(Block block, String name) {
		BlockItem itemBlock = new BlockItem(block, new Item.Properties().group(CyberCoreTab.instance));
		block.setRegistryName(name);
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
