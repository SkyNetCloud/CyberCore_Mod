package ca.skynetcloud.cybercore.client.screen;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.client.container.PoweredFurnaceMenu;
import ca.skynetcloud.cybercore.client.techblock.PoweredFurnaceBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PoweredFunranceMenuScreen extends ScreenBaseCore<PoweredFurnaceMenu>{

    private static final ResourceLocation TEXTURES = new ResourceLocation(
            CyberCoreMain.MODID + ":textures/gui/container/new_powedfurnace.png");

    public PoweredFunranceMenuScreen(PoweredFurnaceMenu container, Inventory inv, Component name) {
        super(container, inv, name);
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
        return i != 0 ? i * pixels / ((PoweredFurnaceBlockEntity) this.te).ticksPerItem() : 0;
    }

    @Override
    protected ResourceLocation getBackgroundTexture() {

        return TEXTURES;
    }

}
