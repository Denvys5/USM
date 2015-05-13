package com.denvys5.uraniumswordmod.machines.uraniumduplicator;

import com.denvys5.uraniumswordmod.api.MachineRecipe;

public class DuplicatorRecipes extends MachineRecipe{
	private static final DuplicatorRecipes smeltingBase = new DuplicatorRecipes();
	public static MachineRecipe smelting(){
		return smeltingBase;
	}
}