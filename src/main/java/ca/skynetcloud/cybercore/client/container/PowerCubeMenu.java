package ca.skynetcloud.cybercore.client.container;


import ca.skynetcloud.cybercore.client.init.ContainerInit;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import net.minecraft.world.entity.player.Inventory;

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
