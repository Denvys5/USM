package com.denvys5.uraniumswordmod.core;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.recipes.VanillaCraftingRecipes;
import com.denvys5.uraniumswordmod.oregenerators.USMOreGenerator;

public class Config{
	public static boolean FullCheatyArmor;
	public static boolean ArmorCrashFix;
	public static boolean NewWIPPickMechanicks;
	private static Configuration config;
	public static boolean renderDrawInside;

	public static void ConfigMethod(File file){
		try{
			file = new File(file.getCanonicalPath().replace(USM.modid, "USM\\UraniumSwordMod"));
		}catch(IOException e){
			e.printStackTrace();
		}
		config = new Configuration(file);
		config.load();
		ConfigItemsAndBlocksCrafting();
		ConfigTweakRegister();
		ConfigGraphics();
		ConfigOreGeneration();
		config.save();
	}
	
	private static void ConfigItemsAndBlocksCrafting(){
		VanillaCraftingRecipes.UraniumWrench = config.getBoolean("Uranium wrench craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Wrench");
		VanillaCraftingRecipes.Duplicator = config.getBoolean("Duplicator craftable", "Items and Blocks crafting", true, "Ability to craft Duplicator");
		VanillaCraftingRecipes.BasicMachineCore = config.getBoolean("Basic Machine Core craftable", "Items and Blocks crafting", true, "Ability to craft Basic Machine Core");
		VanillaCraftingRecipes.DisassemblerCore = config.getBoolean("Disassembler Core craftable", "Items and Blocks crafting", true, "Ability to craft Disassembler Core");
		VanillaCraftingRecipes.PoweredGrinder = config.getBoolean("Powered Grinder craftable", "Items and Blocks crafting", true, "Ability to craft Powered Grinder");
		VanillaCraftingRecipes.UraniumArmor = config.getBoolean("Uranium Armor craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Armor");
		VanillaCraftingRecipes.UraniumSword = config.getBoolean("Uranium Sword craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Sword");
		VanillaCraftingRecipes.UraniumPick = config.getBoolean("Uranium Pick craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Pick");
		VanillaCraftingRecipes.IronStick = config.getBoolean("Iron Stick craftable", "Items and Blocks crafting", true, "Ability to craft Iron Stick");
		VanillaCraftingRecipes.UraniumOre = config.getBoolean("Uranium Ore craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Ore from other mod`s uranium ore");
		VanillaCraftingRecipes.IronIngot = config.getBoolean("Iron Ingot craftable", "Items and Blocks crafting", true, "Ability to craft Iron Ingot");
		VanillaCraftingRecipes.BasicBattery = config.getBoolean("Basic Battery craftable", "Items and Blocks crafting", true, "Ability to craft Basic Battery");
		VanillaCraftingRecipes.UraniumBlock = config.getBoolean("Uranium Block craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Block");
		VanillaCraftingRecipes.NetherstarBlock = config.getBoolean("Netherstar Block craftable", "Items and Blocks crafting", true, "Ability to craft Nether Star Block");
		VanillaCraftingRecipes.BlockMetal = config.getBoolean("Block Metal craftable", "Items and Blocks crafting", true, "Ability to craft Metal Blocks");
		VanillaCraftingRecipes.InfusedUraniumBlock = config.getBoolean("Infused Uranium Block craftable", "Items and Blocks crafting", true, "Ability to craft Infused Uranium Block");
		VanillaCraftingRecipes.UraniumFurnace = config.getBoolean("Uranium Furnace craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Furnace");
		VanillaCraftingRecipes.UraniumOnStick = config.getBoolean("Uranium On Stick craftable", "Items and Blocks crafting", true, "Ability to craft Uranium On Stick");
		VanillaCraftingRecipes.SafeNuke = config.getBoolean("Safe Nuke craftable", "Items and Blocks crafting", true, "Ability to craft Safe Nuke");
		VanillaCraftingRecipes.Nuke = config.getBoolean("Nuke craftable", "Items and Blocks crafting", false, "Ability to craft usual Nuke");
		VanillaCraftingRecipes.UraniumIngot = config.getBoolean("Uranium Ingot craftable", "Items and Blocks crafting", true, "Ability to craft Uranium Ingot");
		VanillaCraftingRecipes.NetherStar = config.getBoolean("Nether Star craftable", "Items and Blocks crafting", true, "Ability to craft Nether Star from block");
		VanillaCraftingRecipes.IngotMetal = config.getBoolean("Ingot Metal craftable", "Items and Blocks crafting", true, "Ability to craft Metal Ingots");
	}
	
	private static void ConfigGraphics(){
		renderDrawInside = config.getBoolean("renderDrawInside", "Graphic FX", true, "Render all custom rendered blocks inside too.");
	}
	
	private static void ConfigOreGeneration(){
		USMOreGenerator.oreUraniumGeneration = config.getBoolean("Uranium Generation", "Ore Generation", true, "Generate uranium ore, or not.");
		USMOreGenerator.oreCopperGeneration = config.getBoolean("Copper Generation", "Ore Generation", true, "Generate copper ore, or not.");
		USMOreGenerator.oreTinGeneration = config.getBoolean("Tin Generation", "Ore Generation", true, "Generate tin ore, or not.");
		USMOreGenerator.oreSilverGeneration = config.getBoolean("Silver Generation", "Ore Generation", true, "Generate silver ore, or not.");
		USMOreGenerator.oreLeadGeneration = config.getBoolean("Lead Generation", "Ore Generation", true, "Generate lead ore, or not.");
		
		USMOreGenerator.oreUraniumRarity = config.getInt("Uranium ore rarity", "Ore Generation", 30, 0, Integer.MAX_VALUE, "Increase for more uranium ore to generate.");
		USMOreGenerator.oreCopperRarity = config.getInt("Copper ore rarity", "Ore Generation", 90, 0, Integer.MAX_VALUE, "Increase for more copper ore to generate.");
		USMOreGenerator.oreTinRarity = config.getInt("Tin ore rarity", "Ore Generation", 85, 0, Integer.MAX_VALUE, "Increase for more tin ore to generate.");
		USMOreGenerator.oreSilverRarity = config.getInt("Silver ore rarity", "Ore Generation", 60, 0, Integer.MAX_VALUE, "Increase for more silver ore to generate.");
		USMOreGenerator.oreLeadRarity = config.getInt("Lead ore rarity", "Ore Generation", 60, 0, Integer.MAX_VALUE, "Increase for more lead ore to generate.");
	}

	private static void ConfigTweakRegister(){
		OreRegistration.OreUraniumRegister = config.getBoolean("UraniumOre_OreRegistration", "Tweaks", true, "Ore Register Uranium Ore");
		OreRegistration.IngotUraniumRegister = config.getBoolean("UraniumIngot_OreRegistration", "Tweaks", true, "Ore Register Uranium Ingot");
		OreRegistration.IronStickRegister = config.getBoolean("IronStick_OreRegistration", "Tweaks", true, "Ore Register Iron Stick");
		FullCheatyArmor = config.getBoolean("NearCreativeArmor", "Tweaks", true, "If Uranium Armor weared, you can`t recieve damage.");
		ArmorCrashFix = config.getBoolean("ArmorCrashFix", "Tweaks", false, "If you get crash of wearing Uranium Armor, set to true.");
		NewWIPPickMechanicks = config.getBoolean("NewWIPPickMechanicks", "Tweaks", false, "Activate new work in progress UranimPickaxe mechanicks");
	}
}
