package com.denvys5.uraniumswordmod.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;

import com.denvys5.uraniumswordmod.item.USMItems;

public abstract class TileEntityMachine extends TileEntity implements ISidedInventory, IEnergyHandler{
	public String localizedName;
	public static int[] slots_top;
	public static int[] slots_bottom;
	public static int[] slots_sides;
	public ItemStack[] slots;
	public static int resultSlot;
	public static int fuelSlot;
	public static int ingredSlot;
	public int machineSpeed;
	public static int batteryChargeSpeed;
	public static int powerUsage;
	public static int maxPower;
	public int cookTime;	
	public EnergyStorage storage;
	public int maxReceive;
	protected int antilagTimer = 0;
	
	public abstract String getInventoryName();
	public abstract int getRequiredPowerForCrafting(ItemStack itemstack);
	public abstract void updateEntity();
	public abstract ItemStack getResult(ItemStack itemstack);
	
	
	public TileEntityMachine(){
		
	}
	
	public boolean isInvNameLocalized(){
		return false;
	}

	public static boolean hasItemPower(ItemStack itemstack){
		if(!getBattery(itemstack)){
			return getItemPower(itemstack) > 0;
		} else{
			return true;
		}
	}

	public static boolean getBattery(ItemStack itemstack){
		if(itemstack == null){
			return false;
		} else{
			if(itemstack.getItem() == USMItems.BasicBattery) return true;
			return false;
		}
	}

	public static int getItemPower(ItemStack itemstack){
		if(itemstack == null) return 0;
		Item item = itemstack.getItem();
		if(item == Items.glowstone_dust) return 1000;
		if(item == Items.redstone) return 500;
		Block block = Block.getBlockFromItem(item);
		if(block == Blocks.redstone_block) return 4500;
		if(block == Blocks.glowstone) return 4000;
			
		return 0;
	}

	@Override
	public int getSizeInventory(){
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i){
		return this.slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j){
		if(this.slots[i] != null){
			ItemStack itemstack;
			if(this.slots[i].stackSize <= j){
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			} else{
				itemstack = this.slots[i].splitStack(j);
				if(this.slots[i].stackSize == 0){
					this.slots[i] = null;
				}
				return itemstack;
			}
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i){
		if(this.slots[i] != null){
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack){
		this.slots[i] = itemstack;

		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()){
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public boolean hasCustomInventoryName(){
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public void setGuiDisplayName(String displayName){
		this.localizedName = displayName;
	}

	@Override
	public int getInventoryStackLimit(){
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer){
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	
	public boolean canInsertItem(int i, ItemStack itemstack, int j){
		return isItemValidForSlot(i, itemstack);
	}

	public void openInventory(){}

	public void closeInventory(){}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1){
		return var1 == 0 ? this.slots_bottom : (var1 == 1 ? this.slots_top : this.slots_sides);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j){
		if(i == this.resultSlot) return true;
		return false;
	}

	public boolean hasPower(){
		return storage.getEnergyStored() > this.powerUsage;
	}

	public int operationSpeed(){
		return getRequiredPowerForCrafting(this.slots[0]) / powerUsage;
	}

	public int craftTime(ItemStack itemstack){
		return getRequiredPowerForCrafting(itemstack) / this.powerUsage;
	}

	public int getPowerRemainingScaled(int i){
		return storage.getEnergyStored() * i / this.maxPower;
	}

	public int getCraftingProgressScaled(int i){
		return this.cookTime * i / this.machineSpeed;
	}
	
	public boolean canOperate(){
		if(this.slots[0] == null){
			return false;
		} else{
			ItemStack itemstack = getResult(this.slots[this.ingredSlot]);
			if(itemstack == null) return false;
			if(this.slots[2] == null) return true;
			if(!this.slots[2].isItemEqual(itemstack)) return false;
			int result = this.slots[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}
	
	public void operateItem(){
		if(this.canOperate()){
			ItemStack itemstack = getResult(this.slots[this.ingredSlot]);
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack){
		if(itemstack != null){
			if(i == ingredSlot){
				if(getResult(itemstack) != null){
					return true;
				}
			}
		}
		return false;
	}

	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound compound = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			if(b >= 0 && b < this.slots.length){
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		//this.power = nbt.getShort("Power");
		this.cookTime = nbt.getShort("CookTime");
		if(nbt.hasKey("CustomName")){
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		//nbt.setShort("Power", (short)this.power);
		nbt.setShort("CookTime", (short)this.cookTime);

		NBTTagList list = new NBTTagList();
		for(int i = 0; i < this.slots.length; i++){
			if(this.slots[i] != null){
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte)i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		nbt.setTag("Items", list);
		if(this.hasCustomInventoryName()){
			nbt.setString("CustomName", this.localizedName);
		}
	}
	
	public void operateBattery(){
		if(storage.getEnergyStored() >= (this.maxPower - this.batteryChargeSpeed) && getBattery(this.slots[1])){
			int a = this.maxPower - storage.getEnergyStored();
			if(this.slots[1].getItemDamage() + a < this.slots[1].getMaxDamage()){
				storage.setEnergyStored(this.maxPower);
				this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + a);
			}
		}
		if(storage.getEnergyStored() <= (this.maxPower - this.batteryChargeSpeed) && getBattery(this.slots[1])){
			if(this.slots[1].getItemDamage() + this.batteryChargeSpeed >= this.slots[1].getMaxDamage()){
				int a = this.slots[1].getMaxDamage() - this.slots[1].getItemDamage();
				storage.setEnergyStored(storage.getEnergyStored() + a);
				this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getMaxDamage());
			}
		}
	}
	
	public void operateBatteryFirst(){
		if(this.slots[1].getItemDamage() + this.batteryChargeSpeed < this.slots[1].getMaxDamage()){
			storage.setEnergyStored(storage.getEnergyStored() + this.batteryChargeSpeed);
			this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + this.batteryChargeSpeed);
		}
	}
	
	public boolean operateFuelandBattery(boolean flag1){
		if(storage.getEnergyStored() <= (this.maxPower - this.getItemPower(this.slots[1])) && this.hasItemPower(this.slots[1])){
			if(!getBattery(this.slots[1])){
				int prevPower = storage.getEnergyStored();
				storage.setEnergyStored(storage.getEnergyStored() + getItemPower(this.slots[1]));
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
				operateBatteryFirst();
			}
			operateBattery();
		}
		return flag1;
	}

	
	// ThermalExpansion Part
	@Override
	public boolean canConnectEnergy(ForgeDirection from){
		return true;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate){
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate){
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from){
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from){
		return storage.getMaxEnergyStored();
	}
}