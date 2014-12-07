package com.denvys5.uraniumswordmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicBattery extends USMItem{
	private int FullBattery = 0;
	private int EmptyBattery = 10000;
	private IIcon Full;
	private IIcon Empty;
	public BasicBattery(int maxCharge){
		super();
		this.setMaxDamage(maxCharge);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg){
		this.itemIcon = reg.registerIcon(USM.modid + ":BasicBattery");
		this.Full = reg.registerIcon(USM.modid + ":BasicBattery");
		this.Empty = reg.registerIcon(USM.modid + ":BasicBattery_empty");

	}

	public IIcon getIconFromDamage(int i){
		if(i != EmptyBattery) return Full;
		if(i == EmptyBattery) return Empty;
		return this.itemIcon;
	}
}
