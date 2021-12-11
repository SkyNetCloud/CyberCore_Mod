package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.CyberCoreTab;
import ca.skynetcloud.cybercore.client.container.ColorChangeContainer;
import ca.skynetcloud.cybercore.client.container.PowerCubeCon;
import ca.skynetcloud.cybercore.client.container.PoweredFurnaceMenu;
import ca.skynetcloud.cybercore.client.entites.hostile.RobotEnemy;
import ca.skynetcloud.cybercore.client.init.material.BasisToolMaterial;
import ca.skynetcloud.cybercore.client.init.material.CustomArmorMaterial;
import ca.skynetcloud.cybercore.client.networking.helper.NameHelper;
import ca.skynetcloud.cybercore.client.screen.ColorChangeScreen;
import ca.skynetcloud.cybercore.client.screen.PowerCubeScreen;
import ca.skynetcloud.cybercore.client.screen.PoweredFunranceMenuScreen;
import ca.skynetcloud.cybercore.client.techblock.*;
import ca.skynetcloud.cybercore.common.block.BlockBaseCore;
import ca.skynetcloud.cybercore.common.block.BlockItemCore;
import ca.skynetcloud.cybercore.common.block.blocks.PowerCube;
import ca.skynetcloud.cybercore.common.block.blocks.cables.CableBlock;
import ca.skynetcloud.cybercore.common.block.blocks.cables.ItemCable;
import ca.skynetcloud.cybercore.common.block.blocks.cables.color.ColorCable;
import ca.skynetcloud.cybercore.common.block.blocks.cables.color.ColorItemCable;
import ca.skynetcloud.cybercore.common.block.blocks.fences.ElecticFencesBlock;
import ca.skynetcloud.cybercore.common.block.blocks.fences.color.FencesColor;
import ca.skynetcloud.cybercore.common.block.blocks.fences.gate.ElecticFenceGate;
import ca.skynetcloud.cybercore.common.block.blocks.fences.gate.color.ColorFenceGate;
import ca.skynetcloud.cybercore.common.block.blocks.fences.top.ElectricFenceTop;
import ca.skynetcloud.cybercore.common.block.blocks.fences.top.color.ElectricFenceTopColor;
import ca.skynetcloud.cybercore.common.block.blocks.slab.Slab_Block;
import ca.skynetcloud.cybercore.common.block.blocks.slab.color.SlabColor;
import ca.skynetcloud.cybercore.common.block.crop.LettuceCrop;
import ca.skynetcloud.cybercore.common.block.crop.TomatoCrop;
import ca.skynetcloud.cybercore.common.block.tech.TechBlockFacing;
import ca.skynetcloud.cybercore.common.item.UpgradeLvl;
import ca.skynetcloud.cybercore.common.item.WrenchItem;
import ca.skynetcloud.cybercore.common.item.armor.CyberArmor;
import ca.skynetcloud.cybercore.common.item.armor.DarkSteelArmor;
import ca.skynetcloud.cybercore.common.item.armor.RubyArmor;
import ca.skynetcloud.cybercore.common.item.enchantment.EnchantmentSoulbound;
import ca.skynetcloud.cybercore.common.item.tools.*;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

import static ca.skynetcloud.cybercore.CyberCoreMain.MODID;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.BLOCK_PIPE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CABLE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CHEESE_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORCHANGERCONTAINER;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORED_Fence_Block_Names;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORED_Fence_Gate_Block_Names;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORED_Fence_Top_Block_Names;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORED_Item_TUBE_NAMES;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORED_Power_Cable_Names;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.COLORED_Slab_Block_Names;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_AXE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_BOOTS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_HELMET_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_HOE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_INGOT;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_PICKAXE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_SHOVEL;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.CYBER_SWORD;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_AXE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_BOOTS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_HELMET_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_HOE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_INGOT;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_PICKAXE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_SHOVEL;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.DARK_STEEL_SWORD;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.Fence_Block;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.Fence_Block_Top;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.Fence_Gate_Block;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.LETTUCE_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.LETTUCE_SEEDS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.MODID;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.POWEREDFURNACECONTAINER;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.POWER_BOX;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.POWER_CARD_1;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.POWER_CARD_2;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.POWER_CARD_3;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_AXE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_BOOTS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_CHESTPLATE_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_HELMET_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_HOE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_INGOT;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_LEGGINGS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_PICKAXE;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_SHOVEL;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.RUBY_SWORD;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.SPEED_CARD_1;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.SPEED_CARD_2;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.SPEED_CARD_3;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.SPEED_CARD_4;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.Slab_Block;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.TACO_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.TOMAO_SEEDS_NAME;
import static ca.skynetcloud.cybercore.client.networking.helper.NameHelper.TOMATO_NAME;

