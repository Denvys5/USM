package com.denvys5.banmod;

import java.io.File;
import java.io.IOException;

import net.minecraftforge.common.config.Configuration;

import com.denvys5.uraniumswordmod.USM;

public class Config{
	public static String serverURL;
	private static Configuration config;
	public static void ConfigMethod(File file){
		try{
			file = new File(file.getCanonicalPath().replace(BanMod.modid, "BanMod\\BanMod"));
		}catch(IOException e){
			e.printStackTrace();
		}
		config = new Configuration(file);
		config.load();
		serverURL = config.getString("Site URL", "Options", "mysite.com/uuid.php", "This is URL to uuid.php");
		config.save();
	}
}
