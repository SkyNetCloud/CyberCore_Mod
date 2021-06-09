package ca.skynetcloud.cybercore.api.addon.jei.colorchanger;

import ca.skynetcloud.cybercore.api.addon.jei.libs.JeiCategoryAbstract;
import ca.skynetcloud.cybercore.init.BlockInit;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ItemConvterRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;

public class ItemConvterCategory extends JeiCategoryAbstract<ItemConvterRecipe> {

	protected static final int INPUT = 0;
	protected static final int OUTPUT = 1;

	public ItemConvterCategory(IGuiHelper helper) {
		super("color_changer", BlockInit.ItemConvterBlock.asItem(), helper, 32, 64, 60, 18);
	}

	@Override
	public Class<? extends ItemConvterRecipe> getRecipeClass() {
		return ItemConvterRecipe.class;
	}

	@Override
	public void setIngredients(ItemConvterRecipe recipe, IIngredients ingredients) {
		ingredients.setInput(VanillaTypes.ITEM, recipe.getInput());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ItemConvterRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(INPUT, true, 0, 0);
		stacks.init(OUTPUT, false, 42, 0);
		stacks.set(ingredients);
	}

}
