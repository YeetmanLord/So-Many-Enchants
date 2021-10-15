package com.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class FastHopperEnchant extends Enchantment
{
	public FastHopperEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentTypesInit.HOPPER, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentTypesInit.HOPPER.canEnchantItem(stack.getItem());
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
		if(Config.f.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.f.maxLevel.get();
	}
}
