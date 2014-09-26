package com.denvys5.uraniumswordmod.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
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
	
	public static void armorRegister(ItemArmor armor, String name){
        LanguageRegistry.instance().addNameForObject(armor, "en_US", name);
        LanguageRegistry.instance().addNameForObject(armor, "ru_RU", "Фу, не знать английский");
	}
}
