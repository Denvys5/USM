package com.denvys5.uraniumswordmod.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import com.denvys5.uraniumswordmod.block.*;
import com.denvys5.uraniumswordmod.item.*;
import com.denvys5.uraniumswordmod.uraniumfurnace.FurnaceUranium;
import com.denvys5.uraniumswordmod.uraniumfurnace.TileEntityFurnaceUranium;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockList {

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Block blockinfuseduranium;
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
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;

	public static void blockRegister() {

		oreuranium = new OreUranium()
				.setBlockName("Uranium Ore");
		Util.regBlock(oreuranium, "oreuranium", "Uranium Ore");

		blocknetherstar = new BlockNetherStar()
				.setBlockName("Nether Star Block");
		Util.regBlock(blocknetherstar, "blocknetherstar", "Nether Star Block");

		blockuranium = new BlockUranium()
				.setBlockName("Block Uranium");
		Util.regBlock(blockuranium, "blockuranium", "Block Uranium");
		
		blockinfuseduranium = new BlockInfusedUranium()
				.setBlockName("Block Infused Uranium");
		Util.regBlock(blockinfuseduranium, "blockinfuseduranium", "Block Infused Uranium");

		furnaceuraniumidle = new FurnaceUranium(
				false).setBlockName("Uranium Furnace").setCreativeTab(USM.USMTab);
		furnaceuraniumactive = new FurnaceUranium(true).setBlockName("Uranium Furnace Active").setLightLevel(0.8F);
		Util.regBlock(furnaceuraniumidle, "furnaceuraniumidle",
				"Uranium Furnace");
		Util.regBlock(furnaceuraniumactive, "furnaceuraniumactive",
				"Uranium Furnace Active");
		GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class,
				"FurnaceUranium");
		LanguageRegistry.instance().addStringLocalization(
				"container.furnaceUranium", "Uranium Furnace");

	}

	public static void itemRegister() {
		sworduranium = new SwordUranium()
				.setUnlocalizedName("Uranium Sword");
		Util.regItem(sworduranium, "sworduranium", "Uranium Sword");
		
		pickuranium = new PickUranium()
				.setUnlocalizedName("Uranium Pickaxe");
		Util.regItem(pickuranium, "pickuranium", "Uranium Pickaxe");
		
		uraniumonstick = new UraniumOnStick()
				.setUnlocalizedName("Uranium On Stick");
		Util.regItem(uraniumonstick, "uraniumonstick", "Uranium On Stick");

		stickiron = new StickIron()
				.setUnlocalizedName("Iron Stick");
		Util.regItem(stickiron, "stickiron", "Iron Stick");

		ingoturanium = new IngotUranium()
				.setUnlocalizedName("Uranium Ingot");
		Util.regItem(ingoturanium, "ingoturanium", "Uranium Ingot");

		ingotinfuseduranium = new IngotInfusedUranium()
				.setUnlocalizedName("Infused Uranium Ingot");
		Util.regItem(ingotinfuseduranium, "ingotinfuseduranium",
				"Infused Uranium Ingot");
	}
	
	public static void armorRegister(){	    
	    UraniumHelmet = new UraniumArmor(0).setUnlocalizedName("UraniumHelmet");
	    Util.armorRegister(UraniumHelmet, "UraniumHelmet", "Uranium Helmet");
	    UraniumChest = new UraniumArmor(1).setUnlocalizedName("UraniumChest");
	    Util.armorRegister(UraniumChest, "UraniumChest", "Uranium Chest");
	    UraniumLeggins = new UraniumArmor(2).setUnlocalizedName("UraniumLeggins");
	    Util.armorRegister(UraniumLeggins, "UraniumLeggins", "Uranium Leggins");
	    UraniumBoots = new UraniumArmor(3).setUnlocalizedName("UraniumBoots");
	    Util.armorRegister(UraniumBoots, "UraniumBoots", "Uranium Boots");
	}
}
