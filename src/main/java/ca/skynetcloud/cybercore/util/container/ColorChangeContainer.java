package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.api.containers.ContainerNames;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import ca.skynetcloud.cybercore.util.container.BaseContainerCore.SlotItemHandlerWithInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ColorChangeContainer extends BaseContainerCore
{
	public ColorChangeContainer(int id, PlayerInventory inv)
	{
		this(id, inv, new ColorChangeTileEntity());
	}

	public ColorChangeContainer(int id, PlayerInventory player, ColorChangeTileEntity tileentity)
	{
		super(id, ContainerNames.CABLE_PAINTER_CON, player, tileentity, 25);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);

		this.addSlot(new ChangeCheckSlot(tileentity, handler, 0, 34, 83, "slot.compressor.input"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 1, 126, 83, "slot.util.output"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, 2, 78, 87, "slot.util.speedupgrade"));

		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 6; x++)
			{
				addSlot(new NoAccessSlot(handler, x + y * 6 + 3, 35 + x * 18, 22 + y * 18, "slot.compressor.select"));
			}
		}

		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyInSlot(), 167, 38, "slot.util.energyin"));
		this.addSlot(new SlotItemHandlerWithInfo(handler, tileentity.getEnergyOutSlot(), 167, 57, "slot.util.energyout"));
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack())
		{
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();

			if (index > 35)
			{
				if (!this.mergeItemStack(stack1, 0, 34, true))
				{
					return ItemStack.EMPTY;
				}
			} else if (index < 36)
			{
				if (!this.mergeItemStack(stack1, 36, 37, false))
				{
					return ItemStack.EMPTY;
				} else if (index >= 0 && index < 27)
				{
					if (!this.mergeItemStack(stack1, 27, 35, false))
						return ItemStack.EMPTY;
				} else if (index >= 27 && index < 36 && !this.mergeItemStack(stack1, 0, 26, false))
				{
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 0, 35, false))
			{
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			} else
			{
				slot.onSlotChanged();

			}
			if (stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

}

class ChangeCheckSlot extends SlotItemHandlerWithInfo
{
	private ColorChangeTileEntity te;

	public ChangeCheckSlot(ColorChangeTileEntity te, IItemHandler itemHandler, int index, int xPosition, int yPosition, String usage)
	{
		super(itemHandler, index, xPosition, yPosition, usage);
		this.te = te;
	}

	@Override
	public void onSlotChanged()
	{
		te.setRecipe();
		super.onSlotChanged();
	}
}
