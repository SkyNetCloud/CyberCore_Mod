package xyz.skynetcloud.cybercore.api.tileentity;

import net.minecraft.tileentity.TileEntityType;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;
import xyz.skynetcloud.cybercore.util.TE.cable.ItemPipeTileEntity;
import xyz.skynetcloud.cybercore.util.TE.techblock.LunaGenTileEntity;
import xyz.skynetcloud.cybercore.util.TE.techblock.PowedFurnaceTileEntity;
import xyz.skynetcloud.cybercore.util.TE.techblock.PowerStorageTileEntity;

public class TileEntityNames {

	public static final TileEntityType<PowerStorageTileEntity> POWER_BOX_TE = TileEntityType.Builder
			.create(PowerStorageTileEntity::new, BlockInit.POWER_BOX).build(null);

	public static final TileEntityType<LunaGenTileEntity> LUNAR_GEN_MACHINE_TE = TileEntityType.Builder
			.create(LunaGenTileEntity::new, BlockInit.LUNAR_BLOCK).build(null);
	public static final TileEntityType<CableTileEntity> CABLE_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockInit.CABLE).build(null);

	public static final TileEntityType<ItemPipeTileEntity> TE_TYPE_ITEM_PIPE = TileEntityType.Builder
			.create(ItemPipeTileEntity::new, BlockInit.ITEM_PIPE).build(null);

	public static final TileEntityType<PowedFurnaceTileEntity> POWER_FURNACE_TE = TileEntityType.Builder
			.create(PowedFurnaceTileEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);

}
