package ca.skynetcloud.cybercore.client.networking.handler;

import ca.skynetcloud.cybercore.client.techblock.ItemPipeTileEntity;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
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
