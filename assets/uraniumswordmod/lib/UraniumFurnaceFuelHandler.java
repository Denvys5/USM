package assets.uraniumswordmod.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class UraniumFurnaceFuelHandler{
	public static ItemStack[] fuels = new ItemStack[20];
	public static int[] burnTime = new int[fuels.length];
	public static void setFuel(ItemStack fuel, int burnTimeforMethod, int fuelID){
		fuels[fuelID] = fuel;
		burnTime[fuelID] = burnTimeforMethod;
	}
	public static int getBurnTime(ItemStack fuel) {
		for(int i = 0; i < fuels.length; i++){
			if(fuel == fuels[i]){
				return burnTime[i];
			}
		}
		return 0;
	}

}

