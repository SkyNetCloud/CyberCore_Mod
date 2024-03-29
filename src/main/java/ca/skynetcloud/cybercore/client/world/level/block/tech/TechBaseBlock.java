package ca.skynetcloud.cybercore.client.world.level.block.tech;

import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyBlockEntity;
import ca.skynetcloud.cybercore.client.energy.baseclasses.PyroEnergyInventoryBlockEntity;
import ca.skynetcloud.cybercore.client.world.level.block.techentity.CyberBlockEntityBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.BiFunction;

public class TechBaseBlock extends CyberBlockEntityBlock
{
    private final BiFunction<BlockPos, BlockState, ? extends BlockEntity> teCreator;
    private final int tier;

    public TechBaseBlock(BiFunction<BlockPos, BlockState, ? extends BlockEntity> teCreator)
    {
        this(teCreator, 0);
    }

    public TechBaseBlock(BiFunction<BlockPos, BlockState, ? extends BlockEntity> teCreator, int tier)
    {
        super(Block.Properties.of(Material.METAL).strength(5.0f, 10.0f).noOcclusion());
        this.teCreator = teCreator;
        this.tier = tier;
    }



    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult ray)
    {
        if (!level.isClientSide && player instanceof ServerPlayer)
        {
            BlockEntity te = level.getBlockEntity(pos);
            if (te instanceof PyroEnergyInventoryBlockEntity eibe)
            {
                NetworkHooks.openGui((ServerPlayer) player, eibe, pos);
                if (eibe.requireSyncUponOpen())
                    level.sendBlockUpdated(pos, state, state, 3);
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if(!level.isClientSide) return PyroEnergyBlockEntity::tick;
        return null;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return teCreator.apply(pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            if (level.getBlockEntity(pos) instanceof PyroEnergyInventoryBlockEntity eibe)
            {
                List<ItemStack> toSpawn = eibe.getInventoryContent();
                for (ItemStack stack : toSpawn)
                    level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stack));
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, BlockGetter level, List<Component> tooltip, TooltipFlag flagIn)
    {
        tooltip.add(Component.literal(Component.translatable("info.tier").getString() + ": " + tier));

        super.appendHoverText(stack, level, tooltip, flagIn);
    }

}