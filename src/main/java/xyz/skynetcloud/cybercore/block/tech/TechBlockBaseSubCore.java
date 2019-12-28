package xyz.skynetcloud.cybercore.block.tech;

import java.util.List;

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
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.init.BlockInit;
import xyz.skynetcloud.cybercore.util.TE.PowerStorageTileEntity;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.PowerInventoryTileEntity;
import xyz.skynetcloud.cybercore.util.TE.otherclasses.PowerTileEntity;

public class TechBlockBaseSubCore extends BlockBaseCore {

	public TechBlockBaseSubCore(Properties properties, ItemGroup group, boolean b) {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(4.1F, 8.0F).doesNotBlockMovement(), group,
				true);

	}

	public IItemProvider getItemDropIItemProvider(BlockState state, World worldIn, BlockPos pos, int fortune) {
		return this;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		return new ItemStack(this);

	}

	
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult ray) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof PowerTileEntity) {
				((ServerPlayerEntity) player).openContainer((PowerTileEntity) te);
			}
		}
		return true;
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (this == BlockInit.lunargen_block) {
			return Block.makeCuboidShape(1, 0, 1, 15, 1, 15);
		} else if (this == BlockInit.power_box) {
			return Block.makeCuboidShape(0, 0, 0, 16, 16, 16);
		}

		return Block.makeCuboidShape(1, 0, 1, 15, 1, 15);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new PowerStorageTileEntity();
		
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {

		if (state.getBlock() != newState.getBlock()) {
			if (worldIn.getTileEntity(pos) instanceof PowerInventoryTileEntity) {
				PowerInventoryTileEntity te = (PowerInventoryTileEntity) worldIn.getTileEntity(pos);
				List<ItemStack> toSpawn = te.getInventortyContent();
				for (ItemStack stack : toSpawn) {
					worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack));
				}
			}
			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}

	}

}
