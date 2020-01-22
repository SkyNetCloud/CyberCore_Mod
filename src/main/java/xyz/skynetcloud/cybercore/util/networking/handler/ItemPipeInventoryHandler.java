package xyz.skynetcloud.cybercore.util.networking.handler;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.items.ItemStackHandler;
import xyz.skynetcloud.cybercore.util.TE.cable.ItemPipeTileEntity;

public class ItemPipeInventoryHandler extends ItemStackHandler {
	private final ItemPipeTileEntity tube;
	private final Direction face;

	public ItemPipeInventoryHandler(ItemPipeTileEntity tube, Direction face) {
		super(1);
		this.tube = tube;
		this.face = face;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (stack.getCount() <= 0) {
			return ItemStack.EMPTY;
		}
		if (!simulate) {
			this.tube.markDirty();
		}
		return this.tube.enqueueItemStack(stack.copy(), this.face, simulate);
	}
}
