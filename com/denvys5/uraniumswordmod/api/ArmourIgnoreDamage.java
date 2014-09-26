package com.denvys5.uraniumswordmod.api;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class ArmourIgnoreDamage extends EntityDamageSource {
	public ArmourIgnoreDamage(String name, Entity entity) {
		super(name, entity);
		setDamageBypassesArmor();
		}
}
