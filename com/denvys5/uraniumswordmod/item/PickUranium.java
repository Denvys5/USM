package com.denvys5.uraniumswordmod.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.Config;
import com.denvys5.uraniumswordmod.core.ToolHandler;
import com.denvys5.uraniumswordmod.core.Util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PickUranium extends ItemPickaxe{

	public PickUranium(){
		super(USM.UraniumPick);
		this.maxStackSize = 1;
		this.efficiencyOnProperMaterial = 20.0F;
		this.setCreativeTab(USM.USMTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(USM.modid + ":UraniumPickaxe");
	}

	public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player){
		if(Config.NewWIPPickMechanicks == true){
			World world = player.worldObj;
			Material mat = world.getBlock(x, y, z).getMaterial();
			if(!ToolHandler.isRightMaterial(mat, ToolHandler.materialsPick)) return false;

			MovingObjectPosition block = ToolHandler.raytraceFromEntity(world, player, true, 4.5);
			if(block == null) return false;

			Block blk = world.getBlock(x, y, z);

			ForgeDirection direction = ForgeDirection.getOrientation(block.sideHit);
			int fortune = EnchantmentHelper.getFortuneModifier(player);
			boolean silk = EnchantmentHelper.getSilkTouchModifier(player);
			switch(ToolHandler.getMode(stack)){
				case 0 :
					break;
				case 1 :{
					boolean doX = direction.offsetX == 0;
					boolean doY = direction.offsetY == 0;
					boolean doZ = direction.offsetZ == 0;

					ToolHandler.removeBlocksInIteration(player, world, x, y, z, doX ? -2 : 0, doY ? -1 : 0, doZ ? -2 : 0, doX ? 3 : 1, doY ? 4 : 1, doZ ? 3 : 1, null, ToolHandler.materialsPick, silk, fortune);

					break;
				}
				case 2 :{
					int xo = -direction.offsetX;
					int yo = -direction.offsetY;
					int zo = -direction.offsetZ;

					ToolHandler.removeBlocksInIteration(player, world, x, y, z, xo >= 0 ? 0 : -10, yo >= 0 ? 0 : -10, zo >= 0 ? 0 : -10, xo > 0 ? 10 : 1, yo > 0 ? 10 : 1, zo > 0 ? 10 : 1, null, ToolHandler.materialsPick, silk, fortune);
					break;
				}
			}
		}
		return false;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if(par3EntityPlayer.isSneaking()){
			ToolHandler.changeMode(par1ItemStack);
			ToolHandler.setTooltip(ToolHandler.getToolModeStr("UraniumPick", par1ItemStack));
		} else{
			Vec3 blockVec = Util.getEntityBlockVector(par3EntityPlayer);
			int posX = (int)(blockVec.xCoord);
			int posY = (int)(blockVec.yCoord);
			int posZ = (int)(blockVec.zCoord);
			boolean silkTouch = false;
			int so = Enchantment.silkTouch.effectId;
			int fortune = Enchantment.fortune.effectId;
			int fortuneLvl = 0;
			NBTTagList enchants = par1ItemStack.getEnchantmentTagList();

			if(enchants != null){
				for(int i = 0; i < enchants.tagCount(); i++){
					if(enchants.getCompoundTagAt(i) instanceof NBTTagCompound){
						NBTTagCompound nbt = (NBTTagCompound)enchants.getCompoundTagAt(i);
						int id = nbt.getShort("id");
						if(id == so){
							silkTouch = true;
						}
						if(id == fortune){
							fortuneLvl = nbt.getShort("lvl");
						}
					}
				}
			}

			for(int i = -5; i <= 5; i++){
				for(int j = -5; j <= 5; j++){
					for(int k = -5; k <= 5; k++){
						Block block = par2World.getBlock(posX + i, posY + j, posZ + k);
						int meta = par2World.getBlockMetadata(posX + i, posY + j, posZ + k);
						if(block != null && block.getBlockHardness(par2World, posX + i, posY + j, posZ + k) != -1){
							float str = func_150893_a(par1ItemStack, block);
							if(str > 1.1f && par2World.canMineBlock(par3EntityPlayer, posX + i, posY + j, posZ + k)){
								// par1ItemStack.getEnchantmentTagList();
								if(silkTouch){
									ItemStack droppedItem = new ItemStack(block, 1, meta);

									if(!par2World.isRemote){
										par2World.spawnEntityInWorld(new EntityItem(par2World, posX, posY + par3EntityPlayer.getEyeHeight(), posZ, droppedItem));
									}
								} else{
									ArrayList<ItemStack> itemDropList = block.getDrops(par2World, posX + i, posY + j, posZ + k, meta, fortuneLvl);

									if(itemDropList != null){
										for(ItemStack item : itemDropList){
											if(!par2World.isRemote){
												par2World.spawnEntityInWorld(new EntityItem(par2World, posX, posY + par3EntityPlayer.getEyeHeight(), posZ, item));
											}
										}
									}
								}

								par2World.setBlockToAir(posX + i, posY + j, posZ + k);
							}
						}
					}
				}
			}
		}
		return par1ItemStack;
	}

	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
		if(par1ItemStack.getItemDamage() > 0){
			par1ItemStack.setItemDamage(0);
			return;
		}
	}
	public void addInformation(ItemStack s, EntityPlayer p, List l, boolean is){
		l.add(StatCollector.translateToLocal("UraniumPick.tooltip"));
	}
}
