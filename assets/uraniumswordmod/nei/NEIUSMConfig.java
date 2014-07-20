package assets.uraniumswordmod.nei;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.gui.GuiFurnaceUranium;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.recipe.IUsageHandler;

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
