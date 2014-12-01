package com.denvys5.uraniumswordmod.core.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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

	public static void UraniumFurnaceRecipes(){
		UraniumFurnaceRecipes.smelting().func_151396_a(USMItems.ingoturanium, new ItemStack(USMItems.ingotinfuseduranium), 1000.0F);
	}

	public static void PoweredGrinderRecipes(){
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.coal_ore, new ItemStack(Items.coal, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.quartz_ore, new ItemStack(Items.quartz, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.emerald_ore, new ItemStack(Items.emerald, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.lapis_ore, new ItemStack(Util.getItemStackFromOreDict("gemLapis").getItem(), 8, Util.getItemStackFromOreDict("gemLapis").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Blocks.diamond_ore, new ItemStack(Items.diamond, 2), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Util.getItemStackFromOreDict("oreCopper"), new ItemStack(Util.getItemStackFromOreDict("dustCopper").getItem(), 2, Util.getItemStackFromOreDict("dustCopper").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Util.getItemStackFromOreDict("oreTin"), new ItemStack(Util.getItemStackFromOreDict("dustTin").getItem(), 2, Util.getItemStackFromOreDict("dustTin").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Util.getItemStackFromOreDict("oreLead"), new ItemStack(Util.getItemStackFromOreDict("dustLead").getItem(), 2, Util.getItemStackFromOreDict("dustLead").getItemDamage()), PowerPerOreGrind);
		PoweredGrinderRecipes.smelting().addGrinderRecipe(Util.getItemStackFromOreDict("oreSilver"), new ItemStack(Util.getItemStackFromOreDict("dustSilver").getItem(), 2, Util.getItemStackFromOreDict("dustSilver").getItemDamage()), PowerPerOreGrind);
	}

	public static void DuplicatorRecipes(){
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Blocks.iron_ore, new ItemStack(Items.iron_ingot, 2), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Blocks.gold_ore, new ItemStack(Items.gold_ingot, 2), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(USMBlocks.oreuranium, new ItemStack(USMItems.ingoturanium, 2), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Util.getItemStackFromOreDict("oreCopper"), new ItemStack(Util.getItemStackFromOreDict("ingotCopper").getItem(), 2, Util.getItemStackFromOreDict("ingotCopper").getItemDamage()), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Util.getItemStackFromOreDict("oreTin"), new ItemStack(Util.getItemStackFromOreDict("ingotTin").getItem(), 2, Util.getItemStackFromOreDict("ingotTin").getItemDamage()), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Util.getItemStackFromOreDict("oreLead"), new ItemStack(Util.getItemStackFromOreDict("ingotLead").getItem(), 2, Util.getItemStackFromOreDict("ingotLead").getItemDamage()), PowerPerOreDuplicate);
		DuplicatorRecipes.smelting().addDuplicatorRecipe(Util.getItemStackFromOreDict("oreSilver"), new ItemStack(Util.getItemStackFromOreDict("ingotSilver").getItem(), 2, Util.getItemStackFromOreDict("ingotSilver").getItemDamage()), PowerPerOreDuplicate);
	}
}
