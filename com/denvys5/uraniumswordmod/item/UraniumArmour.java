package com.denvys5.uraniumswordmod.item;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import com.denvys5.uraniumswordmod.core.BlockList;
import com.denvys5.uraniumswordmod.core.USM;

public class UraniumArmour extends ItemArmor implements ISpecialArmor {
	
    private static int invSize = 9;
    private static IIcon helmetIcon;
    private static IIcon plateIcon;
    private static IIcon leggingsIcon;
    private static IIcon bootsIcon;
    
    public UraniumArmour(int par1, int armorType)
    {
        super(ItemArmor.ArmorMaterial.GOLD, par1, armorType);
        setMaxDamage(1000);
        this.setCreativeTab(USM.USMTab);
    }
    
    /*		
     * Иконка
     */
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        this.itemIcon = iconRegister.registerIcon(USM.modid + ":UraniumHelmet");
        this.helmetIcon = iconRegister.registerIcon(USM.modid + ":UraniumHelmet");
        this.plateIcon = iconRegister.registerIcon(USM.modid + ":UraniumChest");
        this.leggingsIcon = iconRegister.registerIcon(USM.modid + ":UraniumLeggins");
        this.bootsIcon = iconRegister.registerIcon(USM.modid + ":UraniumBoots");
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1)
    {
        if (this == BlockList.UraniumHelmet)
        {
            return this.helmetIcon;
        }

        if (this == BlockList.UraniumChest)
        {
            return this.plateIcon;
        }

        if (this == BlockList.UraniumLeggins)
        {
            return this.leggingsIcon;
        }

        if (this == BlockList.UraniumBoots)
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

        ItemStack helmet = player.getEquipmentInSlot(4);
        ItemStack plate = player.getEquipmentInSlot(3);
        ItemStack leggings = player.getEquipmentInSlot(2);
        ItemStack boots = player.getEquipmentInSlot(1);

        if (helmet == null || plate == null || leggings == null || boots == null)
        {
            return new ArmorProperties(-1, 0, 0);
        }

        if (helmet.getItem() == BlockList.UraniumHelmet || plate.getItem() == BlockList.UraniumChest || leggings.getItem() == BlockList.UraniumLeggins || boots.getItem() == BlockList.UraniumBoots)
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
    
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
        if (this == BlockList.UraniumHelmet || this == BlockList.UraniumChest || this == BlockList.UraniumBoots)
        {
            return USM.modid + ":textures/armor/UraniumArmor_layer_1.png";
        }

        if (this == BlockList.UraniumLeggins)
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
        if (this == BlockList.UraniumHelmet)
        {
            return 3;
        }

        if (this == BlockList.UraniumChest)
        {
            return 8;
        }

        if (this == BlockList.UraniumLeggins)
        {
            return 6;
        }

        if (this == BlockList.UraniumBoots)
        {
            return 3;
        }

        return 5;
    }

    
    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack){
    	
        ItemStack helmet = player.getEquipmentInSlot(4);
        ItemStack plate = player.getEquipmentInSlot(3);
        ItemStack leggings = player.getEquipmentInSlot(2);
        ItemStack boots = player.getEquipmentInSlot(1);
        
        if (helmet.getItem() == BlockList.UraniumHelmet && plate.getItem() == BlockList.UraniumChest && leggings.getItem() == BlockList.UraniumLeggins && boots.getItem() == BlockList.UraniumBoots){
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
            
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.getId(), 1, 0));
            player.addPotionEffect(new PotionEffect(Potion.waterBreathing.getId(), 1, 0));
            
    	}
        return;
    }
}