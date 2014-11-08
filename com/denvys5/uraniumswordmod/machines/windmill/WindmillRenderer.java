package com.denvys5.uraniumswordmod.machines.windmill;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class WindmillRenderer extends TileEntitySpecialRenderer{

	private final ResourceLocation Windmill = new ResourceLocation(USM.modid, "models/tileentities/Windmill.png");
	private int textureWidth = 64;
	private int textureHeight = 32;

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f){
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x, (float)y, (float)z);
		Tessellator tessellator = Tessellator.instance;
		this.bindTexture(Windmill);
		tessellator.startDrawingQuads();
		{
			tessellator.addVertexWithUV(0, 0, 1, 1, 1);
			tessellator.addVertexWithUV(0, 1, 1, 1, 0);
			tessellator.addVertexWithUV(0, 1, 0, 0, 0);
			tessellator.addVertexWithUV(0, 0, 0, 0, 1);
		}
		tessellator.draw();
		GL11.glPopMatrix();
	}

}
