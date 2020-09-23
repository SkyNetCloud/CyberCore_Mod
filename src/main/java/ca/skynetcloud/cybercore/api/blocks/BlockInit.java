package ca.skynetcloud.cybercore.api.blocks;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.block.blocks.BlockIrrigation;
import ca.skynetcloud.cybercore.block.blocks.CropBlockGrower;
import ca.skynetcloud.cybercore.block.blocks.CustomWorldLink;
import ca.skynetcloud.cybercore.block.blocks.CyberExtractorBlock;
import ca.skynetcloud.cybercore.block.blocks.CyberLoaderBlock;
import ca.skynetcloud.cybercore.block.blocks.CyberSlabBlock;
import ca.skynetcloud.cybercore.block.blocks.CyberStairsBlock;
import ca.skynetcloud.cybercore.block.crop.LettuceCrop;
import ca.skynetcloud.cybercore.block.crop.TomatoCrop;
import ca.skynetcloud.cybercore.block.tech.TechBlockFacing;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCoreItemPipe;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;
import ca.skynetcloud.cybercore.block.tech.blocks.color.ColorItemCable;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class BlockInit {

	public static ItemGroup group = CyberCoreTab.instance;

	public static Block IRRIGATION_BLOCK = new BlockIrrigation("irrigtion_block", group);

	public static Block GrowGlass = new CropBlockGrower("grow_glass", group, true);

	@ObjectHolder(Names.ITEM_CABLE)
	public static final CyberCoreItemPipe ITEM_PIPE = null;

	public static Block CABLE = new CyberCoreCable(
			Block.Properties.create(Material.GLASS, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(0.4F)
					.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)).setRegistryName(Names.CABLE);

	public static Block BLOCK_EXTRACTOR = new CyberExtractorBlock(Block.Properties.create(Material.IRON));

	public static Block CABLE_PAINTER = new TechBlockFacing("cable_painter", group);

	public static Block BLOCK_LOADER = new CyberLoaderBlock(Block.Properties.create(Material.IRON))
			.setRegistryName("block_loader");

	public static Block TOMATO_CROP = new TomatoCrop("tomato_crop_block");

	public static Block LETTUCE_CROP = new LettuceCrop("lettuce_crop_block");

	public static Block CYBERLAND = new CustomWorldLink("cyberland_block", CyberCoreTab.instance);

	public static Block POWER_FURNACE_BLOCK = new TechBlockFacing("powered_furnace", group);

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

	public static Block POWER_BOX = new CyberCorePowerBlock();

	public static Block RUBY_SLAB = new CyberSlabBlock(Block.Properties.create(Material.ROCK), "ruby_slab",
			CyberCoreTab.instance);

	public static Block RUBY_STAIRS = new CyberStairsBlock(RUBY_SLAB.getDefaultState(),
			Block.Properties.create(Material.ROCK), "ruby_stairs", CyberCoreTab.instance);

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry,
				new CyberCoreItemPipe(Block.Properties.create(Material.GLASS, MaterialColor.YELLOW_TERRACOTTA)
						.hardnessAndResistance(0.4F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
				Names.ITEM_CABLE);

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new ColorItemCable(DyeColor.values()[i],
								Block.Properties.create(Material.GLASS).hardnessAndResistance(0.4F)
										.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Item_TUBE_NAMES[i]));

	}

	private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name) {
		String prefixedName = CyberCoreMain.MODID + ":" + name;
		newBlock.setRegistryName(prefixedName);
		registry.register(newBlock);
		return newBlock;
	}

}
