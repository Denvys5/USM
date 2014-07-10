package assets.uraniumswordmod.generator;

import java.util.Random;

import assets.uraniumswordmod.USM;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.dimensionId) {
		case -1:
			generateNether(world, random, chunkZ, chunkZ);
			// break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			// break;
		case 1:
			generateEnd(world, random, chunkZ, chunkZ);
			// break;
		}
	}

	public void generateNether(World world, Random random, int x, int y) {
		// were not doing ore ore in the nether
	}

	public void generateSurface(World world, Random random, int x, int y) {
		this.addOreSpawn(USM.oreuranium, world, random, x, y, 16, 16,
				1 + random.nextInt(3), 15, 5, 50);
	}

	public void generateEnd(World world, Random random, int x, int y) {
		// were not going to generate in the end either
	}

	public void addOreSpawn(Block block, World world, Random random,
			int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize,
			int chancesToSpawn, int minY, int maxY) {
		for (int i = 0; i < chancesToSpawn; i++) {
			int PosX = blockXPos + random.nextInt(maxX);
			int PosY = minY + random.nextInt(maxY - minY);
			int PosZ = blockZPos + random.nextInt(maxZ);
			new WorldGenMinable(block.blockID, maxVeinSize).generate(world,
					random, PosX, PosY, PosZ);
		}
	}
}