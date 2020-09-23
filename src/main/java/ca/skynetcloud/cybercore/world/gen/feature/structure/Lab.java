package ca.skynetcloud.cybercore.world.gen.feature.structure;

import java.util.Random;

import ca.skynetcloud.cybercore.CyberCoreMain;
import ca.skynetcloud.cybercore.init.InitStructurePieceType;
import ca.skynetcloud.cybercore.world.gen.feature.LabConfig;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.JigsawReplacementStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class Lab {
	public static final ResourceLocation Lab_Oak = new ResourceLocation(CyberCoreMain.MODID, "lab_oak");
	
	public static final ResourceLocation Lab_Dark_Oak = new ResourceLocation(CyberCoreMain.MODID, "lab_dark_oak");
	
	public static final ResourceLocation Lab_Desert = new ResourceLocation(CyberCoreMain.MODID, "lab_desert");

	public static class Piece extends TemplateStructurePiece {

		public static final ResourceLocation LAB_CHEST_LOOT = new ResourceLocation(CyberCoreMain.MODID,
				"chests/lab_chest");

		private Rotation rotation;
		private ResourceLocation templateLocation;

		public Piece(TemplateManager manager, BlockPos pos, Rotation rotation, LabConfig config) {
			super(InitStructurePieceType.LOOTABLE_lab, 0);
			this.rotation = rotation;
			this.templatePosition = pos;
			this.templateLocation = config.template;
			this.loadTemplate(manager);
		}

		public Piece(TemplateManager manager, CompoundNBT compound) {
			super(InitStructurePieceType.LOOTABLE_lab, compound);
			this.rotation = Rotation.valueOf(compound.getString("Rot"));
			this.templateLocation = new ResourceLocation(compound.getString("Template"));
			this.loadTemplate(manager);
		}

		private void loadTemplate(TemplateManager manager) {
			Template template = manager.getTemplateDefaulted(this.templateLocation);
			PlacementSettings settings = (new PlacementSettings()).setRotation(this.rotation)
					.setCenterOffset(new BlockPos(5, 0, 5)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
					.addProcessor(JigsawReplacementStructureProcessor.INSTANCE);
			this.setup(template, this.templatePosition, settings);
		}

		@Override
		protected void readAdditional(CompoundNBT compound) {
			super.readAdditional(compound);
			compound.putString("Template", this.templateLocation.toString());
			compound.putString("Rot", this.rotation.name());
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand,
				MutableBoundingBox sbb) {
			if ("chest".equals(function)) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = worldIn.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity) {
					((ChestTileEntity) tileentity).setLootTable(LAB_CHEST_LOOT, rand.nextLong());
				}
			}
		}

		@Override
		public boolean create(IWorld world, ChunkGenerator<?> generator, Random rand, MutableBoundingBox bounds,
				ChunkPos chunkPos) {
			int posY = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.templatePosition.getX(),
					this.templatePosition.getZ()) - 1;
			this.templatePosition = new BlockPos(this.templatePosition.getX(), posY, this.templatePosition.getZ());

			switch (this.rotation) {
			case CLOCKWISE_90:
				this.templatePosition = this.templatePosition.add(-1, 0, 0);
				break;
			case CLOCKWISE_180:
				this.templatePosition = this.templatePosition.add(-1, 0, -1);
				break;
			case COUNTERCLOCKWISE_90:
				this.templatePosition = this.templatePosition.add(0, 0, -1);
				break;
			default:
				break;
			}

			return super.create(world, generator, rand, bounds, chunkPos);
		}
	}
}
