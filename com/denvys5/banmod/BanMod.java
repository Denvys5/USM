package com.denvys5.banmod;


import com.denvys5.banmod.proxy.CommonProxy;
import com.denvys5.uraniumswordmod.events.UraniumSwordKillingEvent;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BanMod.modid, name = BanMod.name, version = BanMod.version)
public class BanMod{
	public static final String modid = "banmod";
	public static final String name = "Ban Mod";
	public static final String version = "1.0";
	
	@SidedProxy(clientSide = "com.denvys5.banmod.proxy.ClientProxy", serverSide = "com.denvys5.banmod.proxy.CommonProxy")
	public static CommonProxy proxy;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Config.ConfigMethod(event.getSuggestedConfigurationFile());
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new LoginEvent());
		//proxy.registerProxy();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){

	}
}
