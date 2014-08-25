package assets.uraniumswordmod.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import assets.uraniumswordmod.USM;

public class BlockInfusedUranium extends Block {

	public BlockInfusedUranium(int par1) {
		super(par1, Material.rock);
		this.setCreativeTab(USM.USMTab);
		this.setHardness(30.0F);
		this.setResistance(25F);
		this.setLightValue(15.0F);
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4,
			EntityPlayer par5EntityPlayer) {
		par5EntityPlayer.addPotionEffect(new PotionEffect(17, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(15, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(18, 100, 1));
		par5EntityPlayer.addPotionEffect(new PotionEffect(20, 100, 1));
	}

	@Override
	public void registerIcons(IconRegister reg) {
		this.blockIcon = reg.registerIcon(USM.modid + ":BlockInfusedUranium");
	}
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return true;
	}

	public void onEntityCollidedWithBlock(World par1World, int par2, int par3,
			int par4, Entity par5Entity) {
		par5Entity.attackEntityFrom(DamageSource.cactus, 5.0F);
	}
}

