package ca.skynetcloud.cybercore.util.container;



import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyBlockEntity;
import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyInventoryBlockEntity;
import ca.skynetcloud.cybercore.item.UpgradeLvl;
import ca.skynetcloud.cybercore.util.networking.util.IItemChargeable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Arrays;
import java.util.function.Predicate;

public class BaseContainerCore extends AbstractContainerMenu {

	protected final CoreEnergyInventoryBlockEntity BlockEntity;
	protected final ContainerData data;

	public BaseContainerCore(int id, MenuType<?> type, Inventory player, CoreEnergyInventoryBlockEntity BlockEntity, int slots)
	{
		super(type, id);
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 9; x++)
				addSlot(new Slot(player, x + y * 9 + 9, 23 + x * 18, 106 + y * 18));
		for (int x = 0; x < 9; x++)
			addSlot(new Slot(player, x, 23 + x * 18, 164));
		this.BlockEntity = BlockEntity;
		data = BlockEntity.getContainerData();
		addDataSlots(data);
	}

	@Override
	public boolean stillValid(Player playerIn)
	{
		return BlockEntity.isUsableByPlayer(playerIn);
	}

	public CoreEnergyBlockEntity getTE()
	{
		return BlockEntity;
	}

	public int getValue(int id)
	{
		return data.get(id);
	}

	protected LimitedItemInfoSlot createSpeedUpgradeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		return new LimitedItemInfoSlot(itemHandler, index, xPosition, yPosition, "slot.util.speedupgrade").setConditions((stack) -> {
			Item item = stack.getItem();
			return item instanceof UpgradeLvl && ((UpgradeLvl) item).getItemType() == UpgradeLvl.ItemType.SPEED_UPGRADE;
		}).setLimited();
	}


	protected LimitedItemInfoSlot createCapacityChipSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		return (LimitedItemInfoSlot) new LimitedItemInfoSlot(itemHandler, index, xPosition, yPosition, "slot.util.energystorageupgrade").setConditions((stack) -> 			{
			Item item = stack.getItem();
			return item instanceof UpgradeLvl && ((UpgradeLvl) item).getItemType() == UpgradeLvl.ItemType.CAPACITY_UPGRADE;
		}).setLimited().setShouldListen();
	}


	protected LimitedItemInfoSlot createFakeSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, String usage)
	{
		return new LimitedItemInfoSlot(itemHandler, index, xPosition, yPosition, usage).setConditions(false).setCanTake(false);
	}

	protected LimitedItemInfoSlot createOutoutSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
	{
		return new LimitedItemInfoSlot(itemHandler, index, xPosition, yPosition, "slot.util.output").setConditions(false);
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index)
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = slots.get(index);
		if(slot.hasItem())
		{
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index > 35)
			{
				if (!this.moveItemStackTo(stack1, 0, 34, true))
					return ItemStack.EMPTY;
			}
			if (stack1.isEmpty())
				slot.set(ItemStack.EMPTY);
			else
				slot.setChanged();
			if (stack1.getCount() == stack.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

	public class SlotItemHandlerWithInfo extends SlotItemHandler
	{
		private final String usage;
		protected boolean listening = false;

		public SlotItemHandlerWithInfo(IItemHandler itemHandler, int index, int xPosition, int yPosition, String usage)
		{
			super(itemHandler, index, xPosition, yPosition);
			this.usage = usage;
		}

		public String getUsageString()
		{
			return usage;
		}

		@Override
		public void setChanged()
		{
			super.setChanged();
			if (listening)
			{
				BaseContainerCore.this.broadcastChanges();
				BaseContainerCore.this.BlockEntity.onContainerUpdated(getSlotIndex());
			}
		}

		public SlotItemHandlerWithInfo setShouldListen()
		{
			listening = true;
			return this;
		}
	}

	public class LimitedItemInfoSlot extends SlotItemHandlerWithInfo
	{
		private Predicate<ItemStack> conditions = (stack) -> true;
		private Predicate<Player> canTake = (stack) -> true;

		private boolean limited = false;

		public LimitedItemInfoSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition, String usage)
		{
			super(itemHandler, index, xPosition, yPosition, usage);
		}

		@Override
		public boolean mayPlace(ItemStack stack)
		{
			return conditions.test(stack);
		}

		@Override
		public boolean mayPickup(Player playerIn)
		{
			return canTake.test(playerIn);
		}

		@Override
		public int getMaxStackSize(ItemStack stack)
		{
			return limited ? 1 : super.getMaxStackSize(stack);
		}

		public LimitedItemInfoSlot setLimited()
		{
			limited = true;
			return this;
		}

		public LimitedItemInfoSlot setConditions(boolean enabled)
		{
			return setConditions((stack) -> enabled);
		}

		public LimitedItemInfoSlot setConditions(Item... acceptableItems)
		{
			return setConditions((stack) -> acceptableItems.length == 0 || Arrays.stream(acceptableItems).anyMatch((item) -> stack.getItem() == item));
		}

		public LimitedItemInfoSlot setConditions(Predicate<ItemStack> conditions)
		{
			this.conditions = conditions;
			return this;
		}

		public LimitedItemInfoSlot setCanTake(boolean enabled)
		{
			return setCanTake((stack) -> enabled);
		}

		public LimitedItemInfoSlot setCanTake(Predicate<Player> conditions)
		{
			this.canTake = conditions;
			return this;
		}
	}
}
