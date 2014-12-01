package com.denvys5.uraniumswordmod.machines.poweredgrinder;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.block.USMBlocks;
import com.denvys5.uraniumswordmod.item.UraniumWrench;
import com.denvys5.uraniumswordmod.machines.BlockBasicMachine;
import com.denvys5.uraniumswordmod.machines.USMTiles;

public class PoweredGrinder extends BlockBasicMachine{

	private Random rand = new Random();

	private final boolean isActive;

	private static boolean keepInventory;

	public PoweredGrinder(boolean isActive){
		super();
		this.isActive = isActive;
	}
	
	

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		this.side = iconRegister.registerIcon(USM.modid + ":Machine_side");
		this.front = iconRegister.registerIcon(USM.modid + ":PoweredGrinder_front");
		this.top = iconRegister.registerIcon(USM.modid + ":" + (this.isActive ? "PoweredGrinder_active" : "PoweredGrinder_idle"));
		this.bottom = iconRegister.registerIcon(USM.modid + ":Machine_bottom");
	}

	public Item getItemDropped(int par1, Random random, int par3){
		return Item.getItemFromBlock(USMTiles.PoweredGrinderidle);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		Item equipped = player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().getItem() : null;
		if (equipped instanceof IToolWrench && ! (equipped instanceof UraniumWrench)) {
			if(!player.isSneaking()){
				int USMTilesMetaVar = 0;
				byte Meta = (byte)world.getBlockMetadata(x, y, z);
				if(Meta == 2) USMTilesMetaVar = 5;
				if(Meta == 5) USMTilesMetaVar = 3;
				if(Meta == 3) USMTilesMetaVar = 4;
				if(Meta == 4) USMTilesMetaVar = 2;
				world.setBlockMetadataWithNotify(x, y, z, USMTilesMetaVar, 3);
				return true;
			}else{
				Block TargetBlock = world.getBlock(x, y, z);
				if(TargetBlock == null) return false;
				byte Meta = (byte)world.getBlockMetadata(x, y, z);
				final ItemStack itemstack = new ItemStack(TargetBlock, 1, Meta);
				EntityItem blockDropped = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, itemstack);
				world.setBlockToAir(x, y, z);
				world.spawnEntityInWorld(blockDropped);
			}
		}
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, USM.instance, USM.instance.guiIdPoweredGrinder, world, x, y, z);
		}
		return true;
	}

	public TileEntity createNewTileEntity(World world, int i){
		return new TileEntityPoweredGrinder();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack){
		int l = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if(l == 0){
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if(l == 1){
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if(l == 2){
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if(l == 3){
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		if(itemstack.hasDisplayName()){
			((TileEntityPoweredGrinder)world.getTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
		}
	}

	public static void updatePoweredGrinderBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord){
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;
		if(active){
			worldObj.setBlock(xCoord, yCoord, zCoord, USMTiles.PoweredGrinderactive);
		} else{
			worldObj.setBlock(xCoord, yCoord, zCoord, USMTiles.PoweredGrinderidle);
		}
		keepInventory = false;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		if(tileentity != null){
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata){
		if(!keepInventory){
			TileEntityPoweredGrinder tileentity = (TileEntityPoweredGrinder)world.getTileEntity(x, y, z);
			if(tileentity != null){
				for(int i = 0; i < tileentity.getSizeInventory(); i++){
					ItemStack itemstack = tileentity.getStackInSlot(i);
					if(itemstack != null){
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
						EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), itemstack);
						if(itemstack.hasTagCompound()){
							item.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
						}
						float f3 = 0.05F;
						item.motionX = (double)((float)this.rand.nextGaussian() * f3);
						item.motionY = (double)((float)this.rand.nextGaussian() * f3 + 0.2F);
						item.motionZ = (double)((float)this.rand.nextGaussian() * f3);
						world.spawnEntityInWorld(item);
					}
				}
				world.func_147453_f(x, y, z, oldBlock);
			}
		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
	}

	public Item getItem(World world, int x, int y, int z){
		return Item.getItemFromBlock(USMTiles.PoweredGrinderidle);
	}
}
