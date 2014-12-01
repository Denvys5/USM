package com.denvys5.uraniumswordmod.core;

import ic2.core.init.Energy;

import java.util.HashSet;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.denvys5.uraniumswordmod.machines.pipes.TileEntityPipe;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmill;

public class Connections{
	public HashSet<TileEntity> eTile = new HashSet();
	public boolean canAcceptPower(TileEntity tileentity, int x, int y, int z){
		return tileentity.getWorldObj().getTileEntity(x, y, z) instanceof TileEntityPipe;
	}
}
