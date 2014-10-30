package com.denvys5.uraniumswordmod.events;

import com.denvys5.uraniumswordmod.core.BlockList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class UraniumSwordKillingEvent {
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		try {
			if (event.source.getDamageType().equals("player")) {
				EntityPlayer player = (EntityPlayer) event.source.getSourceOfDamage();
				String user = player.getGameProfile().getName();
				String entity = event.entityLiving.getCommandSenderName();
				if(event.entityLiving.getEquipmentInSlot(0) == new ItemStack(BlockList.sworduranium)){
					MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(user + "was killed by" + entity + "using Uranium Sword"));
				}
			}
		} catch (Exception e) {
			// Something happened
		}
	}
}
