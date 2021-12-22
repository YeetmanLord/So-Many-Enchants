package com.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CavernousStorageEnchant extends Enchantment {

	public CavernousStorageEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentTypesInit.STORAGE, slots);
	}
	
	@Override
	public boolean canApply(ItemStack stack)
	{
		return EnchantmentTypesInit.STORAGE.canEnchantItem(stack.getItem());
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
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
		if(Config.cavernousStorage.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.cavernousStorage.maxLevel.get();
	}

}
