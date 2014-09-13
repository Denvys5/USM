package assets.uraniumswordmod.nei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.Map.Entry;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.gui.GuiFurnaceUranium;
import assets.uraniumswordmod.lib.BlockList;
import assets.uraniumswordmod.lib.UraniumFurnaceRecipes;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import codechicken.nei.ItemList;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.api.IRecipeOverlayRenderer;
import assets.uraniumswordmod.nei.FurnaceUraniumRecipeHandler;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.ICraftingHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler.CachedRecipe;

public class FurnaceUraniumRecipeHandler
		/* implements ICraftingHandler */extends TemplateRecipeHandler {

	public String getRecipeName() {

		return "Uranium Furnace";
	}

	public String getGuiTexture() {
		return new ResourceLocation(USM.modid,
				"textures/gui/FurnaceUraniumNEI_gui.png").toString();
	}

	public class SmeltingPair extends CachedRecipe {
		public SmeltingPair(ItemStack ingred, ItemStack result) {
			ingred.stackSize = 1;
			this.ingred = new PositionedStack(ingred, 51, 6);
			this.result = new PositionedStack(result, 111, 24);
		}

		public PositionedStack getIngredient() {
			int cycle = cycleticks / 48;
			if (ingred.item.getItemDamage() == -1) {
				PositionedStack stack = ingred.copy();
				int maxDamage = 0;
				do {
					maxDamage++;
					stack.item.setItemDamage(maxDamage);
				} while (NEIClientUtils.isValidItem(stack.item));

				stack.item.setItemDamage(cycle % maxDamage);
				return stack;
			}
			return ingred;
		}

		public PositionedStack getResult() {
			return result;
		}

		public PositionedStack getOtherStack() {
			return afuels.get((cycleticks / 48) % afuels.size()).stack;
		}

		PositionedStack ingred;
		PositionedStack result;
	}

	public static class FuelPair {
		public FuelPair(ItemStack ingred, int burnTime) {
			this.stack = new PositionedStack(ingred, 51, 42, false);
			this.burnTime = burnTime;
		}

		public PositionedStack stack;
		public int burnTime;
	}

	public static ArrayList<FuelPair> afuels;
	public static TreeSet<Integer> efuels;

	public void loadTransferRects() {
		transferRects.add(new RecipeTransferRect(new Rectangle(55, 34, 18, 18),
				"fuel"));
		transferRects.add(new RecipeTransferRect(new Rectangle(78, 34, 24, 18),
				"smelting"));
	}

	public Class<? extends GuiContainer> getGuiClass() {
		return GuiFurnaceUranium.class;
	}

	public TemplateRecipeHandler newInstance() {
		if (afuels == null)
			findFuels();
		return super.newInstance();
	}

	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("smelting")
				&& getClass() == FurnaceUraniumRecipeHandler.class)// don't want
																	// subclasses
																	// getting a
																	// hold of
																	// this
		{
			HashMap<Integer, ItemStack> recipes = (HashMap<Integer, ItemStack>) UraniumFurnaceRecipes
					.smelting().getSmeltingList();
			HashMap<List<Integer>, ItemStack> metarecipes = (HashMap<List<Integer>, ItemStack>) UraniumFurnaceRecipes
					.smelting().getMetaSmeltingList();

			for (Entry<Integer, ItemStack> recipe : recipes.entrySet()) {
				ItemStack item = recipe.getValue();
				arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey(), 1,
						-1), item));
			}
			if (metarecipes == null)
				return;
			for (Entry<List<Integer>, ItemStack> recipe : metarecipes
					.entrySet()) {
				ItemStack item = recipe.getValue();
				arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey()
						.get(0), 1, recipe.getKey().get(1)), item));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	public void loadCraftingRecipes(ItemStack result) {
		HashMap<Integer, ItemStack> recipes = (HashMap<Integer, ItemStack>) UraniumFurnaceRecipes
				.smelting().getSmeltingList();
		HashMap<List<Integer>, ItemStack> metarecipes = (HashMap<List<Integer>, ItemStack>) UraniumFurnaceRecipes
				.smelting().getMetaSmeltingList();

		for (Entry<Integer, ItemStack> recipe : recipes.entrySet()) {
			ItemStack item = recipe.getValue();
			if (NEIServerUtils.areStacksSameType(item, result)) {
				arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey(), 1,
						-1), item));
			}
		}
		if (metarecipes == null)
			return;
		for (Entry<List<Integer>, ItemStack> recipe : metarecipes.entrySet()) {
			ItemStack item = recipe.getValue();
			if (NEIServerUtils.areStacksSameType(item, result)) {
				arecipes.add(new SmeltingPair(new ItemStack(recipe.getKey()
						.get(0), 1, recipe.getKey().get(1)), item));
			}
		}
	}

	public void loadUsageRecipes(String inputId, Object... ingredients) {
		if (inputId.equals("fuel")
				&& getClass() == FurnaceUraniumRecipeHandler.class)// don't want
																	// subclasses
																	// getting a
																	// hold of
																	// this
		{
			loadCraftingRecipes("smelting");
		} else {
			super.loadUsageRecipes(inputId, ingredients);
		}
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		HashMap<Integer, ItemStack> recipes = (HashMap<Integer, ItemStack>) UraniumFurnaceRecipes
				.smelting().getSmeltingList();
		HashMap<List<Integer>, ItemStack> metarecipes = (HashMap<List<Integer>, ItemStack>) UraniumFurnaceRecipes
				.smelting().getMetaSmeltingList();

		for (Entry<Integer, ItemStack> recipe : recipes.entrySet()) {
			ItemStack item = recipe.getValue();
			if (ingredient.itemID == recipe.getKey()) {
				arecipes.add(new SmeltingPair(ingredient, item));
			}
		}
		if (metarecipes == null)
			return;
		for (Entry<List<Integer>, ItemStack> recipe : metarecipes.entrySet()) {
			ItemStack item = recipe.getValue();
			if (ingredient.itemID == recipe.getKey().get(0)
					&& ingredient.getItemDamage() == recipe.getKey().get(1)) {
				arecipes.add(new SmeltingPair(ingredient, item));
			}
		}
	}

	public void drawExtras(int recipe) {
		drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
		drawProgressBar(74, 23, 176, 14, 24, 18, 48, 0);
	}

	private static void removeFuels() {
		efuels = new TreeSet<Integer>();
		efuels.add(Block.mushroomCapBrown.blockID);
		efuels.add(Block.mushroomCapRed.blockID);
		efuels.add(Block.signPost.blockID);
		efuels.add(Block.signWall.blockID);
		efuels.add(Block.doorWood.blockID);
		efuels.add(Block.lockedChest.blockID);

	}

	private static void findFuels() {
		afuels = new ArrayList<FuelPair>();
		afuels.add(new FuelPair(new ItemStack(Item.netherStar), 3125));
		afuels.add(new FuelPair(new ItemStack(BlockList.blocknetherstar), 31250));
	}

	public String getOverlayIdentifier() {
		return "smelting";
	}

	static {
		removeFuels();
	}

}
