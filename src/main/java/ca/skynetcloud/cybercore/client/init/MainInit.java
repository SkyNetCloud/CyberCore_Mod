package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.client.container.PowerCubeMenu;
import ca.skynetcloud.cybercore.client.container.PoweredFurnaceMenu;
import ca.skynetcloud.cybercore.client.world.item.WrenchItem;
import ca.skynetcloud.cybercore.client.world.item.armor.CyberArmor;
import ca.skynetcloud.cybercore.client.world.item.armor.material.ArmorMaterialWrapper;
import ca.skynetcloud.cybercore.client.world.item.enchantment.EnchantmentSoulbound;
import ca.skynetcloud.cybercore.client.world.item.tools.*;
import ca.skynetcloud.cybercore.client.world.item.tools.farming.PlaterTool;
import ca.skynetcloud.cybercore.client.world.item.tools.farming.TillerTool;
import ca.skynetcloud.cybercore.client.world.item.tools.material.ToolMaterialWrapper;
import ca.skynetcloud.cybercore.client.world.level.block.*;
import ca.skynetcloud.cybercore.client.world.level.block.crops.LettuceCrop;
import ca.skynetcloud.cybercore.client.world.level.block.crops.TomatoCrop;
import ca.skynetcloud.cybercore.client.world.level.block.gate.ElectricFenceGate;
import ca.skynetcloud.cybercore.client.world.level.block.tech.PowerCube;
import ca.skynetcloud.cybercore.client.world.level.block.tech.TechBaseBlock;
import ca.skynetcloud.cybercore.client.world.level.block.tech.cable.ItemCable;
import ca.skynetcloud.cybercore.client.world.level.block.tech.cable.PowerCable;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.ItemCableBlockEntity;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.PowerCableBlockEntity;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.PoweredFurnaceBlockEntity;
import ca.skynetcloud.cybercore.client.world.level.block.top.ElectricFenceTop;
import ca.skynetcloud.cybercore.client.world.ores.OreBiomeModifier;
import ca.skynetcloud.cybercore.client.world.ores.Ores;
import ca.skynetcloud.cybercore.client.world.structures.PortalStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static ca.skynetcloud.cybercore.CyberCore.MODID;
import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.BLOCKS_TAB;
import static ca.skynetcloud.cybercore.client.utilities.CyberCoreTab.MAIN_TAB;
import static ca.skynetcloud.cybercore.client.world.ores.OreBiomeModifier.ORE_BIOME_MODIFIER_NAME;

