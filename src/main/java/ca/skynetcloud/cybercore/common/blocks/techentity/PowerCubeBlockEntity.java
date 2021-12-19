package ca.skynetcloud.cybercore.common.blocks.techentity;


import ca.skynetcloud.cybercore.client.container.PowerCubeMenu;
import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyInventoryBlockEntity;
import ca.skynetcloud.cybercore.client.init.BlockEntityInit;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.common.items.ItemTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;

import static ca.skynetcloud.cybercore.common.items.ItemTier.ItemType.SOLAR_LENS;

public class PowerCubeBlockEntity extends PyroEnergyInventoryBlockEntity {

	public int ticks = 0;
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
		this(BlockPos.ZERO, BlockInit.POWER_CABLE.get().defaultBlockState());
	}

	public PowerCubeBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(BlockEntityInit.POWER_CUBE_BE.get(), worldPosition, blockState, 1000, 4, 4);
	}

	@Override
	public void doUpdate()
	{
		if (level != null && level.isDay() && level.canSeeSky(worldPosition.above()))
		{
			if (energystorage.getMaxEnergyStored() - energystorage.getEnergyStored() > 0)
			{
				ticks++;
				if (ticks >= getTicksPerAmount())
				{
					energystorage.receiveEnergy(getEnergyPerTick(getUpgradeTier(15, SOLAR_LENS)));
					ticks = 0;
				}
			}
		}
	}

	private int getEnergyPerTick(int focusLevel)
	{
		return switch (focusLevel)
				{
					case 1 -> 20;
					case 2 -> 60;
					case 3 -> 180;
					case 4 -> 540;
					default -> 0;
				};
	}

	public int getTicksPerAmount()
	{
		return 80 - getUpgradeTier(ItemTier.ItemType.SPEED_UP) * 15;
	}

	@Override
	public ContainerData getContainerData()
	{
		return data;
	}


	@Override
	public String getNameString()
	{
		return "power_cube_storage";
	}

	@Override
	public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
		return new PowerCubeMenu(id, inv, this);
	}

	@Override
	protected int getUpgradeSlot() {
		return 12;
	}
}
