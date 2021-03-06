package ca.skynetcloud.cybercore.init;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCablesTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCubeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class TileEntityInit {

	public static final TileEntityType<PowredFurnaceTileEntity> POWER_FURNACE_TE = TileEntityType.Builder
			.of(PowredFurnaceTileEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);

	@ObjectHolder(Names.CABLE)
	public static final TileEntityType<PowerCablesTileEntity> Cable = null;

	@ObjectHolder(Names.BLOCK_PIPE)
	public static final TileEntityType<ItemPipeTileEntity> BLOCK_PIPE = null;

	public static final TileEntityType<PowerCubeTileEntity> POWER_CUBE_TE = TileEntityType.Builder
			.of(PowerCubeTileEntity::new, BlockInit.Battery).build(null);

	public static final TileEntityType<ColorChangeTileEntity> COLOR_Changer_TE = TileEntityType.Builder
			.of(ColorChangeTileEntity::new, BlockInit.C_Changer_Block).build(null);

	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {

		Block[] item_cables = new Block[17];
		IntStream.range(0, 16).forEach(i -> item_cables[i] = ForgeRegistries.BLOCKS
				.getValue(new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Item_TUBE_NAMES[i])));
		item_cables[16] = BlockInit.BLOCK_PIPE; // need an array with all the color tubes + the original tube since they
												// use the
		// same TE

		event.getRegistry().register(TileEntityType.Builder.of(ItemPipeTileEntity::new, item_cables).build(null)
				.setRegistryName(Names.BLOCK_PIPE));

		Block[] tubes = new Block[17];
		IntStream.range(0, 16).forEach(i -> tubes[i] = ForgeRegistries.BLOCKS
				.getValue(new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Power_Cable_Names[i])));
		tubes[16] = BlockInit.CABLE;

		event.getRegistry().register(
				TileEntityType.Builder.of(PowerCablesTileEntity::new, tubes).build(null).setRegistryName(Names.CABLE));
	}

}
