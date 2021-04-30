package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.container.PowerCubeCon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PowerCubeScreen extends ScreenBaseCore<PowerCubeCon> {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_powerstorage.png");

	public PowerCubeScreen(PowerCubeCon container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void renderBg(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(mStack, partialTicks, mouseX, mouseY);

		int k = this.getEnergyStoredScaled(55);
		blit(mStack, this.leftPos + 97, this.topPos + 19 + (55 - k), 205, 55 - k, 16, k);
	}

	@Override
	protected void renderLabels(MatrixStack mStack, int mouseX, int mouseY) {
		@SuppressWarnings("resource")
		FontRenderer fontRenderer = Minecraft.getInstance().font;

		// fontRenderer.drawStringWithShadow(mStack, "Power Storage", 70, 5, 15312);
		fontRenderer.drawShadow(mStack, "Power Stored: " + te.getEnergyStored(), -50, 20, 11111111);
		fontRenderer.drawShadow(mStack, "Max Stored: " + te.getMaxEnergyStored(), -50, 35, 11111111);
	}

	protected void drawTooltips(MatrixStack mStack, int mouseX, int mouseY) {

	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return BACKGROUND;
	}
}
