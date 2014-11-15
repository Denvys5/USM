package com.denvys5.uraniumswordmod.nei;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.IUsageHandler;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.machines.USMTiles;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.GuiPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.GuiFurnaceUranium;

public class NEIUSMConfig implements IConfigureNEI{

	public void loadConfig(){
		API.registerRecipeHandler(new FurnaceUraniumRecipeHandler());
		API.registerUsageHandler(new FurnaceUraniumRecipeHandler());
		// API.registerRecipeHandler(new PoweredGrinderRecipeHandler());
		// API.registerUsageHandler(new PoweredGrinderRecipeHandler());
		API.hideItem(new ItemStack(USMTiles.furnaceuraniumactive));
		API.hideItem(new ItemStack(USMTiles.PoweredGrinderactive));
		API.hideItem(new ItemStack(USMTiles.duplicatoractive));
		// API.setGuiOffset(GuiFurnaceUranium.class, 0, 0);
		// API.setGuiOffset(GuiPoweredGrinder.class, 0, 0);
	}

	public String getName(){
		return USM.name;
	}

	public String getVersion(){
		return USM.version;
	}

}
