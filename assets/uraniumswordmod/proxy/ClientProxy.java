package assets.uraniumswordmod.proxy;

import assets.uraniumswordmod.USM;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy {
	public void registerRandomStuff() {

		LanguageRegistry.instance().addStringLocalization(
				USM.USMTab.getTranslatedTabLabel(), "Uranium Sword Mod");
	}
}
