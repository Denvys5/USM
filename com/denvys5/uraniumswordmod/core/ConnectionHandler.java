package com.denvys5.uraniumswordmod.core;

import cpw.mods.fml.common.network.NetworkCheckHandler;
import ibxm.Player;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.INetHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class ConnectionHandler implements INetHandler {

	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		netHandler.getPlayer().addChatMessage(
				EnumChatFormatting.GOLD
						+ "Uranium Sword Mod " + USM.version + " is developing exclusively for Sagitarium.org");
		if(OreRegistration.OreUraniumRegister != true){
			netHandler.getPlayer().addChatMessage(
					EnumChatFormatting.GOLD
							+ "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Uranium Ore)");	
		}
		if(OreRegistration.IngotUraniumRegister != true){
			netHandler.getPlayer().addChatMessage(
					EnumChatFormatting.GOLD
							+ "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Uranium Ingots)");	
		}
		if(OreRegistration.IronStickRegister != true){
			netHandler.getPlayer().addChatMessage(
					EnumChatFormatting.GOLD
							+ "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Iron Stick)");	
		}
	}

	@Override
	public void onDisconnect(IChatComponent p_147231_1_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionStateTransition(EnumConnectionState p_147232_1_,
			EnumConnectionState p_147232_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNetworkTick() {
		// TODO Auto-generated method stub
		
	}

}
