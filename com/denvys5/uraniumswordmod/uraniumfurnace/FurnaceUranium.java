package com.denvys5.uraniumswordmod.uraniumfurnace;

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

import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.core.USM;

public class FurnaceUranium extends BlockContainer {

	private Random rand = new Random();

	private final boolean isActive;

	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	private IIcon top, bottom, side, front;

	private static boolean keepInventory;

	public FurnaceUranium(boolean isActive) {
		super(Material.rock);
		this.isActive = isActive;
		this.setHardness(10.0F);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		side = iconRegister.registerIcon(USM.modid + ":FurnaceUranium_side");
		front = iconRegister.registerIcon(USM.modid
				+ ":"
				+ (this.isActive ? "FurnaceUranium_active"
						: "FurnaceUranium_idle"));
		top = iconRegister.registerIcon(USM.modid + ":FurnaceUranium_top");
		bottom = iconRegister
				.registerIcon(USM.modid + ":FurnaceUranium_bottom");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == 1 ? this.top : (side == 0 ? this.bottom : (metadata == 2
				&& side == 2 ? this.front
				: (metadata == 5 && side == 5 ? this.front
						: (metadata == 3 && side == 3 ? this.front
								: (metadata == 4 && side == 4 ? this.front
										: this.side)))));
	}

	public Item getItemDropped(int par1, Random random, int par3) {
		return Item.getItemFromBlock(BlockList.furnaceuraniumidle);
	}

	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setDefautDirection(world, x, y, z);
	}

	private void setDefautDirection(World world, int x, int y, int z) {
		if (!world.isRemote) {
			Block block1 = world.getBlock(x, y, z - 1);
			Block block2 = world.getBlock(x, y, z + 1);
			Block block3 = world.getBlock(x - 1, y, z);
			Block block4 = world.getBlock(x + 1, y, z);
			byte b0 = 3;
			if (block1.func_149730_j() && !block2.func_149730_j()) {
				b0 = 3;
			}
			if (block2.func_149730_j() && !block1.func_149730_j()) {
				b0 = 2;
			}
			if (block3.func_149730_j() && !block4.func_149730_j()) {
				b0 = 5;
			}
			if (block4.func_149730_j() && !block3.func_149730_j()) {
				b0 = 4;
			}
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}

	}

	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, USM.instance,
					USM.instance.guiIdFurnaceUranium, world, x, y, z);
		}
		return true;
	}

	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityFurnaceUranium();
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z,
			Random random) {
		if (this.isActive) {
			int direction = world.getBlockMetadata(x, y, z);
			float x1 = (float) x + 0.5F;
			float y1 = (float) ((float) y + random.nextFloat() * 0.4);
			float z1 = (float) z + 0.5F;
			float f = 0.52F;
			float f1 = this.rand.nextFloat() * 0.6F - 0.3F;
			if (direction == 4) {
				world.spawnParticle("smoke", (double) (x1 - f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 - f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
			} else if (direction == 5) {
				world.spawnParticle("smoke", (double) (x1 + f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f), (double) (y1),
						(double) (z1 + f1), 0D, 0D, 0D);
			} else if (direction == 2) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
						(double) (z1 - f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f1), (double) (y1),
						(double) (z1 - f), 0D, 0D, 0D);
			} else if (direction == 3) {
				world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1),
						(double) (z1 + f), 0D, 0D, 0D);
				world.spawnParticle("flame", (double) (x1 + f1), (double) (y1),
						(double) (z1 + f), 0D, 0D, 0D);
			}
		}
	}

	public void onBlockPlacedBy(World world, int x, int y, int z,
			EntityLivingBase entityLivingBase, ItemStack itemstack) {
		int l = MathHelper
				.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		if (l == 0) {
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if (l == 1) {
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if (l == 2) {
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (l == 3) {
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
		}
		if (itemstack.hasDisplayName()) {
			((TileEntityFurnaceUranium) world.getTileEntity(x, y, z))
					.setGuiDisplayName(itemstack.getDisplayName());
		}
	}

	public static void updateFurnaceUraniumBlockState(boolean active,
			World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord,
				zCoord);
		keepInventory = true;
		if (active) {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					BlockList.furnaceuraniumactive);
		} else {
			worldObj.setBlock(xCoord, yCoord, zCoord,
					BlockList.furnaceuraniumidle);
		}
		keepInventory = false;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		if (tileentity != null) {
			tileentity.validate();
			worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block oldBlock,
			int oldMetadata) {
		if (!keepInventory) {
			TileEntityFurnaceUranium tileentity = (TileEntityFurnaceUranium) world
					.getTileEntity(x, y, z);
			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemstack = tileentity.getStackInSlot(i);
					if (itemstack != null) {
						float f = this.rand.nextFloat() * 0.8F + 0.1F;
						float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
						float f2 = this.rand.nextFloat() * 0.8F + 0.1F;
						while (itemstack.stackSize > 0) {
							int j = this.rand.nextInt(21) + 10;
							if (j < itemstack.stackSize) {
								j = itemstack.stackSize;
							}
							itemstack.stackSize -= j;
							EntityItem item = new EntityItem(world,
									(double) ((float) x + f),
									(double) ((float) y + f1),
									(double) ((float) z + f2), new ItemStack(
											itemstack.getItem(), j,
											itemstack.getItemDamage()));
							if (itemstack.hasTagCompound()) {
								item.getEntityItem().setTagCompound(
										(NBTTagCompound) itemstack
												.getTagCompound().copy());
							}
							float f3 = 0.05F;
							item.motionX = (double) ((float) this.rand
									.nextGaussian() * f3);
							item.motionY = (double) ((float) this.rand
									.nextGaussian() * f3 + 0.2F);
							item.motionZ = (double) ((float) this.rand
									.nextGaussian() * f3);
							world.spawnEntityInWorld(item);
						}
					}
				}
				world.func_147453_f(x, y, z, oldBlock);
			}
		}
		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z,
			int i) {
		return Container.calcRedstoneFromInventory((IInventory) world
				.getTileEntity(x, y, z));
	}

	public Item getItem(World world, int x, int y, int z) {
		return Item.getItemFromBlock(BlockList.furnaceuraniumidle);
	}
}
