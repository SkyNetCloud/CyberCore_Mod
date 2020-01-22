package xyz.skynetcloud.cybercore.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.block.blocks.CustomWorldLink;
import xyz.skynetcloud.cybercore.block.blocks.CyberExtractorBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberLoaderBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberSlabBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import xyz.skynetcloud.cybercore.block.crop.LettuceCrop;
import xyz.skynetcloud.cybercore.block.crop.TomatoCrop;
import xyz.skynetcloud.cybercore.block.tech.TechBlockFacing;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreItemPipe;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;

public class BlockInit {

	public static ItemGroup group = CyberCoreTab.instance;
	/*
	 * TODO My a working Chest Block public static Block IRON_CHEST = new
	 * CyberCoreChestBlock(Block.Properties.create(Material.IRON).
	 * hardnessAndResistance(3.0F)).setRegistryName("iron_chest");
	 * 
	 * public static Block GOLD_CHEST = new
	 * GoldChestBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(3
	 * .0F)).setRegistryName("gold_chest");
	 * 
	 * public static Block DIAMOND_CHEST = new
	 * DiamondChestBlock(Block.Properties.create(Material.IRON).
	 * hardnessAndResistance(3.0F)).setRegistryName("diamond_chest");
	 * 
	 * public static Block COPPER_CHEST = new
	 * CopperChestBlock(Block.Properties.create(Material.IRON).hardnessAndResistance
	 * (3.0F)).setRegistryName("crystal_chest");
	 * 
	 * public static Block SILVER_CHEST = new
	 * SilverChestBlock(Block.Properties.create(Material.IRON).hardnessAndResistance
	 * (3.0F)).setRegistryName("silver_chest");
	 * 
	 * public static Block CRYSTAL_CHEST = new
	 * CyberCoreChestBlock(Block.Properties.create(Material.IRON).
	 * hardnessAndResistance(3.0F)).setRegistryName("copper_chest");
	 * 
	 * public static Block OBSIDIAN_CHEST = new
	 * CyberCoreChestBlock(Block.Properties.create(Material.IRON).
	 * hardnessAndResistance(3.0F)).setRegistryName("obsidian_chest");
	 */
	public static Block ITEM_PIPE = new CyberCoreItemPipe(Block.Properties.create(Material.IRON))
			.setRegistryName("block_pipe");

	public static Block BLOCK_EXTRACTOR = new CyberExtractorBlock(Block.Properties.create(Material.IRON))
			.setRegistryName("block_extractor");

	public static Block BLOCK_LOADER = new CyberLoaderBlock(Block.Properties.create(Material.IRON))
			.setRegistryName("block_loader");

	public static Block CYBER_EXTRACTOR_BLOCK = new CyberExtractorBlock(Block.Properties.create(Material.IRON))
			.setRegistryName("block_extractor");

	public static Block TOMATO_CROP = new TomatoCrop("tomato_crop_block");

	public static Block LETTUCE_CROP = new LettuceCrop("lettuce_crop_block");

	public static BlockBaseCore CYBERLAND = (BlockBaseCore) new CustomWorldLink("cyberland_block",
			CyberCoreTab.instance);

	public static Block POWER_FURNACE_BLOCK = new TechBlockFacing("powered_furnace", group);

	public static Block CABLE = new CyberCoreCable("cable", CyberCoreTab.instance);

	public static Block RUBY_BLOCK = new BlockBaseCore(Block.Properties.create(Material.IRON), "ruby_block", group,
			true);
	public static Block DARK_STEEL_BLOCK = new BlockBaseCore(Block.Properties.create(Material.IRON), "dark_steel_block",
			group, true);

	public static Block CYBER_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f)).setRegistryName("cyber_ore");

	public static Block DARK_STEEL_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 15.0f))
					.setRegistryName("dark_steel_ore");

	public static Block RUBY_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 7.0F)).setRegistryName("ruby_ore");

	public static Block LUNAR_BLOCK = new TechBlockFacing("lunarsolargenerator_block", CyberCoreTab.instance);

	public static Block POWER_BOX = (BlockBaseCore) new CyberCorePowerBlock();

	public static Block RUBY_SLAB = (BlockBaseCore) new CyberSlabBlock(Block.Properties.create(Material.ROCK),
			"ruby_slab", CyberCoreTab.instance);

	public static Block RUBY_STAIRS = (BlockBaseCore) new CyberStairsBlock(RUBY_SLAB.getDefaultState(),
			Block.Properties.create(Material.ROCK), "ruby_stairs", CyberCoreTab.instance);

}
