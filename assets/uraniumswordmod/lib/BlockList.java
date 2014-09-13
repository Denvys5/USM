package assets.uraniumswordmod.lib;

import java.io.File;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.block.BlockInfusedUranium;
import assets.uraniumswordmod.block.BlockNetherStar;
import assets.uraniumswordmod.block.BlockUranium;
import assets.uraniumswordmod.block.FurnaceUranium;
import assets.uraniumswordmod.block.OreUranium;
import assets.uraniumswordmod.gui.GuiHandler;
import assets.uraniumswordmod.item.IngotInfusedUranium;
import assets.uraniumswordmod.item.IngotUranium;
import assets.uraniumswordmod.item.StickIron;
import assets.uraniumswordmod.item.SwordUranium;
import assets.uraniumswordmod.item.UraniumArmour;
import assets.uraniumswordmod.item.UraniumOnStick;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import denvys5.core.Util;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class BlockList {

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Block blockinfuseduranium;
	public static Item sworduranium;
	public static Item uraniumonstick;
	public static Item ingotinfuseduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static ItemArmor UraniumHelmet;
	public static ItemArmor UraniumChest;
	public static ItemArmor UraniumLeggins;
	public static ItemArmor UraniumBoots;
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;

	public static int oreuraniumblockID;
	public static int blockuraniumblockID;
	public static int blockinfuseduraniumblockID;
	public static int blocknetherstarblockID;
	public static int sworduraniumitemID;
	public static int ingotinfuseduraniumitemID;
	public static int ingoturaniumitemID;
	public static int stickironitemID;
	public static int furnaceuraniumidleblockID;
	public static int furnaceuraniumactiveblockID;
	public static int uraniumonstickitemID;
	public static int UraniumHelmetID;
	public static int UraniumChestID;
	public static int UraniumLegginsID;
	public static int UraniumBootsID;
	



	public static void blockRegister() {

		oreuranium = new OreUranium(oreuraniumblockID)
				.setUnlocalizedName("Uranium Ore");
		Util.regBlock(oreuranium, "oreuranium", "Uranium Ore");

		blocknetherstar = new BlockNetherStar(blocknetherstarblockID)
				.setUnlocalizedName("Nether Star Block");
		Util.regBlock(blocknetherstar, "blocknetherstar", "Nether Star Block");

		blockuranium = new BlockUranium(blockuraniumblockID)
				.setUnlocalizedName("Block Uranium");
		Util.regBlock(blockuranium, "blockuranium", "Block Uranium");
		
		blockinfuseduranium = new BlockInfusedUranium(blockinfuseduraniumblockID)
				.setUnlocalizedName("Block Infused Uranium");
		Util.regBlock(blockinfuseduranium, "blockinfuseduranium", "Block Infused Uranium");

		furnaceuraniumidle = new FurnaceUranium(
				furnaceuraniumidleblockID, false).setUnlocalizedName(
				"Uranium Furnace").setCreativeTab(USM.USMTab);
		furnaceuraniumactive = new FurnaceUranium(
				furnaceuraniumactiveblockID, true)
				.setUnlocalizedName("Uranium Furnace Active").setLightValue(
						0.8F);
		Util.regBlock(furnaceuraniumidle, "furnaceuraniumidle",
				"Uranium Furnace");
		Util.regBlock(furnaceuraniumactive, "furnaceuraniumactive",
				"Uranium Furnace Active");
		GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class,
				"FurnaceUranium");
		LanguageRegistry.instance().addStringLocalization(
				"container.furnaceUranium", "Uranium Furnace");

	}

	public static void itemRegister() {
		sworduranium = new SwordUranium(sworduraniumitemID)
				.setUnlocalizedName("Uranium Sword");
		Util.regItem(sworduranium, "sworduranium", "Uranium Sword");
		
		uraniumonstick = new UraniumOnStick(uraniumonstickitemID)
		.setUnlocalizedName("Uranium On Stick");
Util.regItem(uraniumonstick, "uraniumonstick", "Uranium On Stick");

		stickiron = new StickIron(stickironitemID)
				.setUnlocalizedName("Iron Stick");
		Util.regItem(stickiron, "stickiron", "Iron Stick");

		ingoturanium = new IngotUranium(ingoturaniumitemID)
				.setUnlocalizedName("Uranium Ingot");
		Util.regItem(ingoturanium, "ingoturanium", "Uranium Ingot");

		ingotinfuseduranium = new IngotInfusedUranium(
				ingotinfuseduraniumitemID)
				.setUnlocalizedName("Infused Uranium Ingot");
		Util.regItem(ingotinfuseduranium, "ingotinfuseduranium",
				"Infused Uranium Ingot");
	}
	
	public static void armourRegister(){
	    UraniumHelmet = new UraniumArmour(UraniumHelmetID, 0, 0);
        LanguageRegistry.instance().addNameForObject(UraniumHelmet, "en_US", "Uranium Helmet");
        LanguageRegistry.instance().addNameForObject(UraniumHelmet, "ru_RU", "Фу, не знать английский");
	    UraniumChest = new UraniumArmour(UraniumChestID, 1, 1);
        LanguageRegistry.instance().addNameForObject(UraniumChest, "en_US", "Uranium Chest");
        LanguageRegistry.instance().addNameForObject(UraniumChest, "ru_RU", "Фу, не знать английский");
	    UraniumLeggins = new UraniumArmour(UraniumLegginsID, 2, 2);
        LanguageRegistry.instance().addNameForObject(UraniumLeggins, "en_US", "Uranium Leggins");
        LanguageRegistry.instance().addNameForObject(UraniumLeggins, "ru_RU", "Фу, не знать английский");
	    UraniumBoots = new UraniumArmour(UraniumBootsID, 3, 3);
        LanguageRegistry.instance().addNameForObject(UraniumBoots, "en_US", "Uranium Boots");
        LanguageRegistry.instance().addNameForObject(UraniumBoots, "ru_RU", "Фу, не знать английский");
	}
}
