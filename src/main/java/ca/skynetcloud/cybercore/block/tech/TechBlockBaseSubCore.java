package ca.skynetcloud.cybercore.block.tech;

import java.util.List;

import ca.skynetcloud.cybercore.api.blocks.BlockInit;
import ca.skynetcloud.cybercore.block.BlockBaseCore;
import ca.skynetcloud.cybercore.util.TE.powerTE.CyberCoreEndPowerTE;
import ca.skynetcloud.cybercore.util.TE.techblock.CablePainterTE;
import ca.skynetcloud.cybercore.util.TE.techblock.LunaGenTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowedFurnaceTileEntity;
import ca.skynetcloud.cybercore.util.TE.techblock.PowerStorageTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TechBlockBaseSubCore extends BlockBaseCore {

	public TechBlockBaseSubCore(ItemGroup group) {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0f, 10.0f).notSolid(), group, true);

	}

	public IItemProvider getItemDropped(BlockState state, World worldIn, BlockPos pos, int fortune) {
		return this;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		return new ItemStack(this);
	}

	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand hand, BlockRayTraceResult ray) {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof CyberCoreEndPowerTE) {
				((ServerPlayerEntity) player).openContainer((CyberCoreEndPowerTE) te);
			}
		}

		return ActionResultType.SUCCESS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

		if (this == BlockInit.LUNAR_BLOCK)
			return Block.makeCuboidShape(1, 0, 1, 15, 1, 15);
		else
			return super.getShape(state, worldIn, pos, context);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		if (this == BlockInit.LUNAR_BLOCK)
			return new LunaGenTileEntity();
		else if (this == BlockInit.POWER_BOX)
			return new PowerStorageTileEntity();
		else if (this == BlockInit.POWER_FURNACE_BLOCK)
			return new PowedFurnaceTileEntity();
		else if (this == BlockInit.CABLE_PAINTER)
			return new CablePainterTE();
		else
			return new LunaGenTileEntity();
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			if (worldIn.getTileEntity(pos) instanceof CyberCoreEndPowerTE) {
				CyberCoreEndPowerTE te = (CyberCoreEndPowerTE) worldIn.getTileEntity(pos);
				List<ItemStack> toSpawn = te.getInventoryContent();
				for (ItemStack stack : toSpawn) {
					worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack));
				}
			}
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

}