package xyz.skynetcloud.cybercore.api.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.block.blocks.CustomWorldLink;
import xyz.skynetcloud.cybercore.block.blocks.CyberSlabBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import xyz.skynetcloud.cybercore.block.tech.TechBlockFacing;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;

public class BlockNames {

	public static List<BlockBaseCore> BLOCKS = new ArrayList<>();

	public static List<BlockBaseCore> BLOCKITEMS = new ArrayList<>();

	public static ItemGroup group = CyberCoreTab.instance;

	public static BlockBaseCore DimWorldLinkBlock = (BlockBaseCore) new CustomWorldLink("worldlinkblock",
			CyberCoreTab.instance);

	public static BlockBaseCore CABLE = (BlockBaseCore) new CyberCoreCable("cable", CyberCoreTab.instance);

	public static BlockBaseCore CYBER_ORE = (BlockBaseCore) new BlockBaseCore(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f), "cyber_ore",
			CyberCoreTab.instance, true);

	public static BlockBaseCore LUNAR_BLOCK = (BlockBaseCore) new TechBlockFacing("lunarsolargenerator_block",
			CyberCoreTab.instance);

	public static BlockBaseCore POWER_BOX = (BlockBaseCore) new CyberCorePowerBlock();

	public static BlockBaseCore CYBER_SLAB = (BlockBaseCore) new CyberSlabBlock(Block.Properties.create(Material.ROCK),
			"cyber_slab", CyberCoreTab.instance);

	public static BlockBaseCore CYBER_STAIRS = (BlockBaseCore) new CyberStairsBlock(CYBER_SLAB.getDefaultState(),
			Block.Properties.create(Material.ROCK), "cyber_stairs", CyberCoreTab.instance);

}
