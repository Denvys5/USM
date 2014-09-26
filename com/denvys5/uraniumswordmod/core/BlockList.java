package com.denvys5.uraniumswordmod.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;

import com.denvys5.uraniumswordmod.block.*;
import com.denvys5.uraniumswordmod.item.*;
import com.denvys5.uraniumswordmod.uraniumfurnace.TileEntityFurnaceUranium;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockList {

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Block blockinfuseduranium;
	public static Item sworduranium;
	public static Item uraniumonstick;
	public static Item ingotinfuseduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static ItemArmor UraniumHelmet;
	public static ItemArmor UraniumChest;
	public static ItemArmor UraniumLeggins;
	public static ItemArmor UraniumBoots;
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
	
	public static void armourRegister(){
	    UraniumHelmet = new UraniumArmour(0 ,0);
	    Util.armorRegister(UraniumHelmet, "Uranium Helmet");
	    UraniumChest = new UraniumArmour(1, 1);
	    Util.armorRegister(UraniumChest, "Uranium Chest");
	    UraniumLeggins = new UraniumArmour(2, 2);
	    Util.armorRegister(UraniumLeggins, "Uranium Leggins");
	    UraniumBoots = new UraniumArmour(3, 3);
	    Util.armorRegister(UraniumBoots, "Uranium Boots");
	}
}
