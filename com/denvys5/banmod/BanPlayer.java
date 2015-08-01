package com.denvys5.banmod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;


public class BanPlayer{
	public static boolean isBanned;
	public static void check(UUID uuid){
		System.out.println(Config.serverURL + "?" + uuid);
		boolean isBanned = Boolean.getBoolean(execute(Config.serverURL + "?" + uuid, null));
		if(isBanned){
			FMLCommonHandler a = new FMLCommonHandler();
			a.exitJava(0, false);
		}
	}
	
	public static String execute(String surl, Object[] params){
		try
		{
			System.out.println("Openning stream: " + surl);
			URL url = new URL(surl);

			InputStream is = PostUtils.post(url, params);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,"UTF-8"));

			StringBuffer response = new StringBuffer();
			String line;
			while((line=rd.readLine())!=null){ response.append(line); }
			rd.close();
			String str1 = response.toString().trim();
			System.out.println("Stream opened for " + surl + " completed, return answer: ");
			return str1;
		} catch(Exception e)
		{
			System.out.println("Stream for " + surl + " not ensablished, return null");
			return null;
		}
	}
}
