package ca.skynetcloud.cybercore.util.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.TE.techblock.PowredFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.container.PowredFurnaceContainer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class PowredFurnaceScreen extends ScreenBaseCore<PowredFurnaceContainer> {
	private static final ResourceLocation TEXTURES = new ResourceLocation(
			CyberCoreMain.MODID + ":textures/gui/container/new_powedfurnace.png");

	public PowredFurnaceScreen(PowredFurnaceContainer container, PlayerInventory player, ITextComponent name) {
		super(container, player, name);
	}

	@Override
	protected void renderBg(PoseStack mStack, float partialTicks, int mouseX, int mouseY) {
		super.renderBg(mStack, partialTicks, mouseX, mouseY);

		for (int p = 0; p < 6; p++) {
			int l = this.getCookProgressScaled(p, 15);
			blit(mStack, this.leftPos + 24 + p * 27, this.topPos + 46, 3, 200, 12, l);
		}

	}

	@Override
	protected void renderLabels(PoseStack mStack, int mouseX, int mouseY) {
		@SuppressWarnings("resource")
		Font fontRenderer = Minecraft.getInstance().font;

		// fontRenderer.drawStringWithShadow(mStack, "Powered Furnace", 270, 60, 15312);
		fontRenderer.drawShadow(mStack, "Power Stored FE: " + ChatFormatting.GREEN + te.getEnergyStored(), -155, 70,
				ChatFormatting.BLUE.getColor());
		fontRenderer.drawShadow(mStack, "Max It Can Stored FE : " + ChatFormatting.GREEN + te.getMaxEnergyStored(),
				-155, 50, ChatFormatting.DARK_GREEN.getColor());
	}
	
	

	private int getCookProgressScaled(int id, int pixels) {
		int i = menu.getValue(id + 2);
		return i != 0 ? i * pixels / ((PowredFurnaceTileEntity) this.te).getTicksPerItem() : 0;
	}

	@Override
	protected ResourceLocation getBackgroundTexture() {

		return TEXTURES;
	}

}
