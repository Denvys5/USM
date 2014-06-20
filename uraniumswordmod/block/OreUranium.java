package assets.uraniumswordmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class OreUranium extends Block {
	
	public OreUranium(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(3.0F);
		this.setResistance(25F);
		this.setLightValue(8.0F);
	}
	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
    {
		par5EntityPlayer.addPotionEffect(new PotionEffect(17, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(15, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(18, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(20, 100, 1));
    }

	@Override
	public void registerIcons(IconRegister reg){
	this.blockIcon = reg.registerIcon("uraniumswordmod:OreUranium");
	}
}
