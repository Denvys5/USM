package com.denvys5.uraniumswordmod.legacy;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class ArmourIgnoreDamage extends DamageSource{
	public static DamageSource Radiation = (new ArmourIgnoreDamage("radiation")).setDamageBypassesArmor();
	public ArmourIgnoreDamage(String label){
		super(label);
		LanguageRegistry.addName(Radiation, "Radiation effect");
	}
}
