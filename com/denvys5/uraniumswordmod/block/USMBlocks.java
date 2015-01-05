package com.denvys5.uraniumswordmod.block;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.denvys5.uraniumswordmod.core.Util;
import com.denvys5.uraniumswordmod.machines.machineparts.BasicMachineCore;
import com.denvys5.uraniumswordmod.machines.machineparts.DisassemblerCore;
import com.denvys5.uraniumswordmod.machines.nuke.Nuke;
import com.denvys5.uraniumswordmod.machines.safenuke.SafeNuke;

import cpw.mods.fml.common.registry.GameRegistry;

public class USMBlocks{

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Block blockinfuseduranium;
	public static Block oreMetal;
	public static Block blockMetal;

	public static Block NuclearWaste;
	public static Block Nuke;
	public static Block SafeNuke;
	
	public static ArrayList<Block> AllBlocks = new ArrayList();

	public static void blockRegister(){
		oreuranium = new OreUranium().setBlockName("Uranium Ore");
		Util.regBlock(oreuranium, "oreuranium", "Uranium Ore");

		blocknetherstar = new BlockNetherStar().setBlockName("Nether Star Block");
		Util.regBlock(blocknetherstar, "blocknetherstar", "Nether Star Block");

		blockuranium = new BlockUranium().setBlockName("Block Uranium");
		Util.regBlock(blockuranium, "blockuranium", "Block Uranium");

		blockinfuseduranium = new BlockInfusedUranium().setBlockName("Block Infused Uranium");
		Util.regBlock(blockinfuseduranium, "blockinfuseduranium", "Block Infused Uranium");

		NuclearWaste = new NuclearWaste().setBlockName("Nuclear Waste").setLightLevel(0.8F);
		Util.regBlock(NuclearWaste, "NuclearWaste", "Nuclear Waste");

		Nuke = new Nuke().setBlockName("Nuke");
		Util.regBlock(Nuke, "Nuke", "Nuke");
		
		SafeNuke = new SafeNuke().setBlockName("Safe Nuke");
		Util.regBlock(SafeNuke, "SafeNuke", "Safe Nuke");
		
		GameRegistry.registerBlock(oreMetal = new OreMetal("ore"), OreMetal.ItemMetaOre.class, "ore");
		GameRegistry.registerBlock(blockMetal = new BlockMetal("block"), BlockMetal.ItemMetaOre.class, "block");
	}
	
	public static void oreRegister(){
		Util.OreRegister(new ItemStack(oreMetal, 1, 0), "ore" + "Copper");
		Util.OreRegister(new ItemStack(oreMetal, 1, 1), "ore" + "Tin");
		Util.OreRegister(new ItemStack(oreMetal, 1, 2), "ore" + "Lead");
		Util.OreRegister(new ItemStack(oreMetal, 1, 3), "ore" + "Silver");
	}
}
