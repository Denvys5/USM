package com.denvys5.uraniumswordmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.denvys5.uraniumswordmod.core.USM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockNetherStar extends Block {

	public BlockNetherStar() {
		super(Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(3.0F);
		this.setResistance(25F);
		this.setLightLevel(8.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(USM.modid + ":BlockNetherStar");
	}
}