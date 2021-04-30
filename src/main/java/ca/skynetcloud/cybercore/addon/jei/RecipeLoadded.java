package ca.skynetcloud.cybercore.addon.jei;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.skynetcloud.cybercore.util.crafting.ModedRecipeTypes;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ColorChangerRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;

public class RecipeLoadded {



	public static List<ColorChangerRecipe> getColorChangerRecipes() {
		List<ColorChangerRecipe> results = new ArrayList<ColorChangerRecipe>();
		@SuppressWarnings("resource")
		ClientWorld world = Minecraft.getInstance().level;
		RecipeManager recipeManager = world.getRecipeManager();
		Iterator<IRecipe<?>> it = recipeManager.getRecipes().iterator();
		while (it.hasNext()) {
			IRecipe<?> recipe = it.next();
			if (recipe.getType() == ModedRecipeTypes.COLORING) {
				ColorChangerRecipe compressorrecipe = (ColorChangerRecipe) recipe;
				results.add(compressorrecipe);
			}
		}
		return results;
	}







}
