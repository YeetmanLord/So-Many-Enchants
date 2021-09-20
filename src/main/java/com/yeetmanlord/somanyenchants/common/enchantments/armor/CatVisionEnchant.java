package com.yeetmanlord.somanyenchants.common.enchantments.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CatVisionEnchant extends Enchantment
{
	
	public CatVisionEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentType.ARMOR_HEAD, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentType.ARMOR_HEAD.canEnchantItem(stack.getItem());
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 15;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 40;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 1;
	}
}
