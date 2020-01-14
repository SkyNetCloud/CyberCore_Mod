package xyz.skynetcloud.cybercore.api.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.block.blocks.CyberSlabBlock;
import xyz.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import xyz.skynetcloud.cybercore.block.tech.TechBlockFacing;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;

public class BlockNames {

	public static List<BlockBaseCore> BLOCKS = new ArrayList<>();

	public static List<BlockBaseCore> BLOCKITEMS = new ArrayList<>();

	public static ItemGroup group = CyberCoreTab.instance;

	public static BlockBaseCore CABLE = (BlockBaseCore) new CyberCoreCable("cable", CyberCoreTab.instance);

	public static OreBlock CYBER_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f));

	public static BlockBaseCore LUNAR_BLOCK = (BlockBaseCore) new TechBlockFacing("lunarsolargenerator_block",
			CyberCoreTab.instance);

	public static BlockBaseCore POWER_BOX = (BlockBaseCore) new CyberCorePowerBlock();

	public static Block CYBER_SLAB = new CyberSlabBlock(Block.Properties.create(Material.ROCK), "cyber_slab",
			CyberCoreTab.instance);

	public static Block CYBER_STAIRS = new CyberStairsBlock(CYBER_SLAB.getDefaultState(),
			Block.Properties.create(Material.ROCK), "cyber_stairs", CyberCoreTab.instance);

}
