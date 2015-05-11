package com.denvys5.uraniumswordmod.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.api.USMItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class IngotMetal extends USMItem{
	private IIcon Copper;
	private IIcon Tin;
	private IIcon Lead;
	private IIcon Silver;
	
	public IngotMetal(String unlocalizedName){
		super();
		this.hasSubtypes = true;
		this.setUnlocalizedName(unlocalizedName);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg){
		this.Copper = reg.registerIcon(USM.modid + ":ingot" + "Copper");
		this.Tin = reg.registerIcon(USM.modid + ":ingot" + "Tin");
		this.Lead = reg.registerIcon(USM.modid + ":ingot" + "Lead");
		this.Silver = reg.registerIcon(USM.modid + ":ingot" + "Silver");
	}
	
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 4; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	public IIcon getIconFromDamage(int meta) {
		IIcon a = null;
	    if (meta > 5) meta = 0;
	    switch(meta){
	    	case 0: a = Copper; break;
	    	case 1: a = Tin; break;
	    	case 2: a = Lead; break;
	    	case 3: a = Silver; break;
	    	default: break;
	    }
	    return a;
	}
	
	public String getUnlocalizedName(ItemStack stack) {
	    switch (stack.getItemDamage()) {
	    case 0:
	        return this.getUnlocalizedName() + "Copper";
	    case 1:
	        return this.getUnlocalizedName() + "Tin";
	    case 2:
	        return this.getUnlocalizedName() + "Lead";
	    case 3:
	        return this.getUnlocalizedName() + "Silver";
	    default:
	        return this.getUnlocalizedName();
	    }
	}
}
