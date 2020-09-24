package ca.skynetcloud.cybercore.util.screen;

import org.apache.logging.log4j.core.pattern.FormattingInfo;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.enegry.CyberSystemPowerStorage;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerStorageTileEntity;
import ca.skynetcloud.cybercore.util.container.PowerStorageContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PowerStorageScreen extends ScreenBaseCore<PowerStorageContainer> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_powerstorage.png");

	public PowerStorageScreen(PowerStorageContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		minecraft.getTextureManager().bindTexture(TEXTURES);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int k = this.getEnergyStoredScaled(55);
		blit(this.guiLeft + 97, this.guiTop + 19 + (55 - k), 205, 55 - k, 16, k);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		@SuppressWarnings("resource")
		FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;

		fontRenderer.drawStringWithShadow("Power Storage", 70, 5, 15312);
		fontRenderer.drawStringWithShadow("Power Stored: " + te.getEnergyStored(), -50, 20, 11111111);
		fontRenderer.drawStringWithShadow("Max Stored: " + te.getMaxEnergyStored(), -50, 35, 11111111);
	}

	protected void drawTooltips(int mouseX, int mouseY) {
		drawTooltip(te.getEnergyStored() + "/" + te.getMaxEnergyStored(), mouseX, mouseY, 97, 19, 16, 55);
	}

}
