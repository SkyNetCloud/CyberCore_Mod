package ca.skynetcloud.cybercore.common.blocks;

import ca.skynetcloud.cybercore.client.init.BlockInit;
import ca.skynetcloud.cybercore.client.utilities.CyberConfig;
import net.minecraft.util.valueproviders.UniformInt;
import ca.skynetcloud.cybercore.client.utilities.blocks.itemcables.wrapper.OreToolTipWrapper.*;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;

public class NewOreBlock extends OreBlock {

    private final UniformInt xpRange;

    public NewOreBlock(Properties properties, UniformInt experienceDropped) {
        super(properties);
        this.xpRange = experienceDropped;
    }

    public NewOreBlock(Properties properties)
    {
    this(properties, UniformInt.of(0, 0));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter worldIn, List<Component> tooltip, TooltipFlag flagIn)
    {
        if(this == BlockInit.CYBER_ORE_BLOCK.get()){
        if (!CyberConfig.Config.cyberOreGeneration.get()){
            tooltip.add(new TranslatableComponent("tooltip.config.tip"));}
        else tooltip.add(new TranslatableComponent(CyberOreTip.oreTip, CyberConfig.Config.cyberOreMinHeight.get().toString(), CyberConfig.Config.cyberOreMaxHeight.get().toString()));}
        else if (this == BlockInit.RUBY_ORE_BLOCK.get()){
        if (!CyberConfig.Config.rubyOreGeneration.get()){
            tooltip.add(new TranslatableComponent("tooltip.config.tip"));}
        else tooltip.add(new TranslatableComponent(RubyOreTip.oreTip, CyberConfig.Config.rubyOreMinHeight.get().toString(), CyberConfig.Config.rubyOreMaxHeight.get().toString()));}
        else if (this == BlockInit.DARK_STEEL_ORE_BLOCK.get()){
        if (!CyberConfig.Config.darksteelOreGeneration.get()){
            tooltip.add(new TranslatableComponent("tooltip.config.tip"));}
        else tooltip.add(new TranslatableComponent(DarkSteelOreTip.oreTip, CyberConfig.Config.darksteelOreMinHeight.get().toString(), CyberConfig.Config.darksteelOreMaxHeight.get().toString()));
        }
        if(this == BlockInit.DEEPSLATE_CYBER_ORE_BLOCK.get()){
        if (!CyberConfig.Config.cyberOreGeneration.get()){
            tooltip.add(new TranslatableComponent("tooltip.config.tip"));}
        else tooltip.add(new TranslatableComponent(CyberOreTip.oreTip, CyberConfig.Config.cyberOreMinHeight.get().toString(), CyberConfig.Config.cyberOreMaxHeight.get().toString()));}
    else if (this == BlockInit.RUBY_ORE_BLOCK.get()){
        if (!CyberConfig.Config.rubyOreGeneration.get()){
            tooltip.add(new TranslatableComponent("tooltip.config.tip"));}
        else tooltip.add(new TranslatableComponent(RubyOreTip.oreTip, CyberConfig.Config.rubyOreMinHeight.get().toString(), CyberConfig.Config.rubyOreMaxHeight.get().toString()));}
    else if (this == BlockInit.DARK_STEEL_ORE_BLOCK.get()){
        if (!CyberConfig.Config.darksteelOreGeneration.get()){
            tooltip.add(new TranslatableComponent("tooltip.config.tip"));}
        else tooltip.add(new TranslatableComponent(DarkSteelOreTip.oreTip, CyberConfig.Config.darksteelOreMinHeight.get().toString(), CyberConfig.Config.darksteelOreMaxHeight.get().toString()));}
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel worldIn, BlockPos blockPos, ItemStack itemStack) {
        super.spawnAfterBreak(state, worldIn, blockPos, itemStack);
    }
}
