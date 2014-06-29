package assets.uraniumswordmod.block;

import assets.uraniumswordmod.USM;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlockNetherStar extends Block {

	public BlockNetherStar(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(3.0F);
		this.setResistance(25F);
		this.setLightValue(8.0F);
	}


	@Override
	public void registerIcons(IconRegister reg){
	this.blockIcon = reg.registerIcon(USM.modid +":BlockNetherStar");
	}
}