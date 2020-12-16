package ca.skynetcloud.cybercore.api.blocks;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.block.crop.LettuceCrop;
import ca.skynetcloud.cybercore.block.crop.TomatoCrop;
import ca.skynetcloud.cybercore.block.tech.TechBlockFacing;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class BlockInit {

	public static ItemGroup group = CyberCoreTab.instance;

	@ObjectHolder(Names.TOMATO_CROP)
	public static Block TOMATO_CROP = new TomatoCrop().setRegistryName(Names.TOMATO_CROP);

	@ObjectHolder(Names.LETTUCE_CROP)
	public static Block LETTUCE_CROP = new LettuceCrop().setRegistryName(Names.LETTUCE_CROP);

	@ObjectHolder(Names.POWER_FURNACE_BLOCK)
	public static Block POWER_FURNACE_BLOCK = new TechBlockFacing(PowredFurnaceTileEntity::new)
			.setRegistryName(Names.POWER_FURNACE_BLOCK);

	@ObjectHolder(Names.RUBY_BLOCK)
	public static Block RUBY_BLOCK = new BlockBaseCore(Block.Properties.create(Material.IRON), true)
			.setRegistryName(Names.RUBY_BLOCK);

	@ObjectHolder(Names.DARK_STEEL_BLOCK)
	public static Block DARK_STEEL_BLOCK = new BlockBaseCore(Block.Properties.create(Material.IRON), true)
			.setRegistryName(Names.DARK_STEEL_BLOCK);

	@ObjectHolder(Names.CYBER_ORE)
	public static Block CYBER_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f)).setRegistryName(Names.CYBER_ORE);

	@ObjectHolder(Names.DARK_STEEL_ORE)
	public static Block DARK_STEEL_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 15.0f))
					.setRegistryName(Names.DARK_STEEL_ORE);

	@ObjectHolder(Names.RUBY_ORE)
	public static Block RUBY_ORE = new OreBlock(
			Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 7.0F)).setRegistryName(Names.RUBY_ORE);

}
