package com.denvys5.uraniumswordmod.core;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class RightsForUsingMod{
	private static String[] ip = new String[1];
	public static void hasRights(){
		ip[0] = "www.sagitarium.org";
		String serverIP = MinecraftServer.getServer().getServerHostname();
		System.out.println(serverIP);
		for(String string : ip){
			if(!string.equals(serverIP)){
				MinecraftServer.getServer().stopServer();
			}
		}
	}
}
