package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.client.utilities.blocks.HasItem;
import ca.skynetcloud.cybercore.common.blocks.NewOreBlock;
import ca.skynetcloud.cybercore.common.blocks.crops.LettuceCrop;
import ca.skynetcloud.cybercore.common.blocks.crops.TomatoCrop;
import ca.skynetcloud.cybercore.common.blocks.tech.PowerCube;
import ca.skynetcloud.cybercore.common.blocks.tech.TechBaseBlockFacing;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.ItemCable;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.PowerCable;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PoweredFurnaceBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class BlockInit {
    public static final BlockBehaviour.Properties RAW_ORE_BLOCKS = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.STONE);

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CyberCore.MODID);


    public static RegistryObject<Block> POWERED_FURNACE = BLOCKS.register("powered_furnace", () -> new TechBaseBlockFacing(PoweredFurnaceBlockEntity::new));

    public static RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new TomatoCrop());
    public static RegistryObject<Block> LETTUCE_CROP = BLOCKS.register("lettuce_crop", () -> new LettuceCrop());

    public static RegistryObject<Block> POWER_CUBE = BLOCKS.register("power_cube", () -> new PowerCube(PowerCubeBlockEntity::new));

    public static RegistryObject<NewOreBlock> CYBER_ORE_BLOCK = BLOCKS.register("cyber_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));
    public static RegistryObject<NewOreBlock> DARK_STEEL_ORE_BLOCK = BLOCKS.register("dark_steel_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));
    public static RegistryObject<NewOreBlock> RUBY_ORE_BLOCK = BLOCKS.register("ruby_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));;

    public static RegistryObject<Block> POWER_CABLE = BLOCKS.register("power_cable_block", () -> new PowerCable());
    public static RegistryObject<Block> ITEM_CABLE = BLOCKS.register("item_cable_block", () -> new ItemCable());


}
