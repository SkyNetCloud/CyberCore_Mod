package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.HashSet;

import ca.skynetcloud.cybercore.block.blocks.PowerCube;
import ca.skynetcloud.cybercore.block.blocks.fences.BasicElecticFence;
import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.init.BlockEntityInit;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.container.PowerCubeCon;
import ca.skynetcloud.cybercore.util.networking.config.CyberConfig.Config;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;

public class PowerCubeTileEntity extends CoreEnergyInventoryTileEntity {

	private int currentLvl = -1;
	protected final ContainerData field_array = new ContainerData() {
		public int get(int index) {
			switch (index) {
			case 0:
				return PowerCubeTileEntity.this.energystorage.getEnergyStored();
			case 1:
				return PowerCubeTileEntity.this.energystorage.getMaxEnergyStored();
			case 2:
				return PowerCubeTileEntity.this.ticksPassed;
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
			case 2:
				PowerCubeTileEntity.this.ticksPassed = value;
				break;
			}

		}

		public int getCount() {
			return 3;
		}
	};

	public PowerCubeTileEntity(BlockPos worldPosition, BlockState blockState) {
		super(BlockEntityInit.POWER_CUBE_TE, Config.POWERLMIT.get(), 3, worldPosition, blockState);
	}

	@Override
	public ContainerData getIntArray() {
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
		super.doUpdate();
		if (level == null)
			return;
		ticksPassed++;
		if (energystorage.getEnergyStored() <= 0)
			setPower(false);
		else if (!getConnected().isEmpty() && ticksPassed >= ticksPerItem()) {
			energystorage.extractEnergy(energyPerAction());
			setPower(true);
			resetProgress(true);
		}

		doEnergyLoop();
	}

	@Override
	public void load(CompoundTag compound) {
		super.load(compound);
	}

	@Override
	public Component getDisplayName() {

		return new TranslatableComponent(Names.POWER_BOX_CON_NAME);
	}

	private void setPower(boolean powered) {
		if (level != null && level.getBlockState(worldPosition).getBlock() instanceof PowerCube) {
			level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState()
					.setValue(PowerCube.SUPPLYING, powered));
		}
	}

	private HashSet<BlockPos> getConnected() {
		HashSet<BlockPos> list = new HashSet<>();
		if (level != null) {
			for (Direction direction : Direction.values()) {
				BlockPos blockPos = this.worldPosition.relative(direction);
				if (level.getBlockState(blockPos).getBlock() instanceof BasicElecticFence)
					list.add(blockPos);
			}
		}
		return list;
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {

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
