package xyz.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.util.container.PowerStorageContainer;

public class PowerStorageScreen extends ScreenBaseCore<PowerStorageContainer> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/powerstorage.png");

	public PowerStorageScreen(PowerStorageContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.func_227637_a_(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bindTexture(TEXTURES);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int k = this.getEnergyStoredScaled(55);
		blit(this.guiLeft + 159, this.guiTop + 28 + (55 - k), 205, 55 - k, 16, 0 + k);
	}
}
