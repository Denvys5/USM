package com.denvys5.uraniumswordmod.core;


public class OreRegistration {
	
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
		Util.OreBlockRegister(BlockList.oreuranium, "oreUranium");
	}
	public static void IngotUraniumRegister(){
		Util.OreItemRegister(BlockList.ingoturanium, "ingotUranium");
	}
	public static void IronStickRegister(){
		Util.OreItemRegister(BlockList.stickiron, "stickIron");
		Util.OreItemRegister(BlockList.stickiron, "ironStick");
		Util.OreItemRegister(BlockList.stickiron, "ironRod");
	}
}
