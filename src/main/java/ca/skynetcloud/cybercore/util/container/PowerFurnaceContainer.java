package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PowedFurnaceTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowerFurnaceContainer extends BaseContainerCore {

	public PowerFurnaceContainer(int id, PlayerInventory inv) {
		this(id, inv, new PowedFurnaceTileEntity());
	}

	public PowerFurnaceContainer(int id, PlayerInventory player, PowedFurnaceTileEntity tileentity) {
		super(id, ContainerNames.POWER_FURNCAE_CON, player, tileentity, 15);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		String input = "slot.poweredfurnace.input";

		String output = "slot.poweredfurnace.output";

		this.addSlot(new SlotItemHandlerWithInfo(handler, 0, 21, 27, input));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 1, 48, 27, input));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 2, 75, 27, input));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 3, 102, 27, input));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 4, 129, 27, input));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 5, 156, 27, input));

		this.addSlot(new SlotItemHandlerWithInfo(handler, 6, 21, 64, output) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 7, 48, 64, output) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 8, 75, 64, output) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 9, 102, 64, output) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 10, 129, 64, output) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 11, 156, 64, output) {
			@Override
			public boolean isItemValid(ItemStack stack) {
				return false;
			}
		});

		this.addSlot(new BaseContainerCore.SlotItemHandlerWithInfo(handler, 12, 197, 75, "slot.util.speedupgrade") {
			@Override
			public boolean isItemValid(ItemStack stack) {
				if (stack.getItem() == ItemInit.speed_upgrade_card_1) {
					return true;
				} else if (stack.getItem() == ItemInit.speed_upgrade_card_2) {
					return true;
				} else if (stack.getItem() == ItemInit.speed_upgrade_card_3) {
					return true;
				} else if (stack.getItem() == ItemInit.speed_upgrade_card_4) {
					return true; 
				}
				return false;
			}
		});
		this.addSlot(new BaseContainerCore.SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 197, 38,
				"slot.util.powerin"));
		this.addSlot(new BaseContainerCore.SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 197, 56,
				"slot.util.powerout"));
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
