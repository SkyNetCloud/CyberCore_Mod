package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.container.FPGSupplierContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FPGScreen extends ScreenBaseCore<FPGSupplierContainer> {
	private static final ResourceLocation BACKGROUND = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/energy_supplier.png");

	public FPGScreen(FPGSupplierContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void renderBg(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(mStack, partialTicks, mouseX, mouseY);

		int k = this.getEnergyStoredScaled(55);
		blit(mStack, this.leftPos + 149, this.topPos + 28 + (55 - k), 208, 55 - k, 16, k);
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {
		return BACKGROUND;
	}

}
