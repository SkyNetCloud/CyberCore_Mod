package ca.skynetcloud.cybercore.common.blocks.techentity;


import ca.skynetcloud.cybercore.client.container.PowerCubeMenu;
import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyInventoryBlockEntity;
import ca.skynetcloud.cybercore.client.init.BlockEntityInit;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.state.BlockState;

public class PowerCubeBlockEntity extends PyroEnergyInventoryBlockEntity {

	//private static final int power_per_lvl = -1;
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
		this(BlockPos.ZERO, BlockInit.POWER_CABLE.get().defaultBlockState());
	}

	public PowerCubeBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(BlockEntityInit.POWER_CUBE_BE.get(), worldPosition, blockState, 1000, 4, 4);
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
