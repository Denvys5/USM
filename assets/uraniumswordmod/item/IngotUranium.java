package assets.uraniumswordmod.item;

import assets.uraniumswordmod.USM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IngotUranium extends Item {
	public IngotUranium(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setCreativeTab(USM.USMTab);

	}
	/*@SideOnly(Side.CLIENT)
	*/private Icon[] icons;

	public void registerIcons(IconRegister reg) {
	/*	icons = new Icon[4];
		for(int i = 0; i < icons.length; i++){
			icons[i] = reg.registerIcon(USM.modid + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	*/	this.itemIcon = reg.registerIcon(USM.modid + ":IngotUranium");
	}
	/*public static final String[] names = new String[] {"first", "second"};
	public String getUnlocalizedName(ItemStack itemstack){
		int i = MathHelper.clamp_int(itemstack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + "." + names[i];
	}
	public Icon getIconFromDamage(int par1){
		return icons[par1];
	}
*/}
