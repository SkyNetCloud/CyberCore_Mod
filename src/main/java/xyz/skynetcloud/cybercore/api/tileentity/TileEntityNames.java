package xyz.skynetcloud.cybercore.api.tileentity;

import net.minecraft.tileentity.TileEntityType;
import xyz.skynetcloud.cybercore.api.blocks.BlockInit;
import xyz.skynetcloud.cybercore.util.TE.LunaGenTileEntity;
import xyz.skynetcloud.cybercore.util.TE.PowedFurnaceTileEntity;
import xyz.skynetcloud.cybercore.util.TE.PowerStorageTileEntity;
import xyz.skynetcloud.cybercore.util.TE.cable.CableTileEntity;
import xyz.skynetcloud.cybercore.util.TE.cable.ItemPipeTileEntity;

public class TileEntityNames {

	public static final TileEntityType<PowerStorageTileEntity> POWER_BOX_TE = TileEntityType.Builder
			.create(PowerStorageTileEntity::new, BlockInit.POWER_BOX).build(null);
	public static final TileEntityType<LunaGenTileEntity> LUNAR_GEN_MACHINE_TE = TileEntityType.Builder
			.create(LunaGenTileEntity::new, BlockInit.LUNAR_BLOCK).build(null);
	public static final TileEntityType<CableTileEntity> CABLE_TE = TileEntityType.Builder
			.create(CableTileEntity::new, BlockInit.CABLE).build(null);

	public static final TileEntityType<ItemPipeTileEntity> TE_TYPE_ITEM_PIPE = TileEntityType.Builder
			.create(ItemPipeTileEntity::new, BlockInit.ITEM_PIPE).build(null);

	public static final TileEntityType<PowedFurnaceTileEntity> POWER_FURNACE_TE = TileEntityType.Builder
			.create(PowedFurnaceTileEntity::new, BlockInit.POWER_FURNACE_BLOCK).build(null);
	/*
	public static final TileEntityType<IronChestTileEntity> IRON_CHEST = TileEntityType.Builder
			.create(IronChestTileEntity::new, BlockInit.IRON_CHEST).build(null);

	public static final TileEntityType<GoldChestTileEntity> GOLD_CHEST = TileEntityType.Builder
			.create(GoldChestTileEntity::new, BlockInit.GOLD_CHEST).build(null);

	public static final TileEntityType<DiamondChestTileEntity> DIAMOND_CHEST = TileEntityType.Builder
			.create(DiamondChestTileEntity::new, BlockInit.DIAMOND_CHEST).build(null);

	public static final TileEntityType<CopperChestTileEntity> COPPER_CHEST = TileEntityType.Builder
			.create(CopperChestTileEntity::new, BlockInit.COPPER_CHEST).build(null);

	public static final TileEntityType<SilverChestTileEntity> SILVER_CHEST = TileEntityType.Builder
			.create(SilverChestTileEntity::new, BlockInit.SILVER_CHEST).build(null);

	public static final TileEntityType<CrystalChestTileEntity> CRYSTAL_CHEST = TileEntityType.Builder
			.create(CrystalChestTileEntity::new, BlockInit.CRYSTAL_CHEST).build(null);

	public static final TileEntityType<ObsidianChestTileEntity> OBSIDIAN_CHEST = TileEntityType.Builder
			.create(ObsidianChestTileEntity::new, BlockInit.OBSIDIAN_CHEST).build(null);
	*/
}
