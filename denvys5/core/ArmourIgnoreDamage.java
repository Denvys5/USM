package denvys5.core;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class ArmourIgnoreDamage extends EntityDamageSource {
	public ArmourIgnoreDamage(String name, Entity entity) {
		super(name, entity);
		setDamageBypassesArmor();
		}
}
