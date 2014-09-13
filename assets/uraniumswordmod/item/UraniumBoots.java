package assets.uraniumswordmod.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import assets.uraniumswordmod.USM;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UraniumBoots extends ItemArmor {
    public UraniumBoots(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
    {
        super(par1, par2EnumArmorMaterial, par3, par4);
        this.setCreativeTab(USM.USMTab);
    }
    
    /*
     * Иконка
     */
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(USM.modid + ":UraniumBoots"); 
    }
    
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
        /*
         * Любой эффект, когда броня одета (не вся, а только определенный предмет(в данном случае - шлем))
         */
    }
    
    /*
     * Текстура брони на игроке
     */
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return USM.modid + ":textures/armor/UraniumArmor.png";
    }
}
