package com.denvys5.uraniumswordmod.machines.pipes;

import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmill;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPipe extends TileEntity{
	public int antilag = 0;
	public ForgeDirection[] connections = new ForgeDirection[6];
	public TileEntityPipe(){
		
	}
	
	public void updateEntity(){
		antilag++;
		if(antilag >= 15){
			antilag = 0;
			this.updateConnections();
		}
	}
	
	public void updateConnections(){
		if(isPipe(this.xCoord, this.yCoord-1, this.zCoord)) connections[0] = ForgeDirection.DOWN;
		else connections[0] = null;
		if(isPipe(this.xCoord, this.yCoord+1, this.zCoord)) connections[1] = ForgeDirection.UP;
		else connections[1] = null;
		if(isPipe(this.xCoord, this.yCoord, this.zCoord-1) || isWindmill(this.xCoord, this.yCoord, this.zCoord-1)) connections[2] = ForgeDirection.NORTH;
		else connections[2] = null;
		if(isPipe(this.xCoord, this.yCoord, this.zCoord+1) || isWindmill(this.xCoord, this.yCoord, this.zCoord+1)) connections[3] = ForgeDirection.SOUTH;
		else connections[3] = null;
		if(isPipe(this.xCoord-1, this.yCoord, this.zCoord) || isWindmill(this.xCoord-1, this.yCoord, this.zCoord)) connections[4] = ForgeDirection.WEST;
		else connections[4] = null;
		if(isPipe(this.xCoord+1, this.yCoord, this.zCoord) || isWindmill(this.xCoord+1, this.yCoord, this.zCoord)) connections[5] = ForgeDirection.EAST;
		else connections[5] = null;
	}
	
	public boolean isPipe(int x, int y, int z){
		return this.worldObj.getTileEntity(x, y, z) instanceof TileEntityPipe;
	}
	
	public boolean isWindmill(int x, int y, int z){
		return this.worldObj.getTileEntity(x, y, z) instanceof TileEntityWindmill && this.worldObj.getBlockMetadata(x, y, z) == 1;
	}
	
	public boolean onlyOneOpposite(ForgeDirection[] directions){
		ForgeDirection mainDirection = null;
		boolean isOpposite = false;
		for(int i = 0; i < directions.length; i++){
			if(mainDirection == null && directions[i] != null){
				mainDirection = directions[i];
			}
			if(directions[i] != null && mainDirection != directions[i]){
				if(!isOpposite(mainDirection, directions[i]))return false;
				else isOpposite = true;
			}
		}
		return isOpposite;
	}
	
	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection){
		if((firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH)))return true;
		if((firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH)))return true;
		if((firstDirection.equals(ForgeDirection.WEST) && secondDirection.equals(ForgeDirection.EAST)))return true;
		if((firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)))return true;
		if((firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN)))return true;
		if((firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP)))return true;
		
		return false;
	}
}
