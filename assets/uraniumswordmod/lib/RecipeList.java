package assets.uraniumswordmod.lib;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class RecipeList {
	public static void ShapedOreCrafting(){
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumHelmet, true,
				new Object[] { "XXX", "X@X",
						Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'),
						BlockList.blocknetherstar }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumChest, true,
				new Object[] { "X#X", "X@X", "XXX",
						Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'),
						BlockList.blocknetherstar }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumLeggins, true,
				new Object[] { "X@X", "X#X", "X#X",
						Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'),
						BlockList.blocknetherstar }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.UraniumBoots, true,
				new Object[] { "X#X", "X@X",
						Character.valueOf('X'), BlockList.blockinfuseduranium, ('@'),
						BlockList.blocknetherstar }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.sworduranium, true,
				new Object[] { "1X2", "3X4", "5S6",
						Character.valueOf('X'), BlockList.blockinfuseduranium, ('S'),
						"stickIron", ('5'), new ItemStack(Item.potion, 1, 8233), ('6'), new ItemStack(Item.potion, 1, 8236) }));

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
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.blockinfuseduranium, true,
				new Object[] { "@@@", "@@@", "@@@", Character.valueOf('@'),
				BlockList.ingotinfuseduranium }));


		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.furnaceuraniumidle,
				true, new Object[] { "@@@", "@X@", "!!!",
						Character.valueOf('@'), Block.blockIron, ('X'),
						Item.netherStar, ('!'), Block.coalBlock }));

		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.furnaceuraniumidle,
				true, new Object[] { "@@@", "@X@", "!!!",
						Character.valueOf('@'), Block.blockIron, ('X'),
						Block.dragonEgg, ('!'), Block.coalBlock }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(BlockList.uraniumonstick, true,
				new Object[] { "@00", "0X0", "00@", Character.valueOf('@'), BlockList.ingotinfuseduranium, Character.valueOf('X'), Item.stick }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockList.ingotinfuseduranium, 2), true,
				new Object[] { "@", Character.valueOf('@'), BlockList.uraniumonstick}));


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
