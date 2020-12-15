package ca.skynetcloud.cybercore.block.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.util.TE.techblock.TileIrrigation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;

public class BlockIrrigation extends BlockBaseCore {

	public BlockIrrigation() {
		super(Block.Properties.create(Material.WATER).hardnessAndResistance(1.3F).harvestTool(ToolType.PICKAXE)
				.notSolid(), true);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileIrrigation();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip,
			ITooltipFlag flagIn) {
		TranslationTextComponent t = new TranslationTextComponent(getTranslationKey() + ".tooltip");
		tooltip.add(t);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		double x = (double) pos.getX() + 1.5D;
		double y = (double) pos.getY() + 1.5D;
		double z = (double) pos.getZ() + 1.5D;
		worldIn.addParticle(ParticleTypes.RAIN, x, y, z, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.RAIN, x, y, z, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.RAIN, x, y, z, 0.0D, 0.0D, 0.0D);
		worldIn.addParticle(ParticleTypes.RAIN, x, y, z, 0.0D, 0.0D, 0.0D);
	}

}
