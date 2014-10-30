package com.denvys5.uraniumswordmod.core;

import ic2.api.item.IC2Items;
import net.minecraft.item.ItemStack;

public class OreHandler{
	public static ItemStack oreCopper(String string){
		if(string.equalsIgnoreCase("IC2")){
			ItemStack ore = IC2Items.getItem("oreCopper");
			return ore;
		}
		return null;
	}
	public static ItemStack oreTin(){
		ItemStack ore = IC2Items.getItem("oreTin");
		return ore;
	}
}
