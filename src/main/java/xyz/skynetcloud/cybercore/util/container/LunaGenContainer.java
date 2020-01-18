package xyz.skynetcloud.cybercore.util.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import xyz.skynetcloud.cybercore.api.containers.ContainerNames;
import xyz.skynetcloud.cybercore.util.TE.LunaGenTileEntity;

public class LunaGenContainer extends BaseContainerCore {

	public LunaGenContainer(int id, PlayerInventory inv) {
		this(id, inv, new LunaGenTileEntity());
	}

	public LunaGenContainer(int id, PlayerInventory player, LunaGenTileEntity tileentity) {
		super(id, ContainerNames.LUNARGEN_CON, player, tileentity, 4);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		//sthis.addSlot(new ChangeCheckSlot(tileentity, handler, 0, 132, 64, "slot.util.powerlvlUp"));
		this.addSlot(new ChangeCheckSlot(tileentity ,handler, 0, 84, 71, "slot.lunagen.lvlcard"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 1, 45, 33, "slot.util.acceleratecard"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 150, 86, "slot.util.powerin"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 168, 86, "slot.util.powerout"));
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (index > 37) {
				if (!this.mergeItemStack(stack1, 0, 35, true))
					return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (index < 36) {
				if (index >= 0 && index < 27) {
					if (!this.mergeItemStack(stack1, 27, 35, false))
						return ItemStack.EMPTY;
				} else if (index >= 27 && index < 36 && !this.mergeItemStack(stack1, 0, 26, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 0, 35, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();

			}
			if (stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

	class ChangeCheckSlot extends SlotItemHandlerWithInfo {
		private LunaGenTileEntity te;

		public ChangeCheckSlot(LunaGenTileEntity te, IItemHandler itemHandler, int index, int xPosition,
				int yPosition, String usage) {
			super(itemHandler, index, xPosition, yPosition, usage);
			this.te = te;
		}

		@Override
		public void onSlotChanged() {
			te.onSlotContentChanged();
			super.onSlotChanged();
		}
	}

}
