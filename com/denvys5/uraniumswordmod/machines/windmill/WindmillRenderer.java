package com.denvys5.uraniumswordmod.machines.windmill;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.Config;
import com.denvys5.uraniumswordmod.machines.TileEntityMachine;
import com.denvys5.uraniumswordmod.machines.USMTiles;
import com.denvys5.uraniumswordmod.machines.pipes.PipeRenderer;
import com.denvys5.uraniumswordmod.machines.pipes.TileEntityPipe;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class WindmillRenderer extends TileEntitySpecialRenderer{

	private final ResourceLocation Windmill = new ResourceLocation(USM.modid, "models/tileentities/Windmill.png");
	private final ResourceLocation BoxTexture = new ResourceLocation(USM.modid, "models/tileentities/Windmill_Box.png");
	private final ResourceLocation BoxFrontTexture = new ResourceLocation(USM.modid, "models/tileentities/Windmill_Box_front.png");
	private final ResourceLocation WindmillWing = new ResourceLocation(USM.modid, "models/tileentities/Windmill_Wing.png");
	private final ResourceLocation Pipe = new ResourceLocation(USM.modid, "models/tileentities/BlockPipe.png");
	private int textureWidth = 32;
	private int textureHeight = 32;
	private int wingHeight = 64;
	private float pixel = 1F / 16F;
	private float texturePixel = 1F/32F;
	boolean drawInside = Config.renderDrawInside;

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f){
		int x1 = tileentity.xCoord;
		int y1 = tileentity.yCoord;
		int z1 = tileentity.zCoord;
		while(tileentity.getWorldObj().getBlockMetadata(x1, y1, z1) < 7 && tileentity.getWorldObj().getBlock(x1, y1, z1).equals(USMTiles.WindmillBlock)){
			y1++;

		}
		int direction = tileentity.getWorldObj().getBlockMetadata(x1, y1, z1) - 8;
		int metadata = tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);

		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glTranslatef((float)x, (float)y, (float)z);

		GL11.glTranslatef(0.5F, 0, 0.5F);
		GL11.glRotatef(direction * 90, 0, 1, 0);
		GL11.glTranslatef(-0.5F, 0, -0.5F);

		if(metadata == 1){
			TileEntity pipe = tileentity.getWorldObj().getTileEntity(tileentity.xCoord+1, tileentity.yCoord, tileentity.zCoord);
			if(pipe instanceof TileEntityPipe || pipe instanceof TileEntityMachine) drawConnector(ForgeDirection.EAST);
			pipe = tileentity.getWorldObj().getTileEntity(tileentity.xCoord-1, tileentity.yCoord, tileentity.zCoord);
			if(pipe instanceof TileEntityPipe || pipe instanceof TileEntityMachine) drawConnector(ForgeDirection.WEST);
			pipe = tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord+1);
			if(pipe instanceof TileEntityPipe || pipe instanceof TileEntityMachine) drawConnector(ForgeDirection.SOUTH);
			pipe = tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord-1);
			if(pipe instanceof TileEntityPipe || pipe instanceof TileEntityMachine) drawConnector(ForgeDirection.NORTH);
		}
		
		Tessellator tessellator = Tessellator.instance;
		this.bindTexture(Windmill);
		tessellator.startDrawingQuads();
		{

			
			if(metadata > 0 && metadata < 7){
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureWidth));
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureWidth));

				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureWidth));
				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureWidth));

				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureWidth));
				tessellator.addVertexWithUV((16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureWidth));

				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 1 * (1F / textureWidth));
				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, (16 - 8) / 2 * pixel, 8 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 1, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 0 * (1F / textureWidth));
				tessellator.addVertexWithUV(1 - (16 - 8) / 2 * pixel, 0, 1 - (16 - 8) / 2 * pixel, 0 * (1F / textureWidth), 1 * (1F / textureWidth));
			}
			
			if(metadata > 7){
				this.bindTexture(BoxTexture);
				tessellator.addVertexWithUV(1, 1, 1, 1, 1);
				tessellator.addVertexWithUV(1, 1, 0, 1, 0);
				tessellator.addVertexWithUV(0, 1, 0, 0, 0);
				tessellator.addVertexWithUV(0, 1, 1, 0, 1);
				
				tessellator.addVertexWithUV(0, 0, 1, 0, 0);
				tessellator.addVertexWithUV(0, 0, 0, 0, 1);
				tessellator.addVertexWithUV(1, 0, 0, 1, 1);
				tessellator.addVertexWithUV(1, 0, 1, 1, 0);
				
				tessellator.addVertexWithUV(1, 0, 1, 1, 1);
				tessellator.addVertexWithUV(1, 1, 1, 1, 0);
				tessellator.addVertexWithUV(0, 1, 1, 0, 0);
				tessellator.addVertexWithUV(0, 0, 1, 0, 1);
				
				tessellator.addVertexWithUV(1, 0, 0, 1, 0);
				tessellator.addVertexWithUV(0, 0, 0, 0, 0);
				tessellator.addVertexWithUV(0, 1, 0, 0, 1);
				tessellator.addVertexWithUV(1, 1, 0, 1, 1);
				
				tessellator.addVertexWithUV(1, 0, 0, 1, 1);
				tessellator.addVertexWithUV(1, 1, 0, 1, 0);
				tessellator.addVertexWithUV(1, 1, 1, 0, 0);
				tessellator.addVertexWithUV(1, 0, 1, 0, 1);
				

			}
		}
		tessellator.draw();
		if(metadata > 7) drawHead();
		if(metadata > 7) drawRotor(tileentity);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}
	
	public void drawConnector(ForgeDirection direction){
		Tessellator tessellator = Tessellator.instance;
		this.bindTexture(Pipe);
		tessellator.startDrawingQuads();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(-90, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.NORTH)){
			GL11.glRotatef(90, 0, 0, 1);
		}else if(direction.equals(ForgeDirection.SOUTH)){	
			GL11.glRotatef(-90, 0, 0, 1);
		}else if(direction.equals(ForgeDirection.WEST)){
			GL11.glRotatef(90, 1, 0, 0);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 0*texturePixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			
			tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 0*texturePixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 0*texturePixel);
			
			tessellator.addVertexWithUV(1-11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 0*texturePixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);

			tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 0*texturePixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 5*texturePixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 5*texturePixel);
			
			
			if(drawInside){
				tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 5*texturePixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 5*texturePixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 0*texturePixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
	
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 0*texturePixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 0*texturePixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 5*texturePixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 0*texturePixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 5*texturePixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 5*texturePixel);

				tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 11*pixel/2, 10*texturePixel, 5*texturePixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 14*texturePixel, 5*texturePixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 14*texturePixel, 0*texturePixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-4*pixel, 1-11*pixel/2, 10*texturePixel, 0*texturePixel);
			}
			
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(90, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.NORTH)){
			GL11.glRotatef(-90, 0, 0, 1);
		}else if(direction.equals(ForgeDirection.SOUTH)){	
			GL11.glRotatef(90, 0, 0, 1);
		}else if(direction.equals(ForgeDirection.WEST)){
			GL11.glRotatef(-90, 1, 0, 0);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}
	
	private void drawRotor(TileEntity tileentity){
		TileEntityWindmill windmill = (TileEntityWindmill)tileentity.getWorldObj().getTileEntity(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord);
		GL11.glTranslatef(0, 0.5F, 0.5F);
		GL11.glRotatef(windmill.rotation, 1, 0, 0);
		GL11.glTranslatef(0, -0.5F, -0.5F);
		Tessellator tessellator = Tessellator.instance;
		this.bindTexture(WindmillWing);
		tessellator.startDrawingQuads();
		{
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F+1*pixel, 9 * (1F / textureWidth), 1 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 2.5F,  0.5F+1*pixel, 9 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 2.5F,  0.5F-1*pixel, 8 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F-1*pixel, 8 * (1F / textureWidth), 1 * (1F / wingHeight));
			
				//tessellator.addVertexWithUV(-2 * pixel, -1.5F,  0.5F+1*pixel, 9 * (1F / textureWidth), 1 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F+1*pixel, 9 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F-1*pixel, 8 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, -1.5F,  0.5F-1*pixel, 8 * (1F / textureWidth), 1 * (1F / wingHeight));
				
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  2.5F, 9 * (1F / textureWidth), 1 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  2.5F, 9 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F+1*pixel, 8 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F+1*pixel, 8 * (1F / textureWidth), 1 * (1F / wingHeight));
				
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F-1*pixel, 9 * (1F / textureWidth), 1 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F-1*pixel, 9 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  -1.5F, 8 * (1F / textureWidth), 0 * (1F / wingHeight));
				//tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  -1.5F, 8 * (1F / textureWidth), 1 * (1F / wingHeight));
				
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F+1*pixel, 1, 1);
				tessellator.addVertexWithUV(-2 * pixel, 2.5F,  0.5F+1*pixel, 1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 2.5F,  0.5F-1*pixel, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F-1*pixel, 0, 1);
				
				tessellator.addVertexWithUV(-2 * pixel, -1.5F,  0.5F+1*pixel, 1, 2);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F+1*pixel, 1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F-1*pixel, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, -1.5F,  0.5F-1*pixel, 0, 1);
				
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  2.5F, 1, 1);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  2.5F, 1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F+1*pixel, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F+1*pixel, 0, 1);
				
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F-1*pixel, 1, 1);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F-1*pixel,1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  -1.5F, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  -1.5F, 0, 1);
				

				
				
				
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F-1*pixel, 0, 1);
				tessellator.addVertexWithUV(-2 * pixel, 2.5F,  0.5F-1*pixel, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 2.5F,  0.5F+1*pixel, 1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F+1*pixel, 1, 1);

				tessellator.addVertexWithUV(-2 * pixel, -1.5F,  0.5F-1*pixel, 0, 1);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F-1*pixel, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F+1*pixel, 1, 0);
				tessellator.addVertexWithUV(-2 * pixel, -1.5F,  0.5F+1*pixel, 1, 2);

				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F+1*pixel, 0, 1);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F+1*pixel, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  2.5F, 1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  2.5F, 1, 1);

				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  -1.5F, 0, 1);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  -1.5F, 0, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F+1*pixel,  0.5F-1*pixel,1, 0);
				tessellator.addVertexWithUV(-2 * pixel, 0.5F-1*pixel,  0.5F-1*pixel, 1, 1);
		}
		tessellator.draw();
	}
	
	private void drawHead(){
		Tessellator tessellator = Tessellator.instance;
		this.bindTexture(BoxFrontTexture);
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0, 0, 0, 1, 1);
		tessellator.addVertexWithUV(0, 0, 1, 0, 1);
		tessellator.addVertexWithUV(0, 1, 1, 0, 0);
		tessellator.addVertexWithUV(0, 1, 0, 1, 0);
		tessellator.draw();
	}

}
