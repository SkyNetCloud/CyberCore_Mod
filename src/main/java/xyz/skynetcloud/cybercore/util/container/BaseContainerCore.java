package xyz.skynetcloud.cybercore.util.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.IIntArray;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import xyz.skynetcloud.cybercore.util.TE.powerTE.CyberCorePowerTE;

public class BaseContainerCore extends Container {

	protected final CyberCorePowerTE tileentity;
	protected final IIntArray field_array;

	public BaseContainerCore(int id, ContainerType<?> type, PlayerInventory player, CyberCorePowerTE tileentity,
			int slots) {
		super(type, id);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlot(new Slot(player, x + y * 9 + 9, 24 + x * 18, 107 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++) {
			addSlot(new Slot(player, x, 24 + x * 18, 165));
		}

		this.tileentity = tileentity;
		field_array = tileentity.getIntArray();
		trackIntArray(field_array);
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return tileentity.isUsableByPlayer(playerIn);
	}

	public CyberCorePowerTE getTE() {
		return tileentity;
	}

	public int getValue(int id) {
		return field_array.get(id);
	}

	public static class SlotItemHandlerWithInfo extends SlotItemHandler {
		private String usage;

		public SlotItemHandlerWithInfo(IItemHandler itemHandler, int index, int xPosition, int yPosition,
				String usage) {
			super(itemHandler, index, xPosition, yPosition);
			this.usage = usage;
		}

		public String getUsageString() {
			return usage;
		}

	}

}