public class CoreInit {

    public static class ContainersInit{
        public static MenuType<PowerCubeCon> POWER_CUBE_CON;
        public static MenuType<ColorChangeContainer> COLOR_CHANGER_CON;
        public static MenuType<PoweredFurnaceMenu> POWERED_FURNACE_CON;

        public static void registerAll(RegistryEvent.Register<MenuType<?>> event)
        {
            IForgeRegistry<MenuType<?>> registry = event.getRegistry();
            registry.register(make(POWER_BOX, new MenuType<>(PowerCubeCon::new)));
            registry.register(make(COLORCHANGERCONTAINER, new MenuType<>(ColorChangeContainer::new)));
            registry.register(make(POWEREDFURNACECONTAINER, new MenuType<>(PoweredFurnaceMenu::new)));

        }

        static <M extends AbstractContainerMenu> MenuType<M> make(String registryName, MenuType<M> MenuType)
        {
            MenuType.setRegistryName(registryName);
            return MenuType;
        }
    }

    public static class ScreensInit {
        public static void registerGUI()
        {
            MenuScreens.register(ContainersInit.POWER_CUBE_CON, PowerCubeScreen::new);
            MenuScreens.register(ContainersInit.POWERED_FURNACE_CON, PoweredFunranceMenuScreen::new);
            MenuScreens.register(ContainersInit.COLOR_CHANGER_CON, ColorChangeScreen::new);

        }
    }

    public static class EntityInit {

        public static Item entity_egg_item;

        public static List<EntityType<?>> ENTITIES = Lists.newArrayList();

        public static final EntityType<RobotEnemy> RoBot = createEntity(RobotEnemy.class, RobotEnemy::new,
                MobCategory.MONSTER, "robot_golem", 1.75F, 4.25F, 0, 0);

