package assets.uraniumswordmod;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class SwordUranium extends ItemSword {
	public SwordUranium(int par1) {
		super(par1, USM.Uranium);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
//	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
//
//	{
//		par3EntityLivingBase.addPotionEffect(new PotionEffect(5, 100, 1));
//		return false;	
//	}
	
	@Override
	public void registerIcons(IconRegister reg){
	this.itemIcon = reg.registerIcon("uraniumswordmod:SwordUranium");
	}
}
