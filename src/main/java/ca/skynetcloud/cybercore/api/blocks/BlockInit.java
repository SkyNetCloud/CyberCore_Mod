package ca.skynetcloud.cybercore.api.blocks;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.block.blocks.BlockIrrigation;
import ca.skynetcloud.cybercore.block.blocks.CropBlockGrower;
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

	@ObjectHolder(Names.IRRIGATION_BLOCK)
	public static Block IRRIGATION_BLOCK = null;

	@ObjectHolder(Names.ITEM_CABLE)
	public static final CyberCoreItemPipe ITEM_PIPE = null;

	@ObjectHolder(Names.CABLE)
	public static Block CABLE = null;

	@ObjectHolder(Names.BLOCK_EXTRACTOR)
	public static Block BLOCK_EXTRACTOR = null;

	@ObjectHolder(Names.CABLE_PAINTER)
	public static Block CABLE_PAINTER = null;

	@ObjectHolder(Names.BLOCK_LOADER)
	public static Block BLOCK_LOADER = null;

	@ObjectHolder(Names.TOMATO_CROP)
	public static Block TOMATO_CROP = null;

	@ObjectHolder(Names.LETTUCE_CROP)
	public static Block LETTUCE_CROP = null;

	@ObjectHolder(Names.POWER_FURNACE_BLOCK)
	public static Block POWER_FURNACE_BLOCK = null;

	@ObjectHolder(Names.RUBY_BLOCK)
	public static Block RUBY_BLOCK = null;

	@ObjectHolder(Names.DARK_STEEL_BLOCK)
	public static Block DARK_STEEL_BLOCK = null;

	@ObjectHolder(Names.CYBER_ORE)
	public static Block CYBER_ORE = null;

	@ObjectHolder(Names.DARK_STEEL_ORE)
	public static Block DARK_STEEL_ORE = null;

	@ObjectHolder(Names.RUBY_ORE)
	public static Block RUBY_ORE = null;

	@ObjectHolder(Names.LUNAR_BLOCK)
	public static Block LUNAR_BLOCK = null;

	@ObjectHolder(Names.POWER_BOX)
	public static Block POWER_BOX = null;

	@ObjectHolder(Names.RUBY_SLAB)
	public static Block RUBY_SLAB = null;

	@ObjectHolder(Names.RUBY_STAIRS)
	public static Block RUBY_STAIRS = null;

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry, new TechBlockFacing(group), Names.CABLE_PAINTER);

		registerBlock(registry, new CyberLoaderBlock(Block.Properties.create(Material.IRON)), Names.BLOCK_LOADER);

		registerBlock(registry, new CyberExtractorBlock(Block.Properties.create(Material.IRON)), Names.BLOCK_EXTRACTOR);

		registerBlock(registry, new CyberCoreItemPipe(), Names.ITEM_CABLE);

		registerBlock(registry, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 15.0f)),
				Names.DARK_STEEL_ORE);

		registerBlock(registry, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 7.0F)),
				Names.RUBY_ORE);

		registerBlock(registry, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f)),
				Names.CYBER_ORE);

		registerBlock(registry, new CyberCoreItemPipe(), Names.ITEM_CABLE);

		registerBlock(registry, new CyberCorePowerBlock(), Names.POWER_BOX);

		registerBlock(registry, new TomatoCrop(), Names.TOMATO_CROP);

		registerBlock(registry, new LettuceCrop(), Names.LETTUCE_CROP);

		registerBlock(registry, new CyberSlabBlock(Block.Properties.create(Material.ROCK), CyberCoreTab.instance),
				Names.RUBY_SLAB);

		registerBlock(registry, new BlockIrrigation(group), Names.IRRIGATION_BLOCK);

		registerBlock(registry, new TechBlockFacing(group), Names.LUNAR_BLOCK);

		registerBlock(registry, new TechBlockFacing(group), Names.POWER_FURNACE_BLOCK);

		registerBlock(registry, new BlockBaseCore(Block.Properties.create(Material.IRON), group, true),
				Names.RUBY_BLOCK);

		registerBlock(registry, new BlockBaseCore(Block.Properties.create(Material.IRON), group, true),
				Names.DARK_STEEL_BLOCK);

		registerBlock(registry,
				new CyberCoreCable(Block.Properties.create(Material.GLASS, MaterialColor.YELLOW_TERRACOTTA)
						.hardnessAndResistance(0.4F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
				Names.CABLE);

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
