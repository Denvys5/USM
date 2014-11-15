package com.denvys5.uraniumswordmod.machines.poweredgrinder;

import com.denvys5.uraniumswordmod.machines.SlotMachine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPoweredGrinder extends Container{

	private TileEntityPoweredGrinder PoweredGrinder;
	public int lastpower;
	public int lastItemBurnTime;
	public int lastCookTime;

	public ContainerPoweredGrinder(InventoryPlayer inventory, TileEntityPoweredGrinder tileentity){
		this.PoweredGrinder = tileentity;
		// Положение слотов
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 35));
		this.addSlotToContainer(new Slot(tileentity, 1, 8, 56));
		this.addSlotToContainer(new SlotMachine(inventory.player, tileentity, 2, 116, 35));
		// Карманы игрока
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i + 9, 8 + i * 18, 84));
		}

		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i + 18, 8 + i * 18, 102));
		}

		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i + 27, 8 + i * 18, 120));
		}

		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	public void canCraftingToCrafters(ICrafting icrafting){
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.PoweredGrinder.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.PoweredGrinder.power);
		icrafting.sendProgressBarUpdate(this, 2, this.PoweredGrinder.maxPower);
	}

	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			if(this.lastCookTime != this.PoweredGrinder.cookTime){
				icrafting.sendProgressBarUpdate(this, 0, this.PoweredGrinder.cookTime);
			}

			if(this.lastpower != this.PoweredGrinder.power){
				icrafting.sendProgressBarUpdate(this, 1, this.PoweredGrinder.power);
			}
		}
		this.lastCookTime = this.PoweredGrinder.cookTime;
		this.lastpower = this.PoweredGrinder.power;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue){
		if(slot == 0) this.PoweredGrinder.cookTime = newValue;
		if(slot == 1) this.PoweredGrinder.power = newValue;
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int clickedSlotNumber){
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(clickedSlotNumber);
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if(clickedSlotNumber == 2){
				if(!this.mergeItemStack(itemstack1, 3, 39, true)){
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);

			} else if(clickedSlotNumber != 1 && clickedSlotNumber != 0){
				if(PoweredGrinderRecipes.smelting().getSmeltingResult(itemstack1) != null){
					if(!this.mergeItemStack(itemstack1, 0, 1, false)){
						return null;
					}
				} else if(TileEntityPoweredGrinder.hasItemPower(itemstack1)){
					if(!this.mergeItemStack(itemstack1, 1, 2, false)){
						return null;
					}
				} else if(clickedSlotNumber >= 3 && clickedSlotNumber < 30){
					if(!this.mergeItemStack(itemstack1, 30, 39, false)){
						return null;
					}
				} else if(clickedSlotNumber >= 30 && clickedSlotNumber < 39){
					if(!this.mergeItemStack(itemstack1, 3, 30, false)){
						return null;
					}
				}
			} else if(!this.mergeItemStack(itemstack1, 3, 39, false)){
				return null;
			}
			if(itemstack1.stackSize == 0){
				slot.putStack((ItemStack)null);
			} else{
				slot.onSlotChanged();
			}
			if(itemstack1.stackSize == itemstack.stackSize){
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}

	public boolean canInteractWith(EntityPlayer entityplayer){
		return this.PoweredGrinder.isUseableByPlayer(entityplayer);
	}

}
