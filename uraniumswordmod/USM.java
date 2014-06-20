package assets.uraniumswordmod;

import assets.uraniumswordmod.block.BlockUranium;
import assets.uraniumswordmod.block.OreUranium;
import assets.uraniumswordmod.item.IngotUranium;
import assets.uraniumswordmod.item.StickIron;
import assets.uraniumswordmod.item.SwordUranium;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod (modid = "uraniumswordmod", name = "Uranium Sword Mod", version = "OR3: First Tile")


public class USM {
	private static final String modid = "uraniumswordmod";


	@Instance(modid)
	public static USM instance;
	
	
	public static Block oreuranium;
	public static Block blockuranium;
	public static Item sworduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	public static Block furnaceuraniumidle;
	public static Block furnaceuraniumactive;
	public static final int guiIdFurnaceUranium = 0;
	
	
//	public static int oreuraniumblockID;
//	public static int sworduraniumblockID;
//	public static int ingoturaniumblockID;
	
	@EventHandler
	public void Load(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(new OreGenerator());
		
		oreuranium = new OreUranium(995).setUnlocalizedName("Uranium Ore");
		GameRegistry.registerBlock(oreuranium, "oreuranium");
		LanguageRegistry.instance().addNameForObject(oreuranium, "en_US", "Uranium Ore");
        LanguageRegistry.instance().addNameForObject(oreuranium, "ru_RU", "Фу, не знать английский");
        
        blockuranium = new BlockUranium(998).setUnlocalizedName("Block Uranium");
		GameRegistry.registerBlock(blockuranium, "blockuranium");
		LanguageRegistry.instance().addNameForObject(oreuranium, "en_US", "Uranium Ore");
        LanguageRegistry.instance().addNameForObject(oreuranium, "ru_RU", "Фу, не знать английский");
        
//        furnaceuraniumidle = new FurnaceUranium(996, false).setUnlocalizedName("Uranium Furnace Idle").setCreativeTab(CreativeTabs.tabMaterials);
//        furnaceuraniumactive = new FurnaceUranium(997, true).setUnlocalizedName("Uranium Furnace Active").setLightValue(0.8F);
//        GameRegistry.registerBlock(furnaceuraniumidle, "Uranium Furnace");
//        GameRegistry.registerBlock(furnaceuraniumactive, "Uranium Furnace Active");
//        GameRegistry.registerTileEntity(TileEntityFurnaceUranium.class, "FurnaceUranium");
//        GuiHandler guiHandler = new GuiHandler();
//        LanguageRegistry.instance().addStringLocalization("container.furnaceUranium", "Uranium Furnace");
        
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
        
        
        OreDictionary.registerOre("ingotUranium", new ItemStack(ingoturanium));
        OreDictionary.registerOre("crushedUranium", new ItemStack(ingoturanium));
        OreDictionary.registerOre("oreUranium", new ItemStack(oreuranium));
        OreDictionary.registerOre("stickIron", new ItemStack(stickiron));
        OreDictionary.registerOre("ironStick", new ItemStack(stickiron));
        
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.sworduranium, true,
        new Object[]{ "#X#", "#X#", "@SA",
        //Character.valueOf('X'), "crushedUranium",('S'), "stickIron",('#'), Block.obsidian,('@'), Potion.harm,('A'), Potion.damageBoost}));
        Character.valueOf('X'), "crushedUranium",('S'), "stickIron",('#'), Block.obsidian}));
        

        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.stickiron, true,
        new Object[]{ "X", "X",
        Character.valueOf('X'), "ingotIron"}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.oreuranium, true,
        new Object[]{ "@",
        Character.valueOf('@'), "oreUranium"}));
        
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.blockuranium, true,
        new Object[]{ "X@",
        Character.valueOf('@'), "blockUranium"}));
        
//        GameRegistry.addRecipe(new ShapedOreRecipe(USM.furnaceuraniumidle, true,
//        new Object[]{ "@@@", "@X@", "!!!",
//        Character.valueOf('@'), Block.blockIron, ('X'), Item.netherStar, ('!'), Block.coalBlock}));
        
        GameRegistry.addSmelting(USM.oreuranium.blockID, new ItemStack(USM.ingoturanium, 1), 20.0F);
        //GameRegistry.addShapelessRecipe(new ItemStack(USM.ingoturanium, 1), new Object[] {USM.oreuranium});
        
		
		
	}
	public static final EnumToolMaterial Uranium = EnumHelper.addToolMaterial("Uranium", 3, 768, 9, 71, 25);
    
//	public void preLoad(FMLPreInitializationEvent event)
//	{
//	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
//	config.load();
//	oreuraniumblockID = config.getBlock("uraniumore", 1000).getInt();
//	sworduraniumblockID = config.getBlock("uraniumore", 1001).getInt();
//	ingoturaniumblockID = config.getBlock("uraniumore", 1002).getInt();
//	config.save();
//	}
	
	
}
