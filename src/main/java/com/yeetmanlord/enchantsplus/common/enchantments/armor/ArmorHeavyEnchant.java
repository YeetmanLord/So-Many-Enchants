package com.yeetmanlord.enchantsplus.common.enchantments.armor;

import com.yeetmanlord.enchantsplus.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ArmorHeavyEnchant extends Enchantment
{
	public ArmorHeavyEnchant(Rarity rarityIn, EquipmentSlotType[] slots) 
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
		return 25;
	}

	public int getMaxEnchantability(int enchantmentLevel) 
	{
		return super.getMinEnchantability(enchantmentLevel) + 100;
	}
	   
	 @Override
	public int getMaxLevel() 
	{
		return 1;
	}
	 
	@Override
	protected boolean canApplyTogether(Enchantment ench) 
	{
			return super.canApplyTogether(ench) && ench != EnchantmentInit.REINFORCEMENT.get() && ench != EnchantmentInit.TEMPER.get();
	}

}
