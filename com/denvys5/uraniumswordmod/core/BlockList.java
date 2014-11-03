package com.denvys5.uraniumswordmod.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.*;
import com.denvys5.uraniumswordmod.item.*;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinder;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.TileEntityPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.Duplicator;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.TileEntityDuplicator;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.FurnaceUranium;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.TileEntityFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.windmill.*;
import com.denvys5.uraniumswordmod.machines.machineparts.BasicMachineCore;
import com.denvys5.uraniumswordmod.machines.machineparts.DisassemblerCore;
import com.denvys5.uraniumswordmod.machines.nuke.Nuke;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockList {

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Block blockinfuseduranium;
	public static Block BasicMachineCore;
	public static Block DisassemblerCore;
	public static Block NuclearWaste;
	public static Block Nuke;
	
	
	public static Item sworduranium;
	public static Item pickuranium;
	public static Item uraniumonstick;
	public static Item ingotinfuseduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static Item UraniumHelmet;
	public static Item UraniumChest;
	public static Item UraniumLeggins;
	public static Item UraniumBoots;
	public static Item UraniumWrench;
	public static Item BasicBattery;
	
	
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;
	public static Block duplicatoridle;
	public static Block duplicatoractive;
	public static Block PoweredGrinderidle;
	public static Block PoweredGrinderactive;
	public static Block WindmillBlock;
	public static Block WindmillPlatform;
	public static Item WindmillItem;
	

	public static void blockRegister() {
		oreuranium = new OreUranium().setBlockName("Uranium Ore");
		Util.regBlock(oreuranium, "oreuranium", "Uranium Ore");

		blocknetherstar = new BlockNetherStar().setBlockName("Nether Star Block");
		Util.regBlock(blocknetherstar, "blocknetherstar", "Nether Star Block");

		blockuranium = new BlockUranium().setBlockName("Block Uranium");
		Util.regBlock(blockuranium, "blockuranium", "Block Uranium");
		
		blockinfuseduranium = new BlockInfusedUranium().setBlockName("Block Infused Uranium");
		Util.regBlock(blockinfuseduranium, "blockinfuseduranium", "Block Infused Uranium");
		
		BasicMachineCore = new BasicMachineCore().setBlockName("Basic Machine Core");
		Util.regBlock(BasicMachineCore, "BasicMachineCore", "Basic Machine Core");
		
		DisassemblerCore = new DisassemblerCore().setBlockName("Disassembler Core");
		Util.regBlock(DisassemblerCore, "DisassemblerCore", "Disassembler Core");
		
		NuclearWaste = new NuclearWaste().setBlockName("Nuclear Waste").setLightLevel(0.8F);
		Util.regBlock(NuclearWaste, "NuclearWaste", "Nuclear Waste");
		
		Nuke = new Nuke().setBlockName("Nuke");
		Util.regBlock(Nuke, "Nuke", "Nuke");
	}

	public static void itemRegister() {
		sworduranium = new SwordUranium().setUnlocalizedName("Uranium Sword");
		Util.regItem(sworduranium, "sworduranium", "Uranium Sword");
		
		pickuranium = new PickUranium().setUnlocalizedName("Uranium Pickaxe");
		Util.regItem(pickuranium, "pickuranium", "Uranium Pickaxe");
		
		uraniumonstick = new UraniumOnStick().setUnlocalizedName("Uranium On Stick");
		Util.regItem(uraniumonstick, "uraniumonstick", "Uranium On Stick");

		stickiron = new StickIron().setUnlocalizedName("Iron Stick");
		Util.regItem(stickiron, "stickiron", "Iron Stick");

		ingoturanium = new IngotUranium().setUnlocalizedName("Uranium Ingot");
		Util.regItem(ingoturanium, "ingoturanium", "Uranium Ingot");

		ingotinfuseduranium = new IngotInfusedUranium().setUnlocalizedName("Infused Uranium Ingot");
		Util.regItem(ingotinfuseduranium, "ingotinfuseduranium", "Infused Uranium Ingot");
		
		UraniumWrench = new UraniumWrench().setUnlocalizedName("Uranium Wrench");
		Util.regItem(UraniumWrench, "UraniumWrench", "Uranium Wrench");
		
		BasicBattery = new BasicBattery(10000).setUnlocalizedName("Basic Battery");
		Util.regItem(BasicBattery, "BasicBattery", "Basic Battery");
	}
	
	public static void tileEntityRegister(){
		furnaceuraniumidle = new FurnaceUranium(false).setBlockName("Uranium Furnace").setCreativeTab(USM.USMTab);
		furnaceuraniumactive = new FurnaceUranium(true).setBlockName("Uranium Furnace Active").setLightLevel(0.8F);
		Util.regTileEntity(furnaceuraniumidle, furnaceuraniumactive, "Uranium Furnace", "furnaceuranium");
		GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class, "FurnaceUranium");
		LanguageRegistry.instance().addStringLocalization("container.furnaceUranium", "Uranium Furnace");
		
		duplicatoridle = new Duplicator(false).setBlockName("Duplicator").setCreativeTab(USM.USMTab);
		duplicatoractive = new Duplicator(true).setBlockName("Duplicator Active").setLightLevel(0.8F);
		Util.regTileEntity(duplicatoridle, duplicatoractive, "Duplicator", "duplicator");
		GameRegistry.registerTileEntity(TileEntityDuplicator.class,"duplicator");
		LanguageRegistry.instance().addStringLocalization("container.Duplicator", "Duplicator");
		
		PoweredGrinderidle = new PoweredGrinder(false).setBlockName("Powered Grinder").setCreativeTab(USM.USMTab);
		PoweredGrinderactive = new PoweredGrinder(true).setBlockName("Powered Grinder Active").setLightLevel(0.8F);
		Util.regTileEntity(PoweredGrinderidle, PoweredGrinderactive, "Powered Grinder", "PoweredGrinder");
		GameRegistry.registerTileEntity(TileEntityPoweredGrinder.class,"PoweredGrinder");
		LanguageRegistry.instance().addStringLocalization("container.PoweredGrinder", "Powered Grinder");
		
		WindmillBlock = new WindmillBlock().setBlockName("Windmill");
		WindmillPlatform = new WindmillPlatform().setBlockName("WindmillPlatform").setCreativeTab(USM.USMTab);
		WindmillItem = new WindmillItem().setUnlocalizedName("WindmillItem").setCreativeTab(USM.USMTab);
		GameRegistry.registerTileEntity(TileEntityWindmill.class, "Windmill");
		GameRegistry.registerTileEntity(TileEntityWindmillPlatform.class, "WindmillPlatform");
		Util.regBlock(WindmillPlatform, "WindmillPlatform", "Windmill Platform");
		Util.regBlock(WindmillBlock, "WindmillBlock", "Windmill Block");
		Util.regItem(WindmillItem, "WindmillItem", "Windmill Item");
	}
	
	public static void armorRegister(){	    
	    UraniumHelmet = new UraniumArmor(0).setUnlocalizedName("UraniumHelmet");
	    Util.regItem(UraniumHelmet, "UraniumHelmet", "Uranium Helmet");
	    UraniumChest = new UraniumArmor(1).setUnlocalizedName("UraniumChest");
	    Util.regItem(UraniumChest, "UraniumChest", "Uranium Chest");
	    UraniumLeggins = new UraniumArmor(2).setUnlocalizedName("UraniumLeggins");
	    Util.regItem(UraniumLeggins, "UraniumLeggins", "Uranium Leggins");
	    UraniumBoots = new UraniumArmor(3).setUnlocalizedName("UraniumBoots");
	    Util.regItem(UraniumBoots, "UraniumBoots", "Uranium Boots");
	}
}
