package ca.skynetcloud.cybercore.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.client.techblock.ColorChangeBlockEntity;
import ca.skynetcloud.cybercore.client.container.ColorChangeContainer;
import ca.skynetcloud.cybercore.client.networking.ButtonPressMessage;
import ca.skynetcloud.cybercore.client.networking.CyberCorePacketHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ColorChangeScreen extends ScreenBaseCore<ColorChangeContainer> {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/painter_gui.png");

	public ColorChangeScreen(ColorChangeContainer container, Inventory player, Component name) {
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
	protected void renderBg(PoseStack mStack, float partialTicks, int mouseX, int mouseY) {
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
	protected void renderLabels(PoseStack mStack, int mouseX, int mouseY) {

		@SuppressWarnings("resource")
		Font fontRenderer = Minecraft.getInstance().font;

		// fontRenderer.drawStringWithShadow(mStack, "Powered Furnace", 270, 60, 15312);
		fontRenderer.drawShadow(mStack, "Max It Can Stored FE : " + ChatFormatting.RED + te.getMaxEnergyStored(), -155,
				50, ChatFormatting.DARK_GREEN.getColor());
		fontRenderer.drawShadow(mStack, "Power Stored FE: " + ChatFormatting.GREEN + te.getEnergyStored(), -155, 70,
				ChatFormatting.BLUE.getColor());
		if (te.getEnergyStored() >= 1000) {
			fontRenderer.drawShadow(mStack, "Power Stored FE: " + ChatFormatting.RED + te.getEnergyStored(), -155, 70,
					ChatFormatting.BLUE.getColor());
		} else if (te.getEnergyStored() >= 500) {
			fontRenderer.drawShadow(mStack, "Power Stored FE: " + ChatFormatting.YELLOW + te.getEnergyStored(), -155,
					70, ChatFormatting.BLUE.getColor());
		} else if (te.getEnergyStored() >= 100) {
			fontRenderer.drawShadow(mStack, "Power Stored FE: " + ChatFormatting.GREEN + te.getEnergyStored(), -155, 70,
					ChatFormatting.BLUE.getColor());
		}
	}

	private int getCookProgressScaled(int pixels) {
		int i = menu.getValue(2);
		return i != 0 ? i * pixels / ((ColorChangeBlockEntity) this.te).ticksPerItem() : 0;
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
