package ca.skynetcloud.cybercore.api.blocks;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.block.blocks.BlockIrrigation;
import ca.skynetcloud.cybercore.block.blocks.CyberExtractorBlock;
import ca.skynetcloud.cybercore.block.blocks.CyberLoaderBlock;
import ca.skynetcloud.cybercore.block.crop.LettuceCrop;
import ca.skynetcloud.cybercore.block.crop.TomatoCrop;
import ca.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import ca.skynetcloud.cybercore.block.tech.TechBlockFacing;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCoreCable;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCoreItemPipe;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;
import ca.skynetcloud.cybercore.block.tech.blocks.color.ColorItemCable;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.LunaGenTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowedFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerStorageTileEntity;
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
	public static Block IRRIGATION_BLOCK;

	@ObjectHolder(Names.BLOCK_PIPE)
	public static CyberCoreItemPipe BLOCK_PIPE;

	@ObjectHolder(Names.CABLE)
	public static CyberCoreCable CABLE;

	@ObjectHolder(Names.BLOCK_EXTRACTOR)
	public static Block BLOCK_EXTRACTOR;

	@ObjectHolder(Names.CABLE_PAINTER)
	public static TechBlockBaseSubCore CABLE_PAINTER;

	@ObjectHolder(Names.BLOCK_LOADER)
	public static Block BLOCK_LOADER;

	@ObjectHolder(Names.TOMATO_CROP)
	public static Block TOMATO_CROP;

	@ObjectHolder(Names.LETTUCE_CROP)
	public static Block LETTUCE_CROP;

	@ObjectHolder(Names.POWER_FURNACE_BLOCK)
	public static TechBlockBaseSubCore POWER_FURNACE_BLOCK;

	@ObjectHolder(Names.RUBY_BLOCK)
	public static Block RUBY_BLOCK;

	@ObjectHolder(Names.DARK_STEEL_BLOCK)
	public static Block DARK_STEEL_BLOCK;

	@ObjectHolder(Names.CYBER_ORE)
	public static Block CYBER_ORE;

	@ObjectHolder(Names.DARK_STEEL_ORE)
	public static Block DARK_STEEL_ORE;

	@ObjectHolder(Names.RUBY_ORE)
	public static Block RUBY_ORE;

	@ObjectHolder(Names.LUNAR_BLOCK)
	public static TechBlockBaseSubCore LUNAR_BLOCK;

	@ObjectHolder(Names.POWER_BOX)
	public static TechBlockBaseSubCore POWER_BOX;

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry, new TechBlockFacing(ColorChangeTileEntity::new), Names.CABLE_PAINTER);

		registerBlock(registry, new CyberLoaderBlock(Block.Properties.create(Material.IRON)), Names.BLOCK_LOADER);

		registerBlock(registry, new CyberExtractorBlock(Block.Properties.create(Material.IRON)), Names.BLOCK_EXTRACTOR);

		registerBlock(registry, new CyberCoreItemPipe(), Names.BLOCK_PIPE);

		registerBlock(registry, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 15.0f)),
				Names.DARK_STEEL_ORE);

		registerBlock(registry, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5F, 7.0F)),
				Names.RUBY_ORE);

		registerBlock(registry, new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.0f, 10.0f)),
				Names.CYBER_ORE);

		registerBlock(registry, new CyberCorePowerBlock(PowerStorageTileEntity::new), Names.POWER_BOX);

		registerBlock(registry, new TomatoCrop(), Names.TOMATO_CROP);

		registerBlock(registry, new LettuceCrop(), Names.LETTUCE_CROP);

		registerBlock(registry, new BlockIrrigation(), Names.IRRIGATION_BLOCK);

		registerBlock(registry, new TechBlockFacing(LunaGenTileEntity::new), Names.LUNAR_BLOCK);

		registerBlock(registry, new TechBlockFacing(PowedFurnaceTileEntity::new), Names.POWER_FURNACE_BLOCK);

		registerBlock(registry, new BlockBaseCore(Block.Properties.create(Material.IRON), true), Names.RUBY_BLOCK);

		registerBlock(registry, new BlockBaseCore(Block.Properties.create(Material.IRON), true),
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
