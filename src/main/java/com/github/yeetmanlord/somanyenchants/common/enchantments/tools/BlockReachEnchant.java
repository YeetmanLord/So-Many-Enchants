package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class BlockReachEnchant extends Enchantment
{
	public BlockReachEnchant(Rarity rarityIn, EquipmentSlot... slots)
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
		if(Config.blockReach.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.blockReach.maxLevel.get();
	}
	
	@Override
	public int getMinCost(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return this.getMinCost(enchantmentLevel) + 15;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
		return super.checkCompatibility(ench) && ench != EnchantmentInit.ATTACK_REACH.get();
	}
}
