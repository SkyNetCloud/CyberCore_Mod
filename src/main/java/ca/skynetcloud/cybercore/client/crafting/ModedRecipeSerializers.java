package ca.skynetcloud.cybercore.client.crafting;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.client.crafting.recipeclasses.ColorChangerRecipe;
import ca.skynetcloud.cybercore.client.crafting.recipeclasses.ColorChangerRecipe.Serializer;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CyberCoreMain.MODID)
public class ModedRecipeSerializers {

	@ObjectHolder("coloring")
	public static ColorChangerRecipe.Serializer COLORCHNAGER = new Serializer();
}
