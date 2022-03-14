package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class DoubleBreakEnchant extends Enchantment
{
	public DoubleBreakEnchant(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentCategory.DIGGER, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return EnchantmentCategory.DIGGER.canEnchant(stack.getItem());
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return EnchantmentCategory.DIGGER.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.doubleBreak.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.doubleBreak.maxLevel.get();
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 15 + (enchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return super.getMinCost(enchantmentLevel) + 50;
	}
}
