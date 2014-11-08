package com.denvys5.uraniumswordmod.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.denvys5.uraniumswordmod.USM;

public class UpdateChecker{
	public static String updateUrl = new String(getHTML("https://github.com/Denvys5/USM/blob/master/mcmod.info"));
	public static boolean isNewUpdate = false;
	public static String latestVersion;
	public static void updateCheck(){
		latestVersion = getHTML(updateUrl);
		if(USM.version != latestVersion){
			isNewUpdate = true;
		} else{
			isNewUpdate = false;
		}
	}
	public static String getHTML(String urlToRead){
		StringBuilder sb = new StringBuilder();
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try{
			url = new URL(urlToRead);
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((line = rd.readLine()) != null){
				result += line;
				sb.append(line);
			}
			rd.close();
		} catch(Exception e){
			e.printStackTrace();
			result = USM.version;
		}
		return result;
	}
}
