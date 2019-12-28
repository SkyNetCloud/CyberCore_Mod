package xyz.skynetcloud.cybercore.block.tech.techblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.block.tech.TechBlockBaseSubCore;
import xyz.skynetcloud.cybercore.init.BlockInit;
import xyz.skynetcloud.cybercore.util.TE.LunaGenTileEntity;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.PowerTileEntity;

public class LunarGenBlock extends TechBlockBaseSubCore {

	public LunarGenBlock() {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5F), CyberCoreTab.instance, true);

	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new LunaGenTileEntity();

	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (this == BlockInit.lunargen_block) {
			return Block.makeCuboidShape(1, 0, 1, 15, 1, 15);
		}
		return Block.makeCuboidShape(1, 0, 1, 15, 1, 15);
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult ray) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof PowerTileEntity) {
				((ServerPlayerEntity) player).openContainer((LunaGenTileEntity) te);
			}
		}
		return true;
	}

}
