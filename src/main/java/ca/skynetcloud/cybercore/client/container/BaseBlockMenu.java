package ca.skynetcloud.cybercore.client.container;

import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyBlockEntity;
import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyInventoryBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;

public class BaseBlockMenu extends BaseMenu {

    protected final PyroEnergyInventoryBlockEntity blockEntity;
    protected final ContainerData data;

    public BaseBlockMenu(int id, MenuType<?> type, Inventory player, PyroEnergyInventoryBlockEntity BlockEntity, int slots)
    {
        super(id, type, player);
        this.blockEntity = BlockEntity;
        data = BlockEntity.getContainerData();
        addDataSlots(data);
    }



    @Override
    public boolean stillValid(Player playerIn)
    {
        return blockEntity.isUsableByPlayer(playerIn);
    }

    public PyroEnergyBlockEntity getTE()
    {
        return blockEntity;
    }

    public int getValue(int id)
    {
        return data.get(id);
    }
}
