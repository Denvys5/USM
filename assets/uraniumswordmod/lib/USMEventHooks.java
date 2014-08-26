package assets.uraniumswordmod.lib;

import assets.uraniumswordmod.USM;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class USMEventHooks {
	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event) {
		if (event.entityLiving.isPotionActive(USM.RadiationUSM)) {
			/*if (event.entityLiving.worldObj.rand.nextInt(20) == 0) {
				event.entityLiving.attackEntityFrom(DamageSource.generic, 2);
				}*/
			if(true){
				event.entityLiving.attackEntityFrom(DamageSource.generic, 75);
			}
			if(event.entityLiving.getActivePotionEffect(USM.RadiationUSM).getDuration()==0){
				event.entityLiving.removePotionEffect(USM.RadiationUSM.id);
				return;
			}
		}
	}
}
