package com.denvys5.uraniumswordmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.api.USMItem;

public class IngotInfusedUranium extends USMItem{
	public IngotInfusedUranium(){
		super();
	}

	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)

	{
		par3EntityLivingBase.addPotionEffect(new PotionEffect(5, 200, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(17, 600, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(15, 600, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(18, 20, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(20, 600, 1));
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack){
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg){
		this.itemIcon = reg.registerIcon(USM.modid + ":IngotUranium");
	}
}
