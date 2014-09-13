package assets.uraniumswordmod.gui;

import assets.uraniumswordmod.lib.UraniumFurnaceRecipes;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denvys5.core.SlotUraniumFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFurnaceUranium extends Container {

	private TileEntityFurnaceUranium furnaceUranium;
	public int lastBurnTime;
	public int lastItemBurnTime;
	public int lastCookTime;

	public ContainerFurnaceUranium(InventoryPlayer inventory,
			TileEntityFurnaceUranium tileentity) {
		this.furnaceUranium = tileentity;
		// Положение слотов
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		this.addSlotToContainer(new SlotUraniumFurnace(inventory.player,
				tileentity, 2, 116, 35));
		// Карманы игрока
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i + 9, 8 + i * 18, 84));
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i + 18, 8 + i * 18, 102));
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i + 27, 8 + i * 18, 120));
		}

		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	public void canCraftingToCrafters(ICrafting icrafting) {
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.furnaceUranium.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.furnaceUranium.burnTime);
		icrafting.sendProgressBarUpdate(this, 2,
				this.furnaceUranium.currentItemBurnTime);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); i++) {
			ICrafting icrafting = (ICrafting) this.crafters.get(i);
			if (this.lastCookTime != this.furnaceUranium.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0,
						this.furnaceUranium.cookTime);
			}

			if (this.lastBurnTime != this.furnaceUranium.burnTime) {
				icrafting.sendProgressBarUpdate(this, 1,
						this.furnaceUranium.burnTime);
			}

			if (this.lastItemBurnTime != this.furnaceUranium.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 2,
						this.furnaceUranium.currentItemBurnTime);
			}
		}
		this.lastCookTime = this.furnaceUranium.cookTime;
		this.lastBurnTime = this.furnaceUranium.burnTime;
		this.lastItemBurnTime = this.furnaceUranium.currentItemBurnTime;
	}

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue) {
		if (slot == 0)
			this.furnaceUranium.cookTime = newValue;
		if (slot == 1)
			this.furnaceUranium.burnTime = newValue;
		if (slot == 2)
			this.furnaceUranium.currentItemBurnTime = newValue;
	}

	public ItemStack transferStackInSlot(EntityPlayer player,
			int clickedSlotNumber) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(clickedSlotNumber);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (clickedSlotNumber == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);

			} else if (clickedSlotNumber != 1 && clickedSlotNumber != 0) {
				if (UraniumFurnaceRecipes.smelting().getSmeltingResult(
						itemstack1) != null) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}
				} else if (TileEntityFurnaceUranium.isItemFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return null;
					}
				} else if (clickedSlotNumber >= 3 && clickedSlotNumber < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (clickedSlotNumber >= 30 && clickedSlotNumber < 39) {
					if (!this.mergeItemStack(itemstack1, 3, 30, false)) {
						return null;
					}
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(player, itemstack1);
		}
		return itemstack;
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {

		return this.furnaceUranium.isUseableByPlayer(entityplayer);
	}

}
