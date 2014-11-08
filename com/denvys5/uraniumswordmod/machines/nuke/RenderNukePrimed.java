package com.denvys5.uraniumswordmod.machines.nuke;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.denvys5.uraniumswordmod.core.BlockList;

public class RenderNukePrimed extends Render{

	private RenderBlocks blockRenderer = new RenderBlocks();

	public RenderNukePrimed(){
		this.shadowSize = 0.5F;
	}

	public void doRender(EntityNukePrimed entityNukePrimed, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_){
		GL11.glPushMatrix();
		GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
		float var10;

		if((float)entityNukePrimed.fuse - p_76986_9_ + 1.0F < 10.0F){
			var10 = 1.0F - ((float)entityNukePrimed.fuse - p_76986_9_ + 1.0F) / 10.0F;

			if(var10 < 0.0F){
				var10 = 0.0F;
			}

			if(var10 > 1.0F){
				var10 = 1.0F;
			}

			var10 *= var10;
			var10 *= var10;
			float var11 = 1.0F + var10 * 0.3F;
			GL11.glScalef(var11, var11, var11);
		}

		var10 = (1.0F - ((float)entityNukePrimed.fuse - p_76986_9_ + 1.0F) / 100.0F) * 0.8F;
		this.bindEntityTexture(entityNukePrimed);
		this.blockRenderer.renderBlockAsItem(BlockList.Nuke, 0, entityNukePrimed.getBrightness(p_76986_9_));

		if(entityNukePrimed.fuse / 5 % 2 == 0){
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_DST_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, var10);
			this.blockRenderer.renderBlockAsItem(BlockList.Nuke, 0, 1.0F);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}

		GL11.glPopMatrix();
	}

	protected ResourceLocation getEntityTexture(EntityNukePrimed entity){
		return TextureMap.locationBlocksTexture;
	}

	protected ResourceLocation getEntityTexture(Entity entity){
		return this.getEntityTexture((EntityNukePrimed)entity);
	}

	public void doRender(Entity entityNukePrimed, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_){
		this.doRender((EntityNukePrimed)entityNukePrimed, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
	}
}
