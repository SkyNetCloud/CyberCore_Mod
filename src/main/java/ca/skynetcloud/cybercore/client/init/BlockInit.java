package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.common.blocks.NewOreBlock;
import ca.skynetcloud.cybercore.common.blocks.crops.LettuceCrop;
import ca.skynetcloud.cybercore.common.blocks.crops.TomatoCrop;
import ca.skynetcloud.cybercore.common.blocks.tech.PowerCube;
import ca.skynetcloud.cybercore.common.blocks.tech.TechBaseBlockFacing;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.ItemCable;
import ca.skynetcloud.cybercore.common.blocks.tech.cable.PowerCable;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PoweredFurnaceBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.SolarGeneratorBlockEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class BlockInit {
    public static final BlockBehaviour.Properties RAW_ORE_BLOCKS = BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.STONE);


    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CyberCore.MODID);

    public static RegistryObject<Block> SOLARGEN = BLOCKS.register("solargen", () -> new TechBaseBlockFacing(SolarGeneratorBlockEntityBlock::new));

    public static RegistryObject<Block> POWERED_FURNACE = BLOCKS.register("powered_furnace", () -> new TechBaseBlockFacing(PoweredFurnaceBlockEntity::new));

    public static RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop", () -> new TomatoCrop());
    public static RegistryObject<Block> LETTUCE_CROP = BLOCKS.register("lettuce_crop", () -> new LettuceCrop());

    public static RegistryObject<Block> POWER_CUBE = BLOCKS.register("power_cube", () -> new PowerCube(PowerCubeBlockEntity::new));

    public static RegistryObject<NewOreBlock> CYBER_ORE_BLOCK = BLOCKS.register("cyber_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));
    public static RegistryObject<NewOreBlock> DARK_STEEL_ORE_BLOCK = BLOCKS.register("dark_steel_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));
    public static RegistryObject<NewOreBlock> RUBY_ORE_BLOCK = BLOCKS.register("ruby_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));

    public static RegistryObject<NewOreBlock> DEEPSLATE_CYBER_ORE_BLOCK = BLOCKS.register("deepslate_cyber_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));
    public static RegistryObject<NewOreBlock> DEEPSLATE_DARK_STEEL_ORE_BLOCK = BLOCKS.register("deepslate_dark_steel_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));
    public static RegistryObject<NewOreBlock> DEEPSLATE_RUBY_ORE_BLOCK = BLOCKS.register("deepslate_ruby_ore", () -> new NewOreBlock(RAW_ORE_BLOCKS));;

    public static RegistryObject<Block> POWER_CABLE = BLOCKS.register("power_cable_block", () -> new PowerCable());
    public static RegistryObject<Block> ITEM_CABLE = BLOCKS.register("item_cable_block", () -> new ItemCable());


}
