package com.denvys5.uraniumswordmod.events;

import com.denvys5.uraniumswordmod.core.USM;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class USMEventHooks {
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if (event.entityLiving.isPotionActive(USM.RadiationUSM)) {
			if(true){
				event.entity.attackEntityFrom(DamageSource.outOfWorld, 75);
				event.entity.attackEntityFrom(DamageSource.magic, 75);
				event.entityLiving.attackEntityFrom(DamageSource.generic, 75);
			}
			if(event.entityLiving.getActivePotionEffect(USM.RadiationUSM).getDuration()==0){
				event.entityLiving.removePotionEffect(USM.RadiationUSM.id);
				return;
			}
		}
	}
}
