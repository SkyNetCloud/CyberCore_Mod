package ca.skynetcloud.cybercore.util.crafting;

import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ItemConvterRecipe;
import net.minecraft.item.crafting.IRecipeType;

public final class ModedRecipeTypes {
	public static final IRecipeType<ItemConvterRecipe> Item_Convter_Recipes = IRecipeType
			.register("cybercore:coloring");
}
