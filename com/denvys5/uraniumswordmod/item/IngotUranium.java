package com.denvys5.uraniumswordmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

import com.denvys5.uraniumswordmod.USM;

public class IngotUranium extends Item {
	public IngotUranium() {
		super();
		this.setHasSubtypes(true);
		this.setCreativeTab(USM.USMTab);

	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(USM.modid + ":IngotUranium");
	}
}
