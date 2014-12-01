package com.denvys5.uraniumswordmod.machines.windmill;

import com.denvys5.uraniumswordmod.machines.TileEntityCore;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinderRecipes;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWindmill extends TileEntityCore{
	public TileEntityWindmill(){
		super(0);
		this.localizedName = "Windmill";
		this.slots_top = new int[]{0, 1};
		this.slots_bottom = new int[]{0, 1};
		this.slots_sides = new int[]{0, 1};
		this.slots = new ItemStack[2];
		this.resultSlot = 1;
		this.ingredSlot = 0;
		this.batteryChargeSpeed = 100;
		this.maxPower = 10000;
	}

	public int rotation = 0;
	public int powerPerRotation = 4;
	
	public void updateEntity(){
		if(this.getWorldObj().getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) > 6 && this.getWorldObj().getTileEntity(this.xCoord, this.yCoord-7, this.zCoord) != null && this.getWorldObj().getTileEntity(this.xCoord, this.yCoord-7, this.zCoord).getBlockMetadata() == 5){
			boolean flag1 = false;
			this.rotation++;
			this.power += this.powerPerRotation;
			if(this.power > this.maxPower) this.power = this.maxPower;
			if(this.power > 0){
				if(canOperate()){
					if(this.slots[0].getItemDamage() >= 0){
						if(this.power >= this.batteryChargeSpeed){
							this.power -= this.batteryChargeSpeed;
							this.slots[0] = new ItemStack(this.slots[0].getItem(), this.slots[0].stackSize, this.slots[0].getItemDamage() - this.batteryChargeSpeed);	
						}else if(this.power < this.batteryChargeSpeed){
							int power1 = (int)this.power;
							this.power -= power1;
							this.slots[0] = new ItemStack(this.slots[0].getItem(), this.slots[0].stackSize, this.slots[0].getItemDamage() - power1);	
						}
					}
				}
			}
			if(this.slots[0] != null){
				if(this.slots[0].getItemDamage() == 0){
					this.operateItem();
					flag1 = true;
				}
			}
			if(flag1){
				this.markDirty();
			}
		}
	}
	
	public void operateItem(){
		if(this.canOperate()){
			if(this.slots[1] == null){
				this.slots[1] = new ItemStack(this.slots[0].getItem());
				this.slots[0] = null;
			}
		}
	}
	
	public boolean canOperate(){
		if(this.slots[0] == null){
			return false;
		} else{
			if(this.slots[1] == null){
				return true;
			}else{
				return false;
			}
			
		}
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j){
		return false;
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
		if(nbt.hasKey("CustomName")){
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setShort("Power", (short)this.power);

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
	
	public boolean hasPower(){
		return this.power > 0;
	}
	
	@Override
	public ItemStack getStackInSlot(int i){
		if(i == 0) return this.slots[0];
		if(i == 1) return this.slots[1];
		return null;
	}
	
}
