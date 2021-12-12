package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.common.blocks.techentity.ItemCableBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCableBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCubeBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PoweredFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CyberCore.MODID);

    public static RegistryObject<BlockEntityType<PowerCableBlockEntity>> POWER_CABLE_BE  = BLOCK_ENTITIES.register("power_cable_be", () -> BlockEntityType.Builder.of(PowerCableBlockEntity::new, BlockInit.POWER_CABLE.get()).build(null));;

    public static RegistryObject<BlockEntityType<ItemCableBlockEntity>> ITEM_CABLE_BE  = BLOCK_ENTITIES.register("item_cable_be", () -> BlockEntityType.Builder.of(ItemCableBlockEntity::new, BlockInit.POWER_CABLE.get()).build(null));

    public static RegistryObject<BlockEntityType<PowerCubeBlockEntity>> POWER_CUBE_BE = BLOCK_ENTITIES.register("power_cube_be", () -> BlockEntityType.Builder.of(PowerCubeBlockEntity::new, BlockInit.POWER_CUBE.get()).build(null));

    public static RegistryObject<BlockEntityType<PoweredFurnaceBlockEntity>> POWERED_FURNACE_BE = BLOCK_ENTITIES.register("powered_furnace_be", () -> BlockEntityType.Builder.of(PoweredFurnaceBlockEntity::new, BlockInit.POWERED_FURNACE.get()).build(null));


}
