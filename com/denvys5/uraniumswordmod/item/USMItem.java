package com.denvys5.uraniumswordmod.item;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public abstract class USMItem extends Item{
	public USMItem(){
		super();
		this.setCreativeTab(USM.USMTab);
	}
	
	@SideOnly(Side.CLIENT)
	public abstract void registerIcons(IIconRegister reg);
}
