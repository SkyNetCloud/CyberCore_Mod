package ca.skynetcloud.cybercore.api.addon.jei;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.api.addon.jei.colorchanger.ItemConvterCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class CyberCoreJeiPlugin implements IModPlugin {
	private ItemConvterCategory item_Convter_Category;

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation(ModIds.JEI_ID, "cybercore");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IJeiHelpers jeiHelpers = registration.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		item_Convter_Category = new ItemConvterCategory(guiHelper);
		registration.addRecipeCategories(item_Convter_Category);
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(RecipeLoadded.getItemConvterRecipes(),
				new ResourceLocation(CyberCoreMain.MODID, "item_convter"));

	}
}