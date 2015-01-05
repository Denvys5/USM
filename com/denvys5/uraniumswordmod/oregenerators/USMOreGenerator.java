package com.denvys5.uraniumswordmod.oregenerators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

import com.denvys5.uraniumswordmod.block.USMBlock;
import com.denvys5.uraniumswordmod.block.USMBlocks;

public class USMOreGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
		switch(world.provider.dimensionId){
			case -1 :
				generateNether(world, random, chunkZ, chunkZ);
				// break;
			case 0 :
				generateSurface(world, random, chunkX * 16, chunkZ * 16);
				// break;
			case 1 :
				generateEnd(world, random, chunkZ, chunkZ);
				// break;
		}
	}

	public void generateNether(World world, Random random, int x, int y){
		// were not doing ore ore in the nether
	}

	public void generateSurface(World world, Random random, int x, int y){
		this.addOreSpawn(USMBlocks.oreuranium, world, random, x, y, 16, 16, 1 + random.nextInt(3), 30, 5, 50);
		this.addOreSpawn(USMBlocks.oreMetal, 0, world, random, x, y, 16, 16, 1 + random.nextInt(6), 120, 14, 40);
		this.addOreSpawn(USMBlocks.oreMetal, 1, world, random, x, y, 16, 16, 1 + random.nextInt(6), 120, 14, 40);
		this.addOreSpawn(USMBlocks.oreMetal, 2, world, random, x, y, 16, 16, 1 + random.nextInt(4), 100, 5, 38);
		this.addOreSpawn(USMBlocks.oreMetal, 3, world, random, x, y, 16, 16, 1 + random.nextInt(4), 100, 5, 35);
	}

	public void generateEnd(World world, Random random, int x, int y){
		// were not going to generate in the end either
	}

	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY){
		for(int i = 0; i < chancesToSpawn; i++){
			int PosX = blockXPos + random.nextInt(maxX);
			int PosY = minY + random.nextInt(maxY - minY);
			int PosZ = blockZPos + random.nextInt(maxZ);
			(new USMWorldGenerator(block, maxVeinSize, 0)).generate(world, random, PosX, PosY, PosZ);
		}
	}
	
	public void addOreSpawn(Block block, int metadata, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY){
		for(int i = 0; i < chancesToSpawn; i++){
			int PosX = blockXPos + random.nextInt(maxX);
			int PosY = minY + random.nextInt(maxY - minY);
			int PosZ = blockZPos + random.nextInt(maxZ);
			(new USMWorldGenerator(block, maxVeinSize, metadata)).generate(world, random, PosX, PosY, PosZ);
		}
	}
}