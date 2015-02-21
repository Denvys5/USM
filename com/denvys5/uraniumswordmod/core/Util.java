package com.denvys5.uraniumswordmod.core;

import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.item.USMItems;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Util{
	private static String russianLocalisation = "Санкции!";

	public static void regBlock(Block block, String blockName, String name){
		GameRegistry.registerBlock(block, blockName);
		LanguageRegistry.instance().addNameForObject(block, "en_US", name);
		LanguageRegistry.instance().addNameForObject(block, "ru_RU", russianLocalisation);
		System.out.println("Registering " + name);
		USMBlocks.AllBlocks.add(block);
	}

	public static void regItem(Item item, String unlocalizedName, String name){
		GameRegistry.registerItem(item, unlocalizedName);
		LanguageRegistry.instance().addNameForObject(item, "en_US", name);
		LanguageRegistry.instance().addNameForObject(item, "ru_RU", russianLocalisation);
		System.out.println("Registering " + name);
		USMItems.AllItems.add(item);
	}

	public static void regTileEntity(Block tileEntityIdle, Block tileEntityActive, String name, String nameWithoutSpaces){
		Util.regBlock(tileEntityIdle, nameWithoutSpaces + "idle", name);
		Util.regBlock(tileEntityActive, nameWithoutSpaces + "active", name + " Active");
	}

	public static void OreRegister(Item item, String orename){
		OreDictionary.registerOre(orename, new ItemStack(item));
	}

	public static void OreRegister(Block block, String orename){
		OreDictionary.registerOre(orename, new ItemStack(block));
	}
	
	public static void OreRegister(ItemStack itemstack, String orename){
		OreDictionary.registerOre(orename, itemstack);
	}

	public static Vec3 getEntityBlockVector(Entity entity){
		int posX = (int)Math.round(entity.posX - 0.5f);
		int posY = (int)entity.posY;
		int posZ = (int)Math.round(entity.posZ - 0.5f);

		return entity.getLookVec().createVectorHelper(posX, posY, posZ);
	}

	public static boolean isFullArmorSetEquiped(EntityPlayer player){
		ItemStack helmet = player.getEquipmentInSlot(4);
		ItemStack plate = player.getEquipmentInSlot(3);
		ItemStack leggings = player.getEquipmentInSlot(2);
		ItemStack boots = player.getEquipmentInSlot(1);
		if(!(helmet == null || plate == null || leggings == null || boots == null)){
			if((helmet.getItem()).equals(USMItems.UraniumHelmet) && (plate.getItem()).equals(USMItems.UraniumChest) && (leggings.getItem()).equals(USMItems.UraniumLeggins) && (boots.getItem()).equals(USMItems.UraniumBoots)){
				return true;
			}
		}
		
		return false;
	}
	
	public static ItemStack getItemStackFromOreDict(String oreDictName){
		for(ItemStack ore : OreDictionary.getOres(oreDictName)){
			if(ore != null){
				if(ore.getItemDamage() != OreDictionary.WILDCARD_VALUE){
					return ore;
				}
			}
		}
		return null;
	}
}
