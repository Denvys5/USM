package com.denvys5.uraniumswordmod.events;

import org.lwjgl.input.Keyboard;

import com.denvys5.uraniumswordmod.item.UraniumArmor;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyHandler {
	/** Key index for easy handling */
	public static final int CUSTOM_INV = 0;
	/** Key descriptions; use a language file to localize the description later */
	private static final String[] desc = {"key.USM.TC4Goggles", "key.USM.NightVis"};
	/** Default key values */
	private static final int[] keyValues = {Keyboard.KEY_P, Keyboard.KEY_I};
	private final KeyBinding[] keys;
	
	
	public KeyHandler() {
		keys = new KeyBinding[desc.length];
		for (int i = 0; i < desc.length; ++i) {
			keys[i] = new KeyBinding(desc[i], keyValues[i], "key.USM.category");
			ClientRegistry.registerKeyBinding(keys[i]);
		}
	}
	
	
	/**
	* KeyInputEvent is in the FML package, so we must register to the FML event bus
	*/
	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
	// FMLClientHandler.instance().getClient().inGameHasFocus
		if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
			if (keys[0].isPressed()) {
				if(UraniumArmor.Goggles){
					UraniumArmor.Goggles = false;
				}else{
					UraniumArmor.Goggles = true;
				}
			}
			if(keys[1].isPressed()){
				if(UraniumArmor.NightVis){
					UraniumArmor.NightVis = false;
				}else{
					UraniumArmor.NightVis = true;
				}
			}
		}
	}
}
