package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.init.ContainerInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCubeTileEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowerCubeCon extends BaseContainerCore {

	public PowerCubeCon(int id, Inventory inv, PowerCubeTileEntity tileentity) {
		super(id, ContainerInit.POWER_CUBE_CON, inv, tileentity, 3);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		this.addSlot(new ChangeCheckSlot(tileentity, handler, 0, 71, 55, "slot.util.powerstoragecardcardslot"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 89, 77, "slot.util.powerin"));
		this.addSlot(
				new SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 107, 77, "slot.util.powerout"));
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();

			if (index > 35) {
				if (!this.moveItemStackTo(stack1, 0, 34, true)) {
					return ItemStack.EMPTY;
				}
			} else if (index < 36) {
				if (index >= 0 && index < 27) {
					if (!this.moveItemStackTo(stack1, 27, 35, false))
						return ItemStack.EMPTY;
				} else if (index >= 27 && index < 36 && !this.moveItemStackTo(stack1, 0, 26, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(stack1, 0, 35, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();

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
		public void setChanged() {
			te.onSlotContentChanged();
			super.setChanged();
		}
	}

}
