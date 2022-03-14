package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class FreezingEnchant extends Enchantment {

	public FreezingEnchant(Rarity rarityIn, EquipmentSlot... slots) 
	{
		super(rarityIn, EnchantmentCategory.WEAPON, slots);
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return EnchantmentCategory.WEAPON.canEnchant(stack.getItem());
	}
	
	@Override
	public boolean canEnchant(ItemStack stack) {
		  return stack.getItem() instanceof TridentItem ? true : super.canEnchant(stack);
	}
	
	@Override
	public boolean isTradeable() 
	{
		return true;
	}
	
	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}
	
	@Override
	public int getMinCost(int enchantmentLevel) {
		return 15 + (enchantmentLevel - 1) * 9;
	   }

	   @Override
	public int getMaxCost(int enchantmentLevel) {
	      return super.getMinCost(enchantmentLevel) + 50;
	   }
	   
	 @Override
	public int getMaxLevel() 
	{
		 if(Config.freezing.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.freezing.maxLevel.get();
	}
	 
	 @Override
	public boolean isDiscoverable() {
		return true;
	}
	 
	 @Override
	protected boolean checkCompatibility(Enchantment ench) 
	{
		return super.checkCompatibility(ench) && ench != Enchantments.SWEEPING_EDGE && ench != EnchantmentInit.CRITICAL.get();
	}
}
