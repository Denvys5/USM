package com.denvys5.uraniumswordmod.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.GuiPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.PoweredGrinderRecipes;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.TileEntityPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.GuiFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.TileEntityFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.UraniumFurnaceRecipes;

public class PoweredGrinderRecipeHandler extends TemplateRecipeHandler
{
    public class SmeltingPair extends CachedRecipe
    {
        public SmeltingPair(ItemStack ingred, ItemStack result) {
            ingred.stackSize = 1;
            this.ingred = new PositionedStack(ingred, 51, 24);
            this.result = new PositionedStack(result, 111, 24);
        }

        public List<PositionedStack> getIngredients() {
            return getCycledIngredients(cycleticks / 48, Arrays.asList(ingred));
        }

        public PositionedStack getResult() {
            return result;
        }

        PositionedStack ingred;
        PositionedStack result;
    }

    @Override
    public void loadTransferRects() {
    	transferRects.add(new RecipeTransferRect(new Rectangle(78, 34, 24, 18), "smelting"));
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass() {
        return GuiPoweredGrinder.class;
    }

    @Override
    public String getRecipeName() {
        return "Powered Grinder";
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("smelting") && getClass() == PoweredGrinderRecipeHandler.class) {//don't want subclasses getting a hold of this
            Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) PoweredGrinderRecipes.smelting().getSmeltingList();
            for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
                arecipes.add(new SmeltingPair(recipe.getKey(), recipe.getValue()));
        }
    }

    @Override
    public void loadUsageRecipes(String inputId, Object... ingredients) {
        if (!(inputId.equals("fuel") && getClass() == PoweredGrinderRecipeHandler.class))//don't want subclasses getting a hold of this
            super.loadUsageRecipes(inputId, ingredients);
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        Map<ItemStack, ItemStack> recipes = (Map<ItemStack, ItemStack>) PoweredGrinderRecipes.smelting().getSmeltingList();
        for (Entry<ItemStack, ItemStack> recipe : recipes.entrySet())
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getKey(), ingredient)) {
                SmeltingPair arecipe = new SmeltingPair(recipe.getKey(), recipe.getValue());
                arecipe.setIngredientPermutation(Arrays.asList(arecipe.ingred), ingredient);
                arecipes.add(arecipe);
            }
    }

    @Override
    public String getGuiTexture() {
        return new ResourceLocation(USM.modid, "textures/gui/PoweredGrinderNEI_gui.png").toString();
    }

    @Override
    public void drawExtras(int recipe) {
        drawProgressBar(10, 53, 176, 74, 11, 14, 44, 1);
        drawProgressBar(79, 29, 176, 0, 25, 29, 48, 0);
    }

    @Override
    public String getOverlayIdentifier() {
        return "Powered Grinder";
    }
}
