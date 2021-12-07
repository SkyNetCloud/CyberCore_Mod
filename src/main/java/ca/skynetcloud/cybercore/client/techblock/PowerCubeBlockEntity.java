package ca.skynetcloud.cybercore.client.techblock;

import java.util.HashSet;

import ca.skynetcloud.cybercore.common.block.blocks.PowerCube;
import ca.skynetcloud.cybercore.common.block.blocks.fences.BasicElecticFence;
import ca.skynetcloud.cybercore.client.enegry.baseclasses.CoreEnergyInventoryBlockEntity;

import ca.skynetcloud.cybercore.client.init.CoreInit;
import ca.skynetcloud.cybercore.common.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.client.container.PowerCubeCon;
import ca.skynetcloud.cybercore.client.networking.config.CyberConfig.Config;
import ca.skynetcloud.cybercore.client.networking.helper.NameHelper;
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

public class PowerCubeBlockEntity extends CoreEnergyInventoryBlockEntity {

	private int currentLvl = -1;
	protected final ContainerData data = new ContainerData()
	{
		public int get(int index)
		{
			return switch (index)
					{
						case 0 -> PowerCubeBlockEntity.this.energystorage.getEnergyStored();
						case 1 -> PowerCubeBlockEntity.this.energystorage.getMaxEnergyStored();
						default -> 0;
					};
		}

		public void set(int index, int value)
		{
			switch (index)
			{
				case 0 -> PowerCubeBlockEntity.this.energystorage.setEnergyStored(value);
				case 1 -> PowerCubeBlockEntity.this.energystorage.setEnergyMaxStored(value);
			}
		}
		public int getCount()
		{
			return 2;
		}
	};
	public PowerCubeBlockEntity()
	{
		this(BlockPos.ZERO, CoreInit.BlockInit.Battery.defaultBlockState());
	}

	public PowerCubeBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(CoreInit.BlockEntityInit.POWER_CUBE_TE, worldPosition, blockState, Config.POWERLMIT.get(), 4);
	}

	@Override
	public ContainerData getContainerData()
	{
		return data;
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

		return new TranslatableComponent(NameHelper.POWER_BOX_CON_NAME);
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
