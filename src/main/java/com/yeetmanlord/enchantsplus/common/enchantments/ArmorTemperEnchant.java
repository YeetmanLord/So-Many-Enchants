package com.yeetmanlord.enchantsplus.common.enchantments;

import com.yeetmanlord.enchantsplus.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ArmorTemperEnchant extends Enchantment
{

	public ArmorTemperEnchant(Rarity rarityIn, EquipmentSlotType[] slots) 
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
		return 25;
	   }

	   public int getMaxEnchantability(int enchantmentLevel) {
	      return super.getMinEnchantability(enchantmentLevel) + 100;
	   }
	   
	 @Override
	public int getMaxLevel() 
	{
		return 1;
	}
	 
	 @Override
	public boolean canGenerateInLoot() {
		return false;
	}
	 
	 @Override
		protected boolean canApplyTogether(Enchantment ench) 
		{
			return super.canApplyTogether(ench) && ench != EnchantmentInit.REINFORCEMENT.get();
		}

}
