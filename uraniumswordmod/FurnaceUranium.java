package assets.uraniumswordmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FurnaceUranium extends BlockContainer {

	
	private final boolean isActive;
	
	@SideOnly(Side.CLIENT)
	private Icon iconFront;
	
	public FurnaceUranium(int id, boolean isActive) {
		super(id, Material.rock);
		this.isActive = isActive;
	}
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	this.blockIcon = iconRegister.registerIcon("uraniumswordmod:FurnaceUranium_side");
    	this.iconFront = iconRegister.registerIcon("uraniumswordmod" + ":" + (this.isActive ? "FurnaceUranium_active" : "FurnaceUranium_idle"));
    }
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata){
    	return side == metadata ? this.iconFront : this.blockIcon;
    	//return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : side != metadata ? this.blockIcon : this.iconFront);
    }
    public int idDropped(int par1, Random random, int par3) {
        return USM.furnaceuraniumidle.blockID;
    }
    public void onBlockAdded(World world, int x, int y, int z){
    	super.onBlockAdded(world, x, y, z);
    	this.setDefautDirection(world, x, y, z);
    }
    private void setDefautDirection(World world, int x, int y, int z){
    	if (!world.isRemote){
    		int l = world.getBlockId(x, y, z - 1);
    		int il = world.getBlockId(x, y, z + 1);
    		int jl = world.getBlockId(x - 1, y, z);
    		int kl = world.getBlockId(x + 1, y, z);
    	    byte b0 = 3;
    	    if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[il]){
    	    	b0 = 3;
    	    }
    	    if(Block.opaqueCubeLookup[il] && !Block.opaqueCubeLookup[l]){
    	    	b0 = 2;
    	    }
    	    if(Block.opaqueCubeLookup[kl] && !Block.opaqueCubeLookup[jl]){
    	    	b0 = 5;
    	    }
    	    if(Block.opaqueCubeLookup[jl] && !Block.opaqueCubeLookup[kl]){
    	    	b0 = 5;
    	    }
    	    world.setBlockMetadataWithNotify(x, y, z, b0, 2);
    	}
    	
    }
    public TileEntity createNewTileEntity(World world){
    	return new TileEntityFurnaceUranium();
    } 
}

//public TileEntity createNewTileEntity(World world){
//	return new TileEntityFurnaceUranium();
//}