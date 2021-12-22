package com.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.yeetmanlord.somanyenchants.core.config.Config;

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
		if(Config.stepAssist.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.stepAssist.maxLevel.get();
	}
}
