package com.denvys5.uraniumswordmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.denvys5.uraniumswordmod.core.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DisassemblerCore extends Block{
	public DisassemblerCore() {
		super(Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(3.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(USM.modid + ":DisassemblerCore");
	}
}
