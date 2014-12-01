package com.denvys5.uraniumswordmod.machines.pipes;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPipe extends BlockContainer{

	public BlockPipe(){
		super(Material.ground);
		float pixel = 1F/16F;
		this.useNeighborBrightness = true;
		this.setBlockBounds(11*pixel/2F, 11*1F/32F, 11*1F/32F, 1-11*1F/32F, 1-11*1F/32F, 1-11*1F/32F);
	}
	
	public TileEntity createNewTileEntity(World world, int p_149915_2_){
		return new TileEntityPipe();
	}
	
	public int getRenderType(){
		return -1;
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}
	
/*	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
		TileEntityPipe pipe = (TileEntityPipe)world.getTileEntity(x, y, z);
		float pixel = 1F/16F;
		if(pipe != null){
			float minX = 11*pixel/2-(pipe.connections[4] != null? (11*pixel/2):0);
			float maxX = 11*pixel/2+(pipe.connections[5] != null? (11*pixel/2):0);
			float minY = 11*pixel/2-(pipe.connections[0] != null? (11*pixel/2):0);
			float maxY = 11*pixel/2+(pipe.connections[1] != null? (11*pixel/2):0);
			float minZ = 11*pixel/2-(pipe.connections[2] != null? (11*pixel/2):0);
			float maxZ = 11*pixel/2+(pipe.connections[3] != null? (11*pixel/2):0);
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
	}
	
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
		TileEntityPipe pipe = (TileEntityPipe)world.getTileEntity(x, y, z);
		float pixel = 1F/16F;
		if(pipe != null){
			float minX = 11*pixel/2-(pipe.connections[4] != null? (11*pixel/2):0);
			float maxX = 11*pixel/2+(pipe.connections[5] != null? (11*pixel/2):0);
			float minY = 11*pixel/2-(pipe.connections[0] != null? (11*pixel/2):0);
			float maxY = 11*pixel/2+(pipe.connections[1] != null? (11*pixel/2):0);
			float minZ = 11*pixel/2-(pipe.connections[2] != null? (11*pixel/2):0);
			float maxZ = 11*pixel/2+(pipe.connections[3] != null? (11*pixel/2):0);
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
	}*/

}
