package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.container.PoweredFurnaceMenu;
import ca.skynetcloud.cybercore.client.utilities.blocks.HasItem;
import ca.skynetcloud.cybercore.common.blocks.crops.LettuceCrop;
import ca.skynetcloud.cybercore.common.blocks.crops.TomatoCrop;
import ca.skynetcloud.cybercore.common.blocks.tech.PowerCube;
import ca.skynetcloud.cybercore.common.blocks.tech.TechBaseBlock;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.ItemCable;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.PowerCable;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PoweredFurnaceBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;

import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CyberCore.MODID);

    @HasItem
    public static RegistryObject<Block> POWERED_FURNACE = BLOCKS.register("powered_furnace", () -> new TechBaseBlock(PoweredFurnaceBlockEntity::new));

    @HasItem
    public static RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new TomatoCrop());

    @HasItem
    public static RegistryObject<Block> LETTUCE_CROP = BLOCKS.register("lettuce_crop", () -> new LettuceCrop());

    @HasItem
    public static RegistryObject<Block> POWER_CUBE = BLOCKS.register("power_cube", () -> new PowerCube(PowerCubeBlockEntity::new));

    @HasItem
    public static RegistryObject<Block> CYBER_ORE_BLOCK = BLOCKS.register("cyber_ore", () -> new DropExperienceBlock(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f)));

    @HasItem
    public static RegistryObject<Block> DARK_STEEL_ORE_BLOCK = BLOCKS.register("dark_steel_ore", () -> new DropExperienceBlock(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f)));

    @HasItem
    public static RegistryObject<Block> RUBY_ORE_BLOCK = BLOCKS.register("ruby_ore", () -> new DropExperienceBlock(Block.Properties.of(Material.STONE).strength(5.0f, 10.0f)));

    @HasItem(isWIP = true)
    public static RegistryObject<Block> POWER_CABLE = BLOCKS.register("power_cable_block", () -> new PowerCable());

    @HasItem(isWIP = true)
    public static RegistryObject<Block> ITEM_CABLE = BLOCKS.register("item_cable_block", () -> new ItemCable());


    public static void registerItemBlocks() {
        try {
            List<Field> list = Arrays.stream(BlockInit.class.getDeclaredFields()).filter(f -> f.isAnnotationPresent(HasItem.class)).collect(Collectors.toList());
            for (Field field : list) {
                HasItem annotation = field.getAnnotation(HasItem.class);
                RegistryObject<Block> registryObject = (RegistryObject<Block>) field.get(RegistryObject.class);
               ItemInit.ITEMS.register(registryObject.getId().getPath(), () -> new BlockItem(registryObject.get(), new Item.Properties().tab(annotation.tab().getTab())) {
                    @Override
                    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tips, TooltipFlag advanced) {
                        //if (annotation.isWIP())
                           //tips.add(new Component("WIP object, may not be obtainable through normal ways.").withStyle(ChatFormatting.RED));
                    }
                });
            }
        } catch (IllegalAccessException e){
            System.err.println(e.getMessage());
        }
    }

}
