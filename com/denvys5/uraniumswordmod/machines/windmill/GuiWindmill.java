package com.denvys5.uraniumswordmod.machines.windmill;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.ContainerPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.TileEntityPoweredGrinder;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiWindmill extends GuiContainer{
	public static final ResourceLocation texture = new ResourceLocation(USM.modid, "textures/gui/WindMill_gui.png");
	public TileEntityWindmill Windmill;

	public GuiWindmill(InventoryPlayer inventoryPlayer, TileEntityWindmill entity){
		super(new ContainerWindmill(inventoryPlayer, entity));
		this.Windmill = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2){
		String name = this.Windmill.hasCustomInventoryName() ? this.Windmill.getInventoryName() : I18n.format(this.Windmill.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 92, 4210752);
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int j){
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if(this.Windmill.storage.energy > 0){
			int k = this.Windmill.getPowerRemainingScaled(44);
			drawTexturedModalRect(guiLeft + 83, guiTop + 65 - k, 176, 44 - k, 11, k + 2);
		}
	}
}
