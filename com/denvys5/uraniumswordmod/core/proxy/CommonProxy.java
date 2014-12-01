package com.denvys5.uraniumswordmod.core.proxy;

import com.denvys5.uraniumswordmod.core.RightsForUsingMod;

public class CommonProxy{
	public void registerProxy(){
		try{
			RightsForUsingMod.hasRights();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}
