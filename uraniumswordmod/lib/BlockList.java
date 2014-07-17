package assets.uraniumswordmod.lib;

import java.io.File;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.block.BlockNetherStar;
import assets.uraniumswordmod.block.BlockUranium;
import assets.uraniumswordmod.block.FurnaceUranium;
import assets.uraniumswordmod.block.OreUranium;
import assets.uraniumswordmod.gui.GuiHandler;
import assets.uraniumswordmod.item.IngotInfusedUranium;
import assets.uraniumswordmod.item.IngotUranium;
import assets.uraniumswordmod.item.StickIron;
import assets.uraniumswordmod.item.SwordUranium;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class BlockList {
	
	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Item sworduranium;
	public static Item ingotinfuseduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;
	
	public static int oreuraniumblockID;
	public static int blockuraniumblockID;
	public static int blocknetherstarblockID;
	public static int sworduraniumitemID;
	public static int ingotinfuseduraniumitemID;
	public static int ingoturaniumitemID;
	public static int stickironitemID;
	public static int furnaceuraniumidleblockID;
	public static int furnaceuraniumactiveblockID;
	
	
	
	public static void thingRegister(){
		oreuranium = new OreUranium(BlockList.oreuraniumblockID)
		.setUnlocalizedName("Uranium Ore");
GameRegistry.registerBlock(oreuranium, "oreuranium");
LanguageRegistry.instance().addNameForObject(oreuranium, "en_US",
		"Uranium Ore");
LanguageRegistry.instance().addNameForObject(oreuranium, "ru_RU",
		"Фу, не знать английский");

blocknetherstar = new BlockNetherStar(BlockList.blocknetherstarblockID)
		.setUnlocalizedName("Nether Star Block");
GameRegistry.registerBlock(blocknetherstar, "blocknetherstar");
LanguageRegistry.instance().addNameForObject(blocknetherstar, "en_US",
		"Nether Star Block");
LanguageRegistry.instance().addNameForObject(blocknetherstar, "ru_RU",
		"Фу, не знать английский");

blockuranium = new BlockUranium(BlockList.blockuraniumblockID)
		.setUnlocalizedName("Block Uranium");
GameRegistry.registerBlock(blockuranium, "blockuranium");
LanguageRegistry.instance().addNameForObject(blockuranium, "en_US",
		"Uranium Ore");
LanguageRegistry.instance().addNameForObject(blockuranium, "ru_RU",
		"Фу, не знать английский");

furnaceuraniumidle = new FurnaceUranium(BlockList.furnaceuraniumidleblockID,
		false).setUnlocalizedName("Uranium Furnace Idle")
		.setCreativeTab(USM.USMTab);
furnaceuraniumactive = new FurnaceUranium(
		BlockList.furnaceuraniumactiveblockID, true).setUnlocalizedName(
		"Uranium Furnace Active").setLightValue(0.8F);
GameRegistry.registerBlock(furnaceuraniumidle, "Uranium Furnace");
GameRegistry.registerBlock(furnaceuraniumactive,
		"Uranium Furnace Active");
GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class,
		"FurnaceUranium");
GuiHandler guiHandler = new GuiHandler();
LanguageRegistry.instance().addStringLocalization(
		"container.furnaceUranium", "Uranium Furnace");
LanguageRegistry.instance().addNameForObject(furnaceuraniumidle,
		"en_US", "Uranium Furnace Idle");
LanguageRegistry.instance().addNameForObject(furnaceuraniumidle,
		"Фу, не знать английский", "");

sworduranium = new SwordUranium(BlockList.sworduraniumitemID)
		.setUnlocalizedName("Uranium Sword");
GameRegistry.registerItem(sworduranium, "sworduranium");
LanguageRegistry.instance().addNameForObject(sworduranium, "en_US",
		"Uranium Sword");
LanguageRegistry.instance().addNameForObject(sworduranium,
		"Фу, не знать английский", "");

stickiron = new StickIron(BlockList.stickironitemID)
		.setUnlocalizedName("Iron Stick");
GameRegistry.registerItem(stickiron, "stickiron");
LanguageRegistry.instance().addNameForObject(stickiron, "en_US",
		"Iron Stick");
LanguageRegistry.instance().addNameForObject(stickiron,
		"Фу, не знать английский", "");

ingoturanium = new IngotUranium(BlockList.ingoturaniumitemID)
		.setUnlocalizedName("Uranium Ingot");
GameRegistry.registerItem(ingoturanium, "ingoturanium");
LanguageRegistry.instance().addNameForObject(ingoturanium, "en_US",
		"Uranium Ingot");
LanguageRegistry.instance().addNameForObject(ingoturanium,
		"Фу, не знать английский", "");

ingotinfuseduranium = new IngotInfusedUranium(
		BlockList.ingotinfuseduraniumitemID)
		.setUnlocalizedName("Infused Uranium Ingot");
GameRegistry.registerItem(ingotinfuseduranium, "ingotinfuseduranium");
LanguageRegistry.instance().addNameForObject(ingotinfuseduranium,
		"en_US", "Infused Uranium Ingot");
LanguageRegistry.instance().addNameForObject(ingotinfuseduranium,
		"Фу, не знать английский", "");
	}
	
	public static void OreRegister(){
		OreDictionary.registerOre("ingotUranium", new ItemStack(BlockList.ingoturanium));
		OreDictionary.registerOre("oreUranium", new ItemStack(BlockList.oreuranium));
		OreDictionary.registerOre("stickIron", new ItemStack(BlockList.stickiron));
		OreDictionary.registerOre("ironStick", new ItemStack(BlockList.stickiron));
		OreDictionary.registerOre("ironRod", new ItemStack(BlockList.stickiron));
		OreDictionary.registerOre("blockUranium", new ItemStack(BlockList.blockuranium));
	}
	public static void ConfigMethod(){
		Configuration config = new Configuration(new File(
				"config/USM/UraniumSwordMod.cfg"));
		config.load();
		oreuraniumblockID = config.get("Blocks", "Uranium Ore", 500).getInt();
		blockuraniumblockID = config.get("Blocks", "Uranium Block", 502)
				.getInt();
		blocknetherstarblockID = config.get("Blocks", "Nether Star Block", 501)
				.getInt();
		sworduraniumitemID = config.get("Items", "Uranium Sword", 1551)
				.getInt();
		ingotinfuseduraniumitemID = config.get("Items",
				"Infused Uranium Ingot", 1553).getInt();
		ingoturaniumitemID = config.get("Items", "Uranium Ingot", 1552)
				.getInt();
		stickironitemID = config.get("Items", "Iron Stick", 1550).getInt();
		furnaceuraniumidleblockID = config.get("Tile Entities",
				"Uranium Furnace", 503).getInt();
		furnaceuraniumactiveblockID = config.get("Tile Entities",
				"Uranium Furnace Active", 504).getInt();
		config.save();
	}

}
