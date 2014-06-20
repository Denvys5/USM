package assets.uraniumswordmod;

import assets.uraniumswordmod.block.FurnaceUranium;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFurnaceUranium extends TileEntity implements ISidedInventory {

	private String localizedName;
	//Доступ к слотам
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};
	private ItemStack[] slots = new ItemStack[3];
	
	public int furnaceSpeed = 200;
	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;
	
	public int getSizeInventory(){
		return this.slots.length;
	}
	public String getInvName(){
		return this.isInvNameLocalised() ? this.localizedName : "container.furnaceUranium";
	}
	public boolean isInvNameLocalised(){
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
		
		
	}
	
	public ItemStack getStackInSlot(int i) {
		return null;
	}
	
	public ItemStack decrStackSize(int i, int j) {
		return null;
	}
	
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}
	
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		
	}
	
	public boolean isInvNameLocalized() {
		return false;
	}
	
	public int getInventoryStackLimit() {
		return 0;
	}
	
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return false;
	}
	
	public void openChest() {
		
	}
	
	public void closeChest() {
		
	}
	
	public boolean isBurning(){
		return this.burnTime > 0;
	}
	
	public void updateEntity(){
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		if(this.burnTime > 0){
			this.burnTime--;
		}
		if(!this.worldObj.isRemote){
			if(this.burnTime == 0 && this.canSmelt()){
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);
				if(this.burnTime > 0){
					flag1 = true;
					if(this.slots[1] != null){
						this.slots[1].stackSize--;
						if(this.slots[1].stackSize == 0){
							this.slots[1] = this.slots[1].getItem().getContainerItemStack(this.slots[1]);
						}
					}
				}
			}
			if(flag != this.isBurning()){
				FurnaceUranium.updateFurnaceUraniumBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if(this.isBurning() && this.canSmelt()){
			this.cookTime++;
			if(this.cookTime == this.furnaceSpeed){
				this.cookTime = 0;
				this.smeltItem();
				flag1 = true;
			}
		}else{
			this.cookTime = 0;
		}
		if(flag1){
			this.onInventoryChanged();
		}

	}
	
	public static int getItemBurnTime(ItemStack itemstack){
		if(itemstack == null){
			return 0;
		}else{
			int i = itemstack.getItem().itemID;
			
			Item item = itemstack.getItem();
			
			if(item instanceof ItemBlock && Block.blocksList[i] != null){
				Block block = Block.blocksList[i];
				if(block == block.woodSingleSlab){
					return 150;
				}
				if(block.blockMaterial == Material.wood){
					return 300;
				}
				if(block == Block.coalBlock){
					return 1600;
				}
			}
			
			if(item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("WOOD")) return 200;
			if(item instanceof ItemSword && ((ItemSword) item).getToolMaterialName().equals("WOOD")) return 200;
			if(item instanceof ItemHoe && ((ItemHoe) item).getMaterialName().equals("WOOD")) return 200;
			if (itemstack.itemID == Item.stick.itemID) return 100;
			if (itemstack.itemID == Item.coal.itemID) return 1600;
			if (itemstack.itemID == Item.bucketLava.itemID) return 20000;
			if (itemstack.itemID == Block.sapling.blockID) return 100;
			if (itemstack.itemID == Item.blazeRod.itemID) return 2400;
			
			
			if (itemstack.itemID == USM.ingoturanium.itemID) return 100;
			return GameRegistry.getFuelValue(itemstack);
		}
	}
	
	public static boolean isItemFuel(ItemStack itemstack){
		return getItemBurnTime(itemstack) > 0;
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 2 ? false : (i == 1 ? isItemFuel(itemstack));
	}
	
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}
	
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}
	
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack.itemID == Item.bucketEmpty.itemID;
	}

}
