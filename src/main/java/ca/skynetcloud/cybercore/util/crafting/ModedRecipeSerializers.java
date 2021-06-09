package ca.skynetcloud.cybercore.util.crafting;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ItemConvterRecipe;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ItemConvterRecipe.Serializer;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class ModedRecipeSerializers {

	@ObjectHolder("coloring")
	public static ItemConvterRecipe.Serializer COLORCHNAGER = new Serializer();
}
