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
import assets.uraniumswordmod.item.UraniumBoots;
import assets.uraniumswordmod.item.UraniumChest;
import assets.uraniumswordmod.item.UraniumHelmet;
import assets.uraniumswordmod.item.UraniumLeggins;
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
	
    public static ItemArmor UraniumHelmet = new UraniumHelmet(3200, USM.UraniumArmour, 0, 0);
    
    public static ItemArmor UraniumChest = new UraniumChest(3201, USM.UraniumArmour, 1, 1);
    
    public static ItemArmor UraniumLeggins = new UraniumLeggins(3202, USM.UraniumArmour, 2, 2);
    
    public static ItemArmor UraniumBoots = new UraniumBoots(3203, USM.UraniumArmour, 3, 3);


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
		
		blockinfuseduranium = new BlockInfusedUranium(BlockList.blockinfuseduraniumblockID)
				.setUnlocalizedName("Block Infused Uranium");
		Util.regBlock(blockinfuseduranium, "blockinfuseduranium", "Block Infused Uranium");

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
		
		uraniumonstick = new UraniumOnStick(BlockList.uraniumonstickitemID)
		.setUnlocalizedName("Uranium On Stick");
Util.regItem(uraniumonstick, "uraniumonstick", "Uranium On Stick");

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
}
