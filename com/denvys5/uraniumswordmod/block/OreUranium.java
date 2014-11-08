package com.denvys5.uraniumswordmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.denvys5.uraniumswordmod.USM;

public class OreUranium extends Block{

	public OreUranium(){
		super(Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(7.0F);
		this.setResistance(25F);
		this.setLightLevel(8.0F);
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer){
		par5EntityPlayer.addPotionEffect(new PotionEffect(17, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(15, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(18, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(20, 100, 1));
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.blockIcon = reg.registerIcon(USM.modid + ":OreUranium");
	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity){
		par5Entity.attackEntityFrom(DamageSource.cactus, 5.0F);
	}
}
