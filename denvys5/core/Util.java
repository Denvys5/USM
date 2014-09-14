package denvys5.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Util {
	public static void regBlock(Block block,String unlocname ,String name)
	{
		GameRegistry.registerBlock(block, unlocname);
        LanguageRegistry.instance().addNameForObject(block, "en_US", name);
        LanguageRegistry.instance().addNameForObject(block, "ru_RU", "Фу, не знать английский");
	}

	public static void regItem(Item item,String unlocname , String name)
	{
		GameRegistry.registerItem(item, unlocname);
        LanguageRegistry.instance().addNameForObject(item, "en_US", name);
        LanguageRegistry.instance().addNameForObject(item, "ru_RU", "Фу, не знать английский");
	}
	public static void OreItemRegister(Item item, String orename){
		OreDictionary.registerOre(orename, new ItemStack(item));
	}
	public static void OreBlockRegister(Block block, String orename){
		OreDictionary.registerOre(orename, new ItemStack(block));
	}
}
