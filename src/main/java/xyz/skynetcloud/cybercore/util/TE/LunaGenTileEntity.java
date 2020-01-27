package xyz.skynetcloud.cybercore.util.TE;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import xyz.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import xyz.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;
import xyz.skynetcloud.cybercore.util.networking.config.ClientSideConfig;
import xyz.skynetcloud.cybercore.util.networking.util.CyberCoreConstants;

public class LunaGenTileEntity extends CyberCoreEndPowerTE {
	int workload = 0;
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return LunaGenTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return LunaGenTileEntity.this.energystorage.getMaxEnergyStored();
			case 2:
				return LunaGenTileEntity.this.workload;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				LunaGenTileEntity.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				LunaGenTileEntity.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				LunaGenTileEntity.this.workload = value;
				;
				break;
			}

		}

		public int size() {
			return 3;
		}
	};

	public LunaGenTileEntity() {
		super(TileEntityNames.LUNAR_GEN_MACHINE_TE, ClientSideConfig.PowerLmit.get(), 4);

	}

	public void doUpdate() {
		if (world.isDaytime() && world.canBlockSeeSky(pos.up())) {
			if (energystorage.getMaxEnergyStored() - energystorage.getEnergyStored() > 0) {
				workload++;
				if (workload >= getTicksPerAmount()) {
					energystorage
							.receiveEnergy(getEnergyPerTick(getMarkLvl(0, CyberCoreConstants.LUNASOLARFOCUS_TYPE)));
					workload = 0;
				}
			}
		}
		this.doEnergyLoop();
	}

	public IIntArray getIntArray() {
		return this.field_array;
	}

	private int getEnergyPerTick(int focusLevel) {
		switch (focusLevel) {
		case 1:
			return 20;
		case 2:
			return 60;
		case 3:
			return 180;
		case 4:
			return 540;
		}

		return 0;
	}

	public int getTicksPerAmount() {
		return ClientSideConfig.LunarGenPerTick.get()
				- (getMarkLvl(1, CyberCoreConstants.SPEEDUPGRADE_INFO_TYPE) * ClientSideConfig.LunarGenPerTick.get());
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("workload", workload);
		super.write(compound);
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		this.workload = compound.getInt("workload");
		super.read(compound);
	}

	@Override
	public String getNameString() {
		return "solargenerator";
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new LunaGenContainer(id, inv, this);
	}

	public int getEnergyInSlot() {
		return 2;
	}

	public int getEnergyOutSlot() {
		return 3;
	}

}
