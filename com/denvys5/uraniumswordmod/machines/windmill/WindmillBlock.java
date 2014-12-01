package com.denvys5.uraniumswordmod.machines.windmill;

import java.util.Random;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.machines.USMTiles;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WindmillBlock extends BlockContainer{
	public WindmillBlock(){
		super(Material.rock);
		this.setHardness(3.0F);
	}
	
    public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
    	float pixel = 1F/16F;
    	if(blockAccess.getBlockMetadata(x, y, z) < 7){
    		this.setBlockBounds(pixel*4, 0, pixel*4, 1-pixel*4, 1, 1-pixel*4);
    	}else{
    		this.setBlockBounds(0, 0, 0, 1, 1, 1);
    	}
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

	public TileEntity createNewTileEntity(World world, int i){
		return new TileEntityWindmill();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, USM.instance, USM.guiIdWindMill, world, x, y, z);
		}
		return true;
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
		return USMTiles.WindmillItem;
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata){
		if(world.getBlock(x, y+1, z).equals(USMTiles.WindmillBlock)){
			world.setBlockToAir(x, y+1, z);
		}
		if(world.getBlock(x, y-1, z).equals(USMTiles.WindmillBlock)){
			world.setBlockToAir(x, y-1, z);
		}
	}
}
