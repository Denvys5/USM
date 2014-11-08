package com.denvys5.uraniumswordmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.denvys5.uraniumswordmod.USM;

public class StickIron extends Item{
	public StickIron(){
		super();
		this.setCreativeTab(USM.USMTab);

	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg){
		this.itemIcon = reg.registerIcon(USM.modid + ":StickIron");
	}
}
