package xyz.skynetcloud.cybercore.api.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import xyz.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import xyz.skynetcloud.cybercore.block.tech.techblocks.CyberCoreCable;

public class BlockNames {

	static ItemGroup group = CyberCoreTab.instance;

	public static Block lunargen_block = new TechBlockBaseSubCore(
			Block.Properties.create(Material.IRON).hardnessAndResistance(15), group, true).setRegistryName("")
					.setRegistryName("lunarsolargenerator_block");

	public static Block cyber_stair_block = new CyberStairsBlock(BlockNames.cyber_block.getDefaultState(),
			Block.Properties.create(Material.IRON), group).setRegistryName("cyber_stair_block");

	public static Block cyber_slab_block = new SlabBlock(Block.Properties.create(Material.IRON))
			.setRegistryName("cyber_slab_block");

	public static Block power_box = new TechBlockBaseSubCore(Block.Properties.create(Material.IRON), group, true)
			.setRegistryName("power_storage_block");

	public static Block cyber_ore = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(6))
					.setRegistryName("cyber_ore");

	public static Block cyber_block = new Block(Block.Properties.create(Material.IRON)).setRegistryName("cyber_block");

	public static Block power_cable = new CyberCoreCable(Block.Properties.create(Material.ROCK), group, false)
			.setRegistryName("power_cable");

}
