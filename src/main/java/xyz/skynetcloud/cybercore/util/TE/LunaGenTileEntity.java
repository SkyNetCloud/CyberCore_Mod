package xyz.skynetcloud.cybercore.util.TE;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import xyz.skynetcloud.cybercore.util.CyberCoreConstants;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.PowerInventoryTileEntity;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;

public class LunaGenTileEntity extends PowerInventoryTileEntity
		implements ITickableTileEntity, INamedContainerProvider {

	int workload = 0;

	protected final IIntArray field_array = new IIntArray() {

		@Override
		public int get(int arg0) {

			return 0;
		}

		@Override
		public void set(int arg0, int arg1) {

		}

		@Override
		public int size() {

			return 0;
		}

	};

	public LunaGenTileEntity() {
		super(TileEntityTypes.LUNAR_GEN_MACHINE_TE, 10000, 4);

	}

	@Override
	public void doUpdate() {

		if (world.getMoonPhase() == 4) {
			if (powerstorage.getMaxEnergyStored() - powerstorage.getEnergyStored() > 0) {
				workload++;
				if (workload >= getTicksPerAmount()) {
					powerstorage.receiveEnergy(getPowerPerTick(markUpLevel(0, CyberCoreConstants.LUNASOLARFOCUS_TYPE)),
							removed);
					workload = 0;
				}
			}
		}

		doPowerLoop();
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {

		return new LunaGenContainer(id, inv, this);
	}

	@Override
	public ITextComponent getDisplayName() {

		return null;
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
	public IIntArray getIntArray() {
		return field_array;
	}

	private int getPowerPerTick(int lvl) {
		switch (lvl) {
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

		return 25 - (markUpLevel(1, CyberCoreConstants.SPEEDUPGRADE_INFO_TYPE) * 15);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {

		compound.putInt("workload", workload);
		return super.write(compound);
	}

	@Override
	public void read(CompoundNBT compound) {
		workload = compound.getInt("workload");
		super.read(compound);
	}

}
