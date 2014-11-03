package com.denvys5.uraniumswordmod.machines.uraniumduplicator;

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
import net.minecraftforge.oredict.OreDictionary;

import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.core.TileEntityCore;

public class TileEntityDuplicator extends TileEntityCore{

	private String localizedName;
	// Доступ к слотам
	private static final int[] slots_top = new int[] { 0, 2 };
	private static final int[] slots_bottom = new int[] { 0, 2 };
	private static final int[] slots_sides = new int[] { 0, 2 };
	private ItemStack[] slots = new ItemStack[3];
	private static final int resultSlot = 2;
	private static final int fuelSlot = 1;
	private static final int ingredSlot = 0;


	// Время операции в тиках
	public int grinderSpeed = 80;
	public static int batteryChargeSpeed = 100;

	public static int powerUsage = 32;
	public int power;
	public static final int maxPower = 32000;
	public int cookTime;

	public int getSizeInventory() {
		return this.slots.length;
	}

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.localizedName : "container.Duplicator";
	}

	public boolean hasCustomInventoryName() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}

	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;

	}

	public ItemStack getStackInSlot(int i) {
		return this.slots[i];
	}

	public ItemStack decrStackSize(int i, int j) {
		if (this.slots[i] != null) {
			ItemStack itemstack;
			if (this.slots[i].stackSize <= j) {
				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;
			} else {
				itemstack = this.slots[i].splitStack(j);
				if (this.slots[i].stackSize == 0) {
					this.slots[i] = null;
				}
				return itemstack;
			}
		}
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.slots[i] != null) {
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}
		return null;
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;

		if (itemstack != null
				&& itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	public boolean isInvNameLocalized() {
		return false;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		this.power = nbt.getShort("Power");
		this.cookTime = nbt.getShort("CookTime");
		if (nbt.hasKey("CustomName")) {
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("Power", (short) this.power);
		nbt.setShort("CookTime", (short) this.cookTime);

		NBTTagList list = new NBTTagList();
		for (int i = 0; i < this.slots.length; i++) {
			if (this.slots[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte) i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		nbt.setTag("Items", list);
		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.localizedName);
		}
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	public boolean hasPower() {
		return this.power > this.powerUsage;
	}
	
	public int getRequiredPowerForCrafting(ItemStack itemstack){
		if (this.slots[0] == null) {
			return 0;
		} else {
			return DuplicatorRecipes.smelting().getFromPowerList(itemstack);
		}
	}
	
	public int operationSpeed(){
		return getRequiredPowerForCrafting(this.slots[0])/powerUsage;
	}

	public void updateEntity() {
		boolean flag = this.power > this.powerUsage;
		boolean flag1 = false;
		if(operationSpeed() != 0){
			this.grinderSpeed = operationSpeed();
		}
		if (!this.worldObj.isRemote) {
				if (this.power <= (this.maxPower - this.getItemPower(this.slots[1])) && this.hasItemPower(this.slots[1])) {
					if(!getBattery(this.slots[1])){
						int prevPower = this.power;
						this.power += getItemPower(this.slots[1]);
						if (prevPower > 0) {
							flag1 = true;
							if (this.slots[1] != null) {
								this.slots[1].stackSize--;
								if (this.slots[1].stackSize == 0) {
									this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
								}
							}
						}
					}else{
						if(this.slots[1].getItemDamage() < this.slots[1].getMaxDamage()){
							this.power += this.batteryChargeSpeed;
							this.slots[1] = new ItemStack(this.slots[1].getItem(), this.slots[1].stackSize, this.slots[1].getItemDamage() + this.batteryChargeSpeed);
							/*if(this.slots[1].getItemDamage() >= this.slots[1].getMaxDamage()){
								this.slots[1].stackSize--;
							}*/
						}
					}
				}
			if(flag && this.canGrind()) {
				Duplicator.updateDuplicatorBlockState(this.canGrind(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}else{
				Duplicator.updateDuplicatorBlockState(this.canGrind(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if (this.hasPower() && this.canGrind()) {
			this.cookTime++;
			this.power -= this.powerUsage;
			if (this.cookTime == this.grinderSpeed) {
				this.cookTime = 0;
				this.grindItem();
				flag1 = true;
			}
		}else if(!this.canGrind()){
			this.cookTime = 0;
		}
		if (flag1) {
			this.markDirty();
		}
	}
	
	public int craftTime(ItemStack itemstack){
		return getRequiredPowerForCrafting(itemstack)/this.powerUsage;
	}

	private boolean canGrind() {
		if (this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemstack = DuplicatorRecipes.smelting().getSmeltingResult(this.slots[0]);
			if(itemstack == null) return false;
			if(this.slots[2] == null) return true;
			if(!this.slots[2].isItemEqual(itemstack)) return false;
			int result = this.slots[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}

	public void grindItem(){
		if(this.canGrind()){
			ItemStack itemstack = DuplicatorRecipes.smelting().getSmeltingResult(this.slots[0]);
			if (this.slots[2] == null) {
				this.slots[2] = itemstack.copy();
			}else if(this.slots[2].isItemEqual(itemstack)){
				this.slots[2].stackSize += itemstack.stackSize;
			}
			this.slots[0].stackSize--;
			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}

	public static int getItemPower(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		} else {
			Item item = itemstack.getItem();
			Block block = Block.getBlockFromItem(item);
			// Время горения в тиках
			if(item == Items.glowstone_dust) return 1000;
			if(item == Items.redstone) return 500;
			if(block == Blocks.redstone_block) return 4500;
			if(block == Blocks.glowstone) return 4000;
			
			return 0;
		}
	}
	
	public static boolean getBattery(ItemStack itemstack){
		if (itemstack == null) {
			return false;
		} else {
			Item item = itemstack.getItem();
			if(item == BlockList.BasicBattery) return true;
			return false;
		}
	}

	public static boolean hasItemPower(ItemStack itemstack) {
		if(!getBattery(itemstack)){
			return getItemPower(itemstack) > 0;
		}else{
			return true;
		}
	}

	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		if (itemstack != null) {
			if (i == this.ingredSlot && DuplicatorRecipes.smelting().getSmeltingResult(itemstack) != null) {
				return true;
			}
		}
		return false;
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		if (i == this.resultSlot) {
			return true;
		}else{
			return false;
		}
	}

	public int getPowerRemainingScaled(int i) {
		return this.power * i / this.maxPower;
	}

	public int getGrinderProgressScaled(int i) {
		if(operationSpeed() != 0){
			this.grinderSpeed = operationSpeed();
		}
		return this.cookTime * i / this.grinderSpeed;

	}

	public void openInventory() {}
	public void closeInventory() {}
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {return false;}

}
