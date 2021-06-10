package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.enegry.baseclasses.CoreEnergyTileEntity;
import ca.skynetcloud.cybercore.util.container.BaseContainerCore;
import ca.skynetcloud.cybercore.util.container.BaseContainerCore.SlotItemHandlerWithInfo;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class ScreenBaseCore<T extends BaseContainerCore> extends ContainerScreen<T> {
	protected static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_lunagen.png");
	protected final PlayerInventory player;
	protected final CoreEnergyTileEntity te;

	protected abstract ResourceLocation getBackgroundTexture();

	@SuppressWarnings("unchecked")
	public ScreenBaseCore(BaseContainerCore inventorySlotsIn, PlayerInventory inventoryPlayer, ITextComponent title) {
		super((T) inventorySlotsIn, inventoryPlayer, title);
		this.te = inventorySlotsIn.getTE();
		this.player = inventoryPlayer;
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
	public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(mStack);
		super.render(mStack, mouseX, mouseY, partialTicks);
		this.drawTooltips(mStack, mouseX, mouseY);
//	        this.renderHoveredToolTip(mStack, mouseX, mouseY);
		this.renderTooltip(mStack, mouseX, mouseY);
	}

	protected void drawTooltips(MatrixStack mStack, int mouseX, int mouseY) {

	}

	public void drawTooltip(MatrixStack mStack, String lines, int mouseX, int mouseY, int posX, int posY, int width,
			int height) {

		posX += this.leftPos;
		posY += this.topPos;
		if (mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height) {
			renderTooltip(mStack, new StringTextComponent(lines), mouseX, mouseY);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bind(getBackgroundTexture());
		blit(mStack, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
	}

	@Override
	protected void renderLabels(MatrixStack mStack, int mouseX, int mouseY) {
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
	protected void renderTooltip(MatrixStack mStack, int x, int y) {
		if (minecraft.player.inventory.getCarried().isEmpty() && this.hoveredSlot != null && !this.hoveredSlot.hasItem()
				&& this.hoveredSlot instanceof BaseContainerCore.SlotItemHandlerWithInfo)
			this.renderTooltip(mStack,
					new TranslationTextComponent(((SlotItemHandlerWithInfo) this.hoveredSlot).getUsageString()), x, y);
		else
			super.renderTooltip(mStack, x, y);
	}

}