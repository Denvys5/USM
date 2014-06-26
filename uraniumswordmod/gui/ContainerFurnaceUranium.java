package assets.uraniumswordmod.gui;

import assets.uraniumswordmod.lib.SlotUraniumFurnace;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
	

	public ContainerFurnaceUranium(InventoryPlayer inventory, TileEntityFurnaceUranium tileentity) {
		this.furnaceUranium = tileentity;
		//Положение слотов
		this.addSlotToContainer(new Slot(tileentity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileentity, 1, 56, 53));
		this.addSlotToContainer(new SlotUraniumFurnace(inventory.player, tileentity, 2, 116, 35));
		//Карманы игрока
		for(int i = 0; i < 9; i++){
				this.addSlotToContainer(new Slot(inventory, i + 9, 8 + i*18, 84));
		}
		
		for(int i = 0; i < 9; i++){
				this.addSlotToContainer(new Slot(inventory, i + 18, 8 + i*18, 102));
		}
		
		for(int i = 0; i < 9; i++){
				this.addSlotToContainer(new Slot(inventory, i + 27, 8 + i*18, 120));
		}

		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 8 + i*18, 142));
		}
	}
	
	public void canCraftingToCrafters(ICrafting icrafting){
		super.addCraftingToCrafters(icrafting);
		icrafting.sendProgressBarUpdate(this, 0, this.furnaceUranium.cookTime);
		icrafting.sendProgressBarUpdate(this, 1, this.furnaceUranium.burnTime);
		icrafting.sendProgressBarUpdate(this, 2, this.furnaceUranium.currentItemBurnTime);
	}
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int slot, int newValue){
		if(slot == 0) this.furnaceUranium.cookTime = newValue;
		if(slot == 1) this.furnaceUranium.burnTime = newValue;
		if(slot == 2) this.furnaceUranium.currentItemBurnTime = newValue;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slot){
		return null;
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {

		return this.furnaceUranium.isUseableByPlayer(entityplayer);
	}

}
