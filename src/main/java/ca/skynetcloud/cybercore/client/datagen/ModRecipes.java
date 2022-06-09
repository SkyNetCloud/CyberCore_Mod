package ca.skynetcloud.cybercore.client.datagen;

import ca.skynetcloud.cybercore.client.init.MainInit;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider {


    public ModRecipes(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(MainInit.ElectricFence_block.get())
                .pattern("iii")
                .pattern("bbb")
                .pattern("iii")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('b', Blocks.IRON_BARS)
                .group("cybercore")
                .unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MainInit.wrench_tool.get())
                .pattern("i i")
                .pattern(" i ")
                .pattern(" i ")
                .define('i', Items.IRON_INGOT)
                .group("cybercore")
                .unlockedBy("safetime", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MainInit.electrical_cabinet_block.get())
                .pattern("ibi")
                .pattern("bfb")
                .pattern("ibi")
                .define('i', Items.IRON_INGOT)
                .define('f', MainInit.shock_flower.get())
                .define('b', Items.IRON_BLOCK)
                .group("cybercore")
                .unlockedBy("basepower", InventoryChangeTrigger.TriggerInstance.hasItems(Blocks.REDSTONE_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MainInit.ElectricFenceGate_block.get())
                .pattern("ii ")
                .pattern("bb ")
                .pattern("ii ")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('b', Blocks.IRON_BARS)
                .group("cybercore")
                .unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MainInit.ElectricFenceTop_block.get())
                .pattern("i i")
                .pattern(" b ")
                .pattern(" i ")
                .define('i', Tags.Items.INGOTS_IRON)
                .define('b', Blocks.IRON_BARS)
                .group("cybercore")
                .unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(MainInit.WHITE_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.WHITE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.ORANGE_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.ORANGE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.MAGENTA_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.MAGENTA_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIGHT_BLUE_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.LIGHT_BLUE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.YELLOW_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.YELLOW_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIME_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.LIME_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.PINK_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.PINK_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.GRAY_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.GRAY_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIGHT_GRAY_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.LIGHT_GRAY_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.CYAN_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.CYAN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.PURPLE_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.PURPLE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BLUE_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.BLUE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BROWN_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.BROWN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.GREEN_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.GREEN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.RED_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.RED_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BLACK_Electric_Fence_block.get()).requires(MainInit.ElectricFence_block.get()).requires(Items.BLACK_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(MainInit.WHITE_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.WHITE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.ORANGE_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.ORANGE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.MAGENTA_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.MAGENTA_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIGHT_BLUE_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.LIGHT_BLUE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.YELLOW_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.YELLOW_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIME_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.LIME_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.PINK_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.PINK_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.GRAY_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.GRAY_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIGHT_GRAY_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.LIGHT_GRAY_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.CYAN_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.CYAN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.PURPLE_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.PURPLE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BLUE_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.BLUE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BROWN_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.BROWN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.GREEN_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.GREEN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.RED_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.RED_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BLACK_Electric_FenceTop_block.get()).requires(MainInit.ElectricFenceTop_block.get()).requires(Items.BLACK_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);

        ShapelessRecipeBuilder.shapeless(MainInit.WHITE_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.WHITE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.ORANGE_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.ORANGE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.MAGENTA_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.MAGENTA_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIGHT_BLUE_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.LIGHT_BLUE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.YELLOW_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.YELLOW_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIME_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.LIME_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.PINK_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.PINK_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.GRAY_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.GRAY_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.LIGHT_GRAY_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.LIGHT_GRAY_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.CYAN_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.CYAN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.PURPLE_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.PURPLE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BLUE_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.BLUE_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BROWN_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.BROWN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.GREEN_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.GREEN_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.RED_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.RED_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        ShapelessRecipeBuilder.shapeless(MainInit.BLACK_Electric_FenceGate_block.get()).requires(MainInit.ElectricFenceGate_block.get()).requires(Items.BLACK_DYE).group("cybercore").unlockedBy("electrified", InventoryChangeTrigger.TriggerInstance.hasItems(MainInit.ElectricCabinet_item.get())).save(consumer);
        

    }
   
}
