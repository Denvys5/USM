package com.denvys5.uraniumswordmod.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicBattery extends Item{
	private int FullBattery = 0;
	private int EmptyBattery = 10000;
	private IIcon Full;
	private IIcon Empty;
	public BasicBattery(int maxCharge){
		super();
		this.setCreativeTab(USM.USMTab);
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
