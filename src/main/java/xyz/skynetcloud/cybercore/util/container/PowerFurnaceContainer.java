package xyz.skynetcloud.cybercore.util.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import xyz.skynetcloud.cybercore.api.containers.ContainerNames;
import xyz.skynetcloud.cybercore.util.TE.PowedFurnaceTileEntity;

public class PowerFurnaceContainer extends BaseContainerCore {

	public PowerFurnaceContainer(int id, PlayerInventory inv) {
		this(id, inv, new PowedFurnaceTileEntity());
	} 

	public PowerFurnaceContainer(int id, PlayerInventory player, PowedFurnaceTileEntity tileentity) {
		super(id, ContainerNames.POWER_FURNCAE_CON, player, tileentity, 15);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		for (int y = 0; y < 2; y++) {
			String usage = "slot.poweredfurnace.input";
			if (y == 1) {
				usage = "slot.util.output";
			}
			for (int x = 0; x < 6; x++) {
				this.addSlot(new SlotItemHandlerWithInfo(handler, x + y * 6, 26 + x * 22, 26 + y * 37, usage));
			}
		}
		this.addSlot(new SlotItemHandlerWithInfo(handler, 12, 113, 85, "slot.util.speedupgrade"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 150, 86, "slot.util.energyin"));
		this.addSlot(
				new SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 168, 86, "slot.util.energyout"));
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
				if (!this.mergeItemStack(stack1, 36, 42, false)) {
					return ItemStack.EMPTY;
				} else if (index >= 0 && index < 27) {
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

}
