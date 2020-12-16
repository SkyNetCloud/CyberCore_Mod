package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCubeTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowerCubeCon extends BaseContainerCore {

	public PowerCubeCon(int id, PlayerInventory inv) {
		this(id, inv, new PowerCubeTileEntity());
	}

	public PowerCubeCon(int id, PlayerInventory player, PowerCubeTileEntity tileentity) {
		super(id, ContainerNames.POWER_CUBE_CON, player, tileentity, 3);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		this.addSlot(new ChangeCheckSlot(tileentity, handler, 0, 71, 55, "slot.util.powerstoragecardcardslot"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 89, 77, "slot.util.powerin"));
		this.addSlot(
				new SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 107, 77, "slot.util.powerout"));
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (index > 35) {
				if (!this.mergeItemStack(stack1, 0, 34, true)) {
					return ItemStack.EMPTY;
				}
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
		private PowerCubeTileEntity te;

		public ChangeCheckSlot(PowerCubeTileEntity te, IItemHandler itemHandler, int index, int xPosition,
				int yPosition, String input) {
			super(itemHandler, index, xPosition, yPosition, input);
			this.te = te;
		}

		@Override
		public void onSlotChanged() {
			te.onSlotContentChanged();
			super.onSlotChanged();
		}
	}
}
