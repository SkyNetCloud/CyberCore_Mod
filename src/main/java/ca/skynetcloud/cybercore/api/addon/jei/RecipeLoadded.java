package ca.skynetcloud.cybercore.api.addon.jei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.skynetcloud.cybercore.util.crafting.ModedRecipeTypes;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ItemConvterRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.recipe.RecipeManager;

public class RecipeLoadded {



	public static List<ItemConvterRecipe> getItemConvterRecipes() {
		List<ItemConvterRecipe> results = new ArrayList<ItemConvterRecipe>();
		@SuppressWarnings("resource")
		ClientWorld world = Minecraft.getInstance().level;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == ModedRecipeTypes.Item_Convter_Recipes) {
				ItemConvterRecipe compressorrecipe = (ItemConvterRecipe) recipe;
				results.add(compressorrecipe);
			}
		}
		return results;
	}







}
