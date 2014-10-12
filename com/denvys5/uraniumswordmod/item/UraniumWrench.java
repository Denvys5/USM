package com.denvys5.uraniumswordmod.item;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import buildcraft.api.tools.IToolWrench;

import com.denvys5.uraniumswordmod.core.USM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class UraniumWrench extends Item implements IToolWrench {
    private float damageVsEntity;
    public static Set<String> blocks = new HashSet();

    public UraniumWrench(){
        super();
        maxStackSize = 1;
        setCreativeTab(USM.USMTab);

        blocks.add("IronChest:BlockIronChest");
        blocks.add("Forestry:tile.for.factory");
        blocks.add("Forestry:tile.for.factory2");
        blocks.add("Forestry:tile.for.engine");
        blocks.add("BuildCraft|Silicon:laserBlock");
        blocks.add("BuildCraft|Factory:autoWorkbenchBlock");
        blocks.add("BuildCraft|Energy:engineBlock");
        blocks.add("BuildCraft|Silicon:assemblyTableBlock");
        blocks.add("BuildCraft|Factory:tankBlock");
        blocks.add("BuildCraft|Factory:miningWellBlock");
        blocks.add("BuildCraft|Factory:pumpBlock");
        blocks.add("BuildCraft|Factory:blockHopper");
        blocks.add("BuildCraft|Factory:refineryBlock");
        blocks.add("BuildCraft|Factory:machineBlock");
        blocks.add("factorization:mirror");

    }

    public boolean canWrench(EntityPlayer entityPlayer, int x, int y, int z)
    {
        return true;
    }

    public void wrenchUsed(EntityPlayer entityPlayer, int x, int y, int z)
    {
        entityPlayer.swingItem();
    }

    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }

    public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int X, int Y, int Z, int par7, float par8, float par9, float par10)
    {
        par2EntityPlayer.swingItem();
        if (par3World.isRemote) {
            return false;
        }
        Block TargetBlock = par3World.getBlock(X, Y, Z);
        if (TargetBlock == null) return false;
        byte Meta = (byte)par3World.getBlockMetadata(X, Y, Z);
        final ItemStack is = new ItemStack(TargetBlock, 1, Meta);
        ForgeDirection side = ForgeDirection.getOrientation(par7);

        par3World.playSoundEffect((double) ((float) X + 0.5F), (double) ((float) Y + 0.5F), (double) ((float) Z + 0.5F), TargetBlock.stepSound.getStepResourcePath(), (TargetBlock.stepSound.getVolume() + 1.0F) / 2.0F, TargetBlock.stepSound.getPitch() * 0.8F);

        TileEntity aTileEntity = par3World.getTileEntity(X, Y, Z);

        if (!par2EntityPlayer.isSneaking()) {
            if (OreDictionary.getOreID(is) == OreDictionary.getOreID("logWood") || OreDictionary.getOreID(is) == OreDictionary.getOreID("logRubber") || OreDictionary.getOreID(is) == OreDictionary.getOreID("woodRubber") || TargetBlock == Blocks.hay_block) {
                par3World.setBlockMetadataWithNotify(X, Y, Z, (Meta + 4) % 12, 3);
                return true;
            }
            if (TargetBlock == Blocks.piston || TargetBlock == Blocks.sticky_piston || TargetBlock == Blocks.dispenser || TargetBlock == Blocks.dropper) {
                par3World.setBlockMetadataWithNotify(X, Y, Z, (Meta + 1) % 6, 3);
                return true;
            }
            if (TargetBlock == Blocks.unpowered_repeater || TargetBlock == Blocks.powered_repeater) {
                par3World.setBlockMetadataWithNotify(X, Y, Z, (Meta / 4) * 4 + (((Meta % 4) + 1) % 4), 3);
                return true;
            }
            if (TargetBlock == Blocks.unpowered_comparator || TargetBlock == Blocks.powered_comparator) {
                par3World.setBlockMetadataWithNotify(X, Y, Z, (Meta / 4) * 4 + (((Meta % 4) + 1) % 4), 3);
                return true;
            }
            if (TargetBlock == Blocks.pumpkin || TargetBlock == Blocks.lit_pumpkin || TargetBlock == Blocks.furnace || TargetBlock == Blocks.lit_furnace || TargetBlock == Blocks.chest || TargetBlock == Blocks.trapped_chest || TargetBlock == Blocks.ender_chest) {
                par3World.setBlockMetadataWithNotify(X, Y, Z, ((Meta - 1) % 4) + 2, 3);
                return true;
            }
            if (TargetBlock == Blocks.hopper) {
                par3World.setBlockMetadataWithNotify(X, Y, Z, (Meta + 1) % 6 == 1 ? (Meta + 1) % 6 : 2, 3);
                return true;
            }
            if (TargetBlock == Blocks.lit_redstone_lamp) {
                par3World.isRemote = true;
                par3World.setBlock(X, Y, Z, Blocks.redstone_lamp, 0, 0);
                par3World.isRemote = false;
                return true;
            }
            if (TargetBlock == Blocks.redstone_lamp) {
                par3World.isRemote = true;
                par3World.setBlock(X, Y, Z, Blocks.lit_redstone_lamp, 0, 0);
                par3World.isRemote = false;
                return true;
            }
            if (TargetBlock == Blocks.golden_rail) {
                par3World.isRemote = true;
                par3World.setBlock(X, Y, Z, TargetBlock, (Meta + 8) % 16, 0);
                par3World.isRemote = false;
                return true;
            }
            if (TargetBlock == Blocks.activator_rail) {
                par3World.isRemote = true;
                par3World.setBlock(X, Y, Z, TargetBlock, (Meta + 8) % 16, 0);
                par3World.isRemote = false;
                return true;
            }
            //interaction with other mods
            //place for advertising
            //end of interaction
            else
            {
                if (Arrays.asList(TargetBlock.getValidRotations(par3World, X, Y, Z)).contains(ForgeDirection.getOrientation(par7))) {
                    if (TargetBlock.rotateBlock(par3World, X, Y, Z, side))
                    {
                        TargetBlock.onNeighborBlockChange(par3World, X, Y, Z, Blocks.air);
                        return true;
                    }
                }
            }
        }
        else if (par2EntityPlayer.isSneaking()){
            try {
            if (par3World.isRemote == false && (TargetBlock == Blocks.crafting_table || TargetBlock == Blocks.bookshelf || TargetBlock == Blocks.furnace || TargetBlock == Blocks.lit_furnace || TargetBlock == Blocks.chest || TargetBlock == Blocks.trapped_chest || TargetBlock == Blocks.ender_chest || TargetBlock == Blocks.piston || TargetBlock == Blocks.sticky_piston || TargetBlock == Blocks.dispenser || TargetBlock == Blocks.dropper)) {
                EntityItem blockDropped = new EntityItem(par3World, X+0.5, Y+0.5, Z+0.5, is);
                par3World.setBlockToAir(X, Y, Z);
                par3World.spawnEntityInWorld(blockDropped);
                return true;
            }
            if (TargetBlock == Blocks.rail) {
                par3World.isRemote = true;
                par3World.setBlock(X, Y, Z, TargetBlock, (Meta + 1) % 10, 0);
                par3World.isRemote = false;
                return true;
            }
            //interaction with other mods
            for (String str : blocks)
                if (par3World.isRemote == false && (TargetBlock == Block.getBlockFromName(str)) && (blocks.contains(str))) {
                EntityItem blockDropped = new EntityItem(par3World, X+0.5, Y+0.5, Z+0.5, is);
                par3World.setBlockToAir(X, Y, Z);
                par3World.spawnEntityInWorld(blockDropped);
                return true;
            }

            //end of interaction
            } catch(Throwable e) {}
        }
        return false;

    }

    public void addInformation(ItemStack s, EntityPlayer p, List l, boolean is){
        l.add(StatCollector.translateToLocal("screwdriver.tooltip"));
    }
}