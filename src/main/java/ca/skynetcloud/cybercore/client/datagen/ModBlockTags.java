package ca.skynetcloud.cybercore.client.datagen;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.init.MainInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class ModBlockTags extends BlockTagsProvider {

    public ModBlockTags(DataGenerator p_126511_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126511_, CyberCore.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(){
        tag(MainInit.Color_Fence)
                .add(MainInit.BLACK_Electric_Fence_block.get())
                .add(MainInit.RED_Electric_Fence_block.get())
                .add(MainInit.BLUE_Electric_Fence_block.get())
                .add(MainInit.YELLOW_Electric_Fence_block.get())
                .add(MainInit.GREEN_Electric_Fence_block.get())
                .add(MainInit.GRAY_Electric_Fence_block.get())
                .add(MainInit.LIGHT_BLUE_Electric_Fence_block.get())
                .add(MainInit.LIGHT_GRAY_Electric_Fence_block.get())
                .add(MainInit.LIME_Electric_Fence_block.get())
                .add(MainInit.MAGENTA_Electric_Fence_block.get())
                .add(MainInit.ORANGE_Electric_Fence_block.get())
                .add(MainInit.PINK_Electric_Fence_block.get())
                .add(MainInit.PURPLE_Electric_Fence_block.get())
                .add(MainInit.CYAN_Electric_Fence_block.get())
                .add(MainInit.WHITE_Electric_Fence_block.get())
                .add(MainInit.BROWN_Electric_Fence_block.get());

        tag(MainInit.Color_Fence_Top)
                .add(MainInit.BLACK_Electric_FenceTop_block.get())
                .add(MainInit.RED_Electric_FenceTop_block.get())
                .add(MainInit.BLUE_Electric_FenceTop_block.get())
                .add(MainInit.YELLOW_Electric_FenceTop_block.get())
                .add(MainInit.GREEN_Electric_FenceTop_block.get())
                .add(MainInit.GRAY_Electric_FenceTop_block.get())
                .add(MainInit.LIGHT_BLUE_Electric_FenceTop_block.get())
                .add(MainInit.LIGHT_GRAY_Electric_FenceTop_block.get())
                .add(MainInit.LIME_Electric_FenceTop_block.get())
                .add(MainInit.MAGENTA_Electric_FenceTop_block.get())
                .add(MainInit.ORANGE_Electric_FenceTop_block.get())
                .add(MainInit.PINK_Electric_FenceTop_block.get())
                .add(MainInit.PURPLE_Electric_FenceTop_block.get())
                .add(MainInit.CYAN_Electric_FenceTop_block.get())
                .add(MainInit.WHITE_Electric_FenceTop_block.get())
                .add(MainInit.BROWN_Electric_FenceTop_block.get());

        tag(MainInit.Color_Fence_Gate)
                .add(MainInit.BLACK_Electric_FenceGate_block.get())
                .add(MainInit.RED_Electric_FenceGate_block.get())
                .add(MainInit.BLUE_Electric_FenceGate_block.get())
                .add(MainInit.YELLOW_Electric_FenceGate_block.get())
                .add(MainInit.GREEN_Electric_FenceGate_block.get())
                .add(MainInit.GRAY_Electric_FenceGate_block.get())
                .add(MainInit.LIGHT_BLUE_Electric_FenceGate_block.get())
                .add(MainInit.LIGHT_GRAY_Electric_FenceGate_block.get())
                .add(MainInit.LIME_Electric_FenceGate_block.get())
                .add(MainInit.MAGENTA_Electric_FenceGate_block.get())
                .add(MainInit.ORANGE_Electric_FenceGate_block.get())
                .add(MainInit.PINK_Electric_FenceGate_block.get())
                .add(MainInit.PURPLE_Electric_FenceGate_block.get())
                .add(MainInit.CYAN_Electric_FenceGate_block.get())
                .add(MainInit.WHITE_Electric_FenceGate_block.get())
                .add(MainInit.BROWN_Electric_FenceGate_block.get());


    }
}
