package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;

public class AttackReachEnchant extends Enchantment
{
	public AttackReachEnchant(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentCategory.WEAPON, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return false;
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) 
	{
		return stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true : EnchantmentCategory.WEAPON.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.attackReach.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.attackReach.maxLevel.get();
	}
	
	@Override
	public int getMinCost(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * 12;
	}
	
	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return this.getMinCost(enchantmentLevel) + 25;
	}
	
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
		return super.checkCompatibility(ench) && ench != EnchantmentInit.BLOCK_REACH.get();
	}
	
	
	@Override
	public boolean isTradeable() 
	{
		return false;
	} 
}
