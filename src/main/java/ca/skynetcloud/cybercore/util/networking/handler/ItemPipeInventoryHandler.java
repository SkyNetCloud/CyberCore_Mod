package ca.skynetcloud.cybercore.util.networking.handler;

import ca.skynetcloud.cybercore.util.TE.techblock.ItemPipeTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.items.ItemStackHandler;

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
			this.tube.setChanged();
		}
		return this.tube.enqueueItemStack(stack.copy(), this.face, simulate);
	}
}
