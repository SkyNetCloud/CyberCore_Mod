package xyz.skynetcloud.cybercore.util.screen;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import xyz.skynetcloud.cybercore.CyberCoreMain;
import xyz.skynetcloud.cybercore.util.CyberCoreConstants;
import xyz.skynetcloud.cybercore.util.TE.LunaGenTileEntity;
import xyz.skynetcloud.cybercore.util.container.LunaGenContainer;

public class LunaGenScreen extends ScreenBaseCore<LunaGenContainer> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/lunagen.png");

	public LunaGenScreen(LunaGenContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		minecraft.getTextureManager().bindTexture(TEXTURES);
		// this.drawScaledCustomSizeModalRect(this.guiLeft, this.guiTop, 0, 0,
		// this.xSize, this.ySize, tileWidth, tileHeight);
		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int k = this.getEnergyStoredScaled(55);
		blit(this.guiLeft + 159, this.guiTop + 28 + (55 - k), 205, 55 - k, 16, 0 + k);

		int l = 0;
		switch (((LunaGenTileEntity) this.te).getMarkLvl(0, CyberCoreConstants.LUNASOLARFOCUS_TYPE)) {
		case 0:
			l = 0;
			break;
		case 1:
			l = 6;
			break;
		case 2:
			l = 15;
			break;
		case 3:
			l = 25;
			break;
		case 4:
			l = 35;
			break;
		}

		int j = getWorkLoadScaled(17);
		blit(this.guiLeft + 136, this.guiTop + 45, 205, 75, j, l);
	}

	private int getWorkLoadScaled(int pixels) {
		int i = container.getValue(2);
		int j = ((LunaGenTileEntity) this.te).getTicksPerAmount();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
}
