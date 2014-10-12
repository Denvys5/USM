package com.denvys5.uraniumswordmod.uraniumduplicator;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.core.USM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiDuplicator extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(
			USM.modid, "textures/gui/Duplicator_gui.png");
	public TileEntityDuplicator duplicator;

	public GuiDuplicator(InventoryPlayer inventoryPlayer,
			TileEntityDuplicator entity) {
		super(new ContainerDuplicator(inventoryPlayer, entity));
		this.duplicator = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.duplicator.hasCustomInventoryName() ? this.duplicator
				.getInventoryName() : I18n
				.format(this.duplicator.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(name,
				this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6,
				4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8,
				this.ySize - 96, 4210752);
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if (this.duplicator.isBurning()) {
			int k = this.duplicator.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - k, 176,
					12 - k, 14, k + 2);
		}
		int k = this.duplicator.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, k + 1, 18);
	}

}
