package com.denvys5.uraniumswordmod.machines.uraniumduplicator;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiDuplicator extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(USM.modid, "textures/gui/Duplicator_gui.png");
	public TileEntityDuplicator Duplicator;

	public GuiDuplicator(InventoryPlayer inventoryPlayer, TileEntityDuplicator entity) {
		super(new ContainerDuplicator(inventoryPlayer, entity));
		this.Duplicator = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.Duplicator.hasCustomInventoryName() ? this.Duplicator.getInventoryName() : I18n.format(this.Duplicator.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 92, 4210752);
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if (this.Duplicator.hasPower()) {
			int k = this.Duplicator.getPowerRemainingScaled(44);
			drawTexturedModalRect(guiLeft + 10, guiTop + 52 - k, 176, 74 - k, 11, k + 2);
		}
		int k = this.Duplicator.getGrinderProgressScaled(25);
		drawTexturedModalRect(guiLeft + 79, guiTop + 29, 176, 0, k + 1, 29);
	}

}
