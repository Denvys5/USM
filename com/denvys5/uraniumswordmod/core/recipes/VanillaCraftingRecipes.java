package com.denvys5.uraniumswordmod.core.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.denvys5.uraniumswordmod.core.BlockList;

import cpw.mods.fml.common.registry.GameRegistry;

public class VanillaCraftingRecipes{
	public static void ShapedOreCrafting(){
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumWrench, true, new Object[]{"X!X", "@@@", "!@!", Character.valueOf('X'), "ingotUranium", ('@'), Items.iron_ingot}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.BasicMachineCore, true, new Object[]{"XXX", "@#@", "X!X", Character.valueOf('X'), Items.iron_ingot, ('@'), Items.gold_ingot, ('#'), Items.redstone, ('!'), BlockList.ingoturanium}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.DisassemblerCore, true, new Object[]{"X!X", "@#@", "$$$", Character.valueOf('X'), Items.flint, ('@'), Items.iron_ingot, ('#'), BlockList.BasicMachineCore, ('!'), Items.iron_pickaxe, ('$'), Blocks.stonebrick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.PoweredGrinderidle, true, new Object[]{"X!X", "X#X", "$@$", Character.valueOf('X'), Items.iron_ingot, ('@'), Blocks.glowstone, ('#'), BlockList.blockuranium, ('!'), BlockList.DisassemblerCore, ('$'), Items.redstone}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumHelmet, true, new Object[]{"XXX", "X@X", Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'), BlockList.blocknetherstar}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumChest, true, new Object[]{"X#X", "X@X", "XXX", Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'), BlockList.blocknetherstar}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumLeggins, true, new Object[]{"X@X", "X#X", "X#X", Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'), BlockList.blocknetherstar}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumBoots, true, new Object[]{"X#X", "X@X", Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'), BlockList.blocknetherstar}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.sworduranium, true, new Object[]{"1X2", "3X4", "5S6", Character.valueOf('X'), BlockList.blockinfuseduranium, ('S'), "stickIron", ('5'), new ItemStack(Items.potionitem, 1, 8233), ('6'), new ItemStack(Items.potionitem, 1, 8236)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.pickuranium, true, new Object[]{"X@X", "3S4", "5S6", Character.valueOf('X'), BlockList.blockinfuseduranium, ('S'), "stickIron", ('@'), BlockList.blocknetherstar, ('3'), new ItemStack(Items.potionitem, 1, 8233), ('4'), new ItemStack(Items.potionitem, 1, 8236)}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.stickiron, true, new Object[]{"@", "@", Character.valueOf('@'), Items.iron_ingot}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.oreuranium, true, new Object[]{"@", Character.valueOf('@'), "oreUranium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.ingoturanium, true, new Object[]{"@", Character.valueOf('@'), "crushedUranium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.BasicBattery, true, new Object[]{"!@!", "#$#", "#$#", Character.valueOf('@'), Items.redstone, ('$'), "ingotUranium", ('#'), Items.iron_ingot}));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockList.blockuranium, 4), new Object[]{"@@", "@@", Character.valueOf('@'), "blockUranium"}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.blocknetherstar, true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), Items.nether_star}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.blockuranium, true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), BlockList.ingoturanium}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.blockinfuseduranium, true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), BlockList.ingotinfuseduranium}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.furnaceuraniumidle, true, new Object[]{"@@@", "@X@", "!!!", Character.valueOf('@'), Blocks.iron_block, ('X'), Items.nether_star, ('!'), Blocks.coal_block}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.furnaceuraniumidle, true, new Object[]{"@@@", "@X@", "!!!", Character.valueOf('@'), Blocks.iron_block, ('X'), Blocks.dragon_egg, ('!'), Blocks.coal_block}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.uraniumonstick, true, new Object[]{"@00", "0X0", "00@", Character.valueOf('@'), BlockList.ingotinfuseduranium, Character.valueOf('X'), Items.stick}));
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.Nuke, true, new Object[]{"@@@", "0X0", "!!!", Character.valueOf('@'), Items.iron_ingot, ('X'), BlockList.blockuranium, ('0'), Items.nether_star, ('!'), Blocks.tnt}));
	}
	public static void ShapelessCrafting(){
		GameRegistry.addShapelessRecipe(new ItemStack(BlockList.ingoturanium, 9), new Object[]{BlockList.blockuranium});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.nether_star, 9), new Object[]{BlockList.blocknetherstar});
	}
	public static void VanillaSmeltingRecipes(){
		GameRegistry.addSmelting(BlockList.oreuranium, new ItemStack(BlockList.ingoturanium, 1), 20.0F);
	}
}
