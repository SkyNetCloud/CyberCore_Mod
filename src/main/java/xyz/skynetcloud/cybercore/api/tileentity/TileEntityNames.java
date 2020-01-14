package xyz.skynetcloud.cybercore.api.tileentity;

import net.minecraft.tileentity.TileEntityType;
import xyz.skynetcloud.cybercore.api.blocks.BlockNames;
import xyz.skynetcloud.cybercore.util.TE.LunaGenTileEntity;
import xyz.skynetcloud.cybercore.util.TE.PowerStorageTileEntity;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;

public class TileEntityNames {

	public static final TileEntityType<PowerStorageTileEntity> POWER_BOX_TE = TileEntityType.Builder
			.create(PowerStorageTileEntity::new, BlockNames.POWER_BOX).build(null);
	public static final TileEntityType<LunaGenTileEntity> LUNAR_GEN_MACHINE_TE = TileEntityType.Builder
			.create(LunaGenTileEntity::new, BlockNames.LUNAR_BLOCK).build(null);
	public static final TileEntityType<CableTileEntity> CABLE_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockNames.CABLE).build(null);

}
