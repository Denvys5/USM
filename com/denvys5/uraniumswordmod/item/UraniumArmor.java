package com.denvys5.uraniumswordmod.item;

import java.util.List;

import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.Optional.Interface;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.core.Config;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
@Optional.InterfaceList(value = {@Interface(iface = "thaumcraft.api.nodes.IRevealer", modid = "Thaumcraft"), @Interface(iface = "thaumcraft.api.IGoggles", modid = "Thaumcraft")})
public class UraniumArmor extends ItemArmor implements ISpecialArmor, IGoggles, IRevealer{

	private static int invSize = 9;
	private static IIcon helmetIcon;
	private static IIcon plateIcon;
	private static IIcon leggingsIcon;
	private static IIcon bootsIcon;
	public static boolean Goggles = false;
	public static boolean NightVis = false;

	public UraniumArmor(int slot){
		super(ArmorMaterial.GOLD, 0, slot);
		setMaxDamage(1000);
		this.setCreativeTab(USM.USMTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(USM.modid + ":UraniumHelmet");
		this.helmetIcon = iconRegister.registerIcon(USM.modid + ":UraniumHelmet");
		this.plateIcon = iconRegister.registerIcon(USM.modid + ":UraniumChest");
		this.leggingsIcon = iconRegister.registerIcon(USM.modid + ":UraniumLeggins");
		this.bootsIcon = iconRegister.registerIcon(USM.modid + ":UraniumBoots");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1){
		if(this == BlockList.UraniumHelmet){
			return this.helmetIcon;
		}

		if(this == BlockList.UraniumChest){
			return this.plateIcon;
		}

		if(this == BlockList.UraniumLeggins){
			return this.leggingsIcon;
		}

		if(this == BlockList.UraniumBoots){
			return this.bootsIcon;
		}

		return this.itemIcon;
	}

	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot){
		if(source.equals(DamageSource.drown)){
			return new ArmorProperties(-1, 0, 10);
		}

		if(source.equals(DamageSource.outOfWorld)){
			return new ArmorProperties(-1, 3, 100000);
		}

		ItemStack helmet = player.getEquipmentInSlot(4);
		ItemStack plate = player.getEquipmentInSlot(3);
		ItemStack leggings = player.getEquipmentInSlot(2);
		ItemStack boots = player.getEquipmentInSlot(1);

		if(helmet == null || plate == null || leggings == null || boots == null){
			return new ArmorProperties(-1, 0, 0);
		}

		if(helmet.getItem() == BlockList.UraniumHelmet || plate.getItem() == BlockList.UraniumChest || leggings.getItem() == BlockList.UraniumLeggins || boots.getItem() == BlockList.UraniumBoots){
			if(source.isUnblockable()){
				return new ArmorProperties(-1, 3, 3);
			}

			return new ArmorProperties(-1, 3, 100000);
		}

		return new ArmorProperties(-1, 0, 0);
	}

	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot){
		stack.setItemDamage(0);
	}

	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type){
		if(itemstack.getItem() == BlockList.UraniumHelmet || itemstack.getItem() == BlockList.UraniumChest || itemstack.getItem() == BlockList.UraniumBoots){
			return USM.modid + ":models/armor/UraniumArmor_layer_1.png";
		} else if(itemstack.getItem() == BlockList.UraniumLeggins){
			return USM.modid + ":models/armor/UraniumArmor_layer_2.png";
		} else{
			return null;
		}
	}

	@SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY){

	}

	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot){
		if(this == BlockList.UraniumHelmet){
			return 3;
		}

		if(this == BlockList.UraniumChest){
			return 8;
		}

		if(this == BlockList.UraniumLeggins){
			return 6;
		}

		if(this == BlockList.UraniumBoots){
			return 3;
		}

		return 5;
	}

	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack){
		if(!(player.getTotalArmorValue() < 10)){
			if(Config.FullCheatyArmor == true){
				player.capabilities.disableDamage = true;
			} else{
				if(player.getHealth() < player.getMaxHealth()){
					player.heal(player.getMaxHealth() - player.getHealth());
				}
			}
			if(itemStack.getItemDamage() > 0){
				itemStack.setItemDamage(0);
			}
			if(NightVis){
				player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 5, 0));
			}
			if(Config.ArmorCrashFix == false){
				if(player.isSprinting()){
					player.capabilities.setPlayerWalkSpeed(0.2F);
				} else{
					player.capabilities.setPlayerWalkSpeed(0.15F);
				}
			} else{
				if(player.isSprinting()){
					player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 5, 2));
				}
			}
			player.capabilities.allowFlying = true;
			player.fallDistance = 0;
		} else{
			player.capabilities.allowFlying = false;
			player.capabilities.disableDamage = false;
		}
		return;
	}
	public void addInformation(ItemStack s, EntityPlayer p, List l, boolean is){
		l.add(StatCollector.translateToLocal("UraniumArmor.tooltip"));
	}

	@Optional.Method(modid = "Thaumcraft")
	public boolean showNodes(ItemStack itemstack, EntityLivingBase player){
		if(Goggles){
			return true;
		}
		return false;
	}

	@Optional.Method(modid = "Thaumcraft")
	public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player){
		if(Goggles){
			return true;
		}
		return false;
	}
}