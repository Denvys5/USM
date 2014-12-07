package com.denvys5.uraniumswordmod.machines;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.Util;
import com.denvys5.uraniumswordmod.machines.machineparts.BasicMachineCore;
import com.denvys5.uraniumswordmod.machines.machineparts.DisassemblerCore;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinder;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.TileEntityPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.Duplicator;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.TileEntityDuplicator;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.FurnaceUranium;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.TileEntityFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmill;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmillPlatform;
import com.denvys5.uraniumswordmod.machines.windmill.WindmillBlock;
import com.denvys5.uraniumswordmod.machines.windmill.WindmillItem;
import com.denvys5.uraniumswordmod.machines.windmill.WindmillPlatform;
import com.denvys5.uraniumswordmod.machines.pipes.BlockPipe;
import com.denvys5.uraniumswordmod.machines.pipes.TileEntityPipe;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class USMTiles{
	
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;
	public static Block duplicatoridle;
	public static Block duplicatoractive;
	public static Block PoweredGrinderidle;
	public static Block PoweredGrinderactive;
	public static Block WindmillBlock;
	public static Block WindmillPlatform;
	public static Block BlockPipe;
	public static Item WindmillItem;
	public static Block BasicMachineCore;
	public static Block DisassemblerCore;
	
	public static void tileEntityRegister(){
		BasicMachineCore = new BasicMachineCore().setBlockName("Basic Machine Core");
		Util.regBlock(BasicMachineCore, "BasicMachineCore", "Basic Machine Core");

		DisassemblerCore = new DisassemblerCore().setBlockName("Disassembler Core");
		Util.regBlock(DisassemblerCore, "DisassemblerCore", "Disassembler Core");
		
		furnaceuraniumidle = new FurnaceUranium(false).setBlockName("Uranium Furnace").setCreativeTab(USM.USMTab);
		furnaceuraniumactive = new FurnaceUranium(true).setBlockName("Uranium Furnace Active").setLightLevel(0.8F);
		Util.regTileEntity(furnaceuraniumidle, furnaceuraniumactive, "Uranium Furnace", "furnaceuranium");
		GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class, "FurnaceUranium");
		LanguageRegistry.instance().addStringLocalization("container.furnaceUranium", "Uranium Furnace");

		duplicatoridle = new Duplicator(false).setBlockName("Duplicator").setCreativeTab(USM.USMTab);
		duplicatoractive = new Duplicator(true).setBlockName("Duplicator Active").setLightLevel(0.8F);
		Util.regTileEntity(duplicatoridle, duplicatoractive, "Duplicator", "duplicator");
		GameRegistry.registerTileEntity(TileEntityDuplicator.class, "duplicator");
		LanguageRegistry.instance().addStringLocalization("container.Duplicator", "Duplicator");

		PoweredGrinderidle = new PoweredGrinder(false).setBlockName("Powered Grinder").setCreativeTab(USM.USMTab);
		PoweredGrinderactive = new PoweredGrinder(true).setBlockName("Powered Grinder Active").setLightLevel(0.8F);
		Util.regTileEntity(PoweredGrinderidle, PoweredGrinderactive, "Powered Grinder", "PoweredGrinder");
		GameRegistry.registerTileEntity(TileEntityPoweredGrinder.class, "PoweredGrinder");
		LanguageRegistry.instance().addStringLocalization("container.PoweredGrinder", "Powered Grinder");
		
		BlockPipe = new BlockPipe().setBlockName("Block Pipe").setCreativeTab(USM.USMTab);
		GameRegistry.registerTileEntity(TileEntityPipe.class, "BlockPipe");
		Util.regBlock(BlockPipe, "BlockPipe", "Block Pipe");

		WindmillBlock = new WindmillBlock().setBlockName("Windmill");
		WindmillPlatform = new WindmillPlatform().setBlockName("WindmillPlatform").setCreativeTab(USM.USMTab);
		WindmillItem = new WindmillItem().setUnlocalizedName("WindmillItem").setCreativeTab(USM.USMTab);
		GameRegistry.registerTileEntity(TileEntityWindmill.class, "Windmill");
		GameRegistry.registerTileEntity(TileEntityWindmillPlatform.class, "WindmillPlatform");
		LanguageRegistry.instance().addStringLocalization("container.Windmill", "Windmill");
		Util.regBlock(WindmillPlatform, "WindmillPlatform", "Windmill Platform");
		Util.regBlock(WindmillBlock, "WindmillBlock", "Windmill Block");
		Util.regItem(WindmillItem, "WindmillItem", "Windmill Item");
	}
}
