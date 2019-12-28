package xyz.skynetcloud.cybercore.util.TE;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import xyz.skynetcloud.cybercore.block.tech.techblocks.CyberCorePowerBlock;
import xyz.skynetcloud.cybercore.init.BlockInit;
import xyz.skynetcloud.cybercore.init.OtherInit.TileEntityInit;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.PowerInventoryTileEntity;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;

@SuppressWarnings("unused")
public class PowerStorageTileEntity extends PowerInventoryTileEntity {

	private int currentLvl = -1;
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return PowerStorageTileEntity.this.powerstorage.getEnergyStored();
			case 1:
				return PowerStorageTileEntity.this.powerstorage.getMaxEnergyStored();
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				PowerStorageTileEntity.this.powerstorage.setEnergyStored(value);
				break;
			case 1:
				PowerStorageTileEntity.this.powerstorage.setEnergyMaxStored(value);
				break;
			}

		}

		public int size() {
			return 2;
		}
	};

	public PowerStorageTileEntity() {
		super(TileEntityInit.POWER_BOX_TE, 1000, 3);
	}

	@Override
	public void doUpdate() {
		doPowerLoop();
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	public void onSlotContentChanged() {
		if (world != null) {
			if (!world.isRemote) {
				int newLvl = markUpLevel(0, 3);
				if (currentLvl != newLvl) {
					switch (newLvl) {
					case 0:
						powerstorage.setEnergyMaxStored(1000);
						break;
					case 1:
						powerstorage.setEnergyMaxStored(10000);
						break;
					case 2:
						powerstorage.setEnergyMaxStored(100000);
						break;
					case 3:
						powerstorage.setEnergyMaxStored(1000000);
						break;
					}
					BlockState state = world.getBlockState(pos);
					if (state != null) {
						if (state.getBlock() == BlockInit.power_box) {
							world.setBlockState(pos, state.with(CyberCorePowerBlock.LEVEL, newLvl), 2);
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
		return "powerstorage";
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new PowerStorageContainer(id, inv, this);
	}

	@Override
	public int getPowerInSlot() {
		return 1;
	}

	@Override
	public int getPowerOutSlot() {
		return 2;
	}

	@Override
	public ITextComponent getDisplayName() {

		return null;
	}
}
