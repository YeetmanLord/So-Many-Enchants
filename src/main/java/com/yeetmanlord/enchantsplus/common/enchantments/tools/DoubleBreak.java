package com.yeetmanlord.enchantsplus.common.enchantments.tools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class DoubleBreak extends Enchantment
{
	public DoubleBreak(Rarity rarityIn, EquipmentSlotType... slots)
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
		return 5;
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
