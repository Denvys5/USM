package com.denvys5.uraniumswordmod.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMetal extends USMBlock{

	private IIcon Copper;
	private IIcon Tin;
	private IIcon Lead;
	private IIcon Silver;
	
	public BlockMetal(String unlocalizedName){
		super();
		this.setBlockName(unlocalizedName);
		this.setHardness(8.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.Copper = reg.registerIcon(USM.modid + ":block" + "Copper");
		this.Tin = reg.registerIcon(USM.modid + ":block" + "Tin");
		this.Lead = reg.registerIcon(USM.modid + ":block" + "Lead");
		this.Silver = reg.registerIcon(USM.modid + ":block" + "Silver");
	}
	
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 4; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	public IIcon getIcon(int side, int meta) {
	    if (meta > 3) meta = 0;
	    switch(meta){
	    	case 0: return Copper;
	    	case 1: return Tin;
	    	case 2: return Lead;
	    	case 3: return Silver;
	    	default: return null;
	    }
	}
	
	public int damageDropped(int meta) {
	    return meta;
	}
	
	public static class ItemMetaOre extends ItemBlockWithMetadata{
	    public ItemMetaOre(Block block) {
	        super(block, block);
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
}
