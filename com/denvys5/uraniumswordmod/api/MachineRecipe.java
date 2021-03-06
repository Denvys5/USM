package com.denvys5.uraniumswordmod.api;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.legacy.PoweredGrinderRecipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class MachineRecipe{
	private static final MachineRecipe smeltingBase = new MachineRecipe();
	/** The list of smelting results. */
	private Map smeltingList = new HashMap();
	private Map powerList = new HashMap();
	private static final String __OBFID = "CL_00000085";

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */

	public void addMachineRecipe(ItemStack Input, Block Result, int PowerForCrafting){
		this.addMachineRecipe(Input, Item.getItemFromBlock(Result), PowerForCrafting);
	}

	public void addMachineRecipe(ItemStack Input, Item Result, int PowerForCrafting){
		this.addMachineRecipe(Input, new ItemStack(Result, 1, 32767), PowerForCrafting);
	}

	public void addMachineRecipe(Block Input, ItemStack Result, int PowerForCrafting){
		this.addMachineRecipe(Item.getItemFromBlock(Input), Result, PowerForCrafting);
	}

	public void addMachineRecipe(Item Input, ItemStack Output, int PowerForCrafting){
		this.addMachineRecipe(new ItemStack(Input, 1, 32767), Output, PowerForCrafting);
	}

	public void addMachineRecipe(ItemStack Input, ItemStack Output, int PowerForCrafting){
		this.smeltingList.put(Input, Output);
		this.powerList.put(Input, PowerForCrafting);
	}

	/**
	 * Returns the smelting result of an item.
	 */
	public ItemStack getSmeltingResult(ItemStack Input){

		Iterator iterator = this.smeltingList.entrySet().iterator();
		Entry entry;

		do{
			if(!iterator.hasNext()){
				return null;
			}

			entry = (Entry)iterator.next();
		} while(!this.func_151397_a(Input, (ItemStack)entry.getKey()));

		return (ItemStack)entry.getValue();
	}

	private boolean func_151397_a(ItemStack itemstack1, ItemStack itemstack2){
		return itemstack2.getItem() == itemstack1.getItem() && (itemstack2.getItemDamage() == 32767 || itemstack2.getItemDamage() == itemstack1.getItemDamage());
	}

	public Map getSmeltingList(){
		return this.smeltingList;
	}
	
	public int getFromPowerList(ItemStack itemstack){

		Iterator iterator = this.powerList.entrySet().iterator();
		Entry entry;

		do{
			if(!iterator.hasNext()){
				return 0;
			}

			entry = (Entry)iterator.next();
		} while(!this.func_151397_a(itemstack, (ItemStack)entry.getKey()));
		return ((Integer)entry.getValue()).intValue();
	}
}