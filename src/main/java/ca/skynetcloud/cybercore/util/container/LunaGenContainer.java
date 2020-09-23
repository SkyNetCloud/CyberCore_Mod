package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.util.TE.techblock.LunaGenTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class LunaGenContainer extends BaseContainerCore {

	public LunaGenContainer(int id, PlayerInventory inv) {
		this(id, inv, new LunaGenTileEntity());
	}

	public LunaGenContainer(int id, PlayerInventory player, LunaGenTileEntity tileentity) {
		super(id, ContainerNames.LUNARGEN_CON, player, tileentity, 4);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		this.addSlot(new SlotItemHandlerWithInfo(handler, 0, 95, 60, "slot.lunagen.cardcard") {
			@Override
			public boolean isItemValid(ItemStack itemstack) {

				if (itemstack.getItem() == ItemInit.lunar_upgrade_card_1.asItem()) {
					return true;
				} else if (itemstack.getItem() == ItemInit.lunar_upgrade_card_2.asItem()) {
					return true;
				} else if (itemstack.getItem() == ItemInit.lunar_upgrade_card_3.asItem()) {
					return true;
				} else if (itemstack.getItem() == ItemInit.lunar_upgrade_card_4.asItem()) {
					return true;
				}
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 1, 95, 31, "slot.util.acceleratecard") {
			@Override
			public boolean isItemValid(ItemStack itemstack) {

				if (itemstack.getItem() == ItemInit.speed_upgrade_card_1.asItem()) {
					return true;
				} else if (itemstack.getItem() == ItemInit.speed_upgrade_card_2.asItem()) {
					return true;
				} else if (itemstack.getItem() == ItemInit.speed_upgrade_card_3.asItem()) {
					return true;
				}
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 150, 86, "slot.util.powerin"));
		this.addSlot(
				new SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 168, 86, "slot.util.powerout"));
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

}
