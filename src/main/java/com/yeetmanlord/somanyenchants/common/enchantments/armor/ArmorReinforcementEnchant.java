package com.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ArmorReinforcementEnchant extends Enchantment
{

	public ArmorReinforcementEnchant(Rarity rarityIn, EquipmentSlotType[] slots) 
	{
		super(rarityIn, EnchantmentType.ARMOR, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentType.ARMOR.canEnchantItem(stack.getItem());
	}
	
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 15;
	}

	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return super.getMinEnchantability(enchantmentLevel) + 100;
    }
	   
	 @Override
	public int getMaxLevel() 
	{
		 if(Config.rei.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.rei.maxLevel.get();
	}
	 
	@Override
	protected boolean canApplyTogether(Enchantment ench) 
	{
		return super.canApplyTogether(ench) && ench != EnchantmentInit.TEMPER.get() && ench != EnchantmentInit.HEAVY.get();
	}

}
