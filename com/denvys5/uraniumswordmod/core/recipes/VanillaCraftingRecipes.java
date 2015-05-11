package com.denvys5.uraniumswordmod.core.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.item.USMItems;
import com.denvys5.uraniumswordmod.machines.USMTiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class VanillaCraftingRecipes{
	
	public static boolean UraniumWrench;
	public static boolean Duplicator;
	public static boolean BasicMachineCore;
	public static boolean DisassemblerCore;
	public static boolean PoweredGrinder;
	public static boolean UraniumArmor;
	public static boolean UraniumSword;
	public static boolean UraniumPick;
	public static boolean IronStick;
	public static boolean UraniumOre;
	public static boolean IronIngot;
	public static boolean BasicBattery;
	public static boolean UraniumBlock;
	public static boolean NetherstarBlock;
	public static boolean BlockMetal;
	public static boolean InfusedUraniumBlock;
	public static boolean UraniumFurnace;
	public static boolean UraniumOnStick;
	public static boolean SafeNuke;
	public static boolean Nuke;
	public static boolean UraniumIngot;
	public static boolean NetherStar;
	public static boolean IngotMetal;


	
	public static void ShapedOreCrafting(){
		if(UraniumWrench) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.UraniumWrench, true, new Object[]{"X!X", "@@@", "!@!", Character.valueOf('X'), "ingotUranium", ('@'), Items.iron_ingot}));
		if(Duplicator) GameRegistry.addRecipe(new ShapedOreRecipe(USMTiles.duplicatoridle, true, new Object[]{"X!X", "V@V", "G#G", Character.valueOf('V'), Items.bucket, ('@'), Blocks.furnace, ('X'), Items.iron_ingot, ('G'), Items.gold_ingot, ('#'), USMTiles.DisassemblerCore, ('!'), USMTiles.BasicMachineCore}));
		if(BasicMachineCore) GameRegistry.addRecipe(new ShapedOreRecipe(USMTiles.BasicMachineCore, true, new Object[]{"XXX", "@#@", "X!X", Character.valueOf('X'), Items.iron_ingot, ('@'), Items.gold_ingot, ('#'), Items.redstone, ('!'), "ingotUranium"}));
		if(DisassemblerCore) GameRegistry.addRecipe(new ShapedOreRecipe(USMTiles.DisassemblerCore, true, new Object[]{"X!X", "@#@", "$$$", Character.valueOf('X'), Items.flint, ('@'), Items.iron_ingot, ('#'), USMTiles.BasicMachineCore, ('!'), Items.iron_pickaxe, ('$'), Blocks.stonebrick}));
		if(PoweredGrinder) GameRegistry.addRecipe(new ShapedOreRecipe(USMTiles.PoweredGrinderidle, true, new Object[]{"X!X", "X#X", "$@$", Character.valueOf('X'), Items.iron_ingot, ('@'), Items.gold_ingot, ('#'), "ingotUranium", ('!'), USMTiles.DisassemblerCore, ('$'), Items.redstone}));
		if(UraniumArmor){ 
			GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.UraniumChest, true, new Object[]{"X#X", "X@X", "XXX", Character.valueOf('X'), USMBlocks.blockinfuseduranium, ('@'), USMBlocks.blocknetherstar}));
			GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.UraniumLeggins, true, new Object[]{"X@X", "X#X", "X#X", Character.valueOf('X'), USMBlocks.blockinfuseduranium, ('@'), USMBlocks.blocknetherstar}));
			GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.UraniumHelmet, true, new Object[]{"XXX", "X@X", Character.valueOf('X'), USMBlocks.blockinfuseduranium, ('@'), USMBlocks.blocknetherstar}));
			GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.UraniumBoots, true, new Object[]{"X#X", "X@X", Character.valueOf('X'), USMBlocks.blockinfuseduranium, ('@'), USMBlocks.blocknetherstar}));
		}
		if(UraniumSword) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.sworduranium, true, new Object[]{"1X2", "3X4", "5S6", Character.valueOf('X'), USMBlocks.blockinfuseduranium, ('S'), "stickIron", ('5'), new ItemStack(Items.potionitem, 1, 8233), ('6'), new ItemStack(Items.potionitem, 1, 8236)}));
		if(UraniumPick) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.pickuranium, true, new Object[]{"X@X", "3S4", "5S6", Character.valueOf('X'), USMBlocks.blockinfuseduranium, ('S'), "stickIron", ('@'), USMBlocks.blocknetherstar, ('3'), new ItemStack(Items.potionitem, 1, 8233), ('4'), new ItemStack(Items.potionitem, 1, 8236)}));
		if(IronStick) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.stickiron, true, new Object[]{"@", "@", Character.valueOf('@'), Items.iron_ingot}));
		if(UraniumOre) GameRegistry.addRecipe(new ShapedOreRecipe(USMBlocks.oreuranium, true, new Object[]{"@", Character.valueOf('@'), "oreUranium"}));
		if(IronIngot) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.ingoturanium, true, new Object[]{"@", Character.valueOf('@'), "crushedUranium"}));
		if(BasicBattery) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.BasicBattery, true, new Object[]{"!@!", "#$#", "#$#", Character.valueOf('@'), Items.redstone, ('$'), "ingotUranium", ('#'), Items.iron_ingot}));
		if(UraniumBlock) GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(USMBlocks.blockuranium, 4), new Object[]{"@@", "@@", Character.valueOf('@'), "blockUranium"}));
		if(NetherstarBlock) GameRegistry.addRecipe(new ShapedOreRecipe(USMBlocks.blocknetherstar, true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), Items.nether_star}));
		if(UraniumBlock) GameRegistry.addRecipe(new ShapedOreRecipe(USMBlocks.blockuranium, true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), USMItems.ingoturanium}));
		if(BlockMetal) for(int i = 0; i < 4; i++){
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(USMBlocks.blockMetal, 1, i), true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), new ItemStack(USMItems.IngotMetal, 1, i)}));
		}
		if(InfusedUraniumBlock) GameRegistry.addRecipe(new ShapedOreRecipe(USMBlocks.blockinfuseduranium, true, new Object[]{"@@@", "@@@", "@@@", Character.valueOf('@'), USMItems.ingotinfuseduranium}));
		if(UraniumFurnace) GameRegistry.addRecipe(new ShapedOreRecipe(USMTiles.furnaceuraniumidle, true, new Object[]{"@@@", "@X@", "!!!", Character.valueOf('@'), Blocks.iron_block, ('X'), Items.nether_star, ('!'), Blocks.coal_block}));
		if(UraniumFurnace) GameRegistry.addRecipe(new ShapedOreRecipe(USMTiles.furnaceuraniumidle, true, new Object[]{"@@@", "@X@", "!!!", Character.valueOf('@'), Blocks.iron_block, ('X'), Blocks.dragon_egg, ('!'), Blocks.coal_block}));
		if(UraniumOnStick) GameRegistry.addRecipe(new ShapedOreRecipe(USMItems.uraniumonstick, true, new Object[]{"@00", "0X0", "00@", Character.valueOf('@'), USMItems.ingotinfuseduranium, Character.valueOf('X'), Items.stick}));
		if(SafeNuke) GameRegistry.addRecipe(new ShapedOreRecipe(USMBlocks.SafeNuke, true, new Object[]{"@@@", "0X0", "!!!", Character.valueOf('@'), Items.iron_ingot, ('X'), USMBlocks.blockuranium, ('0'), USMItems.ingotinfuseduranium, ('!'), Blocks.tnt}));
		if(Nuke) GameRegistry.addRecipe(new ShapedOreRecipe(USMBlocks.Nuke, true, new Object[]{"@@@", "0X0", "!!!", Character.valueOf('@'), Items.iron_ingot, ('X'), USMBlocks.blockuranium, ('0'), Items.nether_star, ('!'), Blocks.tnt}));
	}
	public static void ShapelessCrafting(){
		if(UraniumIngot) GameRegistry.addShapelessRecipe(new ItemStack(USMItems.ingoturanium, 9), new Object[]{USMBlocks.blockuranium});
		if(NetherStar) GameRegistry.addShapelessRecipe(new ItemStack(Items.nether_star, 9), new Object[]{USMBlocks.blocknetherstar});
		if(IngotMetal) for(int i = 0; i < 4; i++){
			GameRegistry.addShapelessRecipe(new ItemStack(USMItems.IngotMetal, 9, i), new Object[]{new ItemStack(USMBlocks.blockMetal, 1, i)});
		}
	}
	public static void VanillaSmeltingRecipes(){
		GameRegistry.addSmelting(USMBlocks.oreuranium, new ItemStack(USMItems.ingoturanium, 1), 20.0F);
		for(int i = 0; i < 4; i++){
			GameRegistry.addSmelting(new ItemStack(USMBlocks.oreMetal, 1, i), new ItemStack(USMItems.IngotMetal, 1, i), 20.0F);
			GameRegistry.addSmelting(new ItemStack(USMItems.DustMetal, 1, i), new ItemStack(USMItems.IngotMetal, 1, i), 20.0F);
		}
	}
}
