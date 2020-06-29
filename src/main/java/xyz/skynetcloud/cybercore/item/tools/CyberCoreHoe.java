package xyz.skynetcloud.cybercore.item.tools;

import net.minecraft.item.HoeItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.util.networking.config.CyberCoreConfig;

public class CyberCoreHoe extends HoeItem {

	private static final float speed = 0.4f;

	public CyberCoreHoe(IItemTier tier, Properties builder) {
		super(tier, speed, builder);

	}

}
