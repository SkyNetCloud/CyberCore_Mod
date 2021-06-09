package ca.skynetcloud.cybercore.init;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.block.blocks.CableBlock;
import ca.skynetcloud.cybercore.block.blocks.DecayedBlock;
import ca.skynetcloud.cybercore.block.blocks.ExtractorBlock;
import ca.skynetcloud.cybercore.block.blocks.ItemCable;
import ca.skynetcloud.cybercore.block.blocks.PowerCube;
import ca.skynetcloud.cybercore.block.blocks.color_cable.ColorCable;
import ca.skynetcloud.cybercore.block.blocks.color_cable.ColorItemCable;
import ca.skynetcloud.cybercore.block.crop.LettuceCrop;
import ca.skynetcloud.cybercore.block.crop.TomatoCrop;
import ca.skynetcloud.cybercore.block.tech.TechBlockFacing;
import ca.skynetcloud.cybercore.util.TE.techblock.ItemConvterBlockTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCubeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.DyeColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

//TODO Clean Up BlockInit
@ObjectHolder(CyberCoreMain.MODID)
public class BlockInit {

	public static ItemGroup group = CyberCoreTab.instance;

	public static Block CABLE = null;

	public static Block ItemConvterBlock = new TechBlockFacing(ItemConvterBlockTileEntity::new)
			.setRegistryName(Names.ITEM_CONVERT_BLOCK);

	public static Block BlockExp = new ExtractorBlock(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f))
			.setRegistryName(Names.BLOCK_EXTRACTOR);

	@ObjectHolder(Names.BLOCK_PIPE)
	public static Block BLOCK_PIPE = null;

	@ObjectHolder(Names.DECAYED_BLOCK_NAME)
	public static Block DECAYED_BLOCK = new DecayedBlock(Block.Properties.of(Material.SAND).strength(5.0f, 5.1f), false)
			.setRegistryName(Names.DECAYED_BLOCK_NAME);

	@ObjectHolder(Names.POWER_BOX)
	public static Block Battery = new PowerCube(PowerCubeTileEntity::new).setRegistryName(Names.POWER_BOX);

	@ObjectHolder(Names.TOMATO_CROP)
	public static Block TOMATO_CROP = new TomatoCrop().setRegistryName(Names.TOMATO_CROP);

	@ObjectHolder(Names.LETTUCE_CROP)
	public static Block LETTUCE_CROP = new LettuceCrop().setRegistryName(Names.LETTUCE_CROP);

	@ObjectHolder(Names.POWER_FURNACE_BLOCK)
	public static Block POWER_FURNACE_BLOCK = new TechBlockFacing(PowredFurnaceTileEntity::new)
			.setRegistryName(Names.POWER_FURNACE_BLOCK);

	@ObjectHolder(Names.RUBY_BLOCK)
	public static Block RUBY_BLOCK = new BlockBaseCore(Block.Properties.of(Material.METAL), true)
			.setRegistryName(Names.RUBY_BLOCK);

	@ObjectHolder(Names.DARK_STEEL_BLOCK)
	public static Block DARK_STEEL_BLOCK = new BlockBaseCore(Block.Properties.of(Material.METAL), true)
			.setRegistryName(Names.DARK_STEEL_BLOCK);

	@ObjectHolder(Names.CYBER_ORE)
	public static Block CYBER_ORE = new OreBlock(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f))
			.setRegistryName(Names.CYBER_ORE);

	@ObjectHolder(Names.DARK_STEEL_ORE)
	public static Block DARK_STEEL_ORE = new OreBlock(Block.Properties.of(Material.STONE).strength(5.0f, 15.0f))
			.setRegistryName(Names.DARK_STEEL_ORE);

	@ObjectHolder(Names.RUBY_ORE)
	public static Block RUBY_ORE = new OreBlock(Block.Properties.of(Material.STONE).strength(0.5F, 7.0F))
			.setRegistryName(Names.RUBY_ORE);

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry, new CableBlock(), Names.CABLE);
		registerBlock(registry, new ItemCable(), Names.BLOCK_PIPE);

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new ColorItemCable(DyeColor.values()[i], Block.Properties.of(Material.GLASS).strength(0.4F)
								.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Item_TUBE_NAMES[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new ColorCable(DyeColor.values()[i], Block.Properties.of(Material.GLASS).strength(0.4F)
								.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Power_Cable_Names[i]));

	}

	private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name) {
		String prefixedName = CyberCoreMain.MODID + ":" + name;
		newBlock.setRegistryName(prefixedName);
		registry.register(newBlock);
		return newBlock;
	}

}
