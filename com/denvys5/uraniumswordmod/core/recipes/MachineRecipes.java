package com.denvys5.uraniumswordmod.core.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.core.Util;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinderRecipes;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.DuplicatorRecipes;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.UraniumFurnaceRecipes;

public class MachineRecipes{
	protected static int PowerPerOreGrind = 500;
	protected static int PowerPerIngotGrind = 250;
	protected static int PowerPerOreDuplicate = 1000;

	public static void UraniumFurnaceRecipes(){
		UraniumFurnaceRecipes.smelting().func_151396_a(BlockList.ingoturanium, new ItemStack(BlockList.ingotinfuseduranium), 1000.0F);
	}

	public static void PoweredGrinderRecipes(){
		PoweredGrinderRecipes.smelting().addGrinderRecipe(BlockList.oreuranium, new ItemStack(BlockList.ingoturanium, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.coal_ore, new ItemStack(Items.coal, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.quartz_ore, new ItemStack(Items.quartz, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.emerald_ore, new ItemStack(Items.emerald, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.lapis_ore, new ItemStack(Util.getItemFromOreDict("gemLapis"), 8, 4), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.diamond_ore, new ItemStack(Items.diamond, 2), PowerPerOreGrind);
		IC2Recipes.PoweredGrinderRecipes();
	}

	public static void DuplicatorRecipes(){
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Blocks.iron_ore, new ItemStack(Items.iron_ingot, 2), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Blocks.gold_ore, new ItemStack(Items.gold_ingot, 2), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(BlockList.oreuranium, new ItemStack(BlockList.ingoturanium, 2), PowerPerOreDuplicate);
		IC2Recipes.DuplicatorRecipes();
	}
}
