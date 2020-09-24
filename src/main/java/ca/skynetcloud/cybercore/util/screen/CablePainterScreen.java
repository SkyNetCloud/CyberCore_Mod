package ca.skynetcloud.cybercore.util.screen;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.packets.ButtonPressMessage;
import ca.skynetcloud.cybercore.packets.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.util.TE.techblock.CablePainterTE;
import ca.skynetcloud.cybercore.util.container.PainterContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CablePainterScreen extends ScreenBaseCore<PainterContainer> {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/painter_gui.png");

	public CablePainterScreen(PainterContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 6; x++) {
				if (inArea(mouseX, mouseY, 35 + x * 18, 26 + y * 18)) {
					CyberCorePacketHandler.sendToServer(new ButtonPressMessage(te.getPos().getX(), te.getPos().getY(),
							te.getPos().getZ(), x + y * 6));
				}
			}
		}
		return super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

		minecraft.getTextureManager().bindTexture(BACKGROUND);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, 195);

		int l = this.getCookProgressScaled(70);
		blit(this.guiLeft + 52, this.guiTop + 86, 0, 199, l, 12);

		int k = this.getEnergyStoredScaled(55);
		blit(this.guiLeft + 149, this.guiTop + 28 + (55 - k), 208, 55 - k, 16, 0 + k);

		int i = container.getValue(3);
		if (i >= 0) {
			blit(this.guiLeft + 35 + (i % 6) * 18, this.guiTop + 26 + (i / 6) * 18, 221, 0, 18, 18);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = container.getValue(2);
		return i != 0 ? i * 72 / ((CablePainterTE) this.te).ticksPerItem() : 0;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		@SuppressWarnings("resource")
		FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;

		fontRenderer.drawStringWithShadow("Power Storage", 120, 120, 10023);
	}

	private boolean inArea(double mouseX, double mouseY, int posX, int posY) {
		posX += this.guiLeft;
		posY += this.guiTop;
		if (mouseX >= posX && mouseX <= posX + 18 && mouseY >= posY && mouseY <= posY + 18) {
			return true;
		}
		return false;
	}

}
