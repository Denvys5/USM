package com.denvys5.uraniumswordmod.core.recipes;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.core.Util;
import com.denvys5.uraniumswordmod.item.USMItems;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinderRecipes;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.DuplicatorRecipes;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.UraniumFurnaceRecipes;

public class MachineRecipes{
	protected static int PowerPerOreGrind = 500;
	protected static int PowerPerIngotGrind = 250;
	protected static int PowerPerOreDuplicate = 1000;
	
	public static ArrayList<String> GrindingOres = new ArrayList<String>();
	
	public static void SettingOres(){
		GrindingOres.add("oreCopper");
		GrindingOres.add("oreTin");
		GrindingOres.add("oreLead");
		GrindingOres.add("oreSilver");
	}

	public static void UraniumFurnaceRecipes(){
		UraniumFurnaceRecipes.smelting().func_151396_a(USMItems.ingoturanium, new ItemStack(USMItems.ingotinfuseduranium), 1000.0F);
	}

	public static void PoweredGrinderRecipes(){
		for(String name : GrindingOres){
			for(ItemStack ore : OreDictionary.getOres(name)){
				String result = name.replace("ore", "dust");
				PoweredGrinderRecipes.smelting().addGrinderRecipe(ore, new ItemStack(Util.getItemStackFromOreDict(result).getItem(), 2, Util.getItemStackFromOreDict(result).getItemDamage()), PowerPerOreGrind);
			}
		}
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.coal_ore, new ItemStack(Items.coal, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.quartz_ore, new ItemStack(Items.quartz, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.emerald_ore, new ItemStack(Items.emerald, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.lapis_ore, new ItemStack(Util.getItemStackFromOreDict("gemLapis").getItem(), 8, Util.getItemStackFromOreDict("gemLapis").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.diamond_ore, new ItemStack(Items.diamond, 2), PowerPerOreGrind);
	}

	public static void DuplicatorRecipes(){
		for(String name : GrindingOres){
			for(ItemStack ore : OreDictionary.getOres(name)){
				String result = name.replace("ore", "ingot");
				DuplicatorRecipes.smelting().addDuplicatorRecipe(ore, new ItemStack(Util.getItemStackFromOreDict(result).getItem(), 2, Util.getItemStackFromOreDict(result).getItemDamage()), PowerPerOreGrind);
			}
		}
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Blocks.iron_ore, new ItemStack(Items.iron_ingot, 2), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Blocks.gold_ore, new ItemStack(Items.gold_ingot, 2), PowerPerOreDuplicate);

		DuplicatorRecipes.smelting().addDuplicatorRecipe(USMBlocks.oreuranium, new ItemStack(USMItems.ingoturanium, 4), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Util.getItemStackFromOreDict("oreUranium"), new ItemStack(USMItems.ingoturanium, 4), PowerPerOreDuplicate);
	}
}
