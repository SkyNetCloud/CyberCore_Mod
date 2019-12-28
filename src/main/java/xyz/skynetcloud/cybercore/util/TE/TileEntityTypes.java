package xyz.skynetcloud.cybercore.util.TE;

import net.minecraft.tileentity.TileEntityType;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;

public class TileEntityTypes {

	private TileEntityTypes() {

	}

	public static TileEntityType<LunaGenTileEntity> LUNAR_GEN_MACHINE_TE;

	public static TileEntityType<PowerStorageTileEntity> POWER_BOX_TE;

	public static TileEntityType<CableTileEntity> POWER_CABLE_TE;

}
