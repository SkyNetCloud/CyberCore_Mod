package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.client.utilities.CyberCoreTab;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

public class FoodInit {

    public static FoodProperties TACO = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).meat().build();
    public static FoodProperties CHEESE = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
    public static FoodProperties TOMATO = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
    public static FoodProperties LETTUCE = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
}
