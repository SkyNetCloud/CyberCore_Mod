package ca.skynetcloud.cybercore.block.building.block;

import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SlabBlocks extends SlabBlock {

	private DyeColor color;

	public SlabBlocks() {
		super(Block.Properties.of(Material.STONE).strength(0.4F).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE));

	}

	public boolean isSlabCap(SlabBlocks slab) {
		return true;
	}

	public DyeColor getColor() {
		return color;
	}

	@Override
	public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity player,
			Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {

		if (player.inventory.hasAnyOf(Set<Item>) {

		}
	}

}
