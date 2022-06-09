package ca.skynetcloud.cybercore.client.container;

import ca.skynetcloud.cybercore.client.init.MainInit;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.PoweredFurnaceBlockEntity;
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
        super(id, MainInit.POWERED_FURNACE_MENU.get(), player, tileentity, 15);
        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        for(int x = 0; x < 6; x++)
            this.addSlot(new SlotItemHandlerWithInfo(handler, x, 21 + x * 22 , 27, "slot.powered_furnace.input"));
        for(int x = 0; x < 6; x++)
            this.addSlot(createOutoutSlot(handler, x + 6, 21 + x * 22 , 64));
    }
}
