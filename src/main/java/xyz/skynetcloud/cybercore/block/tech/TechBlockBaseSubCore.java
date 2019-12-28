package xyz.skynetcloud.cybercore.block.tech;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;

public class TechBlockBaseSubCore extends BlockBaseCore {

	public TechBlockBaseSubCore(Properties properties, ItemGroup group, boolean b) {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(4.1F, 8.0F).doesNotBlockMovement(),
				group, true);

	}

	public IItemProvider getItemDropIItemProvider(BlockState state, World worldIn, BlockPos pos, int fortune) {
		return this;
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos,
			PlayerEntity player) {
		return new ItemStack(this);

	}

	/*public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult ray) {
		if (!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if (te instanceof PowerTileEntity) {
				((ServerPlayerEntity) player).openContainer((PowerTileEntity) te);
			}
		}
		return true;
	}
	*/
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	/*
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		if (this == Names.LUNAR_GEN_MACHINE_BLOCK)
			return new LunaGenTileEntity();
		else if (this == Names.POWER_BOX_BLOCK)
			return new PowerStorageTileEntity();
		else
			return new LunaGenTileEntity();
	}
	*/
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

}
