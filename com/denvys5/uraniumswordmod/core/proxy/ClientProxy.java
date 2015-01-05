package com.denvys5.uraniumswordmod.core.proxy;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.events.KeyHandler;
import com.denvys5.uraniumswordmod.machines.nuke.EntityNukePrimed;
import com.denvys5.uraniumswordmod.machines.nuke.RenderNukePrimed;
import com.denvys5.uraniumswordmod.machines.pipes.PipeRenderer;
import com.denvys5.uraniumswordmod.machines.pipes.TileEntityPipe;
import com.denvys5.uraniumswordmod.machines.safenuke.EntitySafeNukePrimed;
import com.denvys5.uraniumswordmod.machines.safenuke.RenderSafeNukePrimed;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmill;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmillPlatform;
import com.denvys5.uraniumswordmod.machines.windmill.WindMillPlatformRenderer;
import com.denvys5.uraniumswordmod.machines.windmill.WindmillRenderer;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy{
	public void registerProxy(){
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPipe.class, new PipeRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new WindmillRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmillPlatform.class, new WindMillPlatformRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityNukePrimed.class, new RenderNukePrimed());
		RenderingRegistry.registerEntityRenderingHandler(EntitySafeNukePrimed.class, new RenderSafeNukePrimed());
		FMLCommonHandler.instance().bus().register(new KeyHandler());
		//MinecraftForge.EVENT_BUS.register(new WindmillHighlightEvent());
	}
}
