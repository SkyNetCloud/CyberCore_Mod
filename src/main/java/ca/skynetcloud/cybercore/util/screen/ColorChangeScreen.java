package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.TE.techblock.ColorChangeTileEntity;
import ca.skynetcloud.cybercore.util.container.ColorChangeContainer;
import ca.skynetcloud.cybercore.util.networking.ButtonPressMessage;
import ca.skynetcloud.cybercore.util.networking.CyberCorePacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class ColorChangeScreen extends ScreenBaseCore<ColorChangeContainer> {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/painter_gui.png");

	public ColorChangeScreen(ColorChangeContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int p_mouseClicked_5_) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 6; x++) {
				if (inArea(mouseX, mouseY, 35 + x * 18, 26 + y * 18)) {
					CyberCorePacketHandler.sendToServer(new ButtonPressMessage(te.getBlockPos().getX(),
							te.getBlockPos().getY(), te.getBlockPos().getZ(), x + y * 6));
				}
			}
		}
		return super.mouseClicked(mouseX, mouseY, p_mouseClicked_5_);
	}

	@Override
	protected void renderBg(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(mStack, partialTicks, mouseX, mouseY);

		int l = this.getCookProgressScaled(72);
		blit(mStack, this.leftPos + 55, this.topPos + 87, 0, 200, l, 8);

		// int k = this.getEnergyStoredScaled(55);
		// blit(mStack, this.guiLeft + 149, this.guiTop + 29 + (55 - k), 208, 55 - k,
		// 16, k);
		int i = menu.getValue(3);
		if (i >= 0)
			blit(mStack, this.leftPos + 34 + (i % 6) * 18, this.topPos + 26 + (i / 6) * 18, 224, 0, 18, 18);
	}

	@Override
	protected void renderLabels(MatrixStack mStack, int mouseX, int mouseY) {

		@SuppressWarnings("resource")
		FontRenderer fontRenderer = Minecraft.getInstance().font;

		// fontRenderer.drawStringWithShadow(mStack, "Powered Furnace", 270, 60, 15312);
		fontRenderer.drawShadow(mStack, "Max It Can Stored FE : " + TextFormatting.RED + te.getMaxEnergyStored(), -155,
				50, TextFormatting.DARK_GREEN.getColor());
		fontRenderer.drawShadow(mStack, "Power Stored FE: " + TextFormatting.GREEN + te.getEnergyStored(), -155, 70,
				TextFormatting.BLUE.getColor());
		if (te.getEnergyStored() >= 1000) {
			fontRenderer.drawShadow(mStack, "Power Stored FE: " + TextFormatting.RED + te.getEnergyStored(), -155, 70,
					TextFormatting.BLUE.getColor());
		} else if (te.getEnergyStored() >= 500) {
			fontRenderer.drawShadow(mStack, "Power Stored FE: " + TextFormatting.YELLOW + te.getEnergyStored(), -155,
					70, TextFormatting.BLUE.getColor());
		} else if (te.getEnergyStored() >= 100) {
			fontRenderer.drawShadow(mStack, "Power Stored FE: " + TextFormatting.GREEN + te.getEnergyStored(), -155, 70,
					TextFormatting.BLUE.getColor());
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = menu.getValue(2);
		return i != 0 ? i * pixels / ((ColorChangeTileEntity) this.te).ticksPerItem() : 0;
	}

	@SuppressWarnings("unused")
	private boolean inArea(double mouseX, double mouseY, int posX, int posY) {
		posX += this.leftPos;
		posY += this.topPos;
		return mouseX >= posX && mouseX <= posX + 18 && mouseY >= posY && mouseY <= posY + 18;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return BACKGROUND;
	}

}
