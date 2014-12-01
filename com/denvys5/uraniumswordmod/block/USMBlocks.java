package com.denvys5.uraniumswordmod.block;

import java.util.ArrayList;

import net.minecraft.block.Block;

import com.denvys5.uraniumswordmod.core.Util;
import com.denvys5.uraniumswordmod.machines.machineparts.BasicMachineCore;
import com.denvys5.uraniumswordmod.machines.machineparts.DisassemblerCore;
import com.denvys5.uraniumswordmod.machines.nuke.Nuke;

public class USMBlocks{

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Block blockinfuseduranium;

	public static Block NuclearWaste;
	public static Block Nuke;
	
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
	}
}
