package com.denvys5.uraniumswordmod.core;

import com.denvys5.uraniumswordmod.block.USMBlocks;

import scala.actors.threadpool.Arrays;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry.IPartConverter;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.TMultiPart;

public class MultiPartRegister implements IPartFactory, IPartConverter{

	@Override
	public Iterable<Block> blockTypes(){
		return Arrays.asList(USMBlocks.AllBlocks.toArray());
	}

	@Override
	public TMultiPart convert(World world, BlockCoord pos){
		Block b = world.getBlock(pos.x, pos.y, pos.z);
		for(Block fromList : USMBlocks.AllBlocks){
			if(b.equals(fromList)){
				return new USMMultipart(b);
			}
		}
		return null;
	}

	@Override
	public TMultiPart createPart(String name, boolean client){
		if(name.equals("USM|UraniumBlock")) return new USMMultipart();
		return null;
	}
	
	public void init(){
		MultiPartRegistry.registerConverter(this);
		MultiPartRegistry.registerParts(this, new String[]{"USM|UraniumBlock"});
	}

}
