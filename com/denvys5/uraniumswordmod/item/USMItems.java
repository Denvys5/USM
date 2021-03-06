package com.denvys5.uraniumswordmod.item;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.denvys5.uraniumswordmod.block.OreMetal;
import com.denvys5.uraniumswordmod.core.Util;

import cpw.mods.fml.common.registry.GameRegistry;

public class USMItems{
	
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
	public static Item IngotMetal;
	public static Item DustMetal;
	
	public static ArrayList<Item> AllItems = new ArrayList();
	
	public static void itemRegister(){
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
		
		GameRegistry.registerItem(IngotMetal = new IngotMetal("ingot"), "ingot");
		GameRegistry.registerItem(DustMetal = new DustMetal("dust"), "dust");
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
	
	public static void oreRegister(){
		Util.OreRegister(new ItemStack(IngotMetal, 1, 0), "ingot" + "Copper");
		Util.OreRegister(new ItemStack(IngotMetal, 1, 1), "ingot" + "Tin");
		Util.OreRegister(new ItemStack(IngotMetal, 1, 2), "ingot" + "Lead");
		Util.OreRegister(new ItemStack(IngotMetal, 1, 3), "ingot" + "Silver");
		
		Util.OreRegister(new ItemStack(DustMetal, 1, 0), "dust" + "Copper");
		Util.OreRegister(new ItemStack(DustMetal, 1, 1), "dust" + "Tin");
		Util.OreRegister(new ItemStack(DustMetal, 1, 2), "dust" + "Lead");
		Util.OreRegister(new ItemStack(DustMetal, 1, 3), "dust" + "Silver");
	}
}
