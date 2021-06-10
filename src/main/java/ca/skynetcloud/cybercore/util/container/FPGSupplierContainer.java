package ca.skynetcloud.cybercore.util.container;

import ca.skynetcloud.cybercore.init.ContainerInit;
import ca.skynetcloud.cybercore.util.TE.techblock.FencePowerGridTE;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class FPGSupplierContainer extends BaseContainerCore {

	public FPGSupplierContainer(int id, PlayerInventory inv) {
		this(id, inv, new FencePowerGridTE());
	}

	public FPGSupplierContainer(int id, PlayerInventory player, FencePowerGridTE tileentity) {
		super(id, ContainerInit.FPG_CON, player, tileentity, 2);
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		return ItemStack.EMPTY;
	}

}
