package com.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class DoubleBreakEnchant extends Enchantment
{
	public DoubleBreakEnchant(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.DIGGER, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return EnchantmentType.DIGGER.canEnchantItem(stack.getItem());
	}
	
	@Override
	public boolean canApply(ItemStack stack) 
	{
		return EnchantmentType.DIGGER.canEnchantItem(stack.getItem());
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.d.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.d.maxLevel.get();
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 15 + (enchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}
}
