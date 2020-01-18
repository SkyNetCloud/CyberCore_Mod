package xyz.skynetcloud.cybercore.util.TE.cable;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.IHopper;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ItemCableTileEntity extends LockableLootTileEntity implements IHopper, ITickableTileEntity {

	public ItemCableTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);

	}

	@Override
	public int getSizeInventory() {

		return 0;
	}

	@Override
	public void tick() {

	}

	@Override
	public double getXPos() {
		return (double) this.pos.getX() + 0.5D;
	}

	@Override
	public double getYPos() {
		return (double) this.pos.getY() + 0.5D;
	}

	@Override
	public double getZPos() {
		return (double) this.pos.getZ() + 0.5D;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {

		return null;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {

	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.item.pipe");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {

		return null;
	}

}
