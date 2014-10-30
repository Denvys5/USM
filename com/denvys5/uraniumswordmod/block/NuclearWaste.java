package com.denvys5.uraniumswordmod.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.effects.ArmourIgnoreDamage;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class NuclearWaste extends Block{

	public NuclearWaste(){
		super(Material.ground);
		this.setCreativeTab(USM.USMTab);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		this.setTickRandomly(true);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k){
		return null;
	}

	public int quantityDropped(Random random){
		return random.nextInt(4) + 1;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(USM.modid + ":NuclearWaste");
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity){
		if(entity instanceof EntityLivingBase){
			entity.attackEntityFrom(new DamageSource("generic"), 4.0F);
		}
	}

	public void onNeighborBlockChange(World world, int i, int j, int k, Block block){
		if(!this.canBlockStay(world, i, j, k)){
			world.func_147480_a(i, j, k, true);
		}
	}

	public boolean canBlockStay(World world, int x, int y, int z){
		if(world.getBlock(x, y - 1, z).getMaterial().isSolid() && world.getBlock(x, y - 1, z) != Blocks.air && world.getBlock(x, y - 1, z) != BlockList.NuclearWaste){
			return true;
		} else{
			return false;
		}
	}

	public void updateTick(World world, int i, int j, int k, Random random){
		if(!world.isRemote){
			for(int var6 = 0; var6 < 4; ++var6){
				int var7 = i + random.nextInt(3) - 1;
				int var8 = j + random.nextInt(5) - 3;
				int var9 = k + random.nextInt(3) - 1;
				Block var10 = world.getBlock(var7, var8 + 1, var9);
				if(world.getBlock(var7, var8, var9) == Blocks.air && this.canBlockStay(world, var7, var8, var9) && world.getBlock(var7, var8 - 1, var9) != BlockList.NuclearWaste){
					world.setBlock(var7, var8, var9, BlockList.NuclearWaste);
				}
			}
		}
	}

}