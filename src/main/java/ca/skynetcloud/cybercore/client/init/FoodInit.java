package ca.skynetcloud.cybercore.client.init;

import net.minecraft.world.food.FoodProperties;


public class FoodInit {

    public static FoodProperties TACO = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).meat().build();
    public static FoodProperties CHEESE = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
    public static FoodProperties TOMATO = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
    public static FoodProperties LETTUCE = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
}
