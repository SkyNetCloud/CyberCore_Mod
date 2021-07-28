package ca.skynetcloud.cybercore.util.networking.helper;

import java.util.Arrays;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class Names {

	public static final String NAME = "CyberCore";

	public static final String UUID = "TheLewdNeko";

	public static final String MODID = "cybercore";
	public static final String INFO = "INFO";

	public static final String CYBERVILLAGERCONTAINER = MODID + ":cybervillagercontainer";

	public static final String TECHVILLAGER = MODID + ":cybervillager";

	public static final BooleanProperty LIT = BooleanProperty.create("cooking");

	public static final String[] COLORED_Slab_Block_Names = Arrays.stream(DyeColor.values())
			.map(color -> color.toString() + "_slab_block").toArray(String[]::new);

	public static final String[] COLORED_Fence_Block_Names = Arrays.stream(DyeColor.values())
			.map(color -> color.toString() + "_electric_fence").toArray(String[]::new);

	public static final String[] COLORED_Fence_Top_Block_Names = Arrays.stream(DyeColor.values())
			.map(color -> color.toString() + "_electric_fence_top").toArray(String[]::new);

	public static final String[] COLORED_Fence_Gate_Block_Names = Arrays.stream(DyeColor.values())
			.map(color -> color.toString() + "_electric_fence_gate").toArray(String[]::new);

	public static final String[] COLORED_Power_Cable_Names = Arrays.stream(DyeColor.values())
			.map(color -> color.toString() + "_cable").toArray(String[]::new);

	public static final String[] COLORED_Item_TUBE_NAMES = Arrays.stream(DyeColor.values())
			.map(color -> color.toString() + "_block_pipe").toArray(String[]::new);

	public static final String TACO_NAME = (MODID + ":taco");
	public static final String CHEESE_NAME = (MODID + ":cheese");
	public static final String LETTUCE_NAME = (MODID + ":lettuce");
	public static final String TOMATO_NAME = (MODID + ":tomato");

	public static final String BLOCK_LOADER = "block_loader";
	public static final String BLOCK_EXTRACTOR = "block_extractor";

	public static final String IRRIGATION_BLOCK = "irrigation_block";
	public static final String LETTUCE_SEEDS_NAME = "lettuce_seed";
	public static final String TOMAO_SEEDS_NAME = "tomato_seed";

	public static final String SPEED_CARD_1 = (MODID + ":speed_upgrade_card_1");
	public static final String SPEED_CARD_2 = (MODID + ":speed_upgrade_card_2");
	public static final String SPEED_CARD_3 = (MODID + ":speed_upgrade_card_3");
	public static final String SPEED_CARD_4 = (MODID + ":speed_upgrade_card_4");

	public static final String POWER_CARD_1 = (MODID + ":power_storage_card_1");
	public static final String POWER_CARD_2 = (MODID + ":power_storage_card_2");
	public static final String POWER_CARD_3 = (MODID + ":power_storage_card_3");

	public static final String CABLE_PAINTER = "color_chnager";
	public static final String TOMATO_CROP = "tomato_crop_block";
	public static final String LETTUCE_CROP = "lettuce_crop_block";
	public static final String POWER_FURNACE_BLOCK = "powered_furnace";
	public static final String RUBY_BLOCK = "ruby_block";
	public static final String DARK_STEEL_BLOCK = "dark_steel_block";
	public static final String CYBER_ORE = ("cyber_ore");
	public static final String DARK_STEEL_ORE = "dark_steel_ore";
	public static final String RUBY_ORE = "ruby_ore";
	public static final String LUNAR_BLOCK = "lunarsolargenerator_block";
	public static final String POWER_BOX = "power_storage";
	public static final String FENCE_POWER_GIRD = "fence_power_gird";
	public static final String RUBY_SLAB = "ruby_slab";
	public static final String RUBY_STAIRS = "ruby_stairs";

	public static final String POWER_COOKER_CON_NAME = ("container." + POWER_FURNACE_BLOCK);
	public static final String POWER_BOX_CON_NAME = ("container." + POWER_BOX);

	public static final String CyberTAB = "cybercore";

	public static final String CyberTAB_Item_Cable = "cybercore_item_cable";

	public static final String CyberTAB_Power_Cable = "cybercore_power_cable";

	public static final String CyberTAB_Other_Tab = "cybercore_electric_fence";

	public static final String CABLE = "cable";
	public static final String BLOCK_PIPE = "block_pipe";

	public static final String Fence_Block_Top = "electric_fence_top";

	public static final String Fence_Block = "electric_fence";
	public static final String Fence_Gate_Block = "electric_fence_gate";
	public static final String Slab_Block = "slab_block";

	public static final String CYBER_INGOT = ("cyber_ingot");
	public static final String RUBY_INGOT = ("ruby_ingot");
	public static final String DARK_STEEL_INGOT = ("dark_steel_ingot");

	public static final String CYBER_AXE = (MODID + ":cyber_axe");
	public static final String CYBER_PICKAXE = (MODID + ":cyber_pickaxe");
	public static final String CYBER_SHOVEL = (MODID + ":cyber_shovel");
	public static final String CYBER_SWORD = (MODID + ":cyber_sword");
	public static final String CYBER_HOE = (MODID + ":cyber_hoe");

	public static final String RUBY_AXE = (MODID + ":ruby_axe");
	public static final String RUBY_PICKAXE = (MODID + ":ruby_pickaxe");
	public static final String RUBY_SHOVEL = (MODID + ":ruby_shovel");
	public static final String RUBY_SWORD = (MODID + ":ruby_sword");
	public static final String RUBY_HOE = (MODID + ":ruby_hoe");

	public static final String DARK_STEEL_AXE = (MODID + ":dark_steel_axe");
	public static final String DARK_STEEL_PICKAXE = (MODID + ":dark_steel_pickaxe");
	public static final String DARK_STEEL_SHOVEL = (MODID + ":dark_steel_shovel");
	public static final String DARK_STEEL_SWORD = (MODID + ":dark_steel_sword");
	public static final String DARK_STEEL_HOE = (MODID + ":dark_steel_hoe");

	public static final String LUNARGEN_CON = (MODID + ":lunar_gen_machine_con");
	public static final String POWER_BOX_CON = (MODID + ":power_box_con");

	public static final String POWERED_FURNACE_CON = (MODID + ":poweredfurnace_con");
	public static final String POWERED_IRRIGATION_CON = (MODID + ":poweredirrigation_con");
	public static final String CYBER_CHEST_CONS = (MODID + ":cyberchest_con");
	public static final String COLOR_CHANGER_CON = (MODID + ":color_changer_con");
	public static final String DARK_STEEL_HELMET_NAME = (MODID + ":dark_steel_helmet");
	public static final String DARK_STEEL_CHESTPLATE_NAME = (MODID + ":dark_steel_chestplate");
	public static final String DARK_STEEL_LEGGINGS_NAME = (MODID + ":dark_steel_leggings");
	public static final String DARK_STEEL_BOOTS_NAME = (MODID + ":dark_steel_boots");

	public static final String RUBY_HELMET_NAME = (MODID + ":ruby_helmet");
	public static final String RUBY_CHESTPLATE_NAME = (MODID + ":ruby_chestplate");
	public static final String RUBY_LEGGINGS_NAME = (MODID + ":ruby_leggings");
	public static final String RUBY_BOOTS_NAME = (MODID + ":ruby_boots");

	public static final String CYBER_HELMET_NAME = (MODID + ":cyber_helmet");
	public static final String CYBER_CHESTPLATE_NAME = (MODID + ":cyber_chestplate");
	public static final String CYBER_LEGGINGS_NAME = (MODID + ":cyber_leggings");
	public static final String CYBER_BOOTS_NAME = (MODID + ":cyber_boots");

}
