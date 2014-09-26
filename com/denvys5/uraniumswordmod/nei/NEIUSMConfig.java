package com.denvys5.uraniumswordmod.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.IUsageHandler;
import com.denvys5.uraniumswordmod.core.USM;
import com.denvys5.uraniumswordmod.uraniumfurnace.GuiFurnaceUranium;

public class NEIUSMConfig implements IConfigureNEI {

	public void loadConfig() {
		API.registerRecipeHandler(new FurnaceUraniumRecipeHandler());
		API.registerUsageHandler(new FurnaceUraniumRecipeHandler());
		API.setGuiOffset(GuiFurnaceUranium.class, 0, 0);
	}

	public String getName() {
		return USM.name;
	}

	public String getVersion() {
		return USM.version;
	}

}
