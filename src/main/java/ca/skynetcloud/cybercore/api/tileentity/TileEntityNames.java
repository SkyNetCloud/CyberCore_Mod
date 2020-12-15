package ca.skynetcloud.cybercore.api.tileentity;

import java.util.stream.IntStream;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.util.TE.cable.CableTileEntity;
import ca.skynetcloud.cybercore.util.TE.cable.ItemPipeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.LunaGenTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowedFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerStorageTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.TileIrrigation;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class TileEntityNames {

	public static final TileEntityType<PowerStorageTileEntity> POWER_BOX_TE = TileEntityType.Builder
			.create(PowerStorageTileEntity::new, BlockInit.POWER_BOX).build(null);

	public static final TileEntityType<LunaGenTileEntity> LUNAR_GEN_MACHINE_TE = TileEntityType.Builder
			.create(LunaGenTileEntity::new, BlockInit.LUNAR_BLOCK).build(null);

	public static final TileEntityType<CableTileEntity> CABLE_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockInit.CABLE).build(null);

	public static final TileEntityType<ColorChangeTileEntity> CABLE_PAINTER_TE = TileEntityType.Builder
			.create(ColorChangeTileEntity::new, BlockInit.CABLE_PAINTER).build(null);

	@ObjectHolder(Names.BLOCK_PIPE)
	public static final TileEntityType<ItemPipeTileEntity> ITEM_CABLE = null;

	public static final TileEntityType<PowedFurnaceTileEntity> POWER_FURNACE_TE = TileEntityType.Builder
			.create(PowedFurnaceTileEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);

	public static final TileEntityType<TileIrrigation> IrrigationTile = TileEntityType.Builder
			.create(TileIrrigation::new, BlockInit.IRRIGATION_BLOCK).build(null);

	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {

		Block[] pipe = new Block[17];
		IntStream.range(0, 16).forEach(i -> pipe[i] = ForgeRegistries.BLOCKS
				.getValue(new ResourceLocation(CyberCoreMain.MODID, Names.COLORED_Item_TUBE_NAMES[i])));
		pipe[16] = BlockInit.BLOCK_PIPE;

		event.getRegistry().register(TileEntityType.Builder.create(ItemPipeTileEntity::new, pipe).build(null)
				.setRegistryName(Names.BLOCK_PIPE));

	}

}
