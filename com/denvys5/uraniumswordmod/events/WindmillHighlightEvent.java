package com.denvys5.uraniumswordmod.events;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.machines.USMTiles;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

public class WindmillHighlightEvent{
	@SubscribeEvent
	public void onDrawHighLight(DrawBlockHighlightEvent event){
		if(event.target.typeOfHit.equals(MovingObjectType.BLOCK)){
			if(event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ).equals(USMTiles.WindmillBlock) && event.player.worldObj.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ) <= 7){
				onDrawHighLightWindmill(event);
			}
			if(event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ).equals(USMTiles.WindmillPlatform)){
				onDrawHighLightWindmillPlatform(event);
			}
		}
	}
	
	public void onDrawHighLightWindmill(DrawBlockHighlightEvent event){
		event.setCanceled(true);
		Block block = event.player.worldObj.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
		block.setBlockBoundsBasedOnState(event.player.worldObj, event.target.blockX, event.target.blockY, event.target.blockZ);
		double x = event.player.lastTickPosX + (event.player.posX - event.player.lastTickPosX)*event.partialTicks;
		double y = event.player.lastTickPosY + (event.player.posY - event.player.lastTickPosY)*event.partialTicks;
		double z = event.player.lastTickPosZ + (event.player.posZ - event.player.lastTickPosZ)*event.partialTicks;
		float f = 0.002F;
		AxisAlignedBB bounds = block.getSelectedBoundingBoxFromPool(event.player.worldObj, event.target.blockX, event.target.blockY, event.target.blockZ).expand(f, f, f).getOffsetBoundingBox(-x, -y, -z);
		int metadata = event.player.worldObj.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ);
		
		
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(0, 0, 0, 0.4F);
		GL11.glLineWidth(2);
		GL11.glDepthMask(false);
		Tessellator tessellator = new Tessellator();
		tessellator.startDrawing(GL11.GL_LINE);
		{
			tessellator.addVertex(bounds.minX, bounds.minY + (7-metadata), bounds.minZ);
			tessellator.addVertex(bounds.minX, bounds.minY - metadata, bounds.minZ);
			tessellator.addVertex(bounds.maxX, bounds.minY + (7-metadata), bounds.minZ);
			tessellator.addVertex(bounds.maxX, bounds.minY - metadata, bounds.minZ);
			tessellator.addVertex(bounds.minX, bounds.minY + (7-metadata), bounds.maxZ);
			tessellator.addVertex(bounds.minX, bounds.minY - metadata, bounds.maxZ);
			tessellator.addVertex(bounds.maxX, bounds.minY + (7-metadata), bounds.maxZ);
			tessellator.addVertex(bounds.maxX, bounds.minY - metadata, bounds.maxZ);
		}
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}	
	
	public void onDrawHighLightWindmillPlatform(DrawBlockHighlightEvent event){
		event.setCanceled(true);
		Tessellator tessellator = new Tessellator();
		tessellator.startDrawing(GL11.GL_LINE);
		
		
		tessellator.draw();
	}
}
