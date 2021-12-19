package ca.skynetcloud.cybercore.client.container;


import ca.skynetcloud.cybercore.client.init.ContainerInit;
import ca.skynetcloud.cybercore.common.blocks.techentity.PoweredFurnaceBlockEntity;
import ca.skynetcloud.cybercore.common.items.ItemTier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PoweredFurnaceMenu extends BaseBlockMenu{


    public PoweredFurnaceMenu(int id, Inventory inv)
    {

        this(id, inv, new PoweredFurnaceBlockEntity());
    }

    public PoweredFurnaceMenu(int id, Inventory player, PoweredFurnaceBlockEntity tileentity)
    {
        super(id, ContainerInit.POWERED_FURNACE_MENU.get(), player, tileentity, 15);
        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);


        for(int x = 0; x < 6; x++)
            this.addSlot(new SlotItemHandlerWithInfo(handler, x, 21 + x * 31, 28, "slot.powered_furnace.input"));
        for(int x = 0; x < 6; x++)
            this.addSlot(createOutoutSlot(handler, x + 6, 21 + x * 31 , 65));
    }
}
