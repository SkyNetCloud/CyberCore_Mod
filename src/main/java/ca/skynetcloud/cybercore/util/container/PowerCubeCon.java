package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.init.CoreInit;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerCubeBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class PowerCubeCon extends BaseContainerCore {

	public PowerCubeCon(int id, Inventory inv)
	{
		this(id, inv, new PowerCubeBlockEntity());
	}

	public PowerCubeCon(int id, Inventory player, PowerCubeBlockEntity tileentity)
	{
		super(id, CoreInit.ContainersInit.POWER_CUBE_CON,player, tileentity, 3);
		IItemHandler handler = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElseThrow(NullPointerException::new);
		this.addSlot(createCapacityChipSlot(handler, 0, 132, 64));

	}

}
