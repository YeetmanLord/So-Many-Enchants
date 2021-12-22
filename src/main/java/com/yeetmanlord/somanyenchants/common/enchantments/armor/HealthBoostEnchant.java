package com.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class HealthBoostEnchant extends Enchantment 
{

	public HealthBoostEnchant(Rarity rarityIn, EquipmentSlotType[] slots) 
	{
		super(rarityIn, EnchantmentType.ARMOR, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentType.ARMOR.canEnchantItem(stack.getItem());
	}
	
	@Override
	public boolean canVillagerTrade() 
	{
		return true;
	}
	
	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}
	
	public int getMinEnchantability(int enchantmentLevel) {
		return 15 + (enchantmentLevel - 1) * 9;
	   }

	   public int getMaxEnchantability(int enchantmentLevel) {
	      return super.getMinEnchantability(enchantmentLevel) + 50;
	   }
	   
	 @Override
	public int getMaxLevel() 
	{
		 if(Config.healthBoost.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.healthBoost.maxLevel.get();
	}
	 
	 @Override
	public boolean canGenerateInLoot() {
		return true;
	}
	 


}
