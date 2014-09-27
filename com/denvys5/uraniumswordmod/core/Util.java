package com.denvys5.uraniumswordmod.core;

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

public class Util {
	public static void regBlock(Block block, String blockName,String name){
		GameRegistry.registerBlock(block, blockName);
        LanguageRegistry.instance().addNameForObject(block, "en_US", name);
        LanguageRegistry.instance().addNameForObject(block, "ru_RU", "Фу, не знать английский");
	}
	
	public static void regItem(Item item, String unlocalizedName , String name){
		GameRegistry.registerItem(item, unlocalizedName);
        LanguageRegistry.instance().addNameForObject(item, "en_US", name);
        LanguageRegistry.instance().addNameForObject(item, "ru_RU", "Фу, не знать английский");
	}
	
	public static void OreItemRegister(Item item, String orename){
		OreDictionary.registerOre(orename, new ItemStack(item));
	}
	
	public static void OreBlockRegister(Block block, String orename){
		OreDictionary.registerOre(orename, new ItemStack(block));
	}
	
	public static void armorRegister(Item item, String unlocalizedName, String name){
		GameRegistry.registerItem(item, unlocalizedName);
        LanguageRegistry.instance().addNameForObject(item, "en_US", name);
        LanguageRegistry.instance().addNameForObject(item, "ru_RU", "Фу, не знать английский");
	}
	
	public static Vec3 getEntityBlockVector(Entity entity)
	{
		int posX = (int) Math.round(entity.posX - 0.5f);
        int posY = (int) entity.posY;
        int posZ = (int) Math.round(entity.posZ - 0.5f);
        
		return entity.getLookVec().createVectorHelper(posX, posY, posZ);
	}
	
	public boolean isFullArmorSetEquiped(EntityPlayer player){
        ItemStack helmet = player.getEquipmentInSlot(4);
        ItemStack plate = player.getEquipmentInSlot(3);
        ItemStack leggings = player.getEquipmentInSlot(2);
        ItemStack boots = player.getEquipmentInSlot(1);
		if(helmet.getItem().equals(BlockList.UraniumHelmet) && plate.getItem().equals(BlockList.UraniumChest) && leggings.getItem().equals(BlockList.UraniumLeggins) && boots.getItem().equals(BlockList.UraniumBoots)){
			return true;
		}
		return false;
	}
	
}
