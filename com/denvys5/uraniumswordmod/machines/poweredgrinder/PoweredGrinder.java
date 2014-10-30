package com.denvys5.uraniumswordmod.machines.poweredgrinder;

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
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;

public class PoweredGrinder extends BlockContainer{

	private Random rand = new Random();

	private final boolean isActive;

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	private IIcon top, bottom, side, front;

	private static boolean keepInventory;

	public PoweredGrinder(boolean isActive){
		super(Material.rock);
		this.isActive = isActive;
		this.setHardness(30.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister){
		side = iconRegister.registerIcon(USM.modid + ":PoweredGrinder_side");
		front = iconRegister.registerIcon(USM.modid + ":" + (this.isActive ? "PoweredGrinder_active" : "PoweredGrinder_idle"));
		top = iconRegister.registerIcon(USM.modid + ":PoweredGrinder_top");
		bottom = iconRegister.registerIcon(USM.modid + ":PoweredGrinder_bottom");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return side == 1 ? this.top : (side == 0 ? this.bottom : (metadata == 2 && side == 2 ? this.front : (metadata == 5 && side == 5 ? this.front : (metadata == 3 && side == 3 ? this.front : (metadata == 4 && side == 4 ? this.front : this.side)))));
	}

	public Item getItemDropped(int par1, Random random, int par3){
		return Item.getItemFromBlock(BlockList.PoweredGrinderidle);
	}

	public void onBlockAdded(World world, int x, int y, int z){
		super.onBlockAdded(world, x, y, z);
		this.setDefautDirection(world, x, y, z);
	}

	private void setDefautDirection(World world, int x, int y, int z){
		if(!world.isRemote){
			Block block1 = world.getBlock(x, y, z - 1);
			Block block2 = world.getBlock(x, y, z + 1);
			Block block3 = world.getBlock(x - 1, y, z);
			Block block4 = world.getBlock(x + 1, y, z);
			byte b0 = 3;
			if(block1.func_149730_j() && !block2.func_149730_j()){
				b0 = 3;
			}
			if(block2.func_149730_j() && !block1.func_149730_j()){
				b0 = 2;
			}
			if(block3.func_149730_j() && !block4.func_149730_j()){
				b0 = 5;
			}
			if(block4.func_149730_j() && !block3.func_149730_j()){
				b0 = 4;
			}
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}

	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
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
			worldObj.setBlock(xCoord, yCoord, zCoord, BlockList.PoweredGrinderactive);
		} else{
			worldObj.setBlock(xCoord, yCoord, zCoord, BlockList.PoweredGrinderidle);
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
						while(itemstack.stackSize > 0){
							int j = this.rand.nextInt(21) + 10;
							if(j < itemstack.stackSize){
								j = itemstack.stackSize;
							}
							itemstack.stackSize -= j;
							EntityItem item = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));
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
				}
				world.func_147453_f(x, y, z, oldBlock);
			}
		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
	}

	public boolean hasComparatorInputOverride(){
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int i){
		return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
	}

	public Item getItem(World world, int x, int y, int z){
		return Item.getItemFromBlock(BlockList.PoweredGrinderidle);
	}
}
