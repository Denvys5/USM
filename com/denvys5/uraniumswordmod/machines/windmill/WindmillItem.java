package com.denvys5.uraniumswordmod.machines.windmill;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WindmillItem extends Item{
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float x1, float y1, float z1){
		if(!world.isRemote){

			if(side == 1) world.setBlock(x, y+1, z, BlockList.WindmillBlock);
			/*if(side == 2) world.setBlock(x, y, z-1, BlockList.WindmillBlock);
			if(side == 3) world.setBlock(x, y, z+1, BlockList.WindmillBlock);
			if(side == 4) world.setBlock(x-1, y, z, BlockList.WindmillBlock);
			if(side == 5) world.setBlock(x+1, y, z, BlockList.WindmillBlock);
			if(side == 0) world.setBlock(x, y-1, z, BlockList.WindmillBlock);*/
			return true;
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.itemIcon = reg.registerIcon(USM.modid + ":WindmillItem");
	}
}
