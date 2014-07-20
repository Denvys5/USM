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

	public static void blockRegister() {

		oreuranium = new OreUranium(BlockList.oreuraniumblockID)
				.setUnlocalizedName("Uranium Ore");
		Util.regBlock(oreuranium, "oreuranium", "Uranium Ore");

		blocknetherstar = new BlockNetherStar(BlockList.blocknetherstarblockID)
				.setUnlocalizedName("Nether Star Block");
		Util.regBlock(blocknetherstar, "blocknetherstar", "Nether Star Block");

		blockuranium = new BlockUranium(BlockList.blockuraniumblockID)
				.setUnlocalizedName("Block Uranium");
		Util.regBlock(blockuranium, "blockuranium", "Block Uranium");

		furnaceuraniumidle = new FurnaceUranium(
				BlockList.furnaceuraniumidleblockID, false).setUnlocalizedName(
				"Uranium Furnace").setCreativeTab(USM.USMTab);
		furnaceuraniumactive = new FurnaceUranium(
				BlockList.furnaceuraniumactiveblockID, true)
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
		sworduranium = new SwordUranium(BlockList.sworduraniumitemID)
				.setUnlocalizedName("Uranium Sword");
		Util.regItem(sworduranium, "sworduranium", "Uranium Sword");

		stickiron = new StickIron(BlockList.stickironitemID)
				.setUnlocalizedName("Iron Stick");
		Util.regItem(stickiron, "stickiron", "Iron Stick");

		ingoturanium = new IngotUranium(BlockList.ingoturaniumitemID)
				.setUnlocalizedName("Uranium Ingot");
		Util.regItem(ingoturanium, "ingoturanium", "Uranium Ingot");

		ingotinfuseduranium = new IngotInfusedUranium(
				BlockList.ingotinfuseduraniumitemID)
				.setUnlocalizedName("Infused Uranium Ingot");
		Util.regItem(ingotinfuseduranium, "ingotinfuseduranium",
				"Infused Uranium Ingot");
	}

	public static void OreRegister() {
		OreDictionary.registerOre("ingotUranium", new ItemStack(
				BlockList.ingoturanium));
		OreDictionary.registerOre("oreUranium", new ItemStack(
				BlockList.oreuranium));
		OreDictionary.registerOre("stickIron", new ItemStack(
				BlockList.stickiron));
		OreDictionary.registerOre("ironStick", new ItemStack(
				BlockList.stickiron));
		OreDictionary
				.registerOre("ironRod", new ItemStack(BlockList.stickiron));
		OreDictionary.registerOre("blockUranium", new ItemStack(
				BlockList.blockuranium));
	}

	public static void ConfigMethod() {
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
