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
import assets.uraniumswordmod.lib.BlockList;
import assets.uraniumswordmod.lib.Config;
import assets.uraniumswordmod.lib.ConnectionHandler;
import assets.uraniumswordmod.lib.OreRegistration;
import assets.uraniumswordmod.lib.RecipeList;
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
	public static final String version = "0.0.5.2";

	@Instance(modid)
	public static USM instance;

	@SidedProxy(clientSide = "assets.uraniumswordmod.proxy.ClientProxy", serverSide = "assets.uraniumswordmod.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final int guiIdFurnaceUranium = 0;
	public static CreativeTabs USMTab;

	public void initConfiguration(FMLInitializationEvent event) {
		Config.ConfigMethod();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		this.initConfiguration(event);

		USMTab = new CreativeTabs("uraniumswordmodtab") {
			@SideOnly(Side.CLIENT)
			public int getTabIconItemIndex() {
				return BlockList.sworduranium.itemID;
			}
		};
		GameRegistry.registerWorldGenerator(new OreGenerator());
		GuiHandler guiHandler = new GuiHandler();
		BlockList.blockRegister();
		BlockList.itemRegister();
		RecipeList.ShapedOreCrafting();
		RecipeList.ShapelessCrafting();
		RecipeList.VanillaSmeltingRecipes();
		proxy.registerRandomStuff();
		NetworkRegistry.instance().registerConnectionHandler(
				new ConnectionHandler());
		OreRegistration.BooleanRegister();
	}
	public static final EnumToolMaterial UraniumSword = EnumHelper
			.addToolMaterial("UraniumSword", 3, 768, 9.0F, 71.0F, 50);

	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
