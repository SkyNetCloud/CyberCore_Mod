package ca.skynetcloud.cybercore.util.screen;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.item.UpgradeLvl.ItemType;
import ca.skynetcloud.cybercore.util.TE.techblock.LunaGenTileEntity;
import ca.skynetcloud.cybercore.util.container.LunaGenContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class LunaGenScreen extends ScreenBaseCore<LunaGenContainer> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_lunagen.png");

	public LunaGenScreen(LunaGenContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

		minecraft.getTextureManager().bindTexture(TEXTURES);

		blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

		int k = this.getEnergyStoredScaled(55);
		blit(this.guiLeft + 149, this.guiTop + 28 + (55 - k), 205, 55 - k, 16, 0 + k);

		int l = 0;
		switch (((LunaGenTileEntity) this.te).getMarkcard(0, ItemType.SOLAR_FOCUS)) {
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
		blit(this.guiLeft + 136, this.guiTop + 36, 205, 56, j, l);

	}

	private int getWorkLoadScaled(int pixels) {
		int i = container.getValue(2);
		int j = ((LunaGenTileEntity) this.te).getTicksPerAmount();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}
}
