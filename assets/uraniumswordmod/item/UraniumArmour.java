package assets.uraniumswordmod.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import assets.uraniumswordmod.USM;
import assets.uraniumswordmod.lib.BlockList;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class UraniumArmour extends ItemArmor implements ISpecialArmor {
	
    private static int invSize = 9;
    private static Icon helmetIcon;
    private static Icon plateIcon;
    private static Icon leggingsIcon;
    private static Icon bootsIcon;
    
    public UraniumArmour(int par1, int par3, int par4)
    {
        super(par1, EnumArmorMaterial.GOLD, par3, par4);
        setMaxDamage(1000);
        this.setCreativeTab(USM.USMTab);
    }
    
    /*
     * Иконка
     */
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(USM.modid + ":UraniumHelmet");
        this.helmetIcon = iconRegister.registerIcon(USM.modid + ":UraniumHelmet");
        this.plateIcon = iconRegister.registerIcon(USM.modid + ":UraniumChest");
        this.leggingsIcon = iconRegister.registerIcon(USM.modid + ":UraniumLeggins");
        this.bootsIcon = iconRegister.registerIcon(USM.modid + ":UraniumBoots");
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1)
    {
        if (this.itemID == BlockList.UraniumHelmet.itemID)
        {
            return this.helmetIcon;
        }

        if (this.itemID == BlockList.UraniumChest.itemID)
        {
            return this.plateIcon;
        }

        if (this.itemID == BlockList.UraniumLeggins.itemID)
        {
            return this.leggingsIcon;
        }

        if (this.itemID == BlockList.UraniumBoots.itemID)
        {
            return this.bootsIcon;
        }

        return this.itemIcon;
    }
    
    public boolean isImmuneToVoid(ItemStack itemStack){
        return true;
    }

    
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot)
    {
        if (source.equals(DamageSource.drown))
        {
            return new ArmorProperties(-1, 0, 0);
        }

        if (source.equals(DamageSource.outOfWorld))
        {
            if (isImmuneToVoid(armor))
            {
                return new ArmorProperties(-1, 3, 100000);
            } else
            {
                return new ArmorProperties(-1, 0, 0);
            }
        }

        ItemStack helmet = player.getCurrentItemOrArmor(4);
        ItemStack plate = player.getCurrentItemOrArmor(3);
        ItemStack leggings = player.getCurrentItemOrArmor(2);
        ItemStack boots = player.getCurrentItemOrArmor(1);

        if (helmet == null || plate == null || leggings == null || boots == null)
        {
            return new ArmorProperties(-1, 0, 0);
        }

        if (helmet.itemID == BlockList.UraniumHelmet.itemID || plate.itemID == BlockList.UraniumChest.itemID || leggings.itemID == BlockList.UraniumLeggins.itemID || boots.itemID == BlockList.UraniumBoots.itemID)
        {
            if (source.isUnblockable())
            {
                return new ArmorProperties(-1, 3, 3);
            }

            return new ArmorProperties(-1, 3, 100000);
        }

        return new ArmorProperties(-1, 0, 0);
    }
    
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot)
    {
        stack.setItemDamage(stack.getItemDamage() + damage);
    }
    
    /*public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack)
    {
        /*
         * Любой эффект, когда броня одета (не вся, а только определенный предмет(в данном случае - шлем))
         */
    //}
    
    /*
     * Текстура брони на игроке
     */
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if (itemID == BlockList.UraniumHelmet.itemID || itemID == BlockList.UraniumChest.itemID || itemID == BlockList.UraniumBoots.itemID)
        {
            return USM.modid + ":textures/armor/UraniumArmor_layer_1.png";
        }

        if (itemID == BlockList.UraniumLeggins.itemID)
        {
            return USM.modid + ":textures/armor/UraniumArmor_layer_2.png";
        }else{
            return null;
        }
    }
    
    @SideOnly(Side.CLIENT)
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player, ScaledResolution resolution, float partialTicks, boolean hasScreen, int mouseX, int mouseY) {
    	
	}

	@Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot)
    {
        if (this.itemID == BlockList.UraniumHelmet.itemID)
        {
            return 3;
        }

        if (this.itemID == BlockList.UraniumChest.itemID)
        {
            return 8;
        }

        if (this.itemID == BlockList.UraniumLeggins.itemID)
        {
            return 6;
        }

        if (this.itemID == BlockList.UraniumBoots.itemID)
        {
            return 3;
        }

        return 5;
    }

    
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack){
    	
        ItemStack helmet = player.getCurrentItemOrArmor(4);
        ItemStack plate = player.getCurrentItemOrArmor(3);
        ItemStack leggings = player.getCurrentItemOrArmor(2);
        ItemStack boots = player.getCurrentItemOrArmor(1);
        
        if (helmet.itemID == BlockList.UraniumHelmet.itemID || plate.itemID == BlockList.UraniumChest.itemID || leggings.itemID == BlockList.UraniumLeggins.itemID || boots.itemID == BlockList.UraniumBoots.itemID){
            if (!player.isPotionActive(USM.RadiationUSM))
            {
                player.removePotionEffect(USM.RadiationUSM.id);
                player.curePotionEffects(new ItemStack(BlockList.sworduranium));
            }
            if(player.getHealth() < player.getMaxHealth()){
            	player.heal(player.getMaxHealth() - player.getHealth());
            }
            if (itemStack.getItemDamage() > 0)
            {
                if (!player.capabilities.isCreativeMode)
                {
                    itemStack.setItemDamage(0);
                }
            }
    	}
        return;
    }
}