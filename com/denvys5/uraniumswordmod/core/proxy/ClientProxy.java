package com.denvys5.uraniumswordmod.core.proxy;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmill;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmillPlatform;
import com.denvys5.uraniumswordmod.machines.windmill.WindMillPlatformRenderer;
import com.denvys5.uraniumswordmod.machines.windmill.WindmillRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	public void registerProxy() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new WindmillRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillPlatform.class, new WindMillPlatformRenderer());
	}
}
