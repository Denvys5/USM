package com.denvys5.uraniumswordmod.machines.uraniumduplicator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;

import com.denvys5.uraniumswordmod.api.TileEntityMachine;

public class TileEntityDuplicator extends TileEntityMachine{

	public TileEntityDuplicator(){
		super();
		this.maxReceive = 64;
		this.powerUsage = 32;
		this.maxPower = 32000;
		this.slots_top = new int[]{0, 2};
		this.slots_bottom = new int[]{0, 2};
		this.slots_sides = new int[]{0, 2};
		this.slots = new ItemStack[3];
		this.resultSlot = 2;
		this.fuelSlot = 1;
		this.ingredSlot = 0;
		this.machineSpeed = 80;
		this.batteryChargeSpeed = 100;
		this.storage = new EnergyStorage(maxPower, maxReceive);
	}
	
	public String getInventoryName(){
		return this.hasCustomInventoryName() ? this.localizedName : "container.Duplicator";
	}
	
	public int getRequiredPowerForCrafting(ItemStack itemstack){
		if(this.slots[0] == null){
			return 0;
		} else{
			return DuplicatorRecipes.smelting().getFromPowerList(itemstack);
		}
	}

	public void updateEntity(){
		boolean flag = hasPower();
		boolean flag1 = false;
		if(operationSpeed() != 0){
			this.machineSpeed = operationSpeed();
		}
		if(!this.worldObj.isRemote){
			flag1 = operateFuelandBattery(flag1);
			if(flag && this.canOperate()){
				Duplicator.updateDuplicatorBlockState(this.canOperate(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			} else{
				Duplicator.updateDuplicatorBlockState(this.canOperate() && flag, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if(this.hasPower() && this.canOperate()){
			if(storage.getEnergyStored() < this.powerUsage){
				this.cookTime+=(storage.getEnergyStored()/this.powerUsage);
				storage.setEnergyStored(0);
			}else{
				this.cookTime++;
				storage.setEnergyStored(storage.getEnergyStored() - this.powerUsage);
			}
			
			if(this.cookTime == this.machineSpeed){
				this.cookTime = 0;
				this.operateItem();
				flag1 = true;
			}
		} else if(!this.canOperate()){
			this.cookTime = 0;
		}
		if(flag1){
			this.markDirty();
		}
	}

	@Override
	public ItemStack getResult(ItemStack itemstack){
		return DuplicatorRecipes.smelting().getSmeltingResult(itemstack);
	}
}