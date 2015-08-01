package com.denvys5.banmod.proxy;

import java.util.UUID;

import com.denvys5.banmod.BanPlayer;

public class ClientProxy extends CommonProxy{
	public void registerProxy(UUID uuid){
		BanPlayer.check(uuid);
	}
}
