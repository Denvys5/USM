package com.denvys5.uraniumswordmod.events;



import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.OreRegistration;

import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class OnPlayerLoginEvent {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        event.player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Uranium Sword Mod " + USM.version + " is developing exclusively for Sagitarium.org"));

		if(OreRegistration.OreUraniumRegister != true){
	        event.player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Uranium Ore)"));
		}
		
		if(OreRegistration.IngotUraniumRegister != true){
			event.player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Uranium Ingots)"));	
		}
		
		if(OreRegistration.IronStickRegister != true){
			event.player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Iron Stick)"));	
		}
    }
}