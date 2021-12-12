package ca.skynetcloud.cybercore.client.energy.baseclasses;

import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

abstract public class PyroEnergyInventoryBlockEntity extends PyroEnergyBlockEntity
{
    protected ItemStackHandler itemhandler;
    protected LazyOptional<IItemHandler> inventoryCap;
    protected int ticksPassed = 0;
    protected int tier;

    public PyroEnergyInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int energyStorage, int invSize, int tier)
    {
        super(type,pos, state, energyStorage);
        itemhandler = new ItemStackHandler(invSize);
        inventoryCap = LazyOptional.of(() -> itemhandler);
        this.tier = tier;
    }

    /**
     * Get Items that will be spawned in the Level when the block is destroyed
     * @return list of items to spawn
     */
    public List<ItemStack> getInventoryContent()
    {
        List<ItemStack> stacks = new ArrayList<ItemStack>();
        for (int i = 0; i < itemhandler.getSlots(); i++)
            stacks.add(itemhandler.getStackInSlot(i).copy());
        return stacks;
    }

    @Override
    public void doUpdate()
    {
        doEnergyLoop();
    }

    protected void resetProgress(boolean forced)
    {
        ticksPassed = 0;
    }

    public void doEnergyLoop()
    {

    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return inventoryCap.cast();
        return super.getCapability(capability, facing);
    }

    @Override
    public CompoundTag save(CompoundTag compound)
    {
        compound.put("inventory", itemhandler.serializeNBT());
        compound.putInt("tickspassed", ticksPassed);
        return super.save(compound);
    }


    @Override
    public void load(CompoundTag compound)
    {
        super.load(compound);
        int slotamount = itemhandler.getSlots();// prevent crash
        itemhandler.deserializeNBT(compound.getCompound("inventory"));
        if (itemhandler.getSlots() != slotamount)
            itemhandler.setSize(slotamount);// prevent crash when invsize changed while update
        this.ticksPassed = compound.getInt("tickspassed");
    }

    public int energyPerAction()
    {
        return CyberConfig.Config.Energy_PerAction.get();
    }

    public int ticksPerItem()
    {
        return CyberConfig.Config.TICKS_PER_ITEM.get();
    }

}

