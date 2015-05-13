package com.denvys5.uraniumswordmod.core.recipes;

import ic2.api.item.IC2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.core.Util;
import com.denvys5.uraniumswordmod.item.USMItems;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinderRecipes;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.DuplicatorRecipes;

public class IC2Recipes{
	private static int PowerPerOreGrind = MachineRecipes.PowerPerOreGrind;
	private static int PowerPerIngotGrind = MachineRecipes.PowerPerIngotGrind;
	private static int PowerPerOreDuplicate = MachineRecipes.PowerPerOreDuplicate;

	public static void PoweredGrinderRecipes(){
		PoweredGrinderRecipes.smelting().addMachineRecipe(Blocks.iron_ore, new ItemStack(IC2Items.getItem("ironDust").getItem(), 2, IC2Items.getItem("ironDust").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(Blocks.gold_ore, new ItemStack(IC2Items.getItem("goldDust").getItem(), 2, IC2Items.getItem("goldDust").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(Items.coal, IC2Items.getItem("coalDust"), PowerPerIngotGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(Util.getItemStackFromOreDict("gemLapis"), IC2Items.getItem("lapisDust"), PowerPerIngotGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(Items.iron_ingot, IC2Items.getItem("ironDust"), PowerPerIngotGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(Items.gold_ingot, IC2Items.getItem("goldDust"), PowerPerIngotGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(USMBlocks.oreuranium,  new ItemStack(IC2Items.getItem("crushedUraniumOre").getItem(), 2, IC2Items.getItem("crushedUraniumOre").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addMachineRecipe(IC2Items.getItem("uraniumOre"), new ItemStack(IC2Items.getItem("crushedUraniumOre").getItem(), 2, IC2Items.getItem("crushedUraniumOre").getItemDamage()), PowerPerOreGrind);
	}

	public static void DuplicatorRecipes(){
		DuplicatorRecipes.smelting().addMachineRecipe(IC2Items.getItem("uraniumOre"), new ItemStack(USMItems.ingoturanium, 4), PowerPerOreGrind);
	}

}
