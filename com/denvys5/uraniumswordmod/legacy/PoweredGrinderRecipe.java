package com.denvys5.uraniumswordmod.legacy;

import net.minecraft.item.ItemStack;

public class PoweredGrinderRecipe {
	public ItemStack Ingredient;
	public ItemStack Result;
	public int RequiredPower;
	public PoweredGrinderRecipe(ItemStack Input, ItemStack Output, int PowerForCrafting){
		this.Ingredient = Input;
		this.Result = Output;
		this.RequiredPower = PowerForCrafting;
	}
	public ItemStack getCraft(){
		return this.Ingredient;
	}
	public int getRequiredPower(){
		return this.RequiredPower;
	}
	public ItemStack getResult(){
		return this.Result;
	}
}
