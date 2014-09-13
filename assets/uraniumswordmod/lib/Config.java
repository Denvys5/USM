package assets.uraniumswordmod.lib;

import java.io.File;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.achievements.USMAchievementsList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class Config {

	
	public static Configuration config = new Configuration(new File(
			"config/USM/UraniumSwordMod.cfg"));
	
	public static void ConfigMethod() {
		config.load();
		Config.ConfigBlockID();
		Config.ConfigItemID();
		Config.ConfigOreRegister();
		Config.ConfigCrafts();
		config.save();
	}

	private static void ConfigCrafts(){
		
	}


	private static void ConfigOreRegister() {
		OreRegistration.OreUraniumRegister = config.get("Tweaks", "Uranium Ore Ore Registration", 1).getInt();
		OreRegistration.IngotUraniumRegister = config.get("Tweaks", "Uranium Ingot Ore Registration", 1).getInt();
		OreRegistration.IronStickRegister = config.get("Tweaks", "Iron Stick Ore Registration", 1).getInt();
	}

	private static void ConfigBlockID() {
		BlockList.oreuraniumblockID = config.get("Blocks", "Uranium Ore", 500).getInt();
		BlockList.blockuraniumblockID = config.get("Blocks", "Uranium Block", 502)
			.getInt();
		BlockList.blocknetherstarblockID = config.get("Blocks", "Nether Star Block", 501)
			.getInt();
		BlockList.blockinfuseduraniumblockID = config.get("Blocks", "Infused Uranium Block", 505)
				.getInt();
	
	
		BlockList.furnaceuraniumidleblockID = config.get("Tile Entities",
			"Uranium Furnace", 503).getInt();
		BlockList.furnaceuraniumactiveblockID = config.get("Tile Entities",
			"Uranium Furnace Active", 504).getInt();
		
	}
	
	private static void ConfigItemID(){
		BlockList.sworduraniumitemID = config.get("Items", "Uranium Sword", 1551)
				.getInt();
		BlockList.uraniumonstickitemID = config.get("Items", "Uranium On Stick", 1554)
				.getInt();
		BlockList.ingotinfuseduraniumitemID = config.get("Items",
				"Infused Uranium Ingot", 1553).getInt();
		BlockList.ingoturaniumitemID = config.get("Items", "Uranium Ingot", 1552)
				.getInt();
		BlockList.stickironitemID = config.get("Items", "Iron Stick", 1550).getInt();
	}

}
