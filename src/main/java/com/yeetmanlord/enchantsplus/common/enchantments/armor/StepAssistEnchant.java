package com.yeetmanlord.enchantsplus.common.enchantments.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class StepAssistEnchant extends Enchantment
{
	public StepAssistEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentType.ARMOR_FEET.canEnchantItem(stack.getItem());
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 25;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 40;
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 3;
	}
}
