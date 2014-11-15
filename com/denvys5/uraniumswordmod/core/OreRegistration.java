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
		Util.OreBlockRegister(USMBlocks.oreuranium, "oreUranium");
	}
	public static void IngotUraniumRegister(){
		Util.OreItemRegister(USMItems.ingoturanium, "ingotUranium");
	}
	public static void IronStickRegister(){
		Util.OreItemRegister(USMItems.stickiron, "stickIron");
		Util.OreItemRegister(USMItems.stickiron, "ironStick");
		Util.OreItemRegister(USMItems.stickiron, "ironRod");
	}
}
