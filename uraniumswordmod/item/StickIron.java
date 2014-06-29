package assets.uraniumswordmod.item;

import assets.uraniumswordmod.USM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class StickIron extends Item {
	public StickIron(int par1) {
		super(par1);
		this.setCreativeTab(USM.USMTab);
		
		
		
	}
	
	
	@Override
	public void registerIcons(IconRegister reg){
	this.itemIcon = reg.registerIcon(USM.modid +":StickIron");
	}
}
