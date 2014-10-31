package com.denvys5.uraniumswordmod.machines.windmill;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class WindMillPlatformRenderer extends TileEntitySpecialRenderer{

	private final ResourceLocation WindmillPlatform = new ResourceLocation(USM.modid, "models/tileentities/WindmillPlatform.png");
	private int textureWidth = 32;
	private int textureHeight = 32;
	private float pixel = 1F/16F;
	
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		GL11.glPushMatrix();
			GL11.glTranslatef((float)x, (float)y, (float)z);
			Tessellator tessellator = Tessellator.instance;
			this.bindTexture(WindmillPlatform);
			tessellator.startDrawingQuads();
			if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 5){
				tessellator.addVertexWithUV(0, pixel*5, 1, 1F/textureWidth*(8 + 16), 1F/textureHeight*(8 + 16));
				tessellator.addVertexWithUV(1, pixel*5, 1, 1F/textureWidth*8, 1F/textureHeight*8);
				tessellator.addVertexWithUV(1, pixel*5, 0, 1F/textureWidth*8, 1F/textureHeight*8);
				tessellator.addVertexWithUV(0, pixel*5, 0, 1F/textureWidth*8, 1F/textureHeight*(8 + 16));
			}
			tessellator.draw();
		GL11.glPopMatrix();
	}

}
