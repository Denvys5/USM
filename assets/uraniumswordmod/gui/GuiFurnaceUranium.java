package assets.uraniumswordmod.gui;

import org.lwjgl.opengl.GL11;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFurnaceUranium extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation(
			USM.modid, "textures/gui/FurnaceUranium_gui.png");
	public TileEntityFurnaceUranium furnaceUranium;

	public GuiFurnaceUranium(InventoryPlayer inventoryPlayer,
			TileEntityFurnaceUranium entity) {
		super(new ContainerFurnaceUranium(inventoryPlayer, entity));
		this.furnaceUranium = entity;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.furnaceUranium.isInvNameLocalised() ? this.furnaceUranium
				.getInvName() : I18n
				.getString(this.furnaceUranium.getInvName());
		this.fontRenderer.drawString(name,
				this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2, 6,
				4210752);
		this.fontRenderer.drawString(I18n.getString("container.inventory"), 8,
				this.ySize - 96, 4210752);
	}

	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		mc.getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		if (this.furnaceUranium.isBurning()) {
			int k = this.furnaceUranium.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - k, 176,
					12 - k, 14, k + 2);
		}
		int k = this.furnaceUranium.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, k + 1, 18);
	}

}
