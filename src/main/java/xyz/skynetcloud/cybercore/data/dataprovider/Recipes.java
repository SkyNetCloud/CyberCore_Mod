package xyz.skynetcloud.cybercore.data.dataprovider;

import java.util.function.Consumer;

import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.api.items.ItemNames;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BlockInit.CYBER_ORE), ItemNames.cyber_ingot, 1.0F, 200)
				.build(consumer);

		CookingRecipeBuilder
				.smeltingRecipe(Ingredient.fromItems(Items.COAL.getItem()), ItemNames.cyber_ingot, 0.1F, 200)
				.build(consumer);

	}

}
