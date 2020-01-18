package xyz.skynetcloud.cybercore.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.network.play.server.SRespawnPacket;
import net.minecraft.network.play.server.SServerDifficultyPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.server.management.PlayerList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.hooks.BasicEventHooks;
import xyz.skynetcloud.cybercore.CyberCoreMain.CyberCoreTab;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.block.BlockBaseCore;
import xyz.skynetcloud.cybercore.init.DimInit;
import xyz.skynetcloud.cybercore.util.ClientSideConfig;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;

public class CustomWorldLink extends BlockBaseCore {

	public CustomWorldLink(String name, ItemGroup group) {
		super(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5F), name, group, true);
	}

	@Override
	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(BlockInit.DimWorldLinkBlock);
	}

	public Item createItemBlock() {
		return new BlockItem(this, new Item.Properties().group(CyberCoreTab.instance))
				.setRegistryName("worldlinkblock");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		TileEntity te = worldIn.getTileEntity(pos);
		if (te != null) {
			if (te instanceof CableTileEntity) {
				((CableTileEntity) te).deleteCable();
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return (worldIn.getDimension().getType().getId() == ClientSideConfig.getOverworldId()
				|| worldIn.getDimension().getType() == DimensionType.byName(DimInit.CYBERLAND_ID)
						&& super.isValidPosition(state, worldIn, pos));
	}

	@Override
	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn,
			Hand hand, BlockRayTraceResult rts) {

		if (!worldIn.isRemote) {
			// FROM OVERWORLD TO MINING DIM
			if (worldIn.getDimension().getType().getId() == ClientSideConfig.getOverworldId()) {
				if (DimensionType.byName(DimInit.CYBERLAND_ID) == null) {
					DimensionManager.registerDimension(DimInit.CYBERLAND_ID, DimInit.CYBERLAND, null, true);
				}
				World otherWorld = worldIn.getServer().getWorld(DimensionType.byName(DimInit.CYBERLAND_ID));
				otherWorld.getBlockState(pos);
				BlockPos otherWorldPos = otherWorld.getHeight(Heightmap.Type.WORLD_SURFACE, pos);
				boolean foundBlock = false;
				BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable(0, 0, 0);

				for (int y = 0; y < 256; y++) {
					for (int x = pos.getX() - 6; x < pos.getX() + 6; x++) {
						for (int z = pos.getZ() - 6; z < pos.getZ() + 6; z++) {
							mutableBlockPos.setPos(x, y, z);
							if (otherWorld.getBlockState(mutableBlockPos).getBlock() == BlockInit.DimWorldLinkBlock) {
								otherWorldPos = new BlockPos(x, y + 1, z);
								foundBlock = true;
								break;
							}
						}
					}
				}
				if (foundBlock) {
					changeDim(((ServerPlayerEntity) playerIn), otherWorldPos,
							DimensionType.byName(DimInit.CYBERLAND_ID));
				}
				if (!foundBlock) {
					otherWorld.setBlockState(otherWorldPos.down(), BlockInit.DimWorldLinkBlock.getDefaultState());
					changeDim(((ServerPlayerEntity) playerIn), otherWorldPos,
							DimensionType.byName(DimInit.CYBERLAND_ID));
				}
			}

			// FROM MINING DIM TO OVERWORLD
			if (worldIn.getDimension().getType() == DimensionType.byName(DimInit.CYBERLAND_ID)) {
				World overWorld = worldIn.getServer()
						.getWorld(DimensionType.getById(ClientSideConfig.getOverworldId()));
				overWorld.getBlockState(pos);
				BlockPos overWorldPos = overWorld.getHeight(Heightmap.Type.WORLD_SURFACE, pos);
				boolean foundBlock = false;
				BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable(0, 0, 0);

				for (int y = 0; y < 256; y++) {
					for (int x = pos.getX() - 6; x < pos.getX() + 6; x++) {
						for (int z = pos.getZ() - 6; z < pos.getZ() + 6; z++) {
							mutableBlockPos.setPos(x, y, z);
							if (overWorld.getBlockState(mutableBlockPos).getBlock() == BlockInit.DimWorldLinkBlock) {
								overWorldPos = new BlockPos(x, y + 1, z);
								foundBlock = true;
								break;
							}
						}
					}
				}
				if (foundBlock) {
					changeDim(((ServerPlayerEntity) playerIn), overWorldPos,
							DimensionType.getById(ClientSideConfig.getOverworldId()));
				}
				if (!foundBlock) {
					overWorld.setBlockState(overWorldPos.down(), BlockInit.DimWorldLinkBlock.getDefaultState());
					changeDim(((ServerPlayerEntity) playerIn), overWorldPos,
							DimensionType.getById(ClientSideConfig.getOverworldId()));
				}
			}

			return ActionResultType.PASS;
		}
		return ActionResultType.FAIL;
	}

	public static void changeDim(ServerPlayerEntity player, BlockPos pos, DimensionType type) {
		if (!ForgeHooks.onTravelToDimension(player, type))
			return;

		DimensionType dimensiontype = player.dimension;

		ServerWorld serverworld = player.server.getWorld(dimensiontype);
		player.dimension = type;
		ServerWorld serverworld1 = player.server.getWorld(type);
		WorldInfo worldinfo = player.world.getWorldInfo();
		player.connection.sendPacket(
				new SRespawnPacket(type, 10, worldinfo.getGenerator(), player.interactionManager.getGameType()));
		player.connection
				.sendPacket(new SServerDifficultyPacket(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
		PlayerList playerlist = player.server.getPlayerList();
		playerlist.updatePermissionLevel(player);
		serverworld.removeEntity(player, true);
		player.revive();
		float f = player.rotationPitch;
		float f1 = player.rotationYaw;

		player.setLocationAndAngles(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, f1, f);
		serverworld.getProfiler().endSection();
		serverworld.getProfiler().startSection("placing");
		player.setLocationAndAngles(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, f1, f);

		serverworld.getProfiler().endSection();
		player.setWorld(serverworld1);
		serverworld1.func_217447_b(player);
		player.connection.setPlayerLocation(pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, f1, f);
		player.interactionManager.setWorld(serverworld1);
		player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
		playerlist.sendWorldInfo(player, serverworld1);
		playerlist.sendInventory(player);

		for (EffectInstance effectinstance : player.getActivePotionEffects()) {
			player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
		}

		player.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
		BasicEventHooks.firePlayerChangedDimensionEvent(player, dimensiontype, type);
	}
}
