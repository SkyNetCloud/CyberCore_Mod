package ca.skynetcloud.cybercore.common.blocks.techentity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;

public abstract class CyberBlockEntityBlock extends Block implements EntityBlock
{
    protected CyberBlockEntityBlock(Properties property)
    {
        super(property);
    }

    @Override
    public RenderShape getRenderShape(BlockState state)
    {
        return RenderShape.MODEL;
    }


}
