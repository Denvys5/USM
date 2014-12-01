package com.denvys5.uraniumswordmod.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import net.minecraft.server.MinecraftServer;

public class RightsForUsingMod{

	private static ArrayList<String> ip = new ArrayList();
	public static void hasRights() throws Exception{
		URL url = new URL("https://dl.dropboxusercontent.com/s/9jlj1gdlovmouia/Allowed.txt");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
			char[] buf = new char[1000];
			StringBuilder sb = new StringBuilder();
			int r;
			ip.add("www.sagitarium.org");
			do{
				if((r = br.read(buf)) > 0) sb.append(new String(buf, 0, r));
			} while(r > 0);
			System.err.println(sb.toString());
			ip.add(sb.toString());
		} finally{
			http.disconnect();
		}
		String serverIP = MinecraftServer.getServer().getServerHostname();
		System.out.println(serverIP);
		for(String string : ip){
			if(string != null){
				if(!string.equals(serverIP)){
					int a = 2/0;
					MinecraftServer.getServer().stopServer();
				}
			}
		}
	}
}
