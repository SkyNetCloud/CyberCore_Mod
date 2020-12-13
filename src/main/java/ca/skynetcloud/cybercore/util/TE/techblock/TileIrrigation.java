package ca.skynetcloud.cybercore.util.TE.techblock;

import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ticket.AABBTicket;

public class TileIrrigation extends TileEntity {

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
