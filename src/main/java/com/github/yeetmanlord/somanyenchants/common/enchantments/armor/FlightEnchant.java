package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class FlightEnchant extends Enchantment 
{
	public FlightEnchant(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentCategory.ARMOR_FEET, slots);
	}
	
	@Override
	public boolean isTradeable()
	{
		return false;
	}
	
	@Override
	public boolean isTreasureOnly() 
	{
		return true;
		
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return false;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return EnchantmentCategory.ARMOR_FEET.canEnchant(stack.getItem());
	}
	
	@Override
	public boolean checkCompatibility(Enchantment ench)
	{
	      return super.checkCompatibility(ench) && ench != Enchantments.SOUL_SPEED;
	} 
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return enchantmentLevel * 10;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return this.getMinCost(enchantmentLevel) + 15;
	}
	
	@Override
	public int getMaxLevel()
	{
		if(Config.flight.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.flight.maxLevel.get();
	}
	
	@Override
	public boolean isDiscoverable() 
	{
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}
}
