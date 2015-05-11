package com.denvys5.uraniumswordmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.api.USMBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockNetherStar extends USMBlock{

	public BlockNetherStar(){
		super();
		this.setLightLevel(8.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.blockIcon = reg.registerIcon(USM.modid + ":BlockNetherStar");
	}
}