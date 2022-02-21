package com.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ArmorReinforcementEnchant extends Enchantment
{

	public ArmorReinforcementEnchant(Rarity rarityIn, EquipmentSlot[] slots) 
	{
		super(rarityIn, EnchantmentCategory.ARMOR, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentCategory.ARMOR.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 15;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return super.getMinCost(enchantmentLevel) + 100;
    }
	   
	 @Override
	public int getMaxLevel() 
	{
		 if(Config.reinforcement.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.reinforcement.maxLevel.get();
	}
	 
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
		return super.checkCompatibility(ench) && ench != EnchantmentInit.TEMPER.get() && ench != EnchantmentInit.HEAVY.get();
	}

}
