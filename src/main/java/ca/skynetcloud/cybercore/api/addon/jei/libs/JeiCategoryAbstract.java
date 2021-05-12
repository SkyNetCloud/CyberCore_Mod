package ca.skynetcloud.cybercore.api.addon.jei.libs;

import ca.skynetcloud.cybercore.CyberCoreMain;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class JeiCategoryAbstract<T extends IRecipe<IInventory>> implements IRecipeCategory<T>{

	protected final static ResourceLocation TEXTURE = new ResourceLocation(CyberCoreMain.MODID,
			"textures/gui/jei/jeibackground.png");;
	protected String localizedName;
	protected ResourceLocation UID;
	private final IDrawable icon;
	protected IDrawable background;

	public JeiCategoryAbstract(String name,Item icon, IGuiHelper helper, int u, int v, int width, int height)
    {
    	this.UID = new ResourceLocation(CyberCoreMain.MODID, name); 
    	this.background = helper.createDrawable(TEXTURE, u, v, width, height); 
    	this.icon = helper.createDrawableIngredient(new ItemStack(icon));
    	localizedName = new TranslationTextComponent("cybercore." + name).getString();
    }

	@Override
	public ResourceLocation getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return localizedName;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

}
