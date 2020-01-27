package xyz.skynetcloud.cybercore.world.dim;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap.Type;
import xyz.skynetcloud.cybercore.util.networking.config.ClientSideConfig;
import net.minecraft.world.gen.WorldGenRegion;

public class CyberChunkGen extends ChunkGenerator<GenerationSettings> {

	public CyberChunkGen(IWorld world, BiomeProvider biomeProvider, GenerationSettings settings) {
		super(world, biomeProvider, settings);
	}

	@Override
	public void decorate(WorldGenRegion region) {
		int i = region.getMainChunkX();
		int j = region.getMainChunkZ();
		int k = i * 16;
		int l = j * 16;
		BlockPos blockpos = new BlockPos(k, 0, l);
		Biome biome = Biomes.MOUNTAINS;
		SharedSeedRandom sharedseedrandom = new SharedSeedRandom();
		long i1 = sharedseedrandom.setDecorationSeed(region.getSeed(), k, l);

		biome.decorate(GenerationStage.Decoration.VEGETAL_DECORATION, this, region, i1, sharedseedrandom, blockpos);
		biome.decorate(GenerationStage.Decoration.UNDERGROUND_ORES, this, region, i1, sharedseedrandom, blockpos);
	}

	@Override
	public void func_225551_a_(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
		BlockState bedrock = Blocks.BEDROCK.getDefaultState();
		BlockState stone = Blocks.STONE.getDefaultState();
		BlockState dirt = Blocks.DIRT.getDefaultState();
		BlockState grass = Blocks.GRASS_BLOCK.getDefaultState();
		int x1, y1, z1;
		int worldHeight = ClientSideConfig.world_height.get();

		BlockPos.Mutable pos = new BlockPos.Mutable();

		for (x1 = 0; x1 < 16; x1++) {
			for (z1 = 0; z1 < 16; z1++) {
				p_225551_2_.setBlockState(pos.setPos(x1, 0, z1), bedrock, false);
			}
		}
		if (ClientSideConfig.grass_enable.get()) {
			for (x1 = 0; x1 < 16; x1++) {
				for (y1 = 1; y1 < worldHeight - 3; y1++) {
					for (z1 = 0; z1 < 16; z1++) {
						p_225551_2_.setBlockState(pos.setPos(x1, y1, z1), stone, false);
					}
				}
			}
			for (x1 = 0; x1 < 16; x1++) {
				for (y1 = worldHeight - 3; y1 < worldHeight - 1; y1++) {
					for (z1 = 0; z1 < 16; z1++) {
						p_225551_2_.setBlockState(pos.setPos(x1, y1, z1), dirt, false);
					}
				}
			}
			for (x1 = 0; x1 < 16; x1++) {
				for (y1 = worldHeight - 1; y1 < worldHeight; y1++) {
					for (z1 = 0; z1 < 16; z1++) {
						p_225551_2_.setBlockState(pos.setPos(x1, y1, z1), grass, false);
					}
				}
			}
		} else {
			for (x1 = 0; x1 < 16; x1++) {
				for (y1 = 1; y1 < worldHeight; y1++) {
					for (z1 = 0; z1 < 16; z1++) {
						p_225551_2_.setBlockState(pos.setPos(x1, y1, z1), stone, false);
					}
				}
			}
		}
	
	}

	@Override
	public int getGroundHeight() {

		return 15;
	}

	@Override
	public void makeBase(IWorld worldIn, IChunk chunkIn) {

	}

	@Override
	public int func_222529_a(int p_222529_1_, int p_222529_2_, Type p_222529_3_) {

		return 0;
	}

}
