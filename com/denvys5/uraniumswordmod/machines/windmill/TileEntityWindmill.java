package com.denvys5.uraniumswordmod.machines.windmill;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cofh.api.energy.EnergyStorage;

import com.denvys5.uraniumswordmod.item.USMItems;
import com.denvys5.uraniumswordmod.machines.TileEntityGenerator;

public class TileEntityWindmill extends TileEntityGenerator{
	public TileEntityWindmill(){
		super();
		this.localizedName = "Windmill";
		this.slots_top = new int[]{0, 1};
		this.slots_bottom = new int[]{0, 1};
		this.slots_sides = new int[]{0, 1};
		this.slots = new ItemStack[2];
		this.resultSlot = 1;
		this.ingredSlot = 0;
		this.batteryChargeSpeed = 100;
		this.maxPower = 10000;
		this.storage = new EnergyStorage(maxPower, batteryChargeSpeed);
	}

	public int rotation = 0;
	public int powerPerRotation = 4;
	
	public void updateEntity(){
		if(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) > 6 && this.getWorldObj().getTileEntity(this.xCoord, this.yCoord-7, this.zCoord) != null && this.getWorldObj().getTileEntity(this.xCoord, this.yCoord-7, this.zCoord).getBlockMetadata() == 5){
			boolean flag1 = false;
			this.rotation++;
			if(this.slots[0] != null){
				if(this.slots[0].getItemDamage() == 0){
					this.operateItem();
					flag1 = true;
				}
			}
			storage.energy += this.powerPerRotation;
			if(storage.energy > this.maxPower) storage.energy = this.maxPower;
			if(storage.energy > 0){
				if(canOperate()){
					if(this.slots[0].getItemDamage() >= 0){
						if(storage.energy >= this.batteryChargeSpeed){
							storage.energy -= this.batteryChargeSpeed;
							this.slots[0] = new ItemStack(this.slots[0].getItem(), this.slots[0].stackSize, this.slots[0].getItemDamage() - this.batteryChargeSpeed);	
						}else if(storage.energy < this.batteryChargeSpeed){
							int power1 = (int)storage.energy;
							storage.energy -= power1;
							this.slots[0] = new ItemStack(this.slots[0].getItem(), this.slots[0].stackSize, this.slots[0].getItemDamage() - power1);	
						}
					}
				}
			}
			if(flag1){
				this.markDirty();
			}
		}
	}

	@Override
	public String getInventoryName(){
		return this.hasCustomInventoryName() ? this.localizedName : "container.Windmill";
	}

	@Override
	public ItemStack getResult(ItemStack itemStack){
		if(itemStack.getItem() == USMItems.BasicBattery){
			return itemStack;
		}
		return null;
	}

}
