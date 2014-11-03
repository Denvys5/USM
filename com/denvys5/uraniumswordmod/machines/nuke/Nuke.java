package com.denvys5.uraniumswordmod.machines.nuke;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Nuke extends Block{

	private Entity entity;
	private boolean destroyBlocksOnExplosion = true;

	private World world;

	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;

	public Nuke(){
		super(Material.tnt);
		this.setHardness(4.0F);
		this.setCreativeTab(USM.USMTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.blockIcon = iconRegister.registerIcon(USM.modid + ":" + "Nuke_side");
		this.iconTop = iconRegister.registerIcon(USM.modid + ":" + "Nuke_top");
		this.iconBottom = iconRegister.registerIcon(USM.modid + ":" + "Nuke_bottom");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return metadata == 0 && side == 3 ? this.blockIcon : side == 1 ? this.iconTop : (side == 0 ? this.iconBottom : (side == metadata ? this.blockIcon : this.blockIcon));
	}

	public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion){
		if(!world.isRemote){
			EntityNukePrimed explosionNuke = new EntityNukePrimed(world, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), explosion.getExplosivePlacedBy());
			world.spawnEntityInWorld(explosionNuke);
		}
	}

	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityPlayer, int l, float f1, float f2, float f3){
		if(entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() == BlockList.UraniumWrench){
			world.setBlockToAir(i, j, k);
			this.primeNuclearBomb(world, i, j, k, 1, entityPlayer);
			return true;
		} else{
			return super.onBlockActivated(world, i, j, k, entityPlayer, l, f1, f2, f3);
		}
	}

	public void primeNuclearBomb(World world, int i, int j, int k, int l, EntityLivingBase entityPlayer){
		if(!world.isRemote){
			if((l & 1) == 1){
				EntityNukePrimed entityNuclearBombPrimed = new EntityNukePrimed(world, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), entityPlayer);
				world.spawnEntityInWorld(entityNuclearBombPrimed);
				world.playSoundAtEntity(entityNuclearBombPrimed, "game.tnt.primed", 1.0F, 1.0F);
			}
		}
	}

}