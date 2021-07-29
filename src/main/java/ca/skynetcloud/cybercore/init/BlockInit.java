package ca.skynetcloud.cybercore.init;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.block.blocks.fences.ElecticFencesBlock;
import ca.skynetcloud.cybercore.block.blocks.fences.color.FencesColor;
import ca.skynetcloud.cybercore.block.blocks.fences.gate.ElecticFenceGate;
import ca.skynetcloud.cybercore.block.blocks.fences.gate.color.ColorFenceGate;
import ca.skynetcloud.cybercore.block.blocks.fences.top.ElectricFenceTop;
import ca.skynetcloud.cybercore.block.blocks.fences.top.color.ElectricFenceTopColor;
import ca.skynetcloud.cybercore.block.blocks.slab.Slab_Block;
import ca.skynetcloud.cybercore.block.blocks.slab.color.SlabColor;
import ca.skynetcloud.cybercore.block.crop.LettuceCrop;
import ca.skynetcloud.cybercore.block.crop.TomatoCrop;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class BlockInit {

	public static CreativeModeTab group = CyberCoreTab.instance;

	@ObjectHolder(Names.CABLE)
	public static Block CABLE = null;

	@ObjectHolder(Names.BLOCK_PIPE)
	public static Block BLOCK_PIPE = null;

	@ObjectHolder(Names.Fence_Block)
	public static Block Fence_Block = null;

	@ObjectHolder(Names.Fence_Block_Top)
	public static Block Fence_Block_Top = null;

	@ObjectHolder(Names.Fence_Gate_Block)
	public static Block Fence_Gate_Block = null;

	@ObjectHolder(Names.Slab_Block)
	public static Block Slab_Block = null;

	@ObjectHolder(Names.TOMATO_CROP)
	public static Block TOMATO_CROP = new TomatoCrop().setRegistryName(Names.TOMATO_CROP);

	@ObjectHolder(Names.LETTUCE_CROP)
	public static Block LETTUCE_CROP = new LettuceCrop().setRegistryName(Names.LETTUCE_CROP);

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

	@ObjectHolder(Names.CYBER_DeepSlate_ORE)
	public static Block CYBER_DeepSlate_ORE = new OreBlock(Block.Properties.of(Material.STONE)
			.color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE))
					.setRegistryName(Names.CYBER_DeepSlate_ORE);

	@ObjectHolder(Names.DARK_STEEL_DeepSlate_ORE)
	public static Block DARK_STEEL_DeepSlate_ORE = new OreBlock(Block.Properties.of(Material.STONE)
			.color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE))
					.setRegistryName(Names.DARK_STEEL_DeepSlate_ORE);

	@ObjectHolder(Names.RUBY_DeepSlate_ORE)
	public static Block RUBY_DeepSlate_ORE = new OreBlock(Block.Properties.of(Material.STONE)
			.color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F)
			.sound(SoundType.DEEPSLATE))
					.setRegistryName(Names.RUBY_DeepSlate_ORE);

	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();

		registerBlock(registry, new Slab_Block(), Names.Slab_Block);
		registerBlock(registry, new ElecticFencesBlock(), Names.Fence_Block);
		registerBlock(registry, new ElecticFenceGate(), Names.Fence_Gate_Block);
		registerBlock(registry, new ElectricFenceTop(), Names.Fence_Block_Top);

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new FencesColor(DyeColor.values()[i], Block.Properties.of(Material.METAL).strength(30.0F)
								.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Fence_Block_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new ElectricFenceTopColor(DyeColor.values()[i], Block.Properties.of(Material.METAL)
								.strength(30.0F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Fence_Top_Block_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new SlabColor(DyeColor.values()[i], Block.Properties.of(Material.METAL).strength(0.4F)
								.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Slab_Block_Names[i]));

		IntStream.range(0, 16)
				.forEach(i -> registerBlock(registry,
						new ColorFenceGate(DyeColor.values()[i], Block.Properties.of(Material.METAL).strength(30.0F)
								.harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
						Names.COLORED_Fence_Gate_Block_Names[i]));

	}

	private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name) {
		String prefixedName = CyberCoreMain.MODID + ":" + name;
		newBlock.setRegistryName(prefixedName);
		registry.register(newBlock);
		return newBlock;
	}

}
