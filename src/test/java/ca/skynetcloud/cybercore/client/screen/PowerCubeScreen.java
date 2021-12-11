package ca.skynetcloud.cybercore.client.screen;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.client.container.PowerCubeCon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PowerCubeScreen extends ScreenBaseCore<PowerCubeCon> {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_powerstorage.png");

	public PowerCubeScreen(PowerCubeCon container, Inventory inv, Component name) {
		super(container, inv, name);
	}

	@Override
	protected void renderBg(PoseStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(mStack, partialTicks, mouseX, mouseY);

		int k = this.getEnergyStoredScaled(55);
		blit(mStack, this.leftPos + 97, this.topPos + 19 + (55 - k), 205, 55 - k, 16, k);
	}

	@Override
	protected void renderLabels(PoseStack mStack, int mouseX, int mouseY) {
		@SuppressWarnings("resource")
		Font fontRenderer = Minecraft.getInstance().font;

		// fontRenderer.drawStringWithShadow(mStack, "Power Storage", 70, 5, 15312);
		fontRenderer.drawShadow(mStack, "Power Stored: " + te.getEnergyStored(), -50, 20, 11111111);
		fontRenderer.drawShadow(mStack, "Max Stored: " + te.getMaxEnergyStored(), -50, 35, 11111111);
	}

	protected void drawTooltips(PoseStack mStack, int mouseX, int mouseY) {

	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return BACKGROUND;
	}
}
