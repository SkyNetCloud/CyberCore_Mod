package ca.skynetcloud.cybercore.client.container;


import ca.skynetcloud.cybercore.client.init.ContainerInit;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.common.items.ItemTier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowerCubeMenu extends BaseBlockMenu{

    public PowerCubeMenu(int id, Inventory inv)
    {
        this(id, inv, new PowerCubeBlockEntity());
    }

    public PowerCubeMenu(int id, Inventory player, PowerCubeBlockEntity tileentity)
    {
        super(id, ContainerInit.POWER_CUBE_MENU.get(),player, tileentity, 3);

    }
}
