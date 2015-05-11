package com.denvys5.uraniumswordmod.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.ItemEnergyContainer;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.api.USMItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BasicBattery extends USMItem implements IEnergyContainerItem{
	
	protected int capacity;
	protected int maxReceive;
	protected int maxExtract;
	private IIcon Full;
	private IIcon Empty;
	
	public BasicBattery(int maxCharge){
		super();
		this.setMaxDamage(maxCharge);
		this.maxStackSize = 1;
		this.setHasSubtypes(true);
		this.capacity = maxCharge;
		this.maxReceive = 100;
		this.maxExtract = 100;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg){
		this.itemIcon = reg.registerIcon(USM.modid + ":BasicBattery");
		this.Full = reg.registerIcon(USM.modid + ":BasicBattery");
		this.Empty = reg.registerIcon(USM.modid + ":BasicBattery_empty");

	}

	public IIcon getIconFromDamage(int i){
		if(i == this.getMaxDamage()) return Empty;
		return Full;
	}

	/* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate){
		if(container.stackTagCompound == null){
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

		if(!simulate){
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		container.setItemDamage(energy);
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate){
		if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")){
			return 0;
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));

		if(!simulate){
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		container.setItemDamage(energy);
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container){
		if(container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")){
			return 0;
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container){
		return capacity;
	}
}
