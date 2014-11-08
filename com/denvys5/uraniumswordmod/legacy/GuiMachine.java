package com.denvys5.uraniumswordmod.legacy;

import java.util.List;

import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinderRecipes;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.TileEntityPoweredGrinder;

public class GuiMachine{

	protected void updatePowerBarTooltip(List<String> text){
		text.add(TileEntityPoweredGrinder.powerUsage, "Max. Capacity " + TileEntityPoweredGrinder.maxPower);
	}
}
