package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.matrix.MatrixStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.container.PowredFurnaceContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class PowredFurnaceScreen extends ScreenBaseCore<PowredFurnaceContainer> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_powedfurnace.png");

	public PowredFurnaceScreen(PowredFurnaceContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(mStack, partialTicks, mouseX, mouseY);

		for (int p = 0; p < 6; p++) {
			int l = this.getCookProgressScaled(p, 15);
			blit(mStack, this.guiLeft + 24 + p * 27 , this.guiTop + 46, 3, 200, 12, l);
		}

		@SuppressWarnings("resource")
		FontRenderer fontRenderer = Minecraft.getInstance().fontRenderer;

		//fontRenderer.drawStringWithShadow(mStack, "Powered Furnace", 270, 60, 15312);
		fontRenderer.drawStringWithShadow(mStack, "Power Stored FE: " + te.getEnergyStored(), 70, 150, 11111111);
		fontRenderer.drawStringWithShadow(mStack, "Max It Can Stored FE : " + te.getMaxEnergyStored(), 70, 130,
				11111111);

	}

	private int getCookProgressScaled(int id, int pixels) {
		int i = container.getValue(id + 2);
		return i != 0 ? i * pixels / ((PowredFurnaceTileEntity) this.te).getTicksPerItem() : 0;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {

		return TEXTURES;
	}

}