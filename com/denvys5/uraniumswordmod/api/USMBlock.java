package com.denvys5.uraniumswordmod.api;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public abstract class USMBlock extends Block{

	public USMBlock(Material material){
		super(material);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(30.0F);
		this.setResistance(25F);
	}
	
	public USMBlock(){
		super(Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(30.0F);
		this.setResistance(25F);
	}
	
	@SideOnly(Side.CLIENT)
	public abstract void registerBlockIcons(IIconRegister reg);

}
