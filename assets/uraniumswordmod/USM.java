package assets.uraniumswordmod;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import assets.uraniumswordmod.potions.Radiation;
import assets.uraniumswordmod.generator.OreGenerator;
import assets.uraniumswordmod.gui.GuiHandler;
import assets.uraniumswordmod.lib.BlockList;
import assets.uraniumswordmod.lib.Config;
import assets.uraniumswordmod.lib.ConnectionHandler;
import assets.uraniumswordmod.lib.OreRegistration;
import assets.uraniumswordmod.lib.RecipeList;
import assets.uraniumswordmod.lib.USMEventHooks;
import assets.uraniumswordmod.lib.UraniumFurnaceFuelHandler;
import assets.uraniumswordmod.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(modid = USM.modid, name = USM.name, version = USM.version/*, dependencies = "required-after:Denvys5Core@[1.0,)"*/)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class USM {
	public static final String modid = "uraniumswordmod";
	public static final String name = "Uranium Sword Mod";
	public static final String version = "0.3";

	@Instance(modid)
	public static USM instance;

	@SidedProxy(clientSide = "assets.uraniumswordmod.proxy.ClientProxy", serverSide = "assets.uraniumswordmod.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final int guiIdFurnaceUranium = 0;
	public static CreativeTabs USMTab;
	public static AchievementPage USMAchievPage = new AchievementPage("Uranium Sword Mod");
	public void initConfiguration(FMLInitializationEvent event) {
		Config.ConfigMethod();
	}
	public static double UraniumSwordDamage; 
	
	public static Potion RadiationUSM;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Potion[] potionTypes = null;

		for (Field f : Potion.class.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

					potionTypes = (Potion[])f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
				f.set(null, newPotionTypes);
				}
			}
			catch (Exception e) {
				System.err.println("Severe error, please report this to the mod author:");
				System.err.println(e);
			}
		}

		MinecraftForge.EVENT_BUS.register(new USMEventHooks());
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
		BlockList.armourRegister();
		RecipeList.ShapedOreCrafting();
		RecipeList.ShapelessCrafting();
		RecipeList.VanillaSmeltingRecipes();
		proxy.registerRandomStuff();
		AchievementPage.registerAchievementPage(USMAchievPage);
		NetworkRegistry.instance().registerConnectionHandler(
				new ConnectionHandler());
		OreRegistration.BooleanRegister();
		RadiationUSM = (new Radiation(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.radiationusm");
	}
	public static final EnumToolMaterial UraniumSword = EnumHelper
			.addToolMaterial("UraniumSword", 3, 768, 9.0F, 1496.0F, 50);

	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
