package com.denvys5.uraniumswordmod.machines.windmill;

import java.util.Random;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.machines.USMTiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class WindmillBlock extends BlockContainer{
	public WindmillBlock(){
		super(Material.rock);
		this.setHardness(3.0F);
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

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
		return USMTiles.WindmillItem;
	}
}
