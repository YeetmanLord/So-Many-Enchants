package com.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.yeetmanlord.somanyenchants.core.config.Config;
import com.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class CamouflageEnchant extends Enchantment {

	public CamouflageEnchant(Rarity rarityIn, EquipmentSlotType... slots) 
	{
		super(rarityIn, EnchantmentTypesInit.TRAPPED_CHEST, slots);
	}
	
	@Override
	public boolean canApply(ItemStack stack)
	{
		return EnchantmentTypesInit.TRAPPED_CHEST.canEnchantItem(stack.getItem());
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return false;
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel) 
	{
		return 30;
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + 40;
	}
	
	@Override
	public int getMaxLevel() 
	{
		if(Config.camouflage.isEnabled.get() == false)
		 {
			 return 0;
		 }
		 else return Config.camouflage.maxLevel.get();
	}

}
