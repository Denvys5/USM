package com.denvys5.uraniumswordmod.machines.safenuke;

import buildcraft.api.tools.IToolWrench;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.item.USMItems;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SafeNuke extends Block{

	private Entity entity;
	private boolean destroyBlocksOnExplosion = true;

	private World world;

	@SideOnly(Side.CLIENT)
	private IIcon blockIcon;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;
	@SideOnly(Side.CLIENT)
	private IIcon iconBottom;

	public SafeNuke(){
		super(Material.tnt);
		this.setHardness(3.0F);
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
			EntitySafeNukePrimed explosionNuke = new EntitySafeNukePrimed(world, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), explosion.getExplosivePlacedBy());
			world.spawnEntityInWorld(explosionNuke);
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int l, float f1, float f2, float f3){
		if(entityPlayer.getCurrentEquippedItem() != null && entityPlayer.getCurrentEquippedItem().getItem() instanceof IToolWrench){
			world.setBlockToAir(x, y, z);
			this.primeNuclearBomb(world, x, y, z, 1, entityPlayer);
			return true;
		}
		return true;
	}

	public void primeNuclearBomb(World world, int i, int j, int k, int l, EntityLivingBase entityPlayer){
		if(!world.isRemote){
			if((l & 1) == 1){
				EntitySafeNukePrimed entityNuclearBombPrimed = new EntitySafeNukePrimed(world, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), entityPlayer);
				world.spawnEntityInWorld(entityNuclearBombPrimed);
				world.playSoundAtEntity(entityNuclearBombPrimed, "game.tnt.primed", 1.0F, 1.0F);
			}
		}
	}

}