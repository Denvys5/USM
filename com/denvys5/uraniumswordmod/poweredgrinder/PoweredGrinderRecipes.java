package com.denvys5.uraniumswordmod.poweredgrinder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.legacy.PoweredGrinderRecipe;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

public class PoweredGrinderRecipes{
    private static final PoweredGrinderRecipes smeltingBase = new PoweredGrinderRecipes();
    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private static final String __OBFID = "CL_00000085";

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static PoweredGrinderRecipes smelting(){
        return smeltingBase;
    }

    private PoweredGrinderRecipes(){
    	//this.addGrinderRecipe(BlockList.ingoturanium, new ItemStack(BlockList.ingotinfuseduranium));
    }
    
    public void add(PoweredGrinderRecipe recipe){
    }
    
    public void addGrinderRecipe(ItemStack Input, Block Result, int PowerForCrafting){
    	this.addGrinderRecipe(Input, Item.getItemFromBlock(Result), PowerForCrafting);
    }
    
    public void addGrinderRecipe(ItemStack Input, Item Result, int PowerForCrafting){
    	this.addGrinderRecipe(Input, new ItemStack(Result, 1, 32767), PowerForCrafting);
    }

    public void addGrinderRecipe(Block Input, ItemStack Result, int PowerForCrafting){
        this.addGrinderRecipe(Item.getItemFromBlock(Input), Result, PowerForCrafting);
    }

    public void addGrinderRecipe(Item Input, ItemStack Output, int PowerForCrafting){
        this.addGrinderRecipe(new ItemStack(Input, 1, 32767), Output, PowerForCrafting);
    }

    public void addGrinderRecipe(ItemStack Input, ItemStack Output, int PowerForCrafting){
        this.smeltingList.put(Input, Output);
        this.experienceList.put(Input, PowerForCrafting);
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack Input){
    	
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(Input, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
	}
    
	private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_){
    return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
	}

    public Map getSmeltingList(){
        return this.smeltingList;
    }
    public int getFromPowerList(ItemStack itemstack){

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(itemstack, (ItemStack)entry.getKey()));

        return ((int)entry.getValue());
    }
}