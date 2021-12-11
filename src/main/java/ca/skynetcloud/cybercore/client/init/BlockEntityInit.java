package ca.skynetcloud.cybercore.client.init;

import ca.skynetcloud.cybercore.CyberCore;
import ca.skynetcloud.cybercore.common.blocks.techentity.ItemCableBlockEntity;
import ca.skynetcloud.cybercore.common.blocks.techentity.PowerCableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CyberCore.MODID);

    public static RegistryObject<BlockEntityType<PowerCableBlockEntity>> POWER_CABLE_BE  = BLOCK_ENTITIES.register("power_cable_be", () -> BlockEntityType.Builder.of(PowerCableBlockEntity::new, BlockInit.POWER_CABLE.get()).build(null));;

    public static RegistryObject<BlockEntityType<ItemCableBlockEntity>> ITEM_CABLE_BE  = BLOCK_ENTITIES.register("item_cable_be", () -> BlockEntityType.Builder.of(ItemCableBlockEntity::new, BlockInit.POWER_CABLE.get()).build(null));




}
