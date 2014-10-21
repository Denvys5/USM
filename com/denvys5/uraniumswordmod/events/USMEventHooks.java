package com.denvys5.uraniumswordmod.events;

import com.denvys5.uraniumswordmod.core.USM;
import com.denvys5.uraniumswordmod.effects.ArmourIgnoreDamage;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class USMEventHooks {
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) {
		if (event.entityLiving.isPotionActive(USM.RadiationUSM)) {
			if(true){
				event.entityLiving.attackEntityFrom(new ArmourIgnoreDamage("radiation"), 75);
				event.entityLiving.addPotionEffect(new PotionEffect(17, 600, 0));
				event.entityLiving.addPotionEffect(new PotionEffect(15, 600, 0));
				event.entityLiving.addPotionEffect(new PotionEffect(18, 20, 0));
				event.entityLiving.addPotionEffect(new PotionEffect(20, 600, 0));
			}
			if(event.entityLiving.getActivePotionEffect(USM.RadiationUSM).getDuration()==0){
				event.entityLiving.removePotionEffect(USM.RadiationUSM.id);
				return;
			}
		}
	}
}
