package com.denvys5.uraniumswordmod.machines.uraniumduplicator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;

import com.denvys5.uraniumswordmod.machines.TileEntityMachine;

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
			if(storage.energy <= (this.maxPower - this.getItemPower(this.slots[1])) && this.hasItemPower(this.slots[1])){
				if(!getBattery(this.slots[1])){
					int prevPower = storage.energy;
					storage.energy += getItemPower(this.slots[1]);
					if(prevPower > 0){
						flag1 = true;
						if(this.slots[1] != null){
							this.slots[1].stackSize--;
							if(this.slots[1].stackSize == 0){
								this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
							}
						}
					}
				} else{
					if(this.slots[1].getItemDamage() + this.batteryChargeSpeed < this.slots[1].getMaxDamage()){
						storage.energy += this.batteryChargeSpeed;
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + this.batteryChargeSpeed);
					}
				}
				if(storage.energy >= (this.maxPower - this.batteryChargeSpeed) && getBattery(this.slots[1])){
					int a = this.maxPower - storage.energy;
					if(this.slots[1].getItemDamage() + a < this.slots[1].getMaxDamage()){
						storage.energy = this.maxPower;
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + a);
					}
				}
				if(storage.energy <= (this.maxPower - this.batteryChargeSpeed) && getBattery(this.slots[1])){
					if(this.slots[1].getItemDamage() + this.batteryChargeSpeed >= this.slots[1].getMaxDamage()){
						int a = this.slots[1].getMaxDamage() - this.slots[1].getItemDamage();
						storage.energy += a;
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getMaxDamage());
					}
				}
			}
			if(flag && this.canOperate()){
				Duplicator.updateDuplicatorBlockState(this.canOperate(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			} else{
				Duplicator.updateDuplicatorBlockState(this.canOperate() && flag, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if(this.hasPower() && this.canOperate()){
			if(storage.energy < this.powerUsage){
				this.cookTime+=(storage.energy/this.powerUsage);
				storage.energy = 0;
			}else{
				this.cookTime++;
				storage.energy -= this.powerUsage;
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