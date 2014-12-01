package com.denvys5.uraniumswordmod.machines.windmill;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.machines.USMTiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WindmillItem extends Item{
	public WindmillItem(){
		super();
	}
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float x2, float y2, float z2){
		if(!world.isRemote){
			if(side == 1 && world.getBlockMetadata(x, y, z) == 5 && world.getBlock(x, y, z).equals(USMTiles.WindmillPlatform)){
				boolean notEnoughSpace = false;
				for(int x1 = -1; x1 < 1; x1++){
					for(int z1 = -1; z1 < 1; z1++){
						for(int y1 = 0; y1 < 7; y1++){
							if(!world.isAirBlock(x + x1, y + y1 + 1, z + z1)) notEnoughSpace = true;
						}
					}
				}
				if(!notEnoughSpace){
					int direction = (-(int)player.rotationYaw+45)/90;
					if(direction == 0) direction = 4;
					for(int i = 0; i < 7; i++){
						world.setBlock(x, y + i + 1, z, USMTiles.WindmillBlock, (i+1)==7?(i+1+direction):(i+1), 2);
					}
					player.inventory.consumeInventoryItem(itemstack.getItem());
				}
			}
			/*
			 * if(side == 2) world.setBlock(x, y, z-1, BlockList.WindmillBlock);
			 * if(side == 3) world.setBlock(x, y, z+1, BlockList.WindmillBlock);
			 * if(side == 4) world.setBlock(x-1, y, z, BlockList.WindmillBlock);
			 * if(side == 5) world.setBlock(x+1, y, z, BlockList.WindmillBlock);
			 * if(side == 0) world.setBlock(x, y-1, z, BlockList.WindmillBlock);
			 */
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg){
		this.itemIcon = reg.registerIcon(USM.modid + ":WindmillItem");
	}
}
