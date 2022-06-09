package ca.skynetcloud.cybercore.client.world.level.block.techentity;


import ca.skynetcloud.cybercore.client.container.PowerCubeMenu;
import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyInventoryBlockEntity;
import ca.skynetcloud.cybercore.client.init.MainInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;

public class PowerCubeBlockEntity extends PyroEnergyInventoryBlockEntity {

	private int currentLvl = -1;
	public int[] ticksPassed = new int[6];
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
				case 0 -> PowerCubeBlockEntity.this.energystorage.setEnergy(value);
				case 1 -> PowerCubeBlockEntity.this.energystorage.setEnergyMax(value);
			}
		}
		public int getCount()
		{
			return 2;
		}
	};
	public PowerCubeBlockEntity()
	{
		this(BlockPos.ZERO, MainInit.POWER_CABLE.get().defaultBlockState());
	}

	public PowerCubeBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(MainInit.POWER_CUBE_BE.get(), worldPosition, blockState, 1000, 4, 4);
	}

	@Override
	public ContainerData getContainerData()
	{
		return data;
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
					energystorage.receiveEnergy(100 * getTicksPerAmount(), false);
					ticks = 0;
				}
			}
		}
	}

	public int getTicksPerAmount()
	{
		return 550;
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

}
