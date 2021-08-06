package com.yeetmanlord.enchantsplus.common.enchantments.weapons;

import com.yeetmanlord.enchantsplus.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class AttackReach extends Enchantment
{
	public AttackReach(Rarity rarityIn, EquipmentSlotType... slots)
	{
		super(rarityIn, EnchantmentType.WEAPON, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		//return false;
		return EnchantmentType.WEAPON.canEnchantItem(stack.getItem()); //temp
	}
	
	@Override
	public boolean canApply(ItemStack stack) 
	{
		return stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true : super.canApply(stack);
	}
	
	@Override
	public int getMaxLevel() 
	{
		return 1;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * 12;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 25;
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) 
	{
		return super.canApplyTogether(ench) && ench != EnchantmentInit.BLOCK_REACH.get();
	}
	
	/*
	@Override
	public boolean canVillagerTrade() 
	{
		return false;
	} */
}
