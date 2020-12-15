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
		this.xSize = 205;
		this.ySize = 197;
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
	}

	@Override
	public void render(MatrixStack mStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(mStack);
		super.render(mStack, mouseX, mouseY, partialTicks);
		this.drawTooltips(mStack, mouseX, mouseY);
//	        this.renderHoveredToolTip(mStack, mouseX, mouseY);
		this.renderHoveredTooltip(mStack, mouseX, mouseY);
	}

	protected void drawTooltips(MatrixStack mStack, int mouseX, int mouseY) {
		
	}

	public void drawTooltip(MatrixStack mStack, String lines, int mouseX, int mouseY, int posX, int posY, int width,
			int height) {

		posX += this.guiLeft;
		posY += this.guiTop;
		if (mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height) {
			renderTooltip(mStack, new StringTextComponent(lines), mouseX, mouseY);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bindTexture(getBackgroundTexture());
		blit(mStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack mStack, int mouseX, int mouseY) {
		String tileName = title.getString();
		int textcolor = Integer.parseInt("000000", 16);
		font.drawString(mStack, tileName, (this.xSize / 2.0F - font.getStringWidth(tileName) / 2.0F) + 1, 14,
				textcolor);
	}

	protected int getEnergyStoredScaled(int pixels) {
		int i = container.getValue(0);
		int j = container.getValue(1);
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}

	protected int getFluidStoredScaled(int pixels) {
		int i = container.getValue(2);
		int j = container.getValue(3);
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}

	// renderHoveredToolTip
	@Override
	protected void renderHoveredTooltip(MatrixStack mStack, int x, int y) {
		if (minecraft.player.inventory.getItemStack().isEmpty() && this.hoveredSlot != null
				&& !this.hoveredSlot.getHasStack()
				&& this.hoveredSlot instanceof BaseContainerCore.SlotItemHandlerWithInfo)
			this.renderTooltip(mStack,
					new TranslationTextComponent(((SlotItemHandlerWithInfo) this.hoveredSlot).getUsageString()), x, y);
		else
			super.renderHoveredTooltip(mStack, x, y);
	}

}