package com.denvys5.uraniumswordmod.machines;

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

public abstract class TileEntityCore extends TileEntity implements ISidedInventory, IEnergyStorage, IEnergyHandler{
	public EnergyStorage storage;// = new EnergyStorage(maxPower, powerUsage*2);
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
	public int power;
	public static int maxPower;
	public int cookTime;

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
			Item item = itemstack.getItem();
			if(item == USMItems.BasicBattery) return true;
			return false;
		}
	}

	public static int getItemPower(ItemStack itemstack){
		if(itemstack == null){
			return 0;
		} else{
			Item item = itemstack.getItem();
			Block block = Block.getBlockFromItem(item);
			if(item == Items.glowstone_dust) return 1000;
			if(item == Items.redstone) return 500;
			if(block == Blocks.redstone_block) return 4500;
			if(block == Blocks.glowstone) return 4000;
			
			return 0;
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
	public String getInventoryName(){
		return null;
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

	@Override
	public void openInventory(){

	}

	@Override
	public void closeInventory(){

	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_){
		return false;
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
		return this.power > this.powerUsage;
	}

	public int getRequiredPowerForCrafting(ItemStack itemstack){
		return 0;
	}

	public int operationSpeed(){
		return getRequiredPowerForCrafting(this.slots[0]) / powerUsage;
	}

	public int craftTime(ItemStack itemstack){
		return getRequiredPowerForCrafting(itemstack) / this.powerUsage;
	}

	public int getPowerRemainingScaled(int i){
		return this.power * i / this.maxPower;
	}

	public int getCraftingProgressScaled(int i){
		return this.cookTime * i / this.machineSpeed;
	}

	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		for(int i = 0; i < list.tagCount(); i++){
			NBTTagCompound compound = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			if(b >= 0 && b < this.slots.length){
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		this.power = nbt.getShort("Power");
		this.cookTime = nbt.getShort("CookTime");
		if(nbt.hasKey("CustomName")){
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setShort("Power", (short)this.power);
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

	// ThermalExpansion Part

	protected int maxReceive;
	protected int maxExtract;

	public TileEntityCore(int capacity){
		this(capacity, capacity, capacity);
	}

	public TileEntityCore(int capacity, int maxTransfer){
		this(capacity, maxTransfer, maxTransfer);
	}

	public TileEntityCore(int capacity, int maxReceive, int maxExtract){
		this.maxPower = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from){
		return true;
	}

	@Override
	public int getEnergyStored(ForgeDirection from){
		return this.storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from){
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public int getEnergyStored(){
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(){
		return this.storage.getMaxEnergyStored();
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate){
		if (!simulate) {
			if(!(this.power >= this.maxPower)){
				if(!(this.power + maxReceive >= this.maxPower)){
					this.power += maxReceive;
				}else{
					int a = this.maxPower - this.power;
					this.power = this.maxPower;
					return a;
				}
			}
		}
		return maxReceive;
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate){
		return 0;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate){
		if (!simulate) {
			if(!(this.power >= this.maxPower)){
				if(!(this.power + maxReceive >= this.maxPower)){
					this.power += maxReceive;
				}else{
					int a = this.maxPower - this.power;
					this.power = this.maxPower;
					return a;
				}
			}
		}
		return maxReceive;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return 0;
	}
}