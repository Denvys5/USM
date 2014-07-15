package assets.uraniumswordmod.tile;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.block.FurnaceUranium;
import assets.uraniumswordmod.lib.UraniumFurnaceRecipes;
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
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFurnaceUranium extends TileEntity implements
		ISidedInventory {

	private String localizedName;
	// Доступ к слотам
	private static final int[] slots_top = new int[] { 0, 1, 2 };
	private static final int[] slots_bottom = new int[] { 0, 1, 2 };
	private static final int[] slots_sides = new int[] { 1, 0, 2 };
	private ItemStack[] slots = new ItemStack[3];
	private static final int resultSlot = 2;
	private static final int fuelSlot = 1;
	private static final int ingredSlot = 0;

	// Время операции в тиках
	// Рабочий
	 public int furnaceSpeed = 200000;
	// Тестовый
	//public int furnaceSpeed = 20;

	public int burnTime;
	public int currentItemBurnTime;
	public int cookTime;

	public int getSizeInventory() {
		return this.slots.length;
	}

	public String getInvName() {
		return this.isInvNameLocalised() ? this.localizedName
				: "container.furnaceUranium";
	}

	public boolean isInvNameLocalised() {
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
		NBTTagList list = nbt.getTagList("Items");
		this.slots = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = (NBTTagCompound) list.tagAt(i);
			byte b = compound.getByte("Slot");
			if (b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		this.burnTime = nbt.getShort("BurnTime");
		this.cookTime = nbt.getShort("CookTime");
		this.currentItemBurnTime = getItemBurnTime(this.slots[1]);
		if (nbt.hasKey("CustomName")) {
			this.localizedName = nbt.getString("CustomName");
		}
	}

	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short) this.burnTime);
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
		if (this.isInvNameLocalised()) {
			nbt.setString("CustomName", this.localizedName);
		}
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord,
				this.zCoord) != this ? false : entityplayer.getDistanceSq(
				(double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
				(double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	public boolean isBurning() {
		return this.burnTime > 0;
	}

	public void updateEntity() {
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;
		if (this.burnTime > 0) {
			this.burnTime--;
		}
		if (!this.worldObj.isRemote) {
			if (this.burnTime == 0 && this.canSmelt()) {
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]);
				if (this.burnTime > 0) {
					flag1 = true;
					if (this.slots[1] != null) {
						this.slots[1].stackSize--;
						if (this.slots[1].stackSize == 0) {
							this.slots[1] = this.slots[1].getItem()
									.getContainerItemStack(this.slots[1]);
						}
					}
				}
			}
			if (flag != this.isBurning()) {
				FurnaceUranium.updateFurnaceUraniumBlockState(
						this.burnTime > 0, this.worldObj, this.xCoord,
						this.yCoord, this.zCoord);
			}
		}
		if (this.isBurning() && this.canSmelt()) {
			this.cookTime++;
			if (this.cookTime == this.furnaceSpeed) {
				this.cookTime = 0;
				this.smeltItem();
				flag1 = true;
			}
		} else {
			this.cookTime = 0;
		}
		if (flag1) {
			this.onInventoryChanged();
		}

	}

	// Крафты с печкой
	private boolean canSmelt() {
		if (this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemstack = UraniumFurnaceRecipes.smelting()
					.getSmeltingResult(this.slots[0]);
			if (itemstack == null)
				return false;
			if (this.slots[2] == null)
				return true;
			if (!this.slots[2].isItemEqual(itemstack))
				return false;
			int result = this.slots[2].stackSize + itemstack.stackSize;
			return (result <= getInventoryStackLimit() && result <= itemstack
					.getMaxStackSize());
		}
	}

	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemstack = UraniumFurnaceRecipes.smelting()
					.getSmeltingResult(this.slots[0]);
			if (this.slots[2] == null) {
				this.slots[2] = itemstack.copy();
			} else if (this.slots[2].isItemEqual(itemstack)) {
				this.slots[2].stackSize += itemstack.stackSize;
			}
			this.slots[0].stackSize--;
			if (this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}

	public static int getItemBurnTime(ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		} else {
			int i = itemstack.getItem().itemID;

			Item item = itemstack.getItem();

			if (item instanceof ItemBlock && Block.blocksList[i] != null) {
				Block block = Block.blocksList[i];
			}

			// Время горения в тиках
			if (itemstack.itemID == USM.blocknetherstar.blockID)
				return 31250;
			if (itemstack.itemID == Item.netherStar.itemID)
				return 3125;

			return GameRegistry.getFuelValue(itemstack);
		}
	}

	public static boolean isItemFuel(ItemStack itemstack) {
		return getItemBurnTime(itemstack) > 0;
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(i == this.fuelSlot){
			if(isItemFuel(itemstack)){
				return true;
			}
		}else if(i != this.fuelSlot){
			return false;
		}
		return false;
		//return i == 2 ? false : (i == 1 ? isItemFuel(itemstack) : true);
	}

	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slots_bottom : (var1 == 1 ? slots_top : slots_sides);
	}

	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		if (itemstack != null) {
			Item stackItem = itemstack.getItem();
			if (i == this.ingredSlot && stackItem == USM.ingoturanium) {
				return true;
			} else if (i == this.fuelSlot && this.isItemValidForSlot(i, itemstack)) {
				return this.isItemValidForSlot(i, itemstack);
			}
		}
		// return this.isItemValidForSlot(i, itemstack);
		return false;
	}

	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		if (i == this.resultSlot) {
			return true;
		}else if(i != this.resultSlot){
			return false;
		}
		return itemstack.itemID == USM.ingotinfuseduranium.itemID;
	}

	public int getBurnTimeRemainingScaled(int i) {
		if (this.currentItemBurnTime == 0) {
			this.currentItemBurnTime = this.furnaceSpeed;
		}
		return this.burnTime * i / this.currentItemBurnTime;
	}

	public int getCookProgressScaled(int i) {
		return this.cookTime * i / this.furnaceSpeed;

	}

}
