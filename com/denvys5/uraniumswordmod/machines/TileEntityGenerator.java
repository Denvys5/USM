package com.denvys5.uraniumswordmod.machines;

import net.minecraft.entity.player.EntityPlayer;
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

public abstract class TileEntityGenerator extends TileEntity implements ISidedInventory, IEnergyHandler{
	public EnergyStorage storage;
	public String localizedName;
	public static int[] slots_top;
	public static int[] slots_bottom;
	public static int[] slots_sides;
	public ItemStack[] slots;
	public static int resultSlot;
	public static int fuelSlot;
	public static int ingredSlot;
	public static int batteryChargeSpeed;
	public static int powerUsage;
	public static int maxPower;
	
	public abstract void updateEntity();
	public abstract ItemStack getResult(ItemStack itemStack);

	public boolean isInvNameLocalized(){
		return false;
	}

	public static boolean hasItemPower(ItemStack itemstack){
		if(getBattery(itemstack)){
			return true;
		}
		return false;
	}

	public static boolean getBattery(ItemStack itemstack){
		if(itemstack == null){
			return false;
		} else{
			Item item = itemstack.getItem();
			if(item == USMItems.BasicBattery) return true;
			return false;
		}
	}

	@Override
	public int getSizeInventory(){
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i){
		return this.slots[i];
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j){
		return isItemValidForSlot(i, itemstack);
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
	public abstract String getInventoryName();

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

	@Override
	public void openInventory(){

	}

	@Override
	public void closeInventory(){

	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1){
		return var1 == 0 ? this.slots_bottom : (var1 == 1 ? this.slots_top : this.slots_sides);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j){
		if(i == this.resultSlot){
			return true;
		} else{
			return false;
		}
	}

	public boolean hasPower(){
		return storage.energy > this.powerUsage;
	}

	public int getPowerRemainingScaled(int i){
		return storage.energy * i / this.maxPower;
	}
	
	public boolean canOperate(){
		if(this.slots[0] == null){
			return false;
		} else{
			ItemStack itemstack = getResult(this.slots[this.ingredSlot]);
			if(itemstack == null) return false;
			if(this.slots[this.resultSlot] == null) return true;
			if(!this.slots[this.resultSlot].isItemEqual(itemstack)) return false;
			int result = this.slots[this.resultSlot].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}
	
	public void operateItem(){
		if(this.canOperate()){
			ItemStack itemstack = getResult(this.slots[this.ingredSlot]);
			if(this.slots[this.resultSlot] == null){
				this.slots[this.resultSlot] = itemstack.copy();
			} else if(this.slots[this.resultSlot].isItemEqual(itemstack)){
				this.slots[2].stackSize += itemstack.stackSize;
			}
			this.slots[this.ingredSlot].stackSize--;
			if(this.slots[this.ingredSlot].stackSize <= 0){
				this.slots[this.ingredSlot] = null;
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
		if(nbt.hasKey("CustomName")){
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
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

	
	// ThermalExpansion Part	@Override
	public boolean canConnectEnergy(ForgeDirection from){
		return true;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate){
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate){
		return storage.extractEnergy(maxExtract, simulate);
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