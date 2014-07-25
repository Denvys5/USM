package assets.uraniumswordmod.lib;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandler implements IConnectionHandler {

	public void playerLoggedIn(Player player, NetHandler netHandler,
			INetworkManager manager) {
		netHandler.getPlayer().addChatMessage(
				EnumChatFormatting.GOLD
						+ "Uranium Furnace NEI integration is still WIP");
		if(OreRegistration.OreUraniumRegister != 1){
			netHandler.getPlayer().addChatMessage(
					EnumChatFormatting.GOLD
							+ "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Uranium Ore)");	
		}
		if(OreRegistration.IngotUraniumRegister != 1){
			netHandler.getPlayer().addChatMessage(
					EnumChatFormatting.GOLD
							+ "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Uranium Ingots)");	
		}
		if(OreRegistration.IronStickRegister != 1){
			netHandler.getPlayer().addChatMessage(
					EnumChatFormatting.GOLD
							+ "Uranium Sword Mod Ore Registration turned off. Some crafts may dissapear (with Iron Stick)");	
		}
	}

	public String connectionReceived(NetLoginHandler netHandler,
			INetworkManager manager) {
		return null;
	}

	public void connectionOpened(NetHandler netClientHandler, String server,
			int port, INetworkManager manager) {

	}

	public void connectionOpened(NetHandler netClientHandler,
			MinecraftServer server, INetworkManager manager) {

	}

	public void connectionClosed(INetworkManager manager) {

	}

	public void clientLoggedIn(NetHandler clientHandler,
			INetworkManager manager, Packet1Login login) {

	}

}
