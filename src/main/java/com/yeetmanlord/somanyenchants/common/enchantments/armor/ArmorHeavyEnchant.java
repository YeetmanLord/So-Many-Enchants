package com.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ArmorHeavyEnchant extends Enchantment
{
	public ArmorHeavyEnchant(Rarity rarityIn, EquipmentSlot[] slots) 
	{
		super(rarityIn, EnchantmentCategory.ARMOR, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentCategory.ARMOR.canEnchant(stack.getItem());
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) 
	{
		return 25;
	}

	@Override
	public int getMaxCost(int enchantmentLevel) 
	{
		return super.getMinCost(enchantmentLevel) + 100;
	}
	   
	 @Override
	public int getMaxLevel() 
	{
		 if(Config.heavyArmor.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.heavyArmor.maxLevel.get();
	}
	 
	@Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
			return super.checkCompatibility(ench) && ench != EnchantmentInit.REINFORCEMENT.get() && ench != EnchantmentInit.TEMPER.get();
	}

}
