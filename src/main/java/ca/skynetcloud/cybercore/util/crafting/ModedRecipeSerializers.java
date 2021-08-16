package ca.skynetcloud.cybercore.util.crafting;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ColorChangerRecipe;
import ca.skynetcloud.cybercore.util.crafting.recipeclasses.ColorChangerRecipe.Serializer;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class ModedRecipeSerializers {

	@ObjectHolder("coloring")
	public static ColorChangerRecipe.Serializer COLORCHNAGER = new Serializer();
}
