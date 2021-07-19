package com.yeetmanlord.enchantsplus.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class ReachEnchantment extends Enchantment
{
	private int[] COST = new int[] {10, 8};
	private int[] SPAN = new int[] {20, 10};
	private int[] MAX = new int[] {3, 5};
	private int type;
	private static EnchantmentType[] enchantTypes = new EnchantmentType[] {EnchantmentType.ATTACKABLE, EnchantmentType.DIGGER};
	public ReachEnchantment(Rarity rarityIn, EnchantmentType typeIn, int type, EquipmentSlotType... slots)
	{
		super(rarityIn, enchantTypes[type], slots);
		this.type = type;
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) 
	{
		return false;
	}
	
	@Override
	public boolean canApply(ItemStack stack) 
	{
		return enchantTypes[this.type].canEnchantItem(stack.getItem());
	}
	
	@Override
	public int getMaxLevel() 
	{
		return this.MAX[this.type];
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel)
	{
		return 10 + (enchantmentLevel - 1) * COST[this.type];
	}
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
	{
		return this.getMinEnchantability(enchantmentLevel) + SPAN[this.type];
	}
}
