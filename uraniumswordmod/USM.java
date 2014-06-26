package assets.uraniumswordmod;

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
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod (modid = USM.modid, name = USM.name, version = USM.version)
@NetworkMod(clientSideRequired= true, serverSideRequired = false)


public class USM {
	public static final String modid = "uraniumswordmod";
	public static final String name = "Uranium Sword Mod";
	public static final String version = "OR3: First Tile";


	@Instance(modid)
	public static USM instance;
	
	
	public static Block oreuranium;
	public static Block blockuranium;
	public static Block blocknetherstar;
	public static Item sworduranium;
	public static Item ingotinfuseduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;
	public static final int guiIdFurnaceUranium = 0;
	
	
//	public static int oreuraniumblockID;

	
	@EventHandler
	public void Load(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new OreGenerator());
		
		oreuranium = new OreUranium(995).setUnlocalizedName("Uranium Ore");
		GameRegistry.registerBlock(oreuranium, "oreuranium");
		LanguageRegistry.instance().addNameForObject(oreuranium, "en_US", "Uranium Ore");
        LanguageRegistry.instance().addNameForObject(oreuranium, "ru_RU", "Фу, не знать английский");
        
        blocknetherstar = new BlockNetherStar(999).setUnlocalizedName("Nether Star Block");
		GameRegistry.registerBlock(blocknetherstar, "blocknetherstar");
		LanguageRegistry.instance().addNameForObject(blocknetherstar, "en_US", "Nether Star Block");
        LanguageRegistry.instance().addNameForObject(blocknetherstar, "ru_RU", "Фу, не знать английский");
        
        blockuranium = new BlockUranium(998).setUnlocalizedName("Block Uranium");
		GameRegistry.registerBlock(blockuranium, "blockuranium");
		LanguageRegistry.instance().addNameForObject(blockuranium, "en_US", "Uranium Ore");
        LanguageRegistry.instance().addNameForObject(blockuranium, "ru_RU", "Фу, не знать английский");
        
        furnaceuraniumidle = new FurnaceUranium(996, false).setUnlocalizedName("Uranium Furnace Idle").setCreativeTab(CreativeTabs.tabMaterials);
        furnaceuraniumactive = new FurnaceUranium(997, true).setUnlocalizedName("Uranium Furnace Active").setLightValue(0.8F);
        GameRegistry.registerBlock(furnaceuraniumidle, "Uranium Furnace");
        GameRegistry.registerBlock(furnaceuraniumactive, "Uranium Furnace Active");
        GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class, "FurnaceUranium");
        GuiHandler guiHandler = new GuiHandler();
        LanguageRegistry.instance().addStringLocalization("container.furnaceUranium", "Uranium Furnace");
        LanguageRegistry.instance().addNameForObject(furnaceuraniumidle, "en_US", "Uranium Furnace Idle");
        LanguageRegistry.instance().addNameForObject(furnaceuraniumidle, "Фу, не знать английский", "");
        
        sworduranium = new SwordUranium(1551).setUnlocalizedName("Uranium Sword");
        GameRegistry.registerItem(sworduranium, "sworduranium");
        LanguageRegistry.instance().addNameForObject(sworduranium, "en_US", "Uranium Sword");
        LanguageRegistry.instance().addNameForObject(sworduranium, "Фу, не знать английский", "");
        
        stickiron = new StickIron(1550).setUnlocalizedName("Iron Stick");
        GameRegistry.registerItem(stickiron, "stickiron");
        LanguageRegistry.instance().addNameForObject(stickiron, "en_US", "Iron Stick");
        LanguageRegistry.instance().addNameForObject(stickiron, "Фу, не знать английский", "");
        
        
        ingoturanium = new IngotUranium(1552).setUnlocalizedName("Uranium Ingot");
        GameRegistry.registerItem(ingoturanium, "ingoturanium");
        LanguageRegistry.instance().addNameForObject(ingoturanium, "en_US", "Uranium Ingot");
        LanguageRegistry.instance().addNameForObject(ingoturanium, "Фу, не знать английский", "");
        
        ingotinfuseduranium = new IngotInfusedUranium(1553).setUnlocalizedName("Infused Uranium Ingot");
        GameRegistry.registerItem(ingotinfuseduranium, "ingotinfuseduranium");
        LanguageRegistry.instance().addNameForObject(ingotinfuseduranium, "en_US", "Infused Uranium Ingot");
        LanguageRegistry.instance().addNameForObject(ingotinfuseduranium, "Фу, не знать английский", "");

//OreDict
        OreDictionary.registerOre("ingotUranium", new ItemStack(ingoturanium));
        OreDictionary.registerOre("crushedUranium", new ItemStack(ingoturanium));
        OreDictionary.registerOre("oreUranium", new ItemStack(oreuranium));
        OreDictionary.registerOre("stickIron", new ItemStack(stickiron));
        OreDictionary.registerOre("ironStick", new ItemStack(stickiron));
        OreDictionary.registerOre("blockUranium", new ItemStack(blockuranium));
        
//Крафты        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.sworduranium, true,
        new Object[]{ "#X#", "#X#", "@SA",
        //Character.valueOf('X'), USM.ingotinfuseduranium,('S'), "stickIron",('#'), Block.obsidian,('@'), Potion.harm,('A'), Potion.damageBoost}));
        Character.valueOf('X'), USM.ingotinfuseduranium,('S'), "stickIron",('#'), Block.obsidian}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.stickiron, true,
        new Object[]{ "@", "@",
        Character.valueOf('@'), "ingotRefinedIron"}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.oreuranium, true,
        new Object[]{ "@",
        Character.valueOf('@'), "oreUranium"}));
     
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.blockuranium, true,
        new Object[]{ "@",
        Character.valueOf('@'), "blockUranium"}));
      
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.blocknetherstar, true,
        new Object[]{ "@@@", "@@@", "@@@",
        Character.valueOf('@'), Item.netherStar}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.furnaceuraniumidle, true,
        new Object[]{ "@@@", "@X@", "!!!",
        Character.valueOf('@'), Block.blockIron, ('X'), Item.netherStar, ('!'), Block.coalBlock}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.furnaceuraniumidle, true,
        new Object[]{ "@@@", "@X@", "!!!",
        Character.valueOf('@'), Block.blockIron, ('X'), Block.dragonEgg, ('!'), Block.coalBlock}));
        
        GameRegistry.addSmelting(USM.oreuranium.blockID, new ItemStack(USM.ingoturanium, 1), 20.0F);
        //GameRegistry.addShapelessRecipe(new ItemStack(USM.ingoturanium, 1), new Object[] {USM.oreuranium});
        GameRegistry.addShapelessRecipe(new ItemStack(USM.ingoturanium, 9), new Object[] {USM.blockuranium});
        GameRegistry.addShapelessRecipe(new ItemStack(Item.netherStar, 9), new Object[] {USM.blocknetherstar});
        
		
		
	}
	public static final EnumToolMaterial Uranium = EnumHelper.addToolMaterial("Uranium", 3, 768, 9, 71, 50);
//  @EventHandler    
//	public void preLoad(FMLPreInitializationEvent event)
//	{
//	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
//	config.load();
//	oreuraniumblockID = config.getBlock("uraniumore", 1000).getInt();
//	config.save();
//	}
	
	
}
