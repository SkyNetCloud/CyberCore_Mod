package ca.skynetcloud.cybercore.util.TE.techblock;

import java.util.HashSet;

import ca.skynetcloud.cybercore.block.blocks.FencePowerGrid;
import ca.skynetcloud.cybercore.block.blocks.fences.BasicElecticFence;
import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryTileEntity;
import ca.skynetcloud.cybercore.init.TileEntityInit;
import ca.skynetcloud.cybercore.util.container.FPGSupplierContainer;
import ca.skynetcloud.cybercore.util.networking.helper.Names;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class FencePowerGridTE extends CoreEnergyInventoryTileEntity {
	protected final IIntArray field_array = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return FencePowerGridTE.this.energystorage.getEnergyStored();
			case 1:
				return FencePowerGridTE.this.energystorage.getMaxEnergyStored();
			case 2:
				return FencePowerGridTE.this.ticksPassed;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				FencePowerGridTE.this.energystorage.setEnergyStored(value);
				break;
			case 1:
				FencePowerGridTE.this.energystorage.setEnergyMaxStored(value);
				break;
			case 2:
				FencePowerGridTE.this.ticksPassed = value;
				break;
			}

		}

		public int getCount() {
			return 3;
		}
	};

	public FencePowerGridTE() {
		super(TileEntityInit.POWER_FENCE_GRID_TE, 12000, 2);
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
	}

	private void setPower(boolean powered) {
		if (level != null && level.getBlockState(worldPosition).getBlock() instanceof FencePowerGrid) {
			level.setBlockAndUpdate(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState()
					.setValue(FencePowerGrid.SUPPLYING, powered));
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
	public int energyPerAction() {
		return 1;
	}

	@Override
	public int ticksPerItem() {
		return 10;
	}

	@Override
	public IIntArray getIntArray() {
		return field_array;
	}

	@Override
	public String getNameString() {
		return "fpgsupplier";
	}

	@Override
	public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
		return new FPGSupplierContainer(id, inv, this);
	}

	@Override
	public ITextComponent getDisplayName() {

		return new TranslationTextComponent(Names.FENCE_POWER_GIRD);
	}

	@Override
	protected int getEnergyInSlot() {

		return 0;
	}

	@Override
	protected int getEnergyOutSlot() {

		return 1;
	}

}
