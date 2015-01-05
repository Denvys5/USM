package com.denvys5.uraniumswordmod.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.denvys5.uraniumswordmod.USM;

public class OreMetal extends USMBlock{

	private IIcon[] icons;
	private IIcon Copper;
	private IIcon Tin;
	private IIcon Lead;
	private IIcon Silver;

	public OreMetal(String unlocalizedName){
		super();
		this.setBlockName(unlocalizedName);
		this.setHardness(4.0F);
		icons = new IIcon[4];
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg){
		this.Copper = reg.registerIcon(USM.modid + ":ore" + "Copper");
		this.Tin = reg.registerIcon(USM.modid + ":ore" + "Tin");
		this.Lead = reg.registerIcon(USM.modid + ":ore" + "Lead");
		this.Silver = reg.registerIcon(USM.modid + ":ore" + "Silver");

		icons[0] = reg.registerIcon(USM.modid + ":ore" + "Copper");
		icons[1] = reg.registerIcon(USM.modid + ":ore" + "Tin");
		icons[2] = reg.registerIcon(USM.modid + ":ore" + "Lead");
		icons[3] = reg.registerIcon(USM.modid + ":ore" + "Silver");
	}

	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		for(int i = 0; i < 4; i++){
			list.add(new ItemStack(item, 1, i));
		}
	}

	public IIcon getIcon(int side, int meta){
		if(meta > 3) meta = 0;
		/*
		 * switch(meta){ case 0: return Copper; case 1: return Tin; case 2:
		 * return Lead; case 3: return Silver; default: return null; }
		 */
		return icons[meta];
	}

	public int damageDropped(int meta){
		return meta;
	}

	public static class ItemMetaOre extends ItemBlockWithMetadata{
		public ItemMetaOre(Block block){
			super(block, block);
		}

		@Override
		public int getMetadata(int meta){
			return meta;
		}

		public String getUnlocalizedName(ItemStack stack){
			switch(stack.getItemDamage()){
				case 0 :
					return this.getUnlocalizedName() + "Copper";
				case 1 :
					return this.getUnlocalizedName() + "Tin";
				case 2 :
					return this.getUnlocalizedName() + "Lead";
				case 3 :
					return this.getUnlocalizedName() + "Silver";
				default :
					return this.getUnlocalizedName();
			}
		}
	}
}
