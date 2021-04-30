package ca.skynetcloud.cybercore.util.TE.techblock;

import ca.skynetcloud.cybercore.api.Names;
import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.container.PowerCubeCon;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class PowerCubeTileEntity extends CoreEnergyInventoryTileEntity {

	private int currentLvl = -1;
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return PowerCubeTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return PowerCubeTileEntity.this.energystorage.getMaxEnergyStored();
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				PowerCubeTileEntity.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				PowerCubeTileEntity.this.energystorage.setEnergyMaxStored(value);
				break;
			}

		}

		public int getCount() {
			return 2;
		}
	};

	public PowerCubeTileEntity() {
		super(TileEntityNames.POWER_CUBE_TE, CyberCoreConfig.POWERLMIT.get(), 3);
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	@Override
	public void onSlotContentChanged() {
		if (level != null) {
			if (!level.isClientSide) {
				int newLvl = getMarkcard(0, ItemType.POWER_UPGRADE);
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
					currentLvl = newLvl;
				}
			}
		}
	}

	@Override
	public void doUpdate() {
		doEnergyLoop();
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
	}

	@Override
	public ITextComponent getDisplayName() {
		
		return new TranslationTextComponent(Names.POWER_BOX_CON_NAME);
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		
		return new PowerCubeCon(id, inv, this);
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
