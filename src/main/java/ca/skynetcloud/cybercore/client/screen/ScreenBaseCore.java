package ca.skynetcloud.cybercore.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.client.enegry.baseclasses.CoreEnergyBlockEntity;
import ca.skynetcloud.cybercore.client.container.BaseContainerCore;
import ca.skynetcloud.cybercore.client.container.BaseContainerCore.SlotItemHandlerWithInfo;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public abstract class ScreenBaseCore<T extends BaseContainerCore> extends AbstractContainerScreen<T> {
	protected static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_lunagen.png");
	protected final Inventory inv;
	protected final CoreEnergyBlockEntity te;

	protected abstract ResourceLocation getBackgroundTexture();

	@SuppressWarnings("unchecked")
	public ScreenBaseCore(BaseContainerCore inventorySlotsIn, Inventory inventoryPlayer, Component title) {
		super((T) inventorySlotsIn, inventoryPlayer, title);
		this.te = inventorySlotsIn.getTE();
		this.inv = inventoryPlayer;
	}

	@Override
	public void init() {
		super.init();
		this.imageWidth = 205;
		this.imageHeight = 197;
		this.leftPos = (this.width - this.imageWidth) / 2;
		this.topPos = (this.height - this.imageHeight) / 2;
	}

	@Override
	public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(mStack);
		super.render(mStack, mouseX, mouseY, partialTicks);
		this.drawTooltips(mStack, mouseX, mouseY);
//	        this.renderHoveredToolTip(mStack, mouseX, mouseY);
		this.renderTooltip(mStack, mouseX, mouseY);
	}

	protected void drawTooltips(PoseStack mStack, int mouseX, int mouseY) {

	}

	public void drawTooltip(PoseStack mStack, String lines, int mouseX, int mouseY, int posX, int posY, int width,
			int height) {

		posX += this.leftPos;
		posY += this.topPos;
		if (mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height) {
			renderTooltip(mStack, new TextComponent(lines), mouseX, mouseY);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(PoseStack mStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.clearColor(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bindForSetup(getBackgroundTexture());
		blit(mStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	protected void renderLabels(PoseStack mStack, int mouseX, int mouseY) {
		String tileName = title.getString();
		int textcolor = Integer.parseInt("000000", 16);
		font.draw(mStack, tileName, (this.imageWidth / 2.0F - font.width(tileName) / 2.0F) + 1, 14, textcolor);
	}

	protected int getEnergyStoredScaled(int pixels) {
		int i = menu.getValue(0);
		int j = menu.getValue(1);
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}

	protected int getFluidStoredScaled(int pixels) {
		int i = menu.getValue(2);
		int j = menu.getValue(3);
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}

	// renderHoveredToolTip
	@Override
	protected void renderTooltip(PoseStack mStack, int x, int y) {
		if (minecraft.player.inventoryMenu.getCarried().isEmpty() && this.hoveredSlot != null
				&& !this.hoveredSlot.hasItem() && this.hoveredSlot instanceof BaseContainerCore.SlotItemHandlerWithInfo)
			this.renderTooltip(mStack,
					new TranslatableComponent(((SlotItemHandlerWithInfo) this.hoveredSlot).getUsageString()), x, y);
		else
			super.renderTooltip(mStack, x, y);
	}

}