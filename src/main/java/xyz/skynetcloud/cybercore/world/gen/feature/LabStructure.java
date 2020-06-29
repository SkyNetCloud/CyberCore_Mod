package xyz.skynetcloud.cybercore.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import javax.annotation.Nullable;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.skynetcloud.cybercore.init.FeaturesInit;
import xyz.skynetcloud.cybercore.world.gen.feature.structure.Lab;

public class LabStructure extends Structure<LabConfig> {

	public LabStructure(Function<Dynamic<?>, ? extends LabConfig> p_i51427_1_) {
		super(p_i51427_1_);

	}

	@Override
	public boolean canBeGenerated(BiomeManager manager, ChunkGenerator<?> generator, Random rand, int chunkX,
			int chunkZ, Biome biome) {
		if (generator.hasStructure(biome, this)) {
			((SharedSeedRandom) rand).setLargeFeatureSeedWithSalt(generator.getSeed(), chunkX, chunkZ, 0xF00D);
			LabConfig config = generator.getStructureConfig(biome, this);
			return config != null && rand.nextInt(config.chance) == 0;
		}
		return false;
	}

	@Nullable
	@Override
	public BlockPos findNearest(World worldIn, ChunkGenerator<? extends GenerationSettings> chunkGenerator,
			BlockPos pos, int radius, boolean p_211405_5_) {
		return super.findNearest(worldIn, chunkGenerator, pos, radius, p_211405_5_);
	}

	@Override
	public IStartFactory getStartFactory() {
		return Start::new;
	}

	@Override
	public String getStructureName() {
		return this.getRegistryName().toString();
	}

	@Override
	public int getSize() {
		return 3;
	}

	public static class Start extends StructureStart {
		public Start(Structure<?> structure, int chunkX, int chunkZ, MutableBoundingBox bounds, int references,
				long seed) {
			super(structure, chunkX, chunkZ, bounds, references, seed);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager manager, int chunkX, int chunkZ, Biome biome) {
			int posX = chunkX << 4;
			int posZ = chunkZ << 4;
			int height1 = generator.func_222532_b(posX + 3, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG);
			int height2 = generator.func_222532_b(posX + 13, posZ + 3, Heightmap.Type.OCEAN_FLOOR_WG);
			int height3 = generator.func_222532_b(posX + 3, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG);
			int height4 = generator.func_222532_b(posX + 13, posZ + 13, Heightmap.Type.OCEAN_FLOOR_WG);
			if (height1 == height2 && height1 == height3 && height1 == height4 && height1 >= generator.getSeaLevel()) {
				BlockPos pos = new BlockPos(posX + 3, 90, posZ + 3);
				Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
				LabConfig config = generator.getStructureConfig(biome, FeaturesInit.Lab.get());
				if (config != null) {
					this.components.add(new Lab.Piece(manager, pos, rotation, config));
					this.recalculateStructureSize();
				}
			}
		}
	}

}
