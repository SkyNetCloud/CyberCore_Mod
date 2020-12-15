package ca.skynetcloud.cybercore.util.TE.techblock;

import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.api.tileentity.TileEntityNames;
import ca.skynetcloud.cybercore.block.tech.blocks.CyberCorePowerBlock;
import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.container.LunaGenContainer;
import ca.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;
import ca.skynetcloud.cybercore.util.networking.util.CyberCoreConstants;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

public class LunaGenTileEntity extends CoreEnergyInventoryTileEntity {
	int workload = 0;
	private int currentcard = -1;
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
		super(TileEntityNames.LUNAR_GEN_MACHINE_TE, CyberCoreConfig.POWERLMIT.get(), 6, CyberCoreConstants.LUNAR_Block);

	}

	public void doUpdate() {
		if (world.isDaytime() && world.canBlockSeeSky(pos.up())) {
			if (energystorage.getMaxEnergyStored() - energystorage.getEnergyStored() > 0) {
				workload++;
				if (workload >= getTicksPerAmount()) {
					energystorage.receiveEnergy(getEnergyPerTick(getMarkcard(0, ItemType.SOLAR_FOCUS)));
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

	@Override
	public void onSlotContentChanged() {
		if (world != null) {
			if (!world.isRemote) {
				int newcard = getMarkcard(1, ItemType.POWER_UPGRADE);
				if (currentcard != newcard) {
					switch (currentcard) {
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
							world.setBlockState(pos, state.with(CyberCorePowerBlock.card, newcard), 2);
							markDirty();
						}
					}
					currentcard = newcard;
				}
			}
		}
	}

	public int getTicksPerAmount() {
		return 80 - (getMarkcard(1, ItemType.SPEED_UPGRADE) * 15);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("workload", workload);
		super.write(compound);
		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT compound) {
		this.workload = compound.getInt("workload");
		super.read(state, compound);
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

	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(TextFormatting.BLUE + "Lunar Gen");
	}

}
