package assets.uraniumswordmod.block;

import java.util.Random;

import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.tile.TileEntityFurnaceUranium;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FurnaceUranium extends BlockContainer {

	
	private final boolean isActive;
	
	@SideOnly(Side.CLIENT)
	private Icon iconFront;
	private Icon top, bottom, side, front;
	
	private static boolean keepInventory;
	
	public FurnaceUranium(int id, boolean isActive) {
		super(id, Material.rock);
		this.isActive = isActive;
		this.setHardness(3.0F);
	}
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister){
    	side = iconRegister.registerIcon(USM.modid +":FurnaceUranium_side");
    	front = iconRegister.registerIcon(USM.modid + ":" + (this.isActive ? "FurnaceUranium_active" : "FurnaceUranium_idle"));
    	top = iconRegister.registerIcon(USM.modid + ":FurnaceUranium_top");
    	bottom = iconRegister.registerIcon(USM.modid + ":FurnaceUranium_bottom");
    }
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int side, int metadata){
    	//return side == metadata ? this.iconFront : this.blockIcon;
    	return side == 1 ? this.top : (side == 0 ? this.bottom : (metadata == 2 && side == 2 ? this.front : (metadata == 3 && side == 5 ? this.front : (metadata == 0 && side == 3 ? this.front : (metadata == 1 && side == 4 ? this.front : this.side)))));
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
    	    	b0 = 4;
    	    }
    	    world.setBlockMetadataWithNotify(x, y, z, b0, 2);
    	}
    	
    }
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    	if(!world.isRemote){
    	//if(world.isRemote){
    		//return true;
    	//}else{
    		// TileEntityFurnaceUranium tileentityfurnaceuranium = (TileEntityFurnaceUranium)world.getBlockTileEntity(x, y, z);

    	      //  if (tileentityfurnaceuranium != null)
    	        //{
    	     	  // player.openGui(USM.instance.guiIdFurnaceUranium, 0, world, x, y, z);
    	     	
    	      //   }
             //return true;
    		FMLNetworkHandler.openGui(player, USM.instance, USM.instance.guiIdFurnaceUranium, world, x, y, z);
    	}
    	return true;
    }
    public TileEntity createNewTileEntity(World world){
    	return new TileEntityFurnaceUranium();
    } 
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemstack){
    	int l = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    	if(l == 0){
    		world.setBlockMetadataWithNotify(x, y, z, 2, 2);
    	}
    	if(l == 1){
    		world.setBlockMetadataWithNotify(x, y, z, 5, 2);
    	}
    	if(l == 2){
    		world.setBlockMetadataWithNotify(x, y, z, 3, 2);
    	}
        if(l == 3){
        	world.setBlockMetadataWithNotify(x, y, z, 4, 2);
    	}
        if(itemstack.hasDisplayName()){
        	((TileEntityFurnaceUranium)world.getBlockTileEntity(x, y, z)).setGuiDisplayName(itemstack.getDisplayName());
        }
    }
	public static void updateFurnaceUraniumBlockState(boolean active, World worldObj, int xCoord, int yCoord, int zCoord) {
		int i = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		TileEntity tileentity = worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
		keepInventory = true;
		if(active){
			worldObj.setBlock(xCoord, yCoord, zCoord, USM.furnaceuraniumactive.blockID);
		}else{
			worldObj.setBlock(xCoord, yCoord, zCoord, USM.furnaceuraniumidle.blockID);
		}
		keepInventory = false;
		worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, i, 2);
		if(tileentity != null){
			tileentity.validate();
			worldObj.setBlockTileEntity(xCoord, yCoord, zCoord, tileentity);
		}
	}
    public boolean hasComparatorInputOverride(){
    	return true;
    }
    public int getComparatorInputOverride(World world, int x, int y, int z, int i){
    	return Container.calcRedstoneFromInventory((IInventory) world.getBlockTileEntity(x, y, z));
    }
    public int idPicked(World world, int x, int y, int z){
    	return USM.furnaceuraniumidle.blockID;
    }
    
    
    
    
}

