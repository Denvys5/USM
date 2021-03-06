package com.denvys5.uraniumswordmod.machines.windmill;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.machines.USMTiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WindmillPlatform extends BlockContainer{

	public WindmillPlatform(){
		super(Material.rock);
		this.setHardness(1F);
	}
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
    	if(world.getBlockMetadata(x, y, z) == 0) setBlockBounds(0, 0, 0, 1, 1, 1);
    	if(world.getBlockMetadata(x, y, z) == 1) setBlockBounds(0, 0, 0, 0.5F, 1, 0.5F);
    	if(world.getBlockMetadata(x, y, z) == 2) setBlockBounds(0, 0, 0, 0.5F, 1, 1);
    	if(world.getBlockMetadata(x, y, z) == 3) setBlockBounds(0, 0, 0.5F, 0.5F, 1, 1);
    	if(world.getBlockMetadata(x, y, z) == 4) setBlockBounds(0, 0, 0, 1, 1, 0.5F);
    	if(world.getBlockMetadata(x, y, z) == 5) setBlockBounds(0, 0, 0, 1, 1, 1);
    	if(world.getBlockMetadata(x, y, z) == 6) setBlockBounds(0, 0, 0.5F, 1, 1, 1);
    	if(world.getBlockMetadata(x, y, z) == 7) setBlockBounds(0.5F, 0, 0, 1, 1, 0.5F);
    	if(world.getBlockMetadata(x, y, z) == 8) setBlockBounds(0.5F, 0, 0, 1, 1, 1);
    	if(world.getBlockMetadata(x, y, z) == 9) setBlockBounds(0.5F, 0, 0.5F, 1, 1, 1);
    	return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z){
    	
    	return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }
    
	public int getRenderType(){
		return -1;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.blockIcon = reg.registerIcon(USM.modid + ":WindmillPlatform");
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock){
		updateMultiblockStructure(world, x, y, z);
	}
	public void onBlockAdded(World world, int x, int y, int z){
		updateMultiblockStructure(world, x, y, z);
	}

	/*public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec){
		int metadata = world.getBlockMetadata(x, y, z);
		this.setBlockBounds(0, 0, 0, 1, (1F / 16) * metadata, 1);
		return super.collisionRayTrace(world, x, y, z, startVec, endVec);
	}*/

	public void updateMultiblockStructure(World world, int x, int y, int z){
		isMultiblockStructure(world, x, y, z);
	}

	public boolean isMultiblockStructure(World world, int x1, int y1, int z1){
		boolean structure = false;
		boolean currentCheckStructure = true;
		for(int x2 = 0; x2 < 3; x2++){
			for(int z2 = 0; z2 < 3; z2++){
				if(!structure){
					currentCheckStructure = true;
					for(int x3 = 0; x3 < 3; x3++){
						for(int z3 = 0; z3 < 3; z3++){
							if(!world.getBlock(x1 - x3 + x2, y1, z1 - z3 + z2).equals(USMTiles.WindmillPlatform) && currentCheckStructure){
								currentCheckStructure = false;
							}
						}
					}
					if(currentCheckStructure){
						for(int x3 = 0; x3 < 3; x3++){
							for(int z3 = 0; z3 < 3; z3++){
								world.setBlockMetadataWithNotify(x1 + x2 - x3, y1, z1 + z2 - z3, x3 * 3 + z3 + 1, 2);
							}
						}
					}
				}
				structure = currentCheckStructure;
			}
		}
		if(structure) return true;
		if(world.getBlockMetadata(x1, y1, z1) > 0) world.setBlockMetadataWithNotify(x1, y1, z1, 0, 3);
		return false;
	}

	public TileEntity createNewTileEntity(World world, int par1){
		return new TileEntityWindmillPlatform();
	}
}
