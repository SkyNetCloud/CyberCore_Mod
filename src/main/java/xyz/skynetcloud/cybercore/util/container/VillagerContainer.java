package xyz.skynetcloud.cybercore.util.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import xyz.skynetcloud.cybercore.api.containers.ContainerNames;
import xyz.skynetcloud.cybercore.entities.tradesandjobs.BaseVillagerTrade;

public class VillagerContainer extends Container {
	private List<BaseVillagerTrade> trades;

	public VillagerContainer(int id, PlayerInventory inv, PacketBuffer data) {
		this(id, inv, new ArrayList<BaseVillagerTrade>());
		List<BaseVillagerTrade> list = new ArrayList<BaseVillagerTrade>();
		int size = data.readInt();
		for (int i = 0; i < size; i++) {
			list.add(BaseVillagerTrade.fromBuffer(data));
		}
		trades = list;
	}

	public VillagerContainer(int id, PlayerInventory playerInv, List<BaseVillagerTrade> trades) {
		super(ContainerNames.VILLAGER_CON, id);
		this.trades = trades;

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlot(new Slot(playerInv, x + y * 9 + 9, 147 + x * 18, 112 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			this.addSlot(new Slot(playerInv, x, 147 + x * 18, 170));
		}
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (index < 36) {
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

	public void setTrades(List<BaseVillagerTrade> trades) {
		this.trades = trades;
	}

	public List<BaseVillagerTrade> getTrades() {
		return trades;
	}

}
