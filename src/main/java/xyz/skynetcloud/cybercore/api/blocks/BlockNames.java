package xyz.skynetcloud.cybercore.api.blocks;

import static xyz.skynetcloud.cybercore.CyberCoreMain.MODID;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import xyz.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;

public class BlockNames {

	public static class BlockNamesInit {
	public static final String LUNAR_BLOCK = (MODID + ":lunarsolargenerator_block");
	public static final String POWER_CABLE_BLOCK = (MODID + ":power_cable");
	public static final String POWER_BOX_BLOCK = (MODID + ":power_storage_block");
	public static final String CYBER_ORE_BLOCK = (MODID + ":cyber_ore");
	public static final String CYBER_BLOCK = (MODID + ":cyber_block");
	public static final String CYBER_STAIR_BLOCK = (MODID + ":cyber_stair");
	public static final String CYBER_SLAB_BLOCK = (MODID +  ":cyber_slab");
	}

	public static ItemGroup group = CyberCoreTab.instance;

	public static Block cyber_slab_block = new SlabBlock(Block.Properties.create(Material.IRON));

	public static Block lunargen_block = new TechBlockBaseSubCore(
			Block.Properties.create(Material.IRON).hardnessAndResistance(15), true);

	public static Block cyber_stair_block = new CyberStairsBlock(cyber_slab_block.getDefaultState(),
			Block.Properties.create(Material.IRON), group);

	public static Block power_box = new TechBlockBaseSubCore(Block.Properties.create(Material.IRON), true);

	public static Block cyber_ore = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(6));

	public static Block cyber_block = new Block(Block.Properties.create(Material.IRON));

	public static Block power_cable = new CyberCoreCable();

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
