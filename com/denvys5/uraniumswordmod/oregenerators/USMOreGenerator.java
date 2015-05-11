package com.denvys5.uraniumswordmod.oregenerators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

import com.denvys5.uraniumswordmod.api.USMBlock;
import com.denvys5.uraniumswordmod.block.USMBlocks;

public class USMOreGenerator implements IWorldGenerator{
	
	public static int oreUraniumRarity;
	public static int oreCopperRarity;
	public static int oreTinRarity;
	public static int oreSilverRarity;
	public static int oreLeadRarity;
	
	public static boolean oreUraniumGeneration;
	public static boolean oreCopperGeneration;
	public static boolean oreTinGeneration;
	public static boolean oreSilverGeneration;
	public static boolean oreLeadGeneration;


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
		if(oreUraniumGeneration) this.addOreSpawn(USMBlocks.oreuranium, world, random, x, y, 16, 16, 1 + random.nextInt(3), oreUraniumRarity, 5, 50);
		if(oreCopperGeneration) this.addOreSpawn(USMBlocks.oreMetal, 0, world, random, x, y, 16, 16, 1 + random.nextInt(6), oreCopperRarity, 14, 40);
		if(oreTinGeneration) this.addOreSpawn(USMBlocks.oreMetal, 1, world, random, x, y, 16, 16, 1 + random.nextInt(6), oreTinRarity, 14, 40);
		if(oreSilverGeneration) this.addOreSpawn(USMBlocks.oreMetal, 2, world, random, x, y, 16, 16, 1 + random.nextInt(4), oreSilverRarity, 5, 38);
		if(oreLeadGeneration) this.addOreSpawn(USMBlocks.oreMetal, 3, world, random, x, y, 16, 16, 1 + random.nextInt(4), oreLeadRarity, 5, 35);
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