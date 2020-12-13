package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.TE.techblock.PowedFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.container.PowerFurnaceContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PowedFurnaceScreen extends ScreenBaseCore<PowerFurnaceContainer> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_powedfurnace.png");

	public PowedFurnaceScreen(PowerFurnaceContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(mStack, partialTicks, mouseX, mouseY);
		minecraft.getTextureManager().bindTexture(TEXTURES);
		blit(mStack, this.guiLeft, this.guiTop, 0, 0, 220, 200);

		for (int p = 0; p < 6; p++) {
			int l = this.getCookProgressScaled(p, 15);
			blit(mStack, this.guiLeft + 24 + p * 27, this.guiTop + 46, 3, 200, 13, l);
		}

		@SuppressWarnings("resource")
		FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;

		fontRenderer.drawStringWithShadow(mStack, "Powered Furnace", 270, 60, 15312);
		fontRenderer.drawStringWithShadow(mStack, "Power Stored FE: " + te.getEnergyStored(), 70, 150, 11111111);
		fontRenderer.drawStringWithShadow(mStack, "Max It Can Stored FE : " + te.getMaxEnergyStored(), 70, 130,
				11111111);

	}

	private int getCookProgressScaled(int id, int pixels) {
		int i = container.getValue(id + 2);
		return i != 0 ? i * pixels / ((PowedFurnaceTileEntity) this.te).getTicksPerItem() : 0;
	}

}
