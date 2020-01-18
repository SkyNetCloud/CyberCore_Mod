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
import xyz.skynetcloud.cybercore.util.ClientSideConfig;
import xyz.skynetcloud.cybercore.util.CyberCoreConstants;
import xyz.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;

public class LunaGenTileEntity extends CyberCoreEndPowerTE {

	private int currentLvl = -1;
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
			return 100;
		case 3:
			return 1200;
		case 4:
			return 2000;
		}

		return 0;
	}

	public void onSlotContentChanged() {
		if (world != null) {
			if (!world.isRemote) {
				int newLvl = getMarkLvl(0, CyberCoreConstants.POWER_LVL_TYPE);
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
