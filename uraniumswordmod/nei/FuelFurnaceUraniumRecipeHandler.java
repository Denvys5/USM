package assets.uraniumswordmod.nei;

import static codechicken.nei.NEIClientUtils.translate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import assets.uraniumswordmod.lib.UraniumFurnaceRecipes;
import net.minecraft.item.ItemStack;
import codechicken.nei.NEIClientUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.GuiRecipe;

public class FuelFurnaceUraniumRecipeHandler extends
		FurnaceUraniumRecipeHandler {
	public class CachedFuelRecipe extends CachedRecipe {
		public FuelPair fuel;

		public CachedFuelRecipe(FuelPair fuel) {
			this.fuel = fuel;
		}

		@Override
		public PositionedStack getIngredient() {
			return mfurnace.get(cycleticks / 48 % mfurnace.size()).ingred;
		}

		@Override
		public PositionedStack getResult() {
			return mfurnace.get(cycleticks / 48 % mfurnace.size()).result;
		}

		@Override
		public PositionedStack getOtherStack() {
			return fuel.stack;
		}
	}

	public FuelFurnaceUraniumRecipeHandler() {
		super();
		loadAllSmelting();
	}

	public String getRecipeName() {
		return "Uranium Furnace Fuel";
	}

	public void loadAllSmelting() {
		if (mfurnace != null)// already loaded;
			return;

		mfurnace = new ArrayList<FurnaceUraniumRecipeHandler.SmeltingPair>();

		HashMap<Integer, ItemStack> recipes = (HashMap<Integer, ItemStack>) UraniumFurnaceRecipes
				.smelting().getSmeltingList();
		HashMap<List<Integer>, ItemStack> metarecipes = (HashMap<List<Integer>, ItemStack>) UraniumFurnaceRecipes
				.smelting().getMetaSmeltingList();

		for (Entry<Integer, ItemStack> recipe : recipes.entrySet()) {
			ItemStack item = recipe.getValue();
			int ingred = recipe.getKey();
			mfurnace.add(new SmeltingPair(new ItemStack(ingred, 1, 0), item));
		}
		if (metarecipes == null)
			return;
		for (Entry<List<Integer>, ItemStack> recipe : metarecipes.entrySet()) {
			ItemStack item = recipe.getValue();
			mfurnace.add(new SmeltingPair(new ItemStack(recipe.getKey().get(0),
					1, recipe.getKey().get(1)), item));
		}
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals("fuel")
				&& getClass() == FuelFurnaceUraniumRecipeHandler.class)
			for (FuelPair fuel : afuels)
				arecipes.add(new CachedFuelRecipe(fuel));
	}

	public void loadUsageRecipes(ItemStack ingredient) {
		for (FuelPair fuel : afuels)
			if (fuel.stack.contains(ingredient))
				arecipes.add(new CachedFuelRecipe(fuel));
	}

	public String getOverlayIdentifier() {
		return "fuel";
	}

	@Override
	public List<String> handleItemTooltip(GuiRecipe gui, ItemStack stack,
			List<String> currenttip, int recipe) {
		CachedFuelRecipe crecipe = (CachedFuelRecipe) arecipes.get(recipe);
		FuelPair fuel = crecipe.fuel;
		float burnTime = fuel.burnTime / 200F;

		if (gui.isMouseOver(fuel.stack, recipe) && burnTime < 1) {
			burnTime = 1F / burnTime;
			String s_time = Float.toString(burnTime);
			if (burnTime == Math.round(burnTime))
				s_time = Integer.toString((int) burnTime);

			currenttip.add(translate("recipe.fuel.required", s_time));
		} else if ((gui.isMouseOver(crecipe.getResult(), recipe) || gui
				.isMouseOver(crecipe.getIngredient(), recipe)) && burnTime > 1) {
			String s_time = Float.toString(burnTime);
			if (burnTime == Math.round(burnTime))
				s_time = Integer.toString((int) burnTime);

			currenttip
					.add(translate(
							"recipe.fuel."
									+ (gui.isMouseOver(crecipe.getResult(),
											recipe) ? "produced" : "processed"),
							s_time));
		}

		return currenttip;
	}

	public static ArrayList<SmeltingPair> mfurnace;
}
