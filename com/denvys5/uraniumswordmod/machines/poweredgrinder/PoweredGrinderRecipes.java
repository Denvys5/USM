package com.denvys5.uraniumswordmod.machines.poweredgrinder;

import com.denvys5.uraniumswordmod.api.MachineRecipe;

public class PoweredGrinderRecipes extends MachineRecipe{
	private static final PoweredGrinderRecipes smeltingBase = new PoweredGrinderRecipes();
	public static MachineRecipe smelting(){
		return smeltingBase;
	}
}