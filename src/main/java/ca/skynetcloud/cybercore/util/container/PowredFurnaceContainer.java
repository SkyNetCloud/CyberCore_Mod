package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.api.items.ItemInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowredFurnaceContainer extends BaseContainerCore {

	public PowredFurnaceContainer(int id, PlayerInventory inv) {
		this(id, inv, new PowredFurnaceTileEntity());
	}

	public PowredFurnaceContainer(int id, PlayerInventory player, PowredFurnaceTileEntity tileentity) {
		super(id, ContainerNames.POWER_FURNCAE_CON, player, tileentity, 20);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.orElseThrow(NullPointerException::new);

		String input = "slot.poweredfurnace.input";

		String output = "slot.poweredfurnace.output";

		String card = "slot.poweredfurnace.cardcard";

		this.addSlot(new SlotItemHandlerWithInfo(handler, 16, 300, 60, card) {
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

		this.addSlot(new SlotItemHandlerWithInfo(handler, 0, 21, 27, input) {
			@Override
			public boolean isItemValid(ItemStack itemstack) {
				if (itemstack.getItem() == ItemInit.lunar_upgrade_card_1.asItem()) {
					return false;
				} else if (itemstack.getItem() == ItemInit.lunar_upgrade_card_2.asItem()) {
					return false;
				} else if (itemstack.getItem() == ItemInit.lunar_upgrade_card_3.asItem()) {
					return false;
				} else if (itemstack.getItem() == ItemInit.lunar_upgrade_card_4.asItem()) {
					return false;
				}
				return true;
			}
		});
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
