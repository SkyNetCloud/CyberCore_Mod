package ca.skynetcloud.cybercore.util.container;


import ca.skynetcloud.cybercore.init.CoreInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PoweredFurnaceBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PoweredFurnaceMenu extends BaseContainerCore {

    public PoweredFurnaceMenu(int id, Inventory inv)
    {
        this(id, inv, new PoweredFurnaceBlockEntity());
    }
    public PoweredFurnaceMenu(int id, Inventory player, PoweredFurnaceBlockEntity tileentity)
    {
        super(id, CoreInit.ContainersInit.POWERED_FURNACE_CON, player, tileentity, 15);
        IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
        for(int x = 0; x < 6; x++)
            this.addSlot(new SlotItemHandlerWithInfo(handler, x, 21 + x * 22 , 27, "slot.megafurnace.input"));
        for(int x = 0; x < 6; x++)
            this.addSlot(createOutoutSlot(handler, x + 6, 21 + x * 22 , 64));
        this.addSlot(createSpeedUpgradeSlot(handler, 12, 109, 85));

    }
}