public class MainInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MODID);

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    //private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, MODID);
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MODID);


    //region Biome & Dim Stuff
    public static final RegistryObject<Codec<? extends BiomeModifier>> ORE_BIOME_MODIFIER;
    public static final RegistryObject<PlacedFeature> ORE_OVERWORLD;
    //endregion

    //#region Block
    public static RegistryObject<Block> POWERED_FURNACE;

    public static final RegistryObject<Block> PORTAL_BLOCK;

    public static RegistryObject<Block> TOMATO_CROP;
    public static RegistryObject<Block> LETTUCE_CROP;
    public static RegistryObject<PowerCube> POWER_CUBE;
    public static RegistryObject<Block> CYBER_ORE_BLOCK_block;
    public static RegistryObject<Block> DARK_STEEL_ORE_BLOCK_block;
    public static RegistryObject<Block> RUBY_ORE_BLOCK_block;
    public static RegistryObject<Block> POWER_CABLE;
    public static RegistryObject<Block> ITEM_CABLE;
    public static RegistryObject<Block> ElectricFence_block;
    public static RegistryObject<Block> ElectricFenceTop_block;
    public static RegistryObject<Block> ElectricFenceGate_block;
    public static RegistryObject<Block> electrical_cabinet_block;
    public static RegistryObject<Block> WHITE_Electric_Fence_block;
    public static RegistryObject<Block> ORANGE_Electric_Fence_block;
    public static RegistryObject<Block> MAGENTA_Electric_Fence_block;
    public static RegistryObject<Block> LIGHT_BLUE_Electric_Fence_block;
    public static RegistryObject<Block> YELLOW_Electric_Fence_block;
    public static RegistryObject<Block> LIME_Electric_Fence_block;
    public static RegistryObject<Block> PINK_Electric_Fence_block;
    public static RegistryObject<Block> GRAY_Electric_Fence_block;
    public static RegistryObject<Block> LIGHT_GRAY_Electric_Fence_block;
    public static RegistryObject<Block> CYAN_Electric_Fence_block;
    public static RegistryObject<Block> PURPLE_Electric_Fence_block;
    public static RegistryObject<Block> BLUE_Electric_Fence_block;
    public static RegistryObject<Block> BROWN_Electric_Fence_block;
    public static RegistryObject<Block> GREEN_Electric_Fence_block;
    public static RegistryObject<Block> RED_Electric_Fence_block;
    public static RegistryObject<Block> BLACK_Electric_Fence_block;
    public static RegistryObject<Block> WHITE_Electric_FenceTop_block;
    public static RegistryObject<Block> ORANGE_Electric_FenceTop_block;
    public static RegistryObject<Block> MAGENTA_Electric_FenceTop_block;
    public static RegistryObject<Block> LIGHT_BLUE_Electric_FenceTop_block;
    public static RegistryObject<Block> YELLOW_Electric_FenceTop_block;
    public static RegistryObject<Block> LIME_Electric_FenceTop_block;
    public static RegistryObject<Block> PINK_Electric_FenceTop_block;
    public static RegistryObject<Block> GRAY_Electric_FenceTop_block;
    public static RegistryObject<Block> LIGHT_GRAY_Electric_FenceTop_block;
    public static RegistryObject<Block> CYAN_Electric_FenceTop_block;
    public static RegistryObject<Block> PURPLE_Electric_FenceTop_block;
    public static RegistryObject<Block> BLUE_Electric_FenceTop_block;
    public static RegistryObject<Block> BROWN_Electric_FenceTop_block;
    public static RegistryObject<Block> GREEN_Electric_FenceTop_block;
    public static RegistryObject<Block> RED_Electric_FenceTop_block;
    public static RegistryObject<Block> BLACK_Electric_FenceTop_block;
    public static RegistryObject<Block> WHITE_Electric_FenceGate_block;
    public static RegistryObject<Block> ORANGE_Electric_FenceGate_block;
    public static RegistryObject<Block> MAGENTA_Electric_FenceGate_block;
    public static RegistryObject<Block> LIGHT_BLUE_Electric_FenceGate_block;
    public static RegistryObject<Block> YELLOW_Electric_FenceGate_block;
    public static RegistryObject<Block> LIME_Electric_FenceGate_block;
    public static RegistryObject<Block> PINK_Electric_FenceGate_block;
    public static RegistryObject<Block> GRAY_Electric_FenceGate_block;
    public static RegistryObject<Block> LIGHT_GRAY_Electric_FenceGate_block;
    public static RegistryObject<Block> CYAN_Electric_FenceGate_block;
    public static RegistryObject<Block> PURPLE_Electric_FenceGate_block;
    public static RegistryObject<Block> BLUE_Electric_FenceGate_block;
    public static RegistryObject<Block> BROWN_Electric_FenceGate_block;
    public static RegistryObject<Block> GREEN_Electric_FenceGate_block;
    public static RegistryObject<Block> RED_Electric_FenceGate_block;
    public static RegistryObject<Block> BLACK_Electric_FenceGate_block;
    public static RegistryObject<Block> SHOCKFLOWER;
    public static final BlockBehaviour.Properties BLOCK_PROPERTIES;

    public static final RegistryObject<Block> MYSTERIOUS_ORE_OVERWORLD;



    //#endregion

    //region Sounds


    //endregion

    //region Containers
    public static final RegistryObject<MenuType<PoweredFurnaceMenu>> POWERED_FURNACE_MENU;
    public static final RegistryObject<MenuType<PowerCubeMenu>> POWER_CUBE_MENU;
    //endregion

    //#region BE
    public static RegistryObject<BlockEntityType<PowerCableBlockEntity>> POWER_CABLE_BE;;
    public static RegistryObject<BlockEntityType<ItemCableBlockEntity>> ITEM_CABLE_BE;
    public static RegistryObject<BlockEntityType<PowerCubeBlockEntity>> POWER_CUBE_BE;
    public static RegistryObject<BlockEntityType<PoweredFurnaceBlockEntity>> POWERED_FURNACE_BE;
    //endregion

    //#region Tags
    public static final TagKey<Block> Color_Fence;
    public static final TagKey<Block> Color_Fence_Top;
    public static final TagKey<Block> Color_Fence_Gate;
    //#endregion

    //region Items

    public static RegistryObject<Enchantment> SOUL_BOUND;

    public static RegistryObject<Item> PORTAL_ITEM;
    public static RegistryObject<Item> CYBER_ORE_BLOCK_item;
    public static RegistryObject<Item> DARK_STEEL_ORE_BLOCK_item;
    public static RegistryObject<Item> RUBY_ORE_BLOCK_item;


    public static RegistryObject<Item> TOMATO_SEED;
    public static RegistryObject<Item> LETTUCE_SEED;

    public static RegistryObject<Item> TILLER_TOOL;
    public static RegistryObject<Item> PLATER_TOOL;

    public static RegistryObject<Item> CYBER_HELMET;
    public static RegistryObject<Item> CYBER_CHESTPLATE;
    public static RegistryObject<Item> CYBER_LEGGINGS;
    public static RegistryObject<Item> CYBER_BOOTS;

    public static RegistryObject<Item> RUBY_HELMET;
    public static RegistryObject<Item> RUBY_CHESTPLATE;
    public static RegistryObject<Item> RUBY_LEGGINGS;
    public static RegistryObject<Item> RUBY_BOOTS;

    public static RegistryObject<Item> DARK_STEEL_HELMET;
    public static RegistryObject<Item> DARK_STEEL_CHESTPLATE;
    public static RegistryObject<Item> DARK_STEEL_LEGGINGS;
    public static RegistryObject<Item> DARK_STEEL_BOOTS;

    public static RegistryObject<Item> CYBER_AXE;
    public static RegistryObject<Item> CYBER_PICKAXE;
    public static RegistryObject<Item> CYBER_SHOVEL;
    public static RegistryObject<Item> CYBER_SWORD;
    public static RegistryObject<Item> CYBER_HOE;

    public static RegistryObject<Item> RUBY_AXE;
    public static RegistryObject<Item> RUBY_PICKAXE;
    public static RegistryObject<Item> RUBY_SHOVEL;
    public static RegistryObject<Item> RUBY_SWORD;
    public static RegistryObject<Item> RUBY_HOE;

    public static RegistryObject<Item> DARK_STEEL_AXE;
    public static RegistryObject<Item> DARK_STEEL_PICKAXE;

    public static RegistryObject<Item> DARK_STEEL_SHOVEL;
    public static RegistryObject<Item> DARK_STEEL_SWORD;
    public static RegistryObject<Item> DARK_STEEL_HOE;

    public static RegistryObject<Item> TACO;

    public static RegistryObject<Item> CHEESE;
    public static RegistryObject<Item> TOMATO;
    public static RegistryObject<Item> LETTUCE;


    public static RegistryObject<Item> CYBER_INGOT;
    public static RegistryObject<Item> DARK_STEEL_INGOT;
    public static RegistryObject<Item> RUBY_GEM;


    public static RegistryObject<Item> ElectricCabinet_item;
    public static RegistryObject<Item> ElectricFence_item;
    public static RegistryObject<Item> ElectricFenceTop_item;
    public static RegistryObject<Item> ElectricFenceGate_item;
    public static RegistryObject<Item> WHITE_Electric_Fence_item;
    public static RegistryObject<Item> ORANGE_Electric_Fence_item;
    public static RegistryObject<Item> MAGENTA_Electric_Fence_item;
    public static RegistryObject<Item> LIGHT_BLUE_Electric_Fence_item;
    public static RegistryObject<Item> YELLOW_Electric_Fence_item;
    public static RegistryObject<Item> LIME_Electric_Fence_item;
    public static RegistryObject<Item> PINK_Electric_Fence_item;
    public static RegistryObject<Item> GRAY_Electric_Fence_item;
    public static RegistryObject<Item> LIGHT_GRAY_Electric_Fence_item;
    public static RegistryObject<Item> CYAN_Electric_Fence_item;
    public static RegistryObject<Item> PURPLE_Electric_Fence_item;
    public static RegistryObject<Item> BLUE_Electric_Fence_item;
    public static RegistryObject<Item> BROWN_Electric_Fence_item;
    public static RegistryObject<Item> GREEN_Electric_Fence_item;
    public static RegistryObject<Item> RED_Electric_Fence_item;
    public static RegistryObject<Item> BLACK_Electric_Fence_item;
    public static RegistryObject<Item> WHITE_Electric_FenceTop_item;
    public static RegistryObject<Item> ORANGE_Electric_FenceTop_item;
    public static RegistryObject<Item> MAGENTA_Electric_FenceTop_item;
    public static RegistryObject<Item> LIGHT_BLUE_Electric_FenceTop_item;
    public static RegistryObject<Item> YELLOW_Electric_FenceTop_item;
    public static RegistryObject<Item> LIME_Electric_FenceTop_item;
    public static RegistryObject<Item> PINK_Electric_FenceTop_item;
    public static RegistryObject<Item> GRAY_Electric_FenceTop_item;
    public static RegistryObject<Item> LIGHT_GRAY_Electric_FenceTop_item;
    public static RegistryObject<Item> CYAN_Electric_FenceTop_item;
    public static RegistryObject<Item> PURPLE_Electric_FenceTop_item;
    public static RegistryObject<Item> BLUE_Electric_FenceTop_item;
    public static RegistryObject<Item> BROWN_Electric_FenceTop_item;
    public static RegistryObject<Item> GREEN_Electric_FenceTop_item;
    public static RegistryObject<Item> RED_Electric_FenceTop_item;
    public static RegistryObject<Item> BLACK_Electric_FenceTop_item;
    public static RegistryObject<Item> WHITE_Electric_FenceGate_item;
    public static RegistryObject<Item> ORANGE_Electric_FenceGate_item;
    public static RegistryObject<Item> MAGENTA_Electric_FenceGate_item;
    public static RegistryObject<Item> LIGHT_BLUE_Electric_FenceGate_item;
    public static RegistryObject<Item> YELLOW_Electric_FenceGate_item;
    public static RegistryObject<Item> LIME_Electric_FenceGate_item;

    public static RegistryObject<Item> MYSTERIOUS_ORE_OVERWORLD_ITEM;
    public static RegistryObject<Item> PINK_Electric_FenceGate_item;
    public static RegistryObject<Item> GRAY_Electric_FenceGate_item;
    public static RegistryObject<Item> LIGHT_GRAY_Electric_FenceGate_item;
    public static RegistryObject<Item> CYAN_Electric_FenceGate_item;
    public static RegistryObject<Item> PURPLE_Electric_FenceGate_item;
    public static RegistryObject<Item> BLUE_Electric_FenceGate_item;
    public static RegistryObject<Item> BROWN_Electric_FenceGate_item;
    public static RegistryObject<Item> GREEN_Electric_FenceGate_item;
    public static RegistryObject<Item> RED_Electric_FenceGate_item;
    public static RegistryObject<Item> BLACK_Electric_FenceGate_item;
    public static RegistryObject<Item> wrench_tool;
    public static RegistryObject<Item> shock_flower;
    public static RegistryObject<Item> Power_Cube_Item;
    //endregion

    //region Other
    public static RegistryObject<StructureType<?>> PORTAL;
    public static ResourceLocation RL_MYSTERIOUS_DIMENSION_SET;
    public static TagKey<Biome> HAS_PORTAL;
    public static  TagKey<StructureSet> MYSTERIOUS_DIMENSION_STRUCTURE_SET;
    public static RegistryObject<BlockEntityType<GeneratorBE>> GENERATOR_BE;

    //ender


    //region DamageStuff
    public static DamageSource ELECTRIC_FENCE;
    //endregion

    static {
        GENERATOR_BE = BLOCK_ENTITIES.register("generator", () -> BlockEntityType.Builder.of(GeneratorBE::new, POWER_CUBE.get()).build(null));
        RL_MYSTERIOUS_DIMENSION_SET = new ResourceLocation(MODID, "mysterious_dimension_structure_set");
        HAS_PORTAL = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MODID, "has_structure/portal"));
        MYSTERIOUS_DIMENSION_STRUCTURE_SET = TagKey.create(Registry.STRUCTURE_SET_REGISTRY, RL_MYSTERIOUS_DIMENSION_SET);
        PORTAL = STRUCTURES.register("portal", () -> typeConvert(PortalStructure.CODEC));


        //region Dim & Biome Stuff
        RL_MYSTERIOUS_DIMENSION_SET = new ResourceLocation(MODID, "mysterious_dimension_structure_set");
        ORE_OVERWORLD = PLACED_FEATURES.register("overworld_mysterious_ore", () -> Ores.createOverworldOregen().get());
        ORE_BIOME_MODIFIER = BIOME_MODIFIERS.register(ORE_BIOME_MODIFIER_NAME, OreBiomeModifier::makeCodec);
        //endregion

        SOUL_BOUND = ENCHANTMENTS.register("soul_bound_book", () -> new EnchantmentSoulbound());

        //region DamageStuff
        ELECTRIC_FENCE = new DamageSource("Electric_Fence");
        //endregion

        //region Con
        POWERED_FURNACE_MENU = CONTAINERS.register("powered_furnace_menu", () -> new MenuType<>(PoweredFurnaceMenu::new));
        POWER_CUBE_MENU = CONTAINERS.register("power_cube_menu", () -> new MenuType<>(PowerCubeMenu::new));
        //endregion

        //#region Block Static
        PORTAL_BLOCK = BLOCKS.register("portal", PortalBlock::new);
        BLOCK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(2f).requiresCorrectToolForDrops();
        MYSTERIOUS_ORE_OVERWORLD = BLOCKS.register("mysterious_ore_overworld", () -> new Block(BLOCK_PROPERTIES));
        POWERED_FURNACE = BLOCKS.register("powered_furnace", () -> new TechBaseBlock(PoweredFurnaceBlockEntity::new));
        TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new TomatoCrop());
        LETTUCE_CROP = BLOCKS.register("lettuce_crop", () -> new LettuceCrop());
        POWER_CUBE = BLOCKS.register("power_cube", () -> new PowerCube(PowerCubeBlockEntity::new));
        Power_Cube_Item = ITEMS.register("power_cube", () -> new BlockItem(POWER_CUBE.get(), new Item.Properties().tab(BLOCKS_TAB)));
        CYBER_ORE_BLOCK_block = BLOCKS.register("cyber_ore", () -> new Block(BLOCK_PROPERTIES));
        DARK_STEEL_ORE_BLOCK_block = BLOCKS.register("dark_steel_ore", () -> new Block(BLOCK_PROPERTIES));
        RUBY_ORE_BLOCK_block = BLOCKS.register("ruby_ore", () -> new Block(BLOCK_PROPERTIES));
        POWER_CABLE = BLOCKS.register("power_cable_block", () -> new PowerCable());
        ITEM_CABLE = BLOCKS.register("item_cable_block", () -> new ItemCable());
        SHOCKFLOWER = BLOCKS.register("shock_flower", () -> new ShockflowerBlock());
        YELLOW_Electric_FenceGate_block = BLOCKS.register("yellow_electric_fence_gate", () -> new ElectricFenceGate());
        LIGHT_BLUE_Electric_FenceGate_block = BLOCKS.register("light_blue_electric_fence_gate", () -> new ElectricFenceGate());
        MAGENTA_Electric_FenceGate_block = BLOCKS.register("magenta_electric_fence_gate", () -> new ElectricFenceGate());
        ORANGE_Electric_FenceGate_block = BLOCKS.register("orange_electric_fence_gate", () -> new ElectricFenceGate());
        WHITE_Electric_FenceGate_block = BLOCKS.register("white_electric_fence_gate", () -> new ElectricFenceGate());
        BLACK_Electric_FenceTop_block = BLOCKS.register("black_electric_fence_top", () -> new ElectricFenceTop());
        RED_Electric_FenceTop_block = BLOCKS.register("red_electric_fence_top", () -> new ElectricFenceTop());
        GREEN_Electric_FenceTop_block = BLOCKS.register("green_electric_fence_top", () -> new ElectricFenceTop());
        BROWN_Electric_FenceTop_block = BLOCKS.register("brown_electric_fence_top", () -> new ElectricFenceTop());
        BLUE_Electric_FenceTop_block = BLOCKS.register("blue_electric_fence_top", () -> new ElectricFenceTop());
        PURPLE_Electric_FenceTop_block = BLOCKS.register("purple_electric_fence_top", () -> new ElectricFenceTop());
        CYAN_Electric_FenceTop_block = BLOCKS.register("cyan_electric_fence_top", () -> new ElectricFenceTop());
        LIGHT_GRAY_Electric_FenceTop_block = BLOCKS.register("light_gray_electric_fence_top", () -> new ElectricFenceTop());
        GRAY_Electric_FenceTop_block = BLOCKS.register("gray_electric_fence_top", () -> new ElectricFenceTop());
        PINK_Electric_FenceTop_block = BLOCKS.register("pink_electric_fence_top", () -> new ElectricFenceTop());
        LIME_Electric_FenceTop_block = BLOCKS.register("lime_electric_fence_top", () -> new ElectricFenceTop());
        YELLOW_Electric_FenceTop_block = BLOCKS.register("yellow_electric_fence_top", () -> new ElectricFenceTop());
        LIGHT_BLUE_Electric_FenceTop_block = BLOCKS.register("light_blue_electric_fence_top", () -> new ElectricFenceTop());
        MAGENTA_Electric_FenceTop_block = BLOCKS.register("magenta_electric_fence_top", () -> new ElectricFenceTop());
        ORANGE_Electric_FenceTop_block = BLOCKS.register("orange_electric_fence_top", () -> new ElectricFenceTop());
        WHITE_Electric_FenceTop_block = BLOCKS.register("white_electric_fence_top", () -> new ElectricFenceTop());
        BLACK_Electric_Fence_block = BLOCKS.register("black_electric_fence", () -> new ElectricFencesBlockBase());
        RED_Electric_Fence_block = BLOCKS.register("red_electric_fence", () -> new ElectricFencesBlockBase());
        GREEN_Electric_Fence_block = BLOCKS.register("green_electric_fence", () -> new ElectricFencesBlockBase());
        BROWN_Electric_Fence_block = BLOCKS.register("brown_electric_fence", () -> new ElectricFencesBlockBase());
        BLUE_Electric_Fence_block = BLOCKS.register("blue_electric_fence", () -> new ElectricFencesBlockBase());
        PURPLE_Electric_Fence_block = BLOCKS.register("purple_electric_fence", () -> new ElectricFencesBlockBase());
        CYAN_Electric_Fence_block = BLOCKS.register("cyan_electric_fence", () -> new ElectricFencesBlockBase());
        LIGHT_GRAY_Electric_Fence_block = BLOCKS.register("light_gray_electric_fence", () -> new ElectricFencesBlockBase());
        GRAY_Electric_Fence_block = BLOCKS.register("gray_electric_fence", () -> new ElectricFencesBlockBase());
        PINK_Electric_Fence_block = BLOCKS.register("pink_electric_fence", () -> new ElectricFencesBlockBase());
        LIME_Electric_FenceGate_block = BLOCKS.register("lime_electric_fence_gate", () -> new ElectricFenceGate());
        PINK_Electric_FenceGate_block = BLOCKS.register("pink_electric_fence_gate", () -> new ElectricFenceGate());
        GRAY_Electric_FenceGate_block = BLOCKS.register("gray_electric_fence_gate", () -> new ElectricFenceGate());
        LIGHT_GRAY_Electric_FenceGate_block = BLOCKS.register("light_gray_electric_fence_gate", () -> new ElectricFenceGate());
        CYAN_Electric_FenceGate_block = BLOCKS.register("cyan_electric_fence_gate", () -> new ElectricFenceGate());
        PURPLE_Electric_FenceGate_block = BLOCKS.register("purple_electric_fence_gate", () -> new ElectricFenceGate());
        RED_Electric_FenceGate_block = BLOCKS.register("red_electric_fence_gate", () -> new ElectricFenceGate());
        GREEN_Electric_FenceGate_block = BLOCKS.register("green_electric_fence_gate", () -> new ElectricFenceGate());
        BROWN_Electric_FenceGate_block = BLOCKS.register("brown_electric_fence_gate", () -> new ElectricFenceGate());
        BLUE_Electric_FenceGate_block = BLOCKS.register("blue_electric_fence_gate", () -> new ElectricFenceGate());
        BLACK_Electric_FenceGate_block = BLOCKS.register("black_electric_fence_gate", () -> new ElectricFenceGate());
        LIME_Electric_Fence_block = BLOCKS.register("lime_electric_fence", () -> new ElectricFencesBlockBase());
        YELLOW_Electric_Fence_block = BLOCKS.register("yellow_electric_fence", () -> new ElectricFencesBlockBase());
        LIGHT_BLUE_Electric_Fence_block = BLOCKS.register("light_blue_electric_fence", () -> new ElectricFencesBlockBase());
        MAGENTA_Electric_Fence_block = BLOCKS.register("magenta_electric_fence", () -> new ElectricFencesBlockBase());
        ORANGE_Electric_Fence_block = BLOCKS.register("orange_electric_fence", () -> new ElectricFencesBlockBase());
        WHITE_Electric_Fence_block = BLOCKS.register("white_electric_fence", () -> new ElectricFencesBlockBase());
        electrical_cabinet_block = BLOCKS.register("electrical_cabinet", () -> new ElectricalCabinet());
        ElectricFenceGate_block = BLOCKS.register("electric_fence_gate", () -> new ElectricFenceGate());
        ElectricFenceTop_block = BLOCKS.register("electric_fence_top", () -> new ElectricFenceTop());
        ElectricFence_block = BLOCKS.register("electric_fence", () -> new ElectricFencesBlockBase());
        //#endregion

        //region Items


        PORTAL_ITEM = ITEMS.register("portal", () -> new BlockItem(PORTAL_BLOCK.get(), new Item.Properties().tab(BLOCKS_TAB)));
        MYSTERIOUS_ORE_OVERWORLD_ITEM = ITEMS.register("mysterious_ore_overworld", () -> new BlockItem(MYSTERIOUS_ORE_OVERWORLD.get(), new Item.Properties().tab(BLOCKS_TAB)));
        RUBY_ORE_BLOCK_item = ITEMS.register("ruby_ore", () -> new BlockItem(RUBY_ORE_BLOCK_block.get(), new Item.Properties().tab(BLOCKS_TAB)));
        DARK_STEEL_ORE_BLOCK_item = ITEMS.register("dark_steel_ore", () -> new BlockItem(MainInit.DARK_STEEL_ORE_BLOCK_block.get(), new Item.Properties().tab(BLOCKS_TAB)));
        CYBER_ORE_BLOCK_item = ITEMS.register("cyber_ore", () -> new BlockItem(MainInit.CYBER_ORE_BLOCK_block.get(), new Item.Properties().tab(BLOCKS_TAB)));
        TOMATO_SEED = ITEMS.register("tomato_seed", () -> new SeedInit(MainInit.TOMATO_CROP.get()));
        LETTUCE_SEED = ITEMS.register("lettuce_seed", () -> new SeedInit(MainInit.LETTUCE_CROP.get()));
        TILLER_TOOL = ITEMS.register("tiller_tool", () -> new TillerTool(Tiers.GOLD, new Item.Properties().tab(MAIN_TAB)));
        PLATER_TOOL = ITEMS.register("plater_tool", () -> new PlaterTool(new Item.Properties().tab(MAIN_TAB)));
        CYBER_HELMET = ITEMS.register("cyber_armor_head", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.HEAD, new Item.Properties().tab(MAIN_TAB)));
        CYBER_CHESTPLATE = ITEMS.register("cyber_armor_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.CHEST, new Item.Properties().tab(MAIN_TAB)));
        CYBER_LEGGINGS = ITEMS.register("cyber_armor_leg", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.LEGS, new Item.Properties().tab(MAIN_TAB)));
        CYBER_BOOTS = ITEMS.register("cyber_armor_feet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "cyber", EquipmentSlot.FEET, new Item.Properties().tab(MAIN_TAB)));
        RUBY_HELMET = ITEMS.register("ruby_armor_head", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.HEAD, new Item.Properties().tab(MAIN_TAB)));
        RUBY_CHESTPLATE = ITEMS.register("ruby_armor_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.CHEST, new Item.Properties().tab(MAIN_TAB)));
        RUBY_LEGGINGS = ITEMS.register("ruby_armor_leg", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.LEGS, new Item.Properties().tab(MAIN_TAB)));
        RUBY_BOOTS = ITEMS.register("ruby_armor_feet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "ruby_gem", EquipmentSlot.FEET, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_HELMET = ITEMS.register("dark_steel_armor_head", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.HEAD, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_CHESTPLATE = ITEMS.register("dark_steel_armor_chestplate", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.CHEST, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_LEGGINGS = ITEMS.register("dark_steel_armor_leg", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.LEGS, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_BOOTS = ITEMS.register("dark_steel_armor_feet", () -> new CyberArmor(ArmorMaterialWrapper.Cyber_Ingot, "dark_steel", EquipmentSlot.FEET, new Item.Properties().tab(MAIN_TAB)));
        CYBER_AXE = ITEMS.register("cyber_axe", () -> new CyberAxe(ToolMaterialWrapper.cyber_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        CYBER_PICKAXE = ITEMS.register("cyber_pickaxe", () -> new CyberPickaxe(ToolMaterialWrapper.cyber_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        CYBER_SHOVEL = ITEMS.register("cyber_shovel", () -> new CyberShovel(ToolMaterialWrapper.cyber_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        CYBER_SWORD = ITEMS.register("cyber_sword", () -> new CyberSword(ToolMaterialWrapper.cyber_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        CYBER_HOE = ITEMS.register("cyber_hoe", () -> new CyberHoe(ToolMaterialWrapper.cyber_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        RUBY_AXE = ITEMS.register("ruby_axe", () -> new RubyAxe(ToolMaterialWrapper.ruby_gem, 3,
                new Item.Properties().tab(MAIN_TAB)));
        RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new RubyPickaxe(ToolMaterialWrapper.ruby_gem, 3,
                new Item.Properties().tab(MAIN_TAB)));
        RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new RubyShovel(ToolMaterialWrapper.ruby_gem, 3,
                new Item.Properties().tab(MAIN_TAB)));
        RUBY_SWORD = ITEMS.register("ruby_sword", () -> new RubySword(ToolMaterialWrapper.ruby_gem, 3,
                new Item.Properties().tab(MAIN_TAB)));
        RUBY_HOE = ITEMS.register("ruby_hoe", () -> new RubyHoe(ToolMaterialWrapper.ruby_gem, 3,
                new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_AXE = ITEMS.register("dark_steel_axe", () -> new DarkSteelAxe(ToolMaterialWrapper.dark_steel_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_PICKAXE = ITEMS.register("dark_steel_pickaxe", () -> new DarkSteelPickaxe(ToolMaterialWrapper.dark_steel_ingot, 3, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_SHOVEL = ITEMS.register("dark_steel_shovel", () -> new DarkSteelShovel(ToolMaterialWrapper.dark_steel_ingot, 3, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_SWORD = ITEMS.register("dark_steel_sword", () -> new DarkSteelSword(ToolMaterialWrapper.dark_steel_ingot, 3, new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_HOE = ITEMS.register("dark_steel_hoe", () -> new DarkSteelHoe(ToolMaterialWrapper.dark_steel_ingot, 3,
                new Item.Properties().tab(MAIN_TAB)));
        TACO = ITEMS.register("taco", () -> new Item(new Item.Properties().tab(MAIN_TAB).food(FoodInit.TACO)));
        CHEESE = ITEMS.register("cheese", () -> new Item(new Item.Properties().tab(MAIN_TAB).food(FoodInit.CHEESE)));
        TOMATO = ITEMS.register("tomato", () -> new Item(new Item.Properties().tab(MAIN_TAB).food(FoodInit.TOMATO)));
        LETTUCE = ITEMS.register("lettuce", () -> new Item(new Item.Properties().tab(MAIN_TAB).food(FoodInit.LETTUCE)));
        CYBER_INGOT = ITEMS.register("cyber_ingot", () -> new Item(new Item.Properties().tab(MAIN_TAB)));
        DARK_STEEL_INGOT = ITEMS.register("dark_steel_ingot", () -> new Item(new Item.Properties().tab(MAIN_TAB)));
        RUBY_GEM = ITEMS.register("ruby_gem", () -> new Item(new Item.Properties().tab(MAIN_TAB)));
        shock_flower = ITEMS.register("shock_flower", () -> new BlockItem(MainInit.SHOCKFLOWER.get(), new Item.Properties().tab(MAIN_TAB)));
        BLACK_Electric_FenceGate_item = ITEMS.register("black_electric_fence_gate", () -> new BlockItem(MainInit.BLACK_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        RED_Electric_FenceGate_item = ITEMS.register("red_electric_fence_gate", () -> new BlockItem(MainInit.RED_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        GREEN_Electric_FenceGate_item = ITEMS.register("green_electric_fence_gate", () -> new BlockItem(MainInit.GREEN_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        wrench_tool = ITEMS.register("wrench_tool", () -> new WrenchItem());
        BROWN_Electric_FenceGate_item = ITEMS.register("brown_electric_fence_gate", () -> new BlockItem(MainInit.BROWN_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BLUE_Electric_FenceGate_item = ITEMS.register("blue_electric_fence_gate", () -> new BlockItem(MainInit.BLUE_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        PURPLE_Electric_FenceGate_item = ITEMS.register("purple_electric_fence_gate", () -> new BlockItem(MainInit.PURPLE_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        CYAN_Electric_FenceGate_item = ITEMS.register("cyan_electric_fence_gate", () -> new BlockItem(MainInit.CYAN_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIGHT_GRAY_Electric_FenceGate_item = ITEMS.register("light_gray_electric_fence_gate", () -> new BlockItem(MainInit.LIGHT_GRAY_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        GRAY_Electric_FenceGate_item = ITEMS.register("gray_electric_fence_gate", () -> new BlockItem(MainInit.GRAY_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        PINK_Electric_FenceGate_item = ITEMS.register("pink_electric_fence_gate", () -> new BlockItem(MainInit.PINK_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIME_Electric_FenceGate_item = ITEMS.register("lime_electric_fence_gate", () -> new BlockItem(MainInit.LIME_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        YELLOW_Electric_FenceGate_item = ITEMS.register("yellow_electric_fence_gate", () -> new BlockItem(MainInit.YELLOW_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIGHT_BLUE_Electric_FenceGate_item = ITEMS.register("light_blue_electric_fence_gate", () -> new BlockItem(MainInit.LIGHT_BLUE_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        MAGENTA_Electric_FenceGate_item = ITEMS.register("magenta_electric_fence_gate", () -> new BlockItem(MainInit.MAGENTA_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ORANGE_Electric_FenceGate_item = ITEMS.register("orange_electric_fence_gate", () -> new BlockItem(MainInit.ORANGE_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        WHITE_Electric_FenceGate_item = ITEMS.register("white_electric_fence_gate", () -> new BlockItem(MainInit.WHITE_Electric_FenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BLACK_Electric_FenceTop_item = ITEMS.register("black_electric_fence_top", () -> new BlockItem(MainInit.BLACK_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        RED_Electric_FenceTop_item = ITEMS.register("red_electric_fence_top", () -> new BlockItem(MainInit.RED_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        GREEN_Electric_FenceTop_item = ITEMS.register("green_electric_fence_top", () -> new BlockItem(MainInit.GREEN_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BROWN_Electric_FenceTop_item = ITEMS.register("brown_electric_fence_top", () -> new BlockItem(MainInit.BROWN_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BLUE_Electric_FenceTop_item = ITEMS.register("blue_electric_fence_top", () -> new BlockItem(MainInit.BLUE_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        PURPLE_Electric_FenceTop_item = ITEMS.register("purple_electric_fence_top", () -> new BlockItem(MainInit.PURPLE_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        CYAN_Electric_FenceTop_item = ITEMS.register("cyan_electric_fence_top", () -> new BlockItem(MainInit.CYAN_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIGHT_GRAY_Electric_FenceTop_item = ITEMS.register("light_electric_fence_top", () -> new BlockItem(MainInit.LIGHT_GRAY_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        GRAY_Electric_FenceTop_item = ITEMS.register("gray_electric_fence_top", () -> new BlockItem(MainInit.GRAY_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        PINK_Electric_FenceTop_item = ITEMS.register("pink_electric_fence_top", () -> new BlockItem(MainInit.PINK_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIME_Electric_FenceTop_item = ITEMS.register("lime_electric_fence_top", () -> new BlockItem(MainInit.LIME_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        YELLOW_Electric_FenceTop_item = ITEMS.register("yellow_electric_fence_top", () -> new BlockItem(MainInit.YELLOW_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIGHT_BLUE_Electric_FenceTop_item = ITEMS.register("light_blue_electric_fence_top", () -> new BlockItem(MainInit.LIGHT_BLUE_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        MAGENTA_Electric_FenceTop_item = ITEMS.register("magenta_electric_fence_top", () -> new BlockItem(MainInit.MAGENTA_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ORANGE_Electric_FenceTop_item = ITEMS.register("orange_electric_fence_top", () -> new BlockItem(MainInit.ORANGE_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        WHITE_Electric_FenceTop_item = ITEMS.register("white_electric_fence_top", () -> new BlockItem(MainInit.WHITE_Electric_FenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ElectricCabinet_item = ITEMS.register("electrical_cabinet", () -> new BlockItem(MainInit.electrical_cabinet_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ElectricFenceGate_item = ITEMS.register("electric_fence_gate", () -> new BlockItem(MainInit.ElectricFenceGate_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ElectricFenceTop_item = ITEMS.register("electric_fence_top", () -> new BlockItem(MainInit.ElectricFenceTop_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ElectricFence_item = ITEMS.register("electric_fence", () -> new BlockItem(MainInit.ElectricFence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BLACK_Electric_Fence_item = ITEMS.register("black_electric_fence", () -> new BlockItem(MainInit.BLACK_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        RED_Electric_Fence_item = ITEMS.register("red_electric_fence", () -> new BlockItem(MainInit.RED_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        GREEN_Electric_Fence_item = ITEMS.register("green_electric_fence", () -> new BlockItem(MainInit.GREEN_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BROWN_Electric_Fence_item = ITEMS.register("brown_electric_fence", () -> new BlockItem(MainInit.BROWN_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        BLUE_Electric_Fence_item = ITEMS.register("blue_electric_fence", () -> new BlockItem(MainInit.BLUE_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        PURPLE_Electric_Fence_item = ITEMS.register("purple_electric_fence", () -> new BlockItem(MainInit.PURPLE_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        CYAN_Electric_Fence_item = ITEMS.register("cyan_electric_fence", () -> new BlockItem(MainInit.CYAN_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIGHT_GRAY_Electric_Fence_item = ITEMS.register("light_gray_electric_fence", () -> new BlockItem(MainInit.LIGHT_GRAY_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        GRAY_Electric_Fence_item = ITEMS.register("gray_electric_fence", () -> new BlockItem(MainInit.GRAY_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        PINK_Electric_Fence_item = ITEMS.register("pink_electric_fence", () -> new BlockItem(MainInit.PINK_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        WHITE_Electric_Fence_item = ITEMS.register("white_electric_fence", () -> new BlockItem(MainInit.WHITE_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        ORANGE_Electric_Fence_item = ITEMS.register("orange_electric_fence", () -> new BlockItem(MainInit.ORANGE_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        MAGENTA_Electric_Fence_item = ITEMS.register("magenta_electric_fence", () -> new BlockItem(MainInit.MAGENTA_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIGHT_BLUE_Electric_Fence_item = ITEMS.register("light_blue_electric_fence", () -> new BlockItem(MainInit.LIGHT_BLUE_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        YELLOW_Electric_Fence_item = ITEMS.register("yellow_electric_fence", () -> new BlockItem(MainInit.YELLOW_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        LIME_Electric_Fence_item = ITEMS.register("lime_electric_fence", () -> new BlockItem(MainInit.LIME_Electric_Fence_block.get(), new Item.Properties().tab(MAIN_TAB)));
        //endregion

        //region BlockEntity
        POWER_CABLE_BE = BLOCK_ENTITIES.register("power_cable_be", () -> BlockEntityType.Builder.of(PowerCableBlockEntity::new, MainInit.POWER_CABLE.get()).build(null));
        ITEM_CABLE_BE = BLOCK_ENTITIES.register("item_cable_be", () -> BlockEntityType.Builder.of(ItemCableBlockEntity::new, MainInit.POWER_CABLE.get()).build(null));
        POWER_CUBE_BE = BLOCK_ENTITIES.register("power_cube_be", () -> BlockEntityType.Builder.of(PowerCubeBlockEntity::new, MainInit.POWER_CUBE.get()).build(null));
        POWERED_FURNACE_BE = BLOCK_ENTITIES.register("powered_furnace_be", () -> BlockEntityType.Builder.of(PoweredFurnaceBlockEntity::new, MainInit.POWERED_FURNACE.get()).build(null));
        //endregion

        //#region Tags
        Color_Fence_Top = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, "colored_fences_top"));
        Color_Fence = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, "colored_fences"));
        Color_Fence_Gate = TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation(MODID, "colored_fences_gate"));
        //#endregion
    }

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }

}
