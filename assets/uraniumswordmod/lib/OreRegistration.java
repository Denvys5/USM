package assets.uraniumswordmod.lib;

public class OreRegistration {
	
	public static int OreUraniumRegister;
	public static int IngotUraniumRegister;
	public static int IronStickRegister;
	
	public static void BooleanRegister(){
		if(OreRegistration.OreUraniumRegister == 1){
			OreUraniumRegister();
		}
		if(OreRegistration.IngotUraniumRegister == 1){
			IngotUraniumRegister();
		}
		if(OreRegistration.IronStickRegister == 1){
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
