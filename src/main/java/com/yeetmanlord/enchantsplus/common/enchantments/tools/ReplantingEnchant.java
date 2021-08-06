package com.yeetmanlord.enchantsplus.common.enchantments.tools;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;

public class ReplantingEnchant extends Enchantment 
{
	public ReplantingEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentType.DIGGER, slots);
	}
	
	public int getMinEnchantability(int enchantmentLevel) 
	{
	      return 20;
	}
	
	@Override
	public boolean canApply(ItemStack stack)
	{
		return stack.getItem() instanceof HoeItem || stack.getItem() instanceof AxeItem ? true : false;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return stack.getItem() instanceof HoeItem ? true : false;
	}

	public int getMaxEnchantability(int enchantmentLevel) 
	{
	      return this.getMinEnchantability(enchantmentLevel) + 50;
	}
	   
	public int getMaxLevel() 
	{
		return 1;
	}
}
