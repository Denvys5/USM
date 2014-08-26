package assets.uraniumswordmod.item;

import assets.uraniumswordmod.USM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class SwordUranium extends ItemSword {
	public SwordUranium(int par1) {
		super(par1, USM.UraniumSword);
		this.setCreativeTab(USM.USMTab);
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack,
			EntityLivingBase par2EntityLivingBase,
			EntityLivingBase par3EntityLivingBase) {
		par1ItemStack.damageItem(1, par3EntityLivingBase);
		par3EntityLivingBase.addPotionEffect(new PotionEffect(5, 200, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(17, 600, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(15, 600, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(18, 20, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(20, 600, 1));
		par2EntityLivingBase.addPotionEffect(new PotionEffect(USM.RadiationUSM.id, 20, 0));
		return true;
	}

	@Override
	public void registerIcons(IconRegister reg) {
		this.itemIcon = reg.registerIcon(USM.modid + ":SwordUranium");
	}
}
