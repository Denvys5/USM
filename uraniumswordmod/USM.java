package assets.uraniumswordmod;

import java.io.File;

import assets.uraniumswordmod.block.BlockNetherStar;
import assets.uraniumswordmod.block.BlockUranium;
import assets.uraniumswordmod.block.FurnaceUranium;
import assets.uraniumswordmod.block.OreUranium;
import assets.uraniumswordmod.generator.OreGenerator;
import assets.uraniumswordmod.gui.GuiHandler;
import assets.uraniumswordmod.item.IngotInfusedUranium;
import assets.uraniumswordmod.item.IngotUranium;
import assets.uraniumswordmod.item.StickIron;
import assets.uraniumswordmod.item.SwordUranium;
import assets.uraniumswordmod.lib.ConnectionHandler;
import assets.uraniumswordmod.proxy.CommonProxy;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = USM.modid, name = USM.name, version = USM.version)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class USM {
	public static final String modid = "uraniumswordmod";
	public static final String name = "Uranium Sword Mod";
	public static final String version = "0.0.4.4";

	@Instance(modid)
	public static USM instance;

	@SidedProxy(clientSide = "assets.uraniumswordmod.proxy.ClientProxy", serverSide = "assets.uraniumswordmod.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Item sworduranium;
	public static Item ingotinfuseduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static Block decreaseridle;
	public static Block decreaseractive;
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;
	public static final int guiIdFurnaceUranium = 0;
	public static CreativeTabs USMTab;

	public static int oreuraniumblockID;
	public static int blockuraniumblockID;
	public static int blocknetherstarblockID;
	public static int sworduraniumitemID;
	public static int ingotinfuseduraniumitemID;
	public static int ingoturaniumitemID;
	public static int stickironitemID;
	public static int furnaceuraniumidleblockID;
	public static int furnaceuraniumactiveblockID;

	public void initConfiguration(FMLInitializationEvent event) {
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
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		this.initConfiguration(event);
		GameRegistry.registerWorldGenerator(new OreGenerator());

		USMTab = new CreativeTabs("uraniumswordmodtab") {
			@SideOnly(Side.CLIENT)
			public int getTabIconItemIndex() {
				return USM.sworduranium.itemID;
			}
		};

		proxy.registerRandomStuff();

		oreuranium = new OreUranium(this.oreuraniumblockID)
				.setUnlocalizedName("Uranium Ore");
		GameRegistry.registerBlock(oreuranium, "oreuranium");
		LanguageRegistry.instance().addNameForObject(oreuranium, "en_US",
				"Uranium Ore");
		LanguageRegistry.instance().addNameForObject(oreuranium, "ru_RU",
				"Фу, не знать английский");

		blocknetherstar = new BlockNetherStar(this.blocknetherstarblockID)
				.setUnlocalizedName("Nether Star Block");
		GameRegistry.registerBlock(blocknetherstar, "blocknetherstar");
		LanguageRegistry.instance().addNameForObject(blocknetherstar, "en_US",
				"Nether Star Block");
		LanguageRegistry.instance().addNameForObject(blocknetherstar, "ru_RU",
				"Фу, не знать английский");

		blockuranium = new BlockUranium(this.blockuraniumblockID)
				.setUnlocalizedName("Block Uranium");
		GameRegistry.registerBlock(blockuranium, "blockuranium");
		LanguageRegistry.instance().addNameForObject(blockuranium, "en_US",
				"Uranium Ore");
		LanguageRegistry.instance().addNameForObject(blockuranium, "ru_RU",
				"Фу, не знать английский");

		furnaceuraniumidle = new FurnaceUranium(this.furnaceuraniumidleblockID,
				false).setUnlocalizedName("Uranium Furnace Idle")
				.setCreativeTab(USM.USMTab);
		furnaceuraniumactive = new FurnaceUranium(
				this.furnaceuraniumactiveblockID, true).setUnlocalizedName(
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

		sworduranium = new SwordUranium(this.sworduraniumitemID)
				.setUnlocalizedName("Uranium Sword");
		GameRegistry.registerItem(sworduranium, "sworduranium");
		LanguageRegistry.instance().addNameForObject(sworduranium, "en_US",
				"Uranium Sword");
		LanguageRegistry.instance().addNameForObject(sworduranium,
				"Фу, не знать английский", "");

		stickiron = new StickIron(this.stickironitemID)
				.setUnlocalizedName("Iron Stick");
		GameRegistry.registerItem(stickiron, "stickiron");
		LanguageRegistry.instance().addNameForObject(stickiron, "en_US",
				"Iron Stick");
		LanguageRegistry.instance().addNameForObject(stickiron,
				"Фу, не знать английский", "");

		ingoturanium = new IngotUranium(this.ingoturaniumitemID)
				.setUnlocalizedName("Uranium Ingot");
		GameRegistry.registerItem(ingoturanium, "ingoturanium");
		LanguageRegistry.instance().addNameForObject(ingoturanium, "en_US",
				"Uranium Ingot");
		LanguageRegistry.instance().addNameForObject(ingoturanium,
				"Фу, не знать английский", "");

		ingotinfuseduranium = new IngotInfusedUranium(
				this.ingotinfuseduraniumitemID)
				.setUnlocalizedName("Infused Uranium Ingot");
		GameRegistry.registerItem(ingotinfuseduranium, "ingotinfuseduranium");
		LanguageRegistry.instance().addNameForObject(ingotinfuseduranium,
				"en_US", "Infused Uranium Ingot");
		LanguageRegistry.instance().addNameForObject(ingotinfuseduranium,
				"Фу, не знать английский", "");

		NetworkRegistry.instance().registerConnectionHandler(
				new ConnectionHandler());

		// OreDict
		OreDictionary.registerOre("ingotUranium", new ItemStack(ingoturanium));
		OreDictionary.registerOre("oreUranium", new ItemStack(oreuranium));
		OreDictionary.registerOre("stickIron", new ItemStack(stickiron));
		OreDictionary.registerOre("ironStick", new ItemStack(stickiron));
		OreDictionary.registerOre("ironRod", new ItemStack(stickiron));
		OreDictionary.registerOre("blockUranium", new ItemStack(blockuranium));

		// Крафты
		GameRegistry.addRecipe(new ShapedOreRecipe(USM.sworduranium, true,
				new Object[] { "#X#", "#X#", "@SA",
						// Character.valueOf('X'),
						// USM.ingotinfuseduranium,('S'), "stickIron",('#'),
						// Block.obsidian,('@'), Potion.harm,('A'),
						// Potion.damageBoost}));
						Character.valueOf('X'), USM.ingotinfuseduranium, ('S'),
						"stickIron", ('#'), Block.obsidian }));

		GameRegistry.addRecipe(new ShapedOreRecipe(USM.stickiron, true,
				new Object[] { "@", "@", Character.valueOf('@'),
						Item.ingotIron }));

		GameRegistry.addRecipe(new ShapedOreRecipe(USM.oreuranium, true,
				new Object[] { "@", Character.valueOf('@'), "oreUranium" }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(USM.ingoturanium, true,
				new Object[] { "@", Character.valueOf('@'), "crushedUranium" }));

		GameRegistry.addRecipe(new ShapedOreRecipe (new ItemStack(USM.blockuranium, 4),
				new Object[] { "@@", "@@", Character.valueOf('@'), "blockUranium" }));
        
		GameRegistry.addRecipe(new ShapedOreRecipe(USM.blocknetherstar, true,
				new Object[] { "@@@", "@@@", "@@@", Character.valueOf('@'),
						Item.netherStar }));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(USM.blockuranium, true,
				new Object[] { "@@@", "@@@", "@@@", Character.valueOf('@'),
						USM.ingoturanium }));

		GameRegistry.addRecipe(new ShapedOreRecipe(USM.furnaceuraniumidle,
				true, new Object[] { "@@@", "@X@", "!!!",
						Character.valueOf('@'), Block.blockIron, ('X'),
						Item.netherStar, ('!'), Block.coalBlock }));

		GameRegistry.addRecipe(new ShapedOreRecipe(USM.furnaceuraniumidle,
				true, new Object[] { "@@@", "@X@", "!!!",
						Character.valueOf('@'), Block.blockIron, ('X'),
						Block.dragonEgg, ('!'), Block.coalBlock }));

		GameRegistry.addSmelting(USM.oreuranium.blockID, new ItemStack(
				USM.ingoturanium, 1), 20.0F);
		GameRegistry.addShapelessRecipe(new ItemStack(USM.ingoturanium, 9),
				new Object[] { USM.blockuranium });
		GameRegistry.addShapelessRecipe(new ItemStack(Item.netherStar, 9),
				new Object[] { USM.blocknetherstar });

	}

	public static final EnumToolMaterial UraniumSword = EnumHelper.addToolMaterial(
			"UraniumSword", 3, 768, 9.0F, 71.0F, 50);
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
