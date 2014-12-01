package com.denvys5.uraniumswordmod.core;

import com.denvys5.uraniumswordmod.block.USMBlocks;

import net.minecraft.block.Block;
import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.minecraft.McMetaPart;

public class USMMultipart extends McMetaPart{

	private Block block;
	
	public USMMultipart(Block block){
		this.block = block;
	}

	public USMMultipart(){
		this.block = USMBlocks.blockuranium;
	}

	@Override
	public Cuboid6 getBounds(){
		return new Cuboid6(0.2F, 0, 0.2F, 0.8F, 1, 0.8F);
	}

	@Override
	public Block getBlock(){
		return this.block;
	}

	@Override
	public String getType(){
		return "USM|UraniumBlock";
	}


}
