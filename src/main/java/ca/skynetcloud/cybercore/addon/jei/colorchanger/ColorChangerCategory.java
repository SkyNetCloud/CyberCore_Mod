package ca.skynetcloud.cybercore.addon.jei.colorchanger;

import ca.skynetcloud.cybercore.addon.jei.libs.JeiCategoryAbstract;
import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ColorChangerRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;


public class ColorChangerCategory extends JeiCategoryAbstract<ColorChangerRecipe>{
	
	protected static final int INPUT = 0;
	protected static final int OUTPUT = 1;
	
	
	public ColorChangerCategory(IGuiHelper helper)
	{
		super("color_changer", BlockInit.C_Changer_Block.asItem(), helper, 32, 64, 60, 18);
	}


	@Override
	public Class<? extends ColorChangerRecipe> getRecipeClass()
	{
		return ColorChangerRecipe.class;
	}


	@Override
	public void setIngredients(ColorChangerRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInput(VanillaTypes.ITEM, recipe.getInput());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}


	@Override
	public void setRecipe(IRecipeLayout recipeLayout, ColorChangerRecipe recipe, IIngredients ingredients)
	{
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(INPUT, true, 0, 0);
		stacks.init(OUTPUT, false, 42, 0);
		stacks.set(ingredients);
	}

}
