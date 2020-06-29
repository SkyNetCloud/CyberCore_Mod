package xyz.skynetcloud.cybercore.util.TE.techblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ticket.AABBTicket;
import xyz.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import xyz.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;


public class TileIrrigation extends TileEntity{

	  private AABBTicket farmlandTicket;

	  public TileIrrigation() {
			super(TileEntityNames.IrrigationTile);
	  }

	  @Override
	  public void onLoad() {
	    if (!world.isRemote) {
	      farmlandTicket = FarmlandWaterManager.addAABBTicket(world, new AxisAlignedBB(pos).grow(CyberCoreConfig.getIrrigationRange()));
	      farmlandTicket.validate();
	    }
	  }

	  @Override
	  public void onChunkUnloaded() {
	    if (!world.isRemote && farmlandTicket != null) {
	      farmlandTicket.invalidate();
	    }
	  }
}
