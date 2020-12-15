package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.packets.ButtonPressMessage;
import ca.skynetcloud.cybercore.packets.CyberCorePacketHandler;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import ca.skynetcloud.cybercore.util.container.ColorChangeContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ColorChangeScreen extends ScreenBaseCore<ColorChangeContainer> {
	private static final ResourceLocation BACK = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/painter_gui.png");

	public ColorChangeScreen(ColorChangeContainer container, PlayerInventory player, ITextComponent name) {
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
	protected void drawGuiContainerBackgroundLayer(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(mStack, partialTicks, mouseX, mouseY);

		int l = this.getCookProgressScaled(70);
		blit(mStack, this.guiLeft + 52, this.guiTop + 86, 0, 199, l, 12);

		int k = this.getEnergyStoredScaled(55);
		blit(mStack, this.guiLeft + 149, this.guiTop + 28 + (55 - k), 208, 55 - k, 16, 0 + k);

		int i = container.getValue(3);
		if (i >= 0) {
			blit(mStack, this.guiLeft + 35 + (i % 6) * 18, this.guiTop + 22 + (i / 6) * 18, 221, 0, 18, 18);
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = container.getValue(2);
		return i != 0 ? i * 72 / ((ColorChangeTileEntity) this.te).ticksPerItem() : 0;
	}

	private boolean inArea(double mouseX, double mouseY, int posX, int posY) {
		posX += this.guiLeft;
		posY += this.guiTop;
		return mouseX >= posX && mouseX <= posX + 18 && mouseY >= posY && mouseY <= posY + 18;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return BACK;
	}
}
