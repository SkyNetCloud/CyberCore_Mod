package xyz.skynetcloud.cybercore.util.TE;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import xyz.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;
import xyz.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;
import xyz.skynetcloud.cybercore.util.networking.config.ClientSideConfig;

public class PowerStorageTileEntity extends CyberCoreEndPowerTE {

	private int currentLvl = -1;
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return PowerStorageTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return PowerStorageTileEntity.this.energystorage.getMaxEnergyStored();
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				PowerStorageTileEntity.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				PowerStorageTileEntity.this.energystorage.setEnergyMaxStored(value);
				break;
			}

		}

		public int size() {
			return 2;
		}
	};

	public PowerStorageTileEntity() {
		super(TileEntityNames.POWER_BOX_TE, ClientSideConfig.PowerLmit.get(), 3);

	}

	@Override
	public void doUpdate() {
		doEnergyLoop();
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	@Override
	public void onSlotContentChanged() {
		if (world != null) {
			if (!world.isRemote) {
				int newLvl = getMarkLvl(0, 3);
				if (currentLvl != newLvl) {
					switch (currentLvl) {
					case 0:
						energystorage.setEnergyMaxStored(1000);
						break;
					case 1:
						energystorage.setEnergyMaxStored(10000);
						break;
					case 2:
						energystorage.setEnergyMaxStored(100000);
						break;
					case 3:
						energystorage.setEnergyMaxStored(1000000);
						break;
					}
					BlockState state = world.getBlockState(pos);
					if (state != null) {
						if (state.getBlock() == BlockInit.POWER_BOX) {
							world.setBlockState(pos, state.with(CyberCorePowerBlock.LVL, newLvl), 2);
							markDirty();
						}
					}
					currentLvl = newLvl;
				}
			}
		}
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
	}

	@Override
	public String getNameString() {
		return "power_storage";
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new PowerStorageContainer(id, inv, this);
	}

	@Override
	public int getEnergyInSlot() {
		return 1;
	}

	@Override
	public int getEnergyOutSlot() {
		return 2;
	}
}
