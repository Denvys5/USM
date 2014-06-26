package assets.uraniumswordmod.item;

import assets.uraniumswordmod.USM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class IngotInfusedUranium extends Item {
	public IngotInfusedUranium(int par1) {
		super(par1);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		
		
		
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
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
	@Override
	public void registerIcons(IconRegister reg){
	this.itemIcon = reg.registerIcon(USM.modid +":IngotUranium");
	}
}
