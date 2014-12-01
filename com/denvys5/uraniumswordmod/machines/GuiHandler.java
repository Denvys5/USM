package com.denvys5.uraniumswordmod.machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.ContainerPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.GuiPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.poweredgrinder.TileEntityPoweredGrinder;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.ContainerDuplicator;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.GuiDuplicator;
import com.denvys5.uraniumswordmod.machines.uraniumduplicator.TileEntityDuplicator;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.ContainerFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.GuiFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.uraniumfurnace.TileEntityFurnaceUranium;
import com.denvys5.uraniumswordmod.machines.windmill.ContainerWindmill;
import com.denvys5.uraniumswordmod.machines.windmill.GuiWindmill;
import com.denvys5.uraniumswordmod.machines.windmill.TileEntityWindmill;

public class GuiHandler implements IGuiHandler{

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){

		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null){
			switch(ID){
				case USM.guiIdFurnaceUranium :
					if(entity instanceof TileEntityFurnaceUranium){
						return new ContainerFurnaceUranium(player.inventory, (TileEntityFurnaceUranium)entity);
					}
				case USM.guiIdDuplicator :
					if(entity instanceof TileEntityDuplicator){
						return new ContainerDuplicator(player.inventory, (TileEntityDuplicator)entity);
					}
				case USM.guiIdPoweredGrinder :
					if(entity instanceof TileEntityPoweredGrinder){
						return new ContainerPoweredGrinder(player.inventory, (TileEntityPoweredGrinder)entity);
					}
				case USM.guiIdWindMill :
					while(entity instanceof TileEntityWindmill && world.getBlockMetadata(x, y, z) < 8){
						y++;
					}
						return new ContainerWindmill(player.inventory, (TileEntityWindmill)world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){

		TileEntity entity = world.getTileEntity(x, y, z);
		if(entity != null){
			switch(ID){
				case USM.guiIdFurnaceUranium :
					if(entity instanceof TileEntityFurnaceUranium){
						return new GuiFurnaceUranium(player.inventory, (TileEntityFurnaceUranium)entity);
					}
				case USM.guiIdDuplicator :
					if(entity instanceof TileEntityDuplicator){
						return new GuiDuplicator(player.inventory, (TileEntityDuplicator)entity);
					}
				case USM.guiIdPoweredGrinder :
					if(entity instanceof TileEntityPoweredGrinder){
						return new GuiPoweredGrinder(player.inventory, (TileEntityPoweredGrinder)entity);
					}
				case USM.guiIdWindMill :
					while(entity instanceof TileEntityWindmill && world.getBlockMetadata(x, y, z) < 8){
						y++;
					}
					return new GuiWindmill(player.inventory, (TileEntityWindmill)world.getTileEntity(x, y, z));
			}
		}
		return null;
	}

}
