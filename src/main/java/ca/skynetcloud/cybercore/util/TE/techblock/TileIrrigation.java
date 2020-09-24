package ca.skynetcloud.cybercore.util.TE.techblock;

import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.FarmlandWaterManager;
import net.minecraftforge.common.ticket.AABBTicket;

public class TileIrrigation extends CyberCoreEndPowerTE {

	int workload = 0;

	boolean isWatering;
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return TileIrrigation.this.energystorage.getEnergyStored();
			case 1:
				return TileIrrigation.this.energystorage.getMaxEnergyStored();
			case 2:
				return TileIrrigation.this.workload;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				TileIrrigation.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				TileIrrigation.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				TileIrrigation.this.workload = value;
				;
				break;
			}

		}

		public int size() {
			return 3;
		}
	};
	private AABBTicket farmlandTicket;

	public TileIrrigation() {
		super(TileEntityNames.IrrigationTile, 0, 0);
	}

	public void onLoad() {

		isWatering = false;
		if (!world.isRemote) {
			if (this.energystorage.getEnergyStored() > 100) {
				if (!world.canBlockSeeSky(getPos())) {
					isWatering = true;
					farmlandTicket = FarmlandWaterManager.addAABBTicket(world,
							new AxisAlignedBB(pos).grow(CyberCoreConfig.getIrrigationRange()));
					farmlandTicket.validate();
				} else if (!world.isNightTime()) {
					isWatering = false;
					farmlandTicket.invalidate();
					
				} else if (isWatering == true) {
					this.energystorage.extractEnergy(150, false);
				}
				
			}
		}
	}

	public void onChunkUnloaded() {
		if (!world.isRemote && farmlandTicket != null) {
			farmlandTicket.invalidate();
		}
	}

	@Override
	public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
		return null;
	}

	@Override
	protected int getEnergyInSlot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getEnergyOutSlot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IIntArray getIntArray() {
		// TODO Auto-generated method stub
		return field_array;
	}

}
