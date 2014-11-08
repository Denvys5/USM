package com.denvys5.uraniumswordmod.machines.machineparts;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class MachineCore extends Block{
	public MachineCore(){
		super(Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(3.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.blockIcon = reg.registerIcon(USM.modid + ":BasicMachineCore");
	}
}
