package ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.handler;

import ca.skynetcloud.cybercore.client.world.level.block.techentity.ItemCableBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class ItemCableInvtoryHandler extends ItemStackHandler {

    private final ItemCableBlockEntity itemcables;
    private final Direction face;

    public ItemCableInvtoryHandler(ItemCableBlockEntity itemcables, Direction face){
        super(1);
        this.itemcables = itemcables;
        this.face = face;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate){
        if (stack.getCount() <= 0) {
            return ItemStack.EMPTY;

        }
        if (!simulate) {
            this.itemcables.setChanged();
        }
        return this.itemcables.enqueueItemStack(stack.copy(), this.face, simulate);
    }
}
