package com.denvys5.uraniumswordmod.machines.pipes;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.Config;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

public class PipeRenderer extends TileEntitySpecialRenderer{

	public final static ResourceLocation texture = new ResourceLocation(USM.modid, "models/tileentities/BlockPipe.png");
	boolean drawInside = Config.renderDrawInside;
	float pixel = 1F/16F;
	float texturepixel = 1F/32F;
	
	public void renderTileEntityAt(TileEntity tileentity, double translationX, double translationY, double translationZ, float f){
		GL11.glTranslated(translationX, translationY, translationZ);
		GL11.glDisable(GL11.GL_LIGHTING);
		this.bindTexture(texture);
		TileEntityPipe pipe = (TileEntityPipe)tileentity;
		if(!pipe.onlyOneOpposite(pipe.connections)){
			drawCore(tileentity);
			for(int i = 0; i < pipe.connections.length; i++){
				if(pipe.connections[i] != null){
					drawConnector(pipe.connections[i]);
				}
			}
		}else{
			for(int i = 0; i < pipe.connections.length; i++){
				if(pipe.connections[i] != null){
					drawStraight(pipe.connections[i]);
					break;
				}
			}
		}
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glTranslated(-translationX, -translationY, -translationZ);
	}
	
	public void drawStraight(ForgeDirection direction){
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.NORTH) || direction.equals(ForgeDirection.SOUTH)){
			GL11.glRotatef(90, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(90, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
			
			tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 0*texturepixel);
			
			tessellator.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);

			tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 5*texturepixel);
			
			
			if(drawInside){
				tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
	
				tessellator.addVertexWithUV(1 - 11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 5*texturepixel);
				
				tessellator.addVertexWithUV(1 - 11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 5*texturepixel);

				tessellator.addVertexWithUV(11*pixel/2, 0, 11*pixel/2, 10*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 26*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 26*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 0, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
			}
			
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.NORTH) || direction.equals(ForgeDirection.SOUTH)){
			GL11.glRotatef(-90, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.WEST) || direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(-90, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
	}
	
	public void drawConnector(ForgeDirection direction){
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.UP)){

		}else if(direction.equals(ForgeDirection.DOWN)){
			GL11.glRotatef(180, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.NORTH)){
			GL11.glRotatef(270, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.SOUTH)){
			GL11.glRotatef(90, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.WEST)){
			GL11.glRotatef(90, 0, 0, 1);
		}else if(direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
			
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
			
			tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);

			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
			
			
			if(drawInside){
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
	
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
				
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);

				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 11*pixel/2, 10*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1, 1-11*pixel/2, 10*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
			}
			
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		if(direction.equals(ForgeDirection.UP)){

		}else if(direction.equals(ForgeDirection.DOWN)){
			GL11.glRotatef(-180, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.NORTH)){
			GL11.glRotatef(-270, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.SOUTH)){
			GL11.glRotatef(-90, 1, 0, 0);
		}else if(direction.equals(ForgeDirection.WEST)){
			GL11.glRotatef(-90, 0, 0, 1);
		}else if(direction.equals(ForgeDirection.EAST)){
			GL11.glRotatef(-270, 0, 0, 1);
		}
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		
	}
	
	public void drawCore(TileEntity tileentity){
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
			tessellator.addVertexWithUV(1 - 11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
			
			tessellator.addVertexWithUV(1 - 11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);

			tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1 - 11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
			
			tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
			
			tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);

			tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
			tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturepixel, 0*texturepixel);
			tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
			
			
			if(drawInside){
				tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
	
				tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
	
				tessellator.addVertexWithUV(1 - 11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1 - 11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturepixel, 5*texturepixel);
	
				tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
	
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 1-11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 11*pixel/2, 0*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 1-11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
	
				tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 1-11*pixel/2, 0*texturepixel, 5*texturepixel);
				tessellator.addVertexWithUV(1-11*pixel/2, 11*pixel/2, 11*pixel/2, 0*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 11*pixel/2, 5*texturepixel, 0*texturepixel);
				tessellator.addVertexWithUV(11*pixel/2, 11*pixel/2, 1-11*pixel/2, 5*texturepixel, 5*texturepixel);
			}
		tessellator.draw();
	}
}
