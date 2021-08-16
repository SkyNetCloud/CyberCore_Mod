package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.init.ContainerInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowredFurnaceContainer extends BaseContainerCore {

	public PowredFurnaceContainer(int id, Inventory player, PowredFurnaceTileEntity tileentity) {
		super(id, ContainerInit.POWER_FURNCAE_CON, player, tileentity, 20);
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
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 7, 48, 64, output) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 8, 75, 64, output) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 9, 102, 64, output) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 10, 129, 64, output) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		this.addSlot(new SlotItemHandlerWithInfo(handler, 11, 156, 64, output) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});

		this.addSlot(new BaseContainerCore.SlotItemHandlerWithInfo(handler, 12, 180, 27, "slot.util.speed_upgrade"));
		this.addSlot(
				new BaseContainerCore.SlotItemHandlerWithInfo(handler, 13, 180, 46, "slot.util.power_storage_upgrade"));
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();

			if (index > 37) {
				if (!this.moveItemStackTo(stack1, 0, 35, true))
					return ItemStack.EMPTY;
				slot.onQuickCraft(stack1, stack);
			} else if (index < 36) {
				if (!this.moveItemStackTo(stack1, 36, 42, false)) {
					return ItemStack.EMPTY;
				} else if (index >= 0 && index < 27) {
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

}
