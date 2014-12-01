package com.denvys5.uraniumswordmod.core;

import java.io.File;
import java.io.IOException;

import com.denvys5.uraniumswordmod.USM;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class Config{
	public static boolean FullCheatyArmor;
	public static boolean ArmorCrashFix;
	public static boolean NewWIPPickMechanicks;
	private static Configuration config;
	public static boolean renderDrawInside;

	//public static Configuration config = new Configuration(new File("config/USM/UraniumSwordMod.cfg"));

	public static void ConfigMethod(File file){
		try{
			file = new File(file.getCanonicalPath().replace(USM.modid, "USM\\UraniumSwordMod"));
		} catch(IOException e){
			e.printStackTrace();
		}
		config = new Configuration(file);
		config.load();
		ConfigItemsAndBlocksCreating();
		ConfigTweakRegister();
		ConfigGraphics();
		ConfigCrafts();
		config.save();
	}

	private static void ConfigCrafts(){

	}
	
	private static void ConfigItemsAndBlocksCreating(){
		
	}
	
	private static void ConfigGraphics(){
		renderDrawInside = config.getBoolean("renderDrawInside", "Graphic FX", true, "Render all custom rendered blocks inside too.");
	}

	private static void ConfigTweakRegister(){
		OreRegistration.OreUraniumRegister = config.getBoolean("UraniumOre_OreRegistration", "Tweaks", true, "Ore Register Uranium Ore");
		OreRegistration.IngotUraniumRegister = config.getBoolean("UraniumIngot_OreRegistration", "Tweaks", true, "Ore Register Uranium Ingot");
		OreRegistration.IronStickRegister = config.getBoolean("IronStick_OreRegistration", "Tweaks", true, "Ore Register Iron Stick");
		FullCheatyArmor = config.getBoolean("NearCreativeArmor", "Tweaks", true, "If Uranium Armor weared, you can`t recieve damage.");
		ArmorCrashFix = config.getBoolean("ArmorCrashFix", "Tweaks", false, "If you get crash of wearing Uranium Armor, set to true.");
		NewWIPPickMechanicks = config.getBoolean("NewWIPPickMechanicks", "Tweaks", false, "Activate new work in progress UranimPickaxe mechanicks");
	}
}
