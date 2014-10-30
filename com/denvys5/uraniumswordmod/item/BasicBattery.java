package com.denvys5.uraniumswordmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicBattery extends Item {
	private int Charge = 0;
	public BasicBattery(int maxCharge) {
		super();
		this.setCreativeTab(USM.USMTab);
		this.setMaxDamage(maxCharge);
		this.maxStackSize = 1;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(USM.modid + ":BasicBattery");
		
	}
}
