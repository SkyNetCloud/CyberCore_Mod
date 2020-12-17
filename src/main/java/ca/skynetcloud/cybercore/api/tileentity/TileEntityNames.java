package ca.skynetcloud.cybercore.api.tileentity;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCablesTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCubeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class TileEntityNames {

	public static final TileEntityType<PowredFurnaceTileEntity> POWER_FURNACE_TE = TileEntityType.Builder
			.create(PowredFurnaceTileEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);

	@ObjectHolder(Names.CABLE)
	public static final TileEntityType<PowerCablesTileEntity> Cable = null;

	@ObjectHolder(Names.BLOCK_PIPE)
	public static final TileEntityType<ItemPipeTileEntity> BLOCK_PIPE = null;

	public static final TileEntityType<PowerCubeTileEntity> POWER_CUBE_TE = TileEntityType.Builder
			.create(PowerCubeTileEntity::new, BlockInit.Battery).build(null);

	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {

		Block[] item_cables = new Block[17];
		IntStream.range(0, 16).forEach(i -> item_cables[i] = ForgeRegistries.BLOCKS
				.getValue(new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Power_Cable_Names[i])));
		item_cables[16] = BlockInit.BLOCK_PIPE; // need an array with all the color tubes + the original tube since they
												// use the
		// same TE

		event.getRegistry().register(TileEntityType.Builder.create(ItemPipeTileEntity::new, item_cables).build(null)
				.setRegistryName(Names.BLOCK_PIPE));

		Block[] tubes = new Block[17];
		IntStream.range(0, 16).forEach(i -> tubes[i] = ForgeRegistries.BLOCKS
				.getValue(new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Power_Cable_Names[i])));
		tubes[16] = BlockInit.CABLE; // need an array with all the color tubes + the original tube since they use the
										// same TE
		event.getRegistry().register(TileEntityType.Builder.create(PowerCablesTileEntity::new, tubes).build(null)
				.setRegistryName(Names.CABLE));
	}

}
