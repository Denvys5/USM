package assets.uraniumswordmod;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod (modid = "uraniumswordmod", name = "Uranium Sword Mod", version = "OR3")


public class USM {
	
	public static Block oreuranium;
	public static Item sworduranium;
	public static Item ingoturanium;
	public static Item stickiron;
	
	
//	public static int oreuraniumblockID;
//	public static int sworduraniumblockID;
//	public static int ingoturaniumblockID;
	
	@EventHandler
	public void Load(FMLInitializationEvent event)
	{
		
		oreuranium = new OreUranium(995).setUnlocalizedName("Uranium Ore");
		GameRegistry.registerBlock(oreuranium, "oreuranium");
		LanguageRegistry.instance().addNameForObject(oreuranium, "en_US", "Uranium Ore");
        LanguageRegistry.instance().addNameForObject(oreuranium, "ru_RU", "Фу, не знать английский");
		
        sworduranium = new SwordUranium(1550).setUnlocalizedName("Uranium Sword");
        GameRegistry.registerItem(sworduranium, "sworduranium");
        LanguageRegistry.instance().addNameForObject(sworduranium, "en_US", "Uranium Sword");
        LanguageRegistry.instance().addNameForObject(sworduranium, "Фу, не знать английский", "");
        
        stickiron = new StickIron(1551).setUnlocalizedName("Iron Stick");
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
        new Object[]{ "#X#", "#X#", "@S@",
        Character.valueOf('X'), "ingotUranium",('S'), "stickIron",('#'), Block.obsidian}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.sworduranium, true,
        new Object[]{ "#X#", "#X#", "@S@",
        Character.valueOf('X'), "crushedUranium",('S'), "stickIron",('#'), Block.obsidian}));
        
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.stickiron, true,
        new Object[]{ "X@", "X@",
        Character.valueOf('X'), "ingotIron"}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(USM.stickiron, true,
        new Object[]{ "@X", "@X",
        Character.valueOf('X'), "ingotIron"}));
        
        //GameRegistry.addSmelting(USM.oreuranium, new ItemStack(USM.ingoturanium, 1), 1.0F);
        GameRegistry.addShapelessRecipe(new ItemStack(USM.ingoturanium, 1), new Object[] {USM.oreuranium});

		
		
	}
	public static final EnumToolMaterial Uranium = EnumHelper.addToolMaterial("Uranium", 3, 768, 9, 4, 25);
    
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
