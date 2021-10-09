package com.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class BlockReachEnchant extends Enchantment
{
	public BlockReachEnchant(Rarity rarityIn, EquipmentSlotType... slots)
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
		if(Config.b.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.b.maxLevel.get();
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * 10;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 15;
	}
	
	@Override
	protected boolean canApplyTogether(Enchantment ench) 
	{
		return super.canApplyTogether(ench) && ench != EnchantmentInit.ATTACK_REACH.get();
	}
}
