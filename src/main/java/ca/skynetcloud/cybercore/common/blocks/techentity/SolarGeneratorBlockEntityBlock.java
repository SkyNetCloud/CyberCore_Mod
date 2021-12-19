package ca.skynetcloud.cybercore.common.blocks.techentity;

import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyInventoryBlockEntity;
import ca.skynetcloud.cybercore.client.init.BlockEntityInit;
import ca.skynetcloud.cybercore.client.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SolarGeneratorBlockEntityBlock extends PyroEnergyInventoryBlockEntity {

    protected final ContainerData data = new ContainerData()
    {
        public int get(int index)
        {
            return switch (index)
                    {
                        case 0 -> SolarGeneratorBlockEntityBlock.this.energystorage.getEnergyStored();
                        case 1 -> SolarGeneratorBlockEntityBlock.this.energystorage.getMaxEnergyStored();
                        case 2 -> SolarGeneratorBlockEntityBlock.this.ticksPassed;
                        default -> 0;
                    };
        }

        public void set(int index, int value)
        {
            switch (index)
            {
                case 0 -> SolarGeneratorBlockEntityBlock.this.energystorage.setEnergyStored(value);
                case 1 -> SolarGeneratorBlockEntityBlock.this.energystorage.setEnergyMaxStored(value);
                case 2 -> SolarGeneratorBlockEntityBlock.this.ticksPassed = value;
            }
        }
        public int getCount()
        {
            return 3;
        }
    };

    public SolarGeneratorBlockEntityBlock()
    {
        this(BlockPos.ZERO, BlockInit.SOLARGEN.get().defaultBlockState());
    }

    public SolarGeneratorBlockEntityBlock(BlockPos pos, BlockState state)
    {
        super(BlockEntityInit.SOLORGEN_BE.get(), pos, state, 10000, 5, 0);
    }

    @Override
    public ContainerData getContainerData() {
        return data;
    }

    @Override
    protected int getUpgradeSlot() {
        return 0;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return null;
    }
}
