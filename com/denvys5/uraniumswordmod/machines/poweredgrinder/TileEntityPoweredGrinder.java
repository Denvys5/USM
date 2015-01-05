package com.denvys5.uraniumswordmod.machines.poweredgrinder;

import net.minecraft.item.ItemStack;
import cofh.api.energy.EnergyStorage;

import com.denvys5.uraniumswordmod.machines.TileEntityMachine;

public class TileEntityPoweredGrinder extends TileEntityMachine{

	public TileEntityPoweredGrinder(){
		super(maxPower, powerUsage*2);
		this.storage = new EnergyStorage(maxPower, powerUsage*2);
		this.slots_top = new int[]{0, 2};
		this.slots_bottom = new int[]{0, 2};
		this.slots_sides = new int[]{0, 2};
		this.slots = new ItemStack[3];
		this.resultSlot = 2;
		this.fuelSlot = 1;
		this.ingredSlot = 0;
		this.machineSpeed = 80;
		this.batteryChargeSpeed = 100;
		this.powerUsage = 32;
		this.maxPower = 32000;
	}

	
	
	public String getInventoryName(){
		return this.hasCustomInventoryName() ? this.localizedName : "container.PoweredGrinder";
	}

	public int getRequiredPowerForCrafting(ItemStack itemstack){
		if(this.slots[0] == null){
			return 0;
		} else{
			return PoweredGrinderRecipes.smelting().getFromPowerList(itemstack);
		}
	}

	public void updateEntity(){
		boolean flag = hasPower();
		boolean flag1 = false;
		if(operationSpeed() != 0){
			this.machineSpeed = operationSpeed();
		}
		if(!this.worldObj.isRemote){
			if(this.power <= (this.maxPower - this.getItemPower(this.slots[1])) && this.hasItemPower(this.slots[1])){
				if(!getBattery(this.slots[1])){
					int prevPower = this.power;
					this.power += getItemPower(this.slots[1]);
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
						this.power += this.batteryChargeSpeed;
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + this.batteryChargeSpeed);
					}
				}
				if(this.power >= (this.maxPower - this.batteryChargeSpeed) && getBattery(this.slots[1])){
					int a = this.maxPower - this.power;
					if(this.slots[1].getItemDamage() + a < this.slots[1].getMaxDamage()){
						this.power = this.maxPower;
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + a);
					}
				}
				if(this.power <= (this.maxPower - this.batteryChargeSpeed) && getBattery(this.slots[1])){
					if(this.slots[1].getItemDamage() + this.batteryChargeSpeed >= this.slots[1].getMaxDamage()){
						int a = this.slots[1].getMaxDamage() - this.slots[1].getItemDamage();
						this.power += a;
						this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getMaxDamage());
					}
				}
			}
			if(flag && this.canOperate()){
				PoweredGrinder.updatePoweredGrinderBlockState(this.canOperate(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			} else{
				PoweredGrinder.updatePoweredGrinderBlockState(this.canOperate() && flag, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if(this.hasPower() && this.canOperate()){
			if(this.power < this.powerUsage){
				this.cookTime+=(this.power/this.powerUsage);
				this.power = 0;
			}else{
				this.cookTime++;
				this.power -= this.powerUsage;
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
	
	public boolean canOperate(){
		if(this.slots[0] == null){
			return false;
		} else{
			ItemStack itemstack = PoweredGrinderRecipes.smelting().getSmeltingResult(this.slots[this.ingredSlot]);
			if(itemstack == null) return false;
			if(this.slots[2] == null) return true;
			if(!this.slots[2].isItemEqual(itemstack)) return false;
			int result = this.slots[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public void operateItem(){
		if(this.canOperate()){
			ItemStack itemstack = PoweredGrinderRecipes.smelting().getSmeltingResult(this.slots[this.ingredSlot]);
			if(this.slots[2] == null){
				this.slots[2] = itemstack.copy();
			} else if(this.slots[2].isItemEqual(itemstack)){
				this.slots[2].stackSize += itemstack.stackSize;
			}
			this.slots[0].stackSize--;
			if(this.slots[0].stackSize <= 0){
				this.slots[0] = null;
			}
		}
	}
	
	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j){
		if(itemstack != null){
			if(i == this.ingredSlot && PoweredGrinderRecipes.smelting().getSmeltingResult(itemstack) != null){
				return true;
			}
		}
		return false;
	}
}