package com.denvys5.uraniumswordmod.machines.windmill;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class WindMillPlatformRenderer extends TileEntitySpecialRenderer{

	private final ResourceLocation WindmillPlatform = new ResourceLocation(USM.modid, "models/tileentities/WindMillPlatform.png");
	private int textureWidth = 32;
	private int textureHeight = 32;
	private float pixel = 1F / 16F;

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f){
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float)x, (float)y, (float)z);
		Tessellator tessellator = Tessellator.instance;
		this.bindTexture(WindmillPlatform);
		tessellator.startDrawingQuads();
		
		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 0){
			tessellator.addVertexWithUV(1, pixel * 16, 1, 1F / textureWidth * (32), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(1, pixel * 16, 0, 1F / textureWidth * (32), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0, pixel * 16, 0, 1F / textureWidth * (0), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0, pixel * 16, 1, 1F / textureWidth * (0), 1F / textureHeight * (32));

			tessellator.addVertexWithUV(0, 0, 1, 1F / textureWidth * (0), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(0, 0, 0, 1F / textureWidth * (0), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(1, 0, 0, 1F / textureWidth * (32), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(1, 0, 1, 1F / textureWidth * (32), 1F / textureHeight * (32));
		}
		
		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 1){
			tessellator.addVertexWithUV(0.5, pixel * 16, 0.5, 1F / textureWidth * (32), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0, 1F / textureWidth * (32), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0, pixel * 16, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0, pixel * 16, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (32));

			tessellator.addVertexWithUV(0, 0, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(0, 0, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0.5, 0, 0, 1F / textureWidth * (32), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0.5, 0, 0.5, 1F / textureWidth * (32), 1F / textureHeight * (32));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 2){
			tessellator.addVertexWithUV(0.5, pixel * 16, 1, 1F / textureWidth * (32), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0, 1F / textureWidth * (32), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0, pixel * 16, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0, pixel * 16, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));

			tessellator.addVertexWithUV(0, 0, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0, 0, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0.5, 0, 0, 1F / textureWidth * (32), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0.5, 0, 1, 1F / textureWidth * (32), 1F / textureHeight * (8 + 16));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 3){
			tessellator.addVertexWithUV(0.5, pixel * 16, 1, 1F / textureWidth * (32), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0.5, 1F / textureWidth * (32), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0, pixel * 16, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0, pixel * 16, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8));

			tessellator.addVertexWithUV(0, 0, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0, 0, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0.5, 0, 0.5, 1F / textureWidth * (32), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0.5, 0, 1, 1F / textureWidth * (32), 1F / textureHeight * (8));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 4){
			tessellator.addVertexWithUV(1, pixel * 16, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(1, pixel * 16, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0, pixel * 16, 0, 1F / textureWidth * (8), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0, pixel * 16, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (32));

			tessellator.addVertexWithUV(0, 0, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(0, 0, 0, 1F / textureWidth * (8), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(1, 0, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(1, 0, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (32));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 5){
			tessellator.addVertexWithUV(1, pixel * 16, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(1, pixel * 16, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * 8);
			tessellator.addVertexWithUV(0, pixel * 16, 0, 1F / textureWidth * 8, 1F / textureHeight * 8);
			tessellator.addVertexWithUV(0, pixel * 16, 1, 1F / textureWidth * 8, 1F / textureHeight * (8 + 16));

			tessellator.addVertexWithUV(0, 0, 1, 1F / textureWidth * 8, 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0, 0, 0, 1F / textureWidth * 8, 1F / textureHeight * 8);
			tessellator.addVertexWithUV(1, 0, 0, 1F / textureWidth * (8 + 16), 1F / textureHeight * 8);
			tessellator.addVertexWithUV(1, 0, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8 + 16));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 6){
			tessellator.addVertexWithUV(1, pixel * 16, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(1, pixel * 16, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0, pixel * 16, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0, pixel * 16, 1, 1F / textureWidth * (8), 1F / textureHeight * (8));

			tessellator.addVertexWithUV(0, 0, 1, 1F / textureWidth * (8), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0, 0, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(1, 0, 0.5, 1F / textureWidth * (8 + 16), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(1, 0, 1, 1F / textureWidth * (8 + 16), 1F / textureHeight * (8));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 7){
			tessellator.addVertexWithUV(1, pixel * 16, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(1, pixel * 16, 0, 1F / textureWidth * (8), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0, 1F / textureWidth * (0), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0.5, 1F / textureWidth * (0), 1F / textureHeight * (32));

			tessellator.addVertexWithUV(0.5, 0, 0.5, 1F / textureWidth * (0), 1F / textureHeight * (32));
			tessellator.addVertexWithUV(0.5, 0, 0, 1F / textureWidth * (0), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(1, 0, 0, 1F / textureWidth * (8), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(1, 0, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (32));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 8){
			tessellator.addVertexWithUV(1, pixel * 16, 1, 1F / textureWidth * (8), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(1, pixel * 16, 0, 1F / textureWidth * (8), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0, 1F / textureWidth * (0), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0.5, pixel * 16, 1, 1F / textureWidth * (0), 1F / textureHeight * (8 + 16));

			tessellator.addVertexWithUV(0.5, 0, 1, 1F / textureWidth * (0), 1F / textureHeight * (8 + 16));
			tessellator.addVertexWithUV(0.5, 0, 0, 1F / textureWidth * (0), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(1, 0, 0, 1F / textureWidth * (8), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(1, 0, 1, 1F / textureWidth * (8), 1F / textureHeight * (8 + 16));
		}

		if(tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 9){
			tessellator.addVertexWithUV(1, pixel * 16, 1, 1F / textureWidth * (8), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(1, pixel * 16, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0.5, pixel * 16, 0.5, 1F / textureWidth * (0), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(0.5, pixel * 16, 1, 1F / textureWidth * (0), 1F / textureHeight * (8));

			tessellator.addVertexWithUV(0.5, 0, 1, 1F / textureWidth * (0), 1F / textureHeight * (8));
			tessellator.addVertexWithUV(0.5, 0, 0.5, 1F / textureWidth * (0), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(1, 0, 0.5, 1F / textureWidth * (8), 1F / textureHeight * (0));
			tessellator.addVertexWithUV(1, 0, 1, 1F / textureWidth * (8), 1F / textureHeight * (8));
		}

		tessellator.draw();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
