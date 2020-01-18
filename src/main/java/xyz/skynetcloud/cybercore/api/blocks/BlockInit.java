package xyz.skynetcloud.cybercore.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.block.blocks.CustomWorldLink;
import xyz.skynetcloud.cybercore.block.blocks.CyberSlabBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import xyz.skynetcloud.cybercore.block.crop.LettuceCrop;
import xyz.skynetcloud.cybercore.block.crop.TomatoCrop;
import xyz.skynetcloud.cybercore.block.tech.TechBlockFacing;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;

public class BlockInit {

	public static ItemGroup group = CyberCoreTab.instance;

	public static Block TOMATO_CROP = new TomatoCrop("tomato_crop_block");

	public static Block LETTUCE_CROP = new LettuceCrop("lettuce_crop_block");

	public static BlockBaseCore DimWorldLinkBlock = (BlockBaseCore) new CustomWorldLink("worldlinkblock",
			CyberCoreTab.instance);

	public static Block POWER_FURNACE_BLOCK = new TechBlockFacing("power_furnace_block", group);

	public static Block CABLE = (BlockBaseCore) new CyberCoreCable("cable", CyberCoreTab.instance);

	public static Block CYBER_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f)).setRegistryName("cyber_ore");

	public static Block LUNAR_BLOCK = new TechBlockFacing("lunarsolargenerator_block", CyberCoreTab.instance);

	public static Block POWER_BOX = (BlockBaseCore) new CyberCorePowerBlock();

	public static Block CYBER_SLAB = (BlockBaseCore) new CyberSlabBlock(Block.Properties.create(Material.ROCK),
			"cyber_slab", CyberCoreTab.instance);

	public static Block CYBER_STAIRS = (BlockBaseCore) new CyberStairsBlock(CYBER_SLAB.getDefaultState(),
			Block.Properties.create(Material.ROCK), "cyber_stairs", CyberCoreTab.instance);

}
