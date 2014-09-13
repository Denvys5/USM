package assets.uraniumswordmod.lib;

import java.io.File;
import java.nio.file.Path;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.achievements.USMAchievementsList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;

public class Config {
	
	public static Configuration config = new Configuration(new File(
			"config/USM/UraniumSwordMod.cfg"));
	
	public static void ConfigMethod() {
		config.load();
		ConfigBlockID();
		ConfigItemID();
		ConfigTweakRegister();
		ConfigCrafts();
		config.save();
	}

	private static void ConfigCrafts(){
		
	}


	private static void ConfigTweakRegister() {
		OreRegistration.OreUraniumRegister = config.get("Tweaks", "Uranium Ore Ore Registration", true).getBoolean(true);
		OreRegistration.IngotUraniumRegister = config.get("Tweaks", "Uranium Ingot Ore Registration", true).getBoolean(true);
		OreRegistration.IronStickRegister = config.get("Tweaks", "Iron Stick Ore Registration", true).getBoolean(true);
		//Property comment = config.get("Tweaks", "SomeConfigString", "nothing");
		//comment.comment = "This value can be read as a string!";
		//USM.UraniumSwordDamage = config.get("Tweaks", "Uranium Sword Base Damage", 1500.0).getDouble(1500.0);
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
		BlockList.UraniumHelmetID = config.get("Items", "Uranium Helmet", 3200).getInt();
		BlockList.UraniumChestID = config.get("Items", "Uranium Chestplate", 3201).getInt();
		BlockList.UraniumLegginsID = config.get("Items", "Uranium Leggins", 3202).getInt();
		BlockList.UraniumBootsID = config.get("Items", "Uranium Boots", 3203).getInt();
		
	}

}
