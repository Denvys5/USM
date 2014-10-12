package com.denvys5.uraniumswordmod.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

import com.denvys5.uraniumswordmod.poweredgrinder.ContainerPoweredGrinder;
import com.denvys5.uraniumswordmod.poweredgrinder.GuiPoweredGrinder;
import com.denvys5.uraniumswordmod.poweredgrinder.TileEntityPoweredGrinder;
import com.denvys5.uraniumswordmod.uraniumduplicator.ContainerDuplicator;
import com.denvys5.uraniumswordmod.uraniumduplicator.GuiDuplicator;
import com.denvys5.uraniumswordmod.uraniumduplicator.TileEntityDuplicator;
import com.denvys5.uraniumswordmod.uraniumfurnace.ContainerFurnaceUranium;
import com.denvys5.uraniumswordmod.uraniumfurnace.GuiFurnaceUranium;
import com.denvys5.uraniumswordmod.uraniumfurnace.TileEntityFurnaceUranium;

public class GuiHandler implements IGuiHandler {

	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null) {
			switch (ID) {
			case USM.guiIdFurnaceUranium:
				if (entity instanceof TileEntityFurnaceUranium) {
					return new ContainerFurnaceUranium(player.inventory,
							(TileEntityFurnaceUranium) entity);
				}
			case USM.guiIdDuplicator:
				if (entity instanceof TileEntityDuplicator) {
					return new ContainerDuplicator(player.inventory, (TileEntityDuplicator) entity);
				}
			case USM.guiIdPoweredGrinder:
				if (entity instanceof TileEntityPoweredGrinder) {
					return new ContainerPoweredGrinder(player.inventory, (TileEntityPoweredGrinder) entity);
				}
			}	
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null) {
			switch (ID) {
			case USM.guiIdFurnaceUranium:
				if (entity instanceof TileEntityFurnaceUranium) {
					return new GuiFurnaceUranium(player.inventory, (TileEntityFurnaceUranium) entity);
				}
			case USM.guiIdDuplicator:
				if (entity instanceof TileEntityDuplicator) {
					return new GuiDuplicator(player.inventory, (TileEntityDuplicator) entity);
			}
			case USM.guiIdPoweredGrinder:
				if (entity instanceof TileEntityPoweredGrinder) {
					return new GuiPoweredGrinder(player.inventory,(TileEntityPoweredGrinder) entity);
			}
		}
	}
		return null;
	}

}