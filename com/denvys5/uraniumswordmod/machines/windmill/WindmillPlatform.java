package com.denvys5.uraniumswordmod.machines.windmill;

import com.denvys5.uraniumswordmod.USM;
import com.denvys5.uraniumswordmod.core.BlockList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

public class WindmillPlatform extends Block{

	public WindmillPlatform() {
		super(Material.rock);
		this.setBlockBounds(0, 0, 0, 1, 0.3F, 1);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(USM.modid + ":WindmillPlatform");
	}
	
	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock){
		updateMultiblockStructure(world, x, y, z);		
	}
	public void onBlockAdded(World world, int x, int y, int z){
		updateMultiblockStructure(world, x, y, z);
	}
	
	public void updateMultiblockStructure(World world, int x, int y, int z){
		isMultiblockStructure(world, x, y, z);
	}
	
	public boolean isMultiblockStructure(World world, int x1, int y1, int z1){
		boolean structure = false;
		boolean currentCheckStructure = true;
		for(int x3 = 0; x3 < 3; x3++){
			for(int z3 = 0; z3 < 3; z3++){
				if(!world.getBlock(x1 - x3, y1, z1 - z3).equals(BlockList.WindmillPlatform) && currentCheckStructure){
					currentCheckStructure = false;
				}
			}
		}
		return false;
	}
}
