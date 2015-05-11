package com.denvys5.uraniumswordmod.api;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockBasicMachine extends BlockContainer{
	public Random rand = new Random();


	@SideOnly(Side.CLIENT)
	public IIcon iconFront;
	public IIcon top, bottom, side, front;
	
	public BlockBasicMachine(){
		super(Material.rock);
		this.setHardness(30.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.side = iconRegister.registerIcon(USM.modid + ":Machine_side");
		this.bottom = iconRegister.registerIcon(USM.modid + ":Machine_bottom");
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return side == 1 ? this.top : (side == 0 ? this.bottom : (metadata == 2 && side == 2 ? this.front : (metadata == 5 && side == 5 ? this.front : (metadata == 3 && side == 3 ? this.front : (metadata == 4 && side == 4 ? this.front : this.side)))));
	}
	
	public void onBlockAdded(World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
		this.setDefautDirection(world, x, y, z);
	}
	
	public void setDefautDirection(World world, int x, int y, int z){
		if(!world.isRemote){
			Block block1 = world.getBlock(x, y, z - 1);
			Block block2 = world.getBlock(x, y, z + 1);
			Block block3 = world.getBlock(x - 1, y, z);
			Block block4 = world.getBlock(x + 1, y, z);
			byte b0 = 3;
			if(block1.func_149730_j() && !block2.func_149730_j()){
				b0 = 3;
			}
			if(block2.func_149730_j() && !block1.func_149730_j()){
				b0 = 2;
			}
			if(block3.func_149730_j() && !block4.func_149730_j()){
				b0 = 5;
			}
			if(block4.func_149730_j() && !block3.func_149730_j()){
				b0 = 4;
			}
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}

	}
	
	public boolean hasComparatorInputOverride(){
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int i){
		return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
	}

}
