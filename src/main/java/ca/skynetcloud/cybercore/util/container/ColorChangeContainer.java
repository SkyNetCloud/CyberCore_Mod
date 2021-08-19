package ca.skynetcloud.cybercore.util.container;


import ca.skynetcloud.cybercore.init.CoreInit;
import ca.skynetcloud.cybercore.item.ItemBaseCore;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ColorChangeContainer extends BaseContainerCore {

	public ColorChangeContainer(int id, Inventory inv)
	{
		this(id, inv, new ColorChangeBlockEntity());
	}

	public ColorChangeContainer(int id, Inventory player, ColorChangeBlockEntity tileentity)
	{
		super(id, CoreInit.ContainersInit.COLOR_CHANGER_CON, player, tileentity, 25);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);

		this.addSlot(new LimitedItemInfoSlot(handler, 0, 34, 83, "slot.colorchanger.input").setConditions((stack) -> stack.getItem() instanceof ItemBaseCore).setShouldListen());
		this.addSlot(createOutoutSlot(handler, tileentity.getOutputSlotIndex(), 126, 83));
		this.addSlot(createSpeedUpgradeSlot(handler, 2, 78, 87));
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 6; x++)
				addSlot(createFakeSlot(handler, x + y * 6 + 3, 35 + x * 18, 26 + y * 18, "slot.colorchanger.select"));
	}


}
