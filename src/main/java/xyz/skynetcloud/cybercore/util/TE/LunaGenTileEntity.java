package xyz.skynetcloud.cybercore.util.TE;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.skynetcloud.cybercore.util.CyberCoreConstants;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.PowerInventoryTileEntity;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;

public class LunaGenTileEntity extends PowerInventoryTileEntity {
	int workload = 0;
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return LunaGenTileEntity.this.powerstorage.getEnergyStored();
			case 1:
				return LunaGenTileEntity.this.powerstorage.getMaxEnergyStored();
			case 2:
				return LunaGenTileEntity.this.workload;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				LunaGenTileEntity.this.powerstorage.setEnergyStored(value);
				break;
			case 1:
				LunaGenTileEntity.this.powerstorage.setEnergyMaxStored(value);
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
		super(null, 10000, 4);
	}

	@Override
	public void doUpdate() {
		if (world.isDaytime() && world.canBlockSeeSky(pos.up())) {
			if (powerstorage.getMaxEnergyStored() - powerstorage.getEnergyStored() > 0) {
				workload++;
				if (workload >= getTicksPerAmount()) {
					powerstorage.receiveEnergy(getEnergyPerTick(markUpLevel(0, CyberCoreConstants.LUNASOLARFOCUS_TYPE)));
					workload = 0;
				}
			}
		}
		doPowerLoop();
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	private int getEnergyPerTick(int level) {
		switch (level) {
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
		return 80 - (markUpLevel(1, CyberCoreConstants.SPEEDUPGRADE_INFO_TYPE) * 15);
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
		return "lunagenerator";
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new LunaGenContainer(id, inv, this);
	}

	@Override
	public int getPowerInSlot() {
		return 2;
	}

	@Override
	public int getPowerOutSlot() {
		return 3;
	}

	@Override
	public ITextComponent getDisplayName() {
		// TODO Auto-generated method stub
		return new StringTextComponent("lunar_te");
	}

}
