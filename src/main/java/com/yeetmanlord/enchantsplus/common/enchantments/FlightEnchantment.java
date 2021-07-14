package com.yeetmanlord.enchantsplus.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class FlightEnchantment extends Enchantment 
{
	public FlightEnchantment(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
	}
	
	@Override
	public boolean canVillagerTrade()
	{
		return false;
	}
	
	@Override
	public boolean isTreasureEnchantment() 
	{
		return true;
		
	}
	
	public boolean canApplyTogether(Enchantment ench)
	{
	      return super.canApplyTogether(ench) && ench != Enchantments.SOUL_SPEED;
	} 
	
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 15 + (enchantmentLevel - 1) * 9;
	}
	
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
	
	@Override
	public int getMaxLevel()
	{
		return 3;
	}
	
	@Override
	public boolean canGenerateInLoot() 
	{
		return false;
	}
	
	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}
}
