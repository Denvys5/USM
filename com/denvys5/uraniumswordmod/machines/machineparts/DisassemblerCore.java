package com.denvys5.uraniumswordmod.machines.machineparts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DisassemblerCore extends MachineCore{
	public DisassemblerCore(){
		super();
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.blockIcon = reg.registerIcon(USM.modid + ":DisassemblerCore");
	}
}
