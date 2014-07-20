package assets.uraniumswordmod.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeList {
	public static void ShapedOreCrafting(){
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.sworduranium, true,
				new Object[] { "#X#", "#X#", "@SA",
						// Character.valueOf('X'),
						// USM.ingotinfuseduranium,('S'), "stickIron",('#'),
						// Block.obsidian,('@'), Potion.harm,('A'),
						// Potion.damageBoost}));
						Character.valueOf('X'), BlockList.ingotinfuseduranium, ('S'),
						"stickIron", ('#'), Block.obsidian }));

		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.stickiron, true,
				new Object[] { "@", "@", Character.valueOf('@'),
						Item.ingotIron }));

		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.oreuranium, true,
				new Object[] { "@", Character.valueOf('@'), "oreUranium" }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.ingoturanium, true,
				new Object[] { "@", Character.valueOf('@'), "crushedUranium" }));

		GameRegistry.addRecipe(new ShapedOreRecipe (new ItemStack(BlockList.blockuranium, 4),
				new Object[] { "@@", "@@", Character.valueOf('@'), "blockUranium" }));
        
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.blocknetherstar, true,
				new Object[] { "@@@", "@@@", "@@@", Character.valueOf('@'),
						Item.netherStar }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.blockuranium, true,
				new Object[] { "@@@", "@@@", "@@@", Character.valueOf('@'),
				BlockList.ingoturanium }));

		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.furnaceuraniumidle,
				true, new Object[] { "@@@", "@X@", "!!!",
						Character.valueOf('@'), Block.blockIron, ('X'),
						Item.netherStar, ('!'), Block.coalBlock }));

		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.furnaceuraniumidle,
				true, new Object[] { "@@@", "@X@", "!!!",
						Character.valueOf('@'), Block.blockIron, ('X'),
						Block.dragonEgg, ('!'), Block.coalBlock }));


	}
	public static void ShapelessCrafting(){
		GameRegistry.addShapelessRecipe(new ItemStack(BlockList.ingoturanium, 9),
				new Object[] { BlockList.blockuranium });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.netherStar, 9),
				new Object[] { BlockList.blocknetherstar });
	}
	public static void VanillaSmeltingRecipes(){
		GameRegistry.addSmelting(BlockList.oreuranium.blockID, new ItemStack(
				BlockList.ingoturanium, 1), 20.0F);
	}	
}
