package com.denvys5.uraniumswordmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import com.denvys5.uraniumswordmod.core.USM;

public class SwordUranium extends ItemSword {
	public SwordUranium() {
		super(USM.UraniumSword);
		this.setCreativeTab(USM.USMTab);
	}

	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLivingBase,
			EntityLivingBase par3EntityLivingBase) {
		//par1ItemStack.damageItem(1, par3EntityLivingBase);
		par2EntityLivingBase.addPotionEffect(new PotionEffect(17, 600, 0));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(15, 600, 0));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(18, 20, 0));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(20, 600, 0));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(USM.RadiationUSM.id, 2, 0));
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(USM.modid + ":SwordUranium");
	}
}
