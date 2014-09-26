package com.denvys5.uraniumswordmod.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.denvys5.uraniumswordmod.uraniumfurnace.ContainerFurnaceUranium;
import com.denvys5.uraniumswordmod.uraniumfurnace.GuiFurnaceUranium;
import com.denvys5.uraniumswordmod.uraniumfurnace.TileEntityFurnaceUranium;

import cpw.mods.fml.common.network.IGuiHandler;
import com.denvys5.uraniumswordmod.core.USM;

public class GuiHandler implements IGuiHandler {

	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null) {
			switch (ID) {
			case USM.guiIdFurnaceUranium:
				if (entity instanceof TileEntityFurnaceUranium) {
					return new ContainerFurnaceUranium(player.inventory,
							(TileEntityFurnaceUranium) entity);
				}
			}
		}

		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null) {
			switch (ID) {
			case USM.guiIdFurnaceUranium:
				if (entity instanceof TileEntityFurnaceUranium) {
					return new GuiFurnaceUranium(player.inventory,
							(TileEntityFurnaceUranium) entity);
				}
			}
		}

		return null;
	}

}
