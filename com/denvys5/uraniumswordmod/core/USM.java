package com.denvys5.uraniumswordmod.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;

import com.denvys5.uraniumswordmod.api.GuiHandler;
import com.denvys5.uraniumswordmod.core.proxy.CommonProxy;
import com.denvys5.uraniumswordmod.effects.Radiation;
import com.denvys5.uraniumswordmod.events.USMEventHooks;
import com.denvys5.uraniumswordmod.oregenerators.UraniumOreGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = USM.modid, name = USM.name, version = USM.version/*, dependencies = "required-after:Denvys5Core@[1.0,)"*/)
public class USM {
	public static final String modid = "uraniumswordmod";
	public static final String name = "Uranium Sword Mod";
	public static final String version = "0.4.3";

	@Instance(modid)
	public static USM instance;

	@SidedProxy(clientSide = "com.denvys5.uraniumswordmod.core.proxy.ClientProxy", serverSide = "com.denvys5.uraniumswordmod.core.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final int guiIdFurnaceUranium = 0;
	public static CreativeTabs USMTab;
	public static AchievementPage USMAchievPage = new AchievementPage("Uranium Sword Mod");
	public void initConfiguration(FMLInitializationEvent event) {
		Config.ConfigMethod();
	}
	public static double UraniumSwordDamage; 
	public static ToolMaterial UraniumSword = EnumHelper.addToolMaterial("UraniumSword", 3, 768, 9.0F, 1496.0F, 50);
	 public static ToolMaterial UraniumPick = EnumHelper.addToolMaterial("UraniumPick", 4, 1000, 15.0F, 8.0F, 50);
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
			public Item getTabIconItem() {
				return BlockList.sworduranium;
				//return Item.getItemFromBlock(BlockList.blocknetherstar);
			}

		};
		GameRegistry.registerWorldGenerator(new UraniumOreGenerator(), 0);
		GuiHandler guiHandler = new GuiHandler();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
		BlockList.blockRegister();
		BlockList.itemRegister();
		BlockList.armorRegister();
		RecipeList.ShapedOreCrafting();
		RecipeList.ShapelessCrafting();
		RecipeList.VanillaSmeltingRecipes();
		proxy.registerRandomStuff();
		AchievementPage.registerAchievementPage(USMAchievPage);
		OreRegistration.BooleanRegister();
		RadiationUSM = (new Radiation(32, false, 0)).setIconIndex(0, 0).setPotionName("potion.radiationusm");
	}


	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
