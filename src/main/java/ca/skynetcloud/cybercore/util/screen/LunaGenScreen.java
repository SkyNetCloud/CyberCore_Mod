package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.item.UpgradeLvl;
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
	protected void drawGuiContainerBackgroundLayer(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(mStack, partialTicks, mouseX, mouseY);

		int k = this.getEnergyStoredScaled(55);
		blit(mStack, this.guiLeft + 150, this.guiTop + 28 + (55 - k), 208, 55 - k, 16, 0 + k);

		int l = 0;
		switch (((LunaGenTileEntity) this.te).getMarkcard(0, UpgradeLvl.ItemType.SOLAR_FOCUS)) {
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
		blit(mStack, this.guiLeft + 136, this.guiTop + 36, 205, 56, j, l);
	}

	private int getWorkLoadScaled(int pixels) {
		int i = container.getValue(2);
		int j = ((LunaGenTileEntity) this.te).getTicksPerAmount();
		return i != 0 && j != 0 ? i * pixels / j : 0;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return TEXTURES;
	}
}
