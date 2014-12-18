package com.denvys5.uraniumswordmod.core;

import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.item.USMItems;

public class OreRegistration{

	public static boolean OreUraniumRegister;
	public static boolean IngotUraniumRegister;
	public static boolean IronStickRegister;

	public static void BooleanRegister(){
		if(OreRegistration.OreUraniumRegister == true){
			OreUraniumRegister();
		}
		if(OreRegistration.IngotUraniumRegister == true){
			IngotUraniumRegister();
		}
		if(OreRegistration.IronStickRegister == true){
			IronStickRegister();
		}
	}
	public static void OreUraniumRegister(){
		Util.OreRegister(USMBlocks.oreuranium, "oreUranium");
	}
	public static void IngotUraniumRegister(){
		Util.OreRegister(USMItems.ingoturanium, "ingotUranium");
	}
	public static void IronStickRegister(){
		Util.OreRegister(USMItems.stickiron, "stickIron");
		Util.OreRegister(USMItems.stickiron, "ironStick");
		Util.OreRegister(USMItems.stickiron, "ironRod");
	}
}