        private static <T extends Entity> EntityType<T> createEntity(Class<T> entityClass, EntityType.EntityFactory<T> factory,
                                                                     MobCategory entityClassification, String name, float width, float height, int eggPrimary,
                                                                     int eggSecondary) {
            ResourceLocation location = new ResourceLocation(CyberCoreMain.MODID, name);

            EntityType<T> entity = EntityType.Builder.of(factory, entityClassification).sized(width, height)
                    .setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)
                    .build(location.toString());
            entity.setRegistryName(location);
            ENTITIES.add(entity);
            return entity;
        }

    }

    @ObjectHolder(CyberCoreMain.MODID)
    public class BlockEntityInit {

        public static final BlockEntityType<PoweredFurnaceBlockEntity> ee = BlockEntityType.Builder
                .of(PoweredFurnaceBlockEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);

        @ObjectHolder(NameHelper.CABLE)
        public static final BlockEntityType<PowerCablesBlockEntity> Cable = null;

        @ObjectHolder(NameHelper.BLOCK_PIPE)
        public static final BlockEntityType<ItemPipeTileEntity> BLOCK_PIPE = null;

        public static final BlockEntityType<PowerCubeBlockEntity> POWER_CUBE_TE = BlockEntityType.Builder
                .of(PowerCubeBlockEntity::new, BlockInit.Battery).build(null);

        public static final BlockEntityType<ColorChangeBlockEntity> COLOR_Changer_TE = BlockEntityType.Builder
                .of(ColorChangeBlockEntity::new, BlockInit.C_Changer_Block).build(null);

        public static void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> event) {

            Block[] item_cables = new Block[17];
            IntStream.range(0, 16).forEach(i -> item_cables[i] = ForgeRegistries.BLOCKS
                    .getValue(new ResourceLocation(CyberCoreMain.MODID, NameHelper.COLORED_Item_TUBE_NAMES[i])));
            item_cables[16] = BlockInit.BLOCK_PIPE;

            event.getRegistry().register(BlockEntityType.Builder.of(ItemPipeTileEntity::new, item_cables).build(null)
                    .setRegistryName(NameHelper.BLOCK_PIPE));

            Block[] tubes = new Block[17];
            IntStream.range(0, 16).forEach(i -> tubes[i] = ForgeRegistries.BLOCKS
                    .getValue(new ResourceLocation(CyberCoreMain.MODID, NameHelper.COLORED_Power_Cable_Names[i])));
            tubes[16] = BlockInit.CABLE;

            event.getRegistry().register(
                    BlockEntityType.Builder.of(PowerCablesBlockEntity::new, tubes).build(null).setRegistryName(NameHelper.CABLE));
        }
    }

    public static class DamageInit {

        public static final DamageSource ELECTRIC_FENCE = new DamageSource("electricFence");

    }

    public static class EnchantmentInit {
        public static void EnchantmentLoad(RegistryEvent.Register<Enchantment> event){
            event.getRegistry().register(ItemInit.Soul_Bound);
        }
    }

    public static class FoodInit {
        public static FoodProperties taco = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).meat()
                .build();
        public static FoodProperties cheese = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
        public static FoodProperties tomato = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
        public static FoodProperties lettuce = (new FoodProperties.Builder()).nutrition(12).saturationMod(0.5F).build();
    }

    public static class ItemInit {


        @SuppressWarnings("unused")
        private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST,
                EquipmentSlot.LEGS, EquipmentSlot.FEET };

        public static BlockItem CABLE_PAINTER;

        public static Enchantment Soul_Bound = new EnchantmentSoulbound();


        public static Item planter = new ItemPlanter(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(MODID + ":planter");

        public static Item whrechItem = new WrenchItem();

        // public static Item cable = new BlockItemCore(BlockInit.CABLE);

        public static Item taco_shell = new Item(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName("taco_shell");

        public static Item cyber_bits = new Item(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName("cyber_bits");

        public static Item cyber_blend = new Item(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName("cyber_blend");

        public static BlockItem ruby_block = new BlockItemCore(BlockInit.RUBY_BLOCK);

        public static BlockItem color_changer = new BlockItemCore(BlockInit.C_Changer_Block);

        public static BlockItem power_cube = new BlockItemCore(BlockInit.Battery);

        public static BlockItem dark_steel_block = new BlockItemCore(BlockInit.DARK_STEEL_BLOCK);

        public static BlockItem lettuce_crop = new BlockItemCore(BlockInit.LETTUCE_CROP);

        public static BlockItem power_furnace_block = new BlockItemCore(BlockInit.POWER_FURNACE_BLOCK);

        public static BlockItem cyber_ore = new BlockItemCore(BlockInit.CYBER_ORE);

        public static BlockItem dark_steel_ore = new BlockItemCore(BlockInit.DARK_STEEL_ORE);

        public static BlockItem ruby_ore = new BlockItemCore(BlockInit.RUBY_ORE);

        public static BlockItem tomato_crop = new BlockItemCore(BlockInit.TOMATO_CROP);

        public static Item lettuce_seed = new SeedsInit(BlockInit.LETTUCE_CROP).setRegistryName(LETTUCE_SEEDS_NAME);

        public static Item tomato_seed = new SeedsInit(BlockInit.TOMATO_CROP).setRegistryName(TOMAO_SEEDS_NAME);

        // --------------- Foood ----------- \\

        public static Item tomato = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.lettuce))
                .setRegistryName(TOMATO_NAME);

        public static Item lettuce = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.lettuce))
                .setRegistryName(LETTUCE_NAME);

        public static Item taco = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.taco))
                .setRegistryName(TACO_NAME);

        public static Item cheese = new Item(new Item.Properties().tab(CyberCoreTab.TAB_FOOD).food(FoodInit.cheese))
                .setRegistryName(CHEESE_NAME);

        // ------------------ Cyber Tools -------------- \\

        public static Item cyber_pickaxe = new CyberPickaxe(BasisToolMaterial.cyber_ingot, 3,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_PICKAXE);

        public static Item cyber_axe = new CyberAxe(BasisToolMaterial.cyber_ingot, 5F,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_AXE);

        public static Item cyber_hoe = new CyberHoe(BasisToolMaterial.cyber_ingot, 0,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_HOE);

        public static Item cyber_shovel = new CyberShovel(BasisToolMaterial.cyber_ingot, 0.5F,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_SHOVEL);

        public static Item cyber_sword = new CyberSword(BasisToolMaterial.cyber_ingot, 5,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_SWORD);

        // ------------------ Ruby Tools -------------- \\

        public static Item ruby_pickaxe = new RubyPickaxe(BasisToolMaterial.ruby_gem, 1,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_PICKAXE);

        public static Item ruby_axe = new RubyAxe(BasisToolMaterial.ruby_gem, 3,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_AXE);

        public static Item ruby_hoe = new RubyHoe(BasisToolMaterial.ruby_gem, 0,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_HOE);

        public static Item ruby_shovel = new RubyShovel(BasisToolMaterial.ruby_gem, 0.5F,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_SHOVEL);

        public static Item ruby_sword = new RubySword(BasisToolMaterial.ruby_gem, 3,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_SWORD);

        // ------------------ Dark Steel Tools -------------- \\

        public static Item dark_steel_pickaxe = new DarkSteelPickaxe(BasisToolMaterial.dark_steel_ingot, 1,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_PICKAXE);

        public static Item dark_steel_axe = new DarkSteelAxe(BasisToolMaterial.dark_steel_ingot, 1,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_AXE);

        public static Item dark_steel_hoe = new DarkSteelHoe(BasisToolMaterial.dark_steel_ingot, 0,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_HOE);

        public static Item dark_steel_shovel = new DarkSteelShovel(BasisToolMaterial.dark_steel_ingot, 1,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_SHOVEL);

        public static Item dark_steel_sword = new DarkSteelSword(BasisToolMaterial.dark_steel_ingot, 1,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(DARK_STEEL_SWORD);

        // ------------- Other Stuff ------------------- \\

        public static Item cyber_ingot = new Item(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(CYBER_INGOT);

        public static Item ruby_ingot = new Item(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(RUBY_INGOT);

        public static Item dark_steel_ingot = new Item(new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(DARK_STEEL_INGOT);

        public static Item speed_upgrade_card_1 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 1, UpgradeLvl.ItemType.SPEED_UPGRADE)
                .setRegistryName(SPEED_CARD_1);

        public static Item speed_upgrade_card_2 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(2), 2, UpgradeLvl.ItemType.SPEED_UPGRADE)
                .setRegistryName(SPEED_CARD_2);

        public static Item speed_upgrade_card_3 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(3), 3, UpgradeLvl.ItemType.SPEED_UPGRADE)
                .setRegistryName(SPEED_CARD_3);

        public static Item speed_upgrade_card_4 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(4), 4, UpgradeLvl.ItemType.SPEED_UPGRADE)
                .setRegistryName(SPEED_CARD_4);

        public static Item power_upgrade_card_1 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 1, UpgradeLvl.ItemType.POWER_UPGRADE)
                .setRegistryName(POWER_CARD_1);

        public static Item power_upgrade_card_2 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 2, UpgradeLvl.ItemType.POWER_UPGRADE)
                .setRegistryName(POWER_CARD_2);

        public static Item power_upgrade_card_3 = new UpgradeLvl(
                new Item.Properties().tab(CyberCoreTab.instance).stacksTo(1), 3, UpgradeLvl.ItemType.POWER_UPGRADE)
                .setRegistryName(POWER_CARD_3);

        public static Item DARK_STEEL_HELMET = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
                EquipmentSlot.HEAD, new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(DARK_STEEL_HELMET_NAME);

        public static Item DARK_STEEL_CHESTPLATE = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
                EquipmentSlot.CHEST, new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(DARK_STEEL_CHESTPLATE_NAME);

        public static Item DARK_STEEL_LEGGINGS = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
                EquipmentSlot.LEGS, new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(DARK_STEEL_LEGGINGS_NAME);

        public static Item DARK_STEEL_BOOTS = new DarkSteelArmor(CustomArmorMaterial.Dark_Steel, "dark_steel",
                EquipmentSlot.FEET, new Item.Properties().tab(CyberCoreTab.instance))
                .setRegistryName(DARK_STEEL_BOOTS_NAME);

        public static Item RUBY_HELMET = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.HEAD,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_HELMET_NAME);

        public static Item RUBY_CHESTPLATE = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.CHEST,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_CHESTPLATE_NAME);

        public static Item RUBY_LEGGINGS = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.LEGS,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_LEGGINGS_NAME);

        public static Item RUBY_BOOTS = new RubyArmor(CustomArmorMaterial.Ruby, "ruby", EquipmentSlot.FEET,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(RUBY_BOOTS_NAME);

        public static Item CYBER_HELMET = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.HEAD,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_HELMET_NAME);

        public static Item CYBER_CHESTPLATE = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.CHEST,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_CHESTPLATE_NAME);

        public static Item CYBER_LEGGINGS = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.LEGS,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_LEGGINGS_NAME);

        public static Item CYBER_BOOTS = new CyberArmor(CustomArmorMaterial.Cyber_Ingot, "cyber", EquipmentSlot.FEET,
                new Item.Properties().tab(CyberCoreTab.instance)).setRegistryName(CYBER_BOOTS_NAME);

        public static void registerItems(RegistryEvent.Register<Item> event) {

            IForgeRegistry<Item> registry = event.getRegistry();

            registerItem(registry, new BlockItem(BlockInit.CABLE, new Item.Properties().tab(CyberCoreTab.power_cable)),
                    CABLE);

            registerItem(registry, new BlockItem(BlockInit.BLOCK_PIPE, new Item.Properties().tab(CyberCoreTab.item_cable)),
                    BLOCK_PIPE);

            registerItem(registry, new BlockItem(BlockInit.Fence_Block, new Item.Properties().tab(CyberCoreTab.other)),
                    Fence_Block);

            registerItem(registry, new BlockItem(BlockInit.Fence_Block_Top, new Item.Properties().tab(CyberCoreTab.other)),
                    Fence_Block_Top);

            registerItem(registry, new BlockItem(BlockInit.Fence_Gate_Block, new Item.Properties().tab(CyberCoreTab.other)),
                    Fence_Gate_Block);

            registerItem(registry, new BlockItem(BlockInit.Slab_Block, new Item.Properties().tab(CyberCoreTab.instance)),
                    Slab_Block);

            IntStream.range(0, 16).forEach(i -> registerItem(registry,
                    new BlockItem(
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
                                    new ResourceLocation(MODID, COLORED_Power_Cable_Names[i]))),
                            new Item.Properties().tab(CyberCoreTab.power_cable)),
                    COLORED_Power_Cable_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerItem(registry,
                            new BlockItem(
                                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
                                            new ResourceLocation(MODID, COLORED_Item_TUBE_NAMES[i]))),
                                    new Item.Properties().tab(CyberCoreTab.item_cable)),
                            COLORED_Item_TUBE_NAMES[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerItem(registry,
                            new BlockItem(
                                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
                                            new ResourceLocation(MODID, COLORED_Fence_Block_Names[i]))),
                                    new Item.Properties().tab(CyberCoreTab.other)),
                            COLORED_Fence_Block_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerItem(registry, new BlockItem(
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
                                    new ResourceLocation(MODID, COLORED_Fence_Top_Block_Names[i]))),
                            new Item.Properties().tab(CyberCoreTab.other)), COLORED_Fence_Top_Block_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerItem(registry,
                            new BlockItem(
                                    Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
                                            new ResourceLocation(MODID, COLORED_Slab_Block_Names[i]))),
                                    new Item.Properties().tab(CyberCoreTab.instance)),
                            COLORED_Slab_Block_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerItem(registry, new BlockItem(
                            Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
                                    new ResourceLocation(MODID, COLORED_Fence_Gate_Block_Names[i]))),
                            new Item.Properties().tab(CyberCoreTab.other)), COLORED_Fence_Gate_Block_Names[i]));


            event.getRegistry().register(power_cube);
            event.getRegistry().register(color_changer);
            event.getRegistry().register(whrechItem);
            event.getRegistry().register(ruby_block);
            event.getRegistry().register(dark_steel_block);
            event.getRegistry().register(power_furnace_block);
            event.getRegistry().register(taco_shell);
            event.getRegistry().register(cyber_ore);
            event.getRegistry().register(dark_steel_ore);
            event.getRegistry().register(ruby_ore);
            event.getRegistry().register(cyber_ingot);
            event.getRegistry().register(cyber_blend);
            event.getRegistry().register(cyber_bits);
            event.getRegistry().register(dark_steel_ingot);
            event.getRegistry().register(ruby_ingot);
            event.getRegistry().register(planter);
            event.getRegistry().register(cheese);
            event.getRegistry().register(tomato);
            event.getRegistry().register(taco);
            event.getRegistry().register(tomato_seed);
            event.getRegistry().register(lettuce);
            event.getRegistry().register(lettuce_seed);
            event.getRegistry().register(cyber_axe);
            event.getRegistry().register(cyber_hoe);
            event.getRegistry().register(cyber_pickaxe);
            event.getRegistry().register(cyber_shovel);
            event.getRegistry().register(cyber_sword);
            event.getRegistry().register(ruby_axe);
            event.getRegistry().register(ruby_hoe);
            event.getRegistry().register(ruby_pickaxe);
            event.getRegistry().register(ruby_shovel);
            event.getRegistry().register(ruby_sword);
            event.getRegistry().register(dark_steel_axe);
            event.getRegistry().register(dark_steel_hoe);
            event.getRegistry().register(dark_steel_pickaxe);
            event.getRegistry().register(dark_steel_shovel);
            event.getRegistry().register(dark_steel_sword);
            event.getRegistry().register(speed_upgrade_card_1);
            event.getRegistry().register(speed_upgrade_card_2);
            event.getRegistry().register(speed_upgrade_card_3);
            event.getRegistry().register(speed_upgrade_card_4);
            event.getRegistry().register(power_upgrade_card_1);
            event.getRegistry().register(power_upgrade_card_2);
            event.getRegistry().register(power_upgrade_card_3);

            event.getRegistry().register(DARK_STEEL_HELMET);
            event.getRegistry().register(DARK_STEEL_CHESTPLATE);
            event.getRegistry().register(DARK_STEEL_LEGGINGS);
            event.getRegistry().register(DARK_STEEL_BOOTS);

            event.getRegistry().register(RUBY_HELMET);
            event.getRegistry().register(RUBY_CHESTPLATE);
            event.getRegistry().register(RUBY_LEGGINGS);
            event.getRegistry().register(RUBY_BOOTS);

            event.getRegistry().register(CYBER_HELMET);
            event.getRegistry().register(CYBER_CHESTPLATE);
            event.getRegistry().register(CYBER_LEGGINGS);
            event.getRegistry().register(CYBER_BOOTS);
        }

        private static <T extends Item> T registerItem(IForgeRegistry<Item> registry, T newItem, String name) {
            String prefixedName = CyberCoreMain.MODID + ":" + name;
            newItem.setRegistryName(prefixedName);
            registry.register(newItem);
            return newItem;
        }

    }

    public static class BlockInit {

        public static CreativeModeTab group = CyberCoreTab.instance;


        public static Block CABLE = null;

        public static Block C_Changer_Block = new TechBlockFacing(null).setRegistryName("color_changer");


        public static Block BLOCK_PIPE = null;


        public static Block Fence_Block = null;


        public static Block Fence_Block_Top = null;


        public static Block Fence_Gate_Block = null;

        public static Block Slab_Block = new Slab_Block();


        public static Block Battery = new PowerCube(null).setRegistryName(NameHelper.POWER_BOX);

        public static Block TOMATO_CROP = new TomatoCrop().setRegistryName(NameHelper.TOMATO_CROP);

        public static Block LETTUCE_CROP = new LettuceCrop().setRegistryName(NameHelper.LETTUCE_CROP);

        public static Block POWER_FURNACE_BLOCK = new TechBlockFacing(null).setRegistryName(NameHelper.POWER_FURNACE_BLOCK);

        public static Block RUBY_BLOCK = new BlockBaseCore(Block.Properties.of(Material.METAL), true)
                .setRegistryName(NameHelper.RUBY_BLOCK);

        public static Block DARK_STEEL_BLOCK = new BlockBaseCore(Block.Properties.of(Material.METAL), true)
                .setRegistryName(NameHelper.DARK_STEEL_BLOCK);

        public static Block CYBER_ORE = new OreBlock(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f))
                .setRegistryName(NameHelper.CYBER_ORE);


        public static Block DARK_STEEL_ORE = new OreBlock(Block.Properties.of(Material.STONE).strength(5.0f, 15.0f))
                .setRegistryName(NameHelper.DARK_STEEL_ORE);

        public static Block RUBY_ORE = new OreBlock(Block.Properties.of(Material.STONE).strength(0.5F, 7.0F))
                .setRegistryName(NameHelper.RUBY_ORE);


        public static Block CYBER_DeepSlate_ORE = new OreBlock(Block.Properties.of(Material.STONE)
                .color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE))
                .setRegistryName(NameHelper.CYBER_DEEPSLATE_ORE);

        public static Block DARK_STEEL_DeepSlate_ORE = new OreBlock(Block.Properties.of(Material.STONE)
                .color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE))
                .setRegistryName(NameHelper.DARK_STEEL_DEEPSLATE_ORE);


        public static Block RUBY_DeepSlate_ORE = new OreBlock(Block.Properties.of(Material.STONE)
                .color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE))
                .setRegistryName(NameHelper.RUBY_DEEPSLATE_ORE);

        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            IForgeRegistry<Block> registry = event.getRegistry();

            event.getRegistry().register(LETTUCE_CROP);
            event.getRegistry().register(TOMATO_CROP);

            event.getRegistry().register(CYBER_ORE);
            event.getRegistry().register(RUBY_ORE);
            event.getRegistry().register(DARK_STEEL_ORE);

            event.getRegistry().register(CYBER_DeepSlate_ORE);
            event.getRegistry().register(RUBY_DeepSlate_ORE);
            event.getRegistry().register(DARK_STEEL_DeepSlate_ORE);

            event.getRegistry().register(RUBY_BLOCK);
            event.getRegistry().register(DARK_STEEL_BLOCK);


            registerBlock(registry, new CableBlock(), NameHelper.CABLE);
            registerBlock(registry, new ItemCable(), NameHelper.BLOCK_PIPE);
            registerBlock(registry, new Slab_Block(), NameHelper.Slab_Block);
            registerBlock(registry, new ElecticFencesBlock(), NameHelper.Fence_Block);
            registerBlock(registry, new ElecticFenceGate(), NameHelper.Fence_Gate_Block);
            registerBlock(registry, new ElectricFenceTop(), NameHelper.Fence_Block_Top);

            IntStream.range(0, 16)
                    .forEach(i -> registerBlock(registry,
                            new ColorItemCable(DyeColor.values()[i], Block.Properties.of(Material.GLASS).strength(0.4F)
                                    .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
                            NameHelper.COLORED_Item_TUBE_NAMES[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerBlock(registry,
                            new ColorCable(DyeColor.values()[i], Block.Properties.of(Material.GLASS).strength(0.4F)
                                    .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
                            NameHelper.COLORED_Power_Cable_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerBlock(registry,
                            new FencesColor(DyeColor.values()[i], Block.Properties.of(Material.METAL).strength(30.0F)
                                    .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
                            NameHelper.COLORED_Fence_Block_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerBlock(registry,
                            new ElectricFenceTopColor(DyeColor.values()[i], Block.Properties.of(Material.METAL)
                                    .strength(30.0F).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
                            NameHelper.COLORED_Fence_Top_Block_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerBlock(registry,
                            new SlabColor(DyeColor.values()[i], Block.Properties.of(Material.METAL).strength(0.4F)
                                    .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
                            NameHelper.COLORED_Slab_Block_Names[i]));

            IntStream.range(0, 16)
                    .forEach(i -> registerBlock(registry,
                            new ColorFenceGate(DyeColor.values()[i], Block.Properties.of(Material.METAL).strength(30.0F)
                                    .harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)),
                            NameHelper.COLORED_Fence_Gate_Block_Names[i]));

        }

        private static <T extends Block> T registerBlock(IForgeRegistry<Block> registry, T newBlock, String name) {
            String prefixedName = CyberCoreMain.MODID + ":" + name;
            newBlock.setRegistryName(prefixedName);
            registry.register(newBlock);
            return newBlock;
        }
    }

    public static class SoundInit {


        public static SoundEvent ELECTRIC_FENCE_IDLE;


        public static SoundEvent Robot_Hurt_Noise;


        public static SoundEvent Robot_Death_Noise;

        public static SoundEvent Robot_Walk_Noise;

        public static void registerAll(IForgeRegistry<SoundEvent> registry) {
            registry.register(make("electric_fence_idle"));
            registry.register(make("robot_hurt"));
            registry.register(make("robot_death"));
            registry.register(make("robot_walk"));
        }

        private static SoundEvent make(String soundName) {
            return new SoundEvent(new ResourceLocation(CyberCoreMain.MODID, soundName)).setRegistryName(soundName);
        }
    }

    public static class SeedsInit extends ItemNameBlockItem {

        public SeedsInit(Block crop) {
            super(crop, new Properties().tab(CyberCoreTab.instance));

        }

    }

}
