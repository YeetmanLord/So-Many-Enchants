package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.MobType;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;

public class HeavyBladeEnchant extends Enchantment
{

	public HeavyBladeEnchant(Rarity rarityIn, EquipmentSlot... slots)
	{
		super(rarityIn, EnchantmentCategory.WEAPON, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack)
	{
		return this.category.canEnchant(stack.getItem());
	}

	@Override
	public boolean canEnchant(ItemStack stack)
	{
		return stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true
				: super.canEnchant(stack);
	}

	@Override
	public boolean isTradeable()
	{
		return true;
	}

	@Override
	public boolean isAllowedOnBooks()
	{ return true; }

	@Override
	public int getMinCost(int enchantmentLevel)
	{
		return 15 + (enchantmentLevel - 1) * 9;
	}

	@Override
	public int getMaxCost(int enchantmentLevel)
	{
		return super.getMinCost(enchantmentLevel) + 50;
	}

	@Override
	public int getMaxLevel()
	{

		if (Config.heavyBlade.isEnabled.get() == false)
		{
			return 0;
		}
		else
			return Config.heavyBlade.maxLevel.get();

	}

	@Override
	public boolean isDiscoverable()
	{
		return true;
	}

	@Override
	protected boolean checkCompatibility(Enchantment ench)
	{
		return ench == EnchantmentInit.LIGHT_BLADE.get() || ench instanceof DamageEnchantment
				|| ench == EnchantmentInit.ATTACK_REACH.get() || ench == EnchantmentInit.BLOCK_REACH.get()
						? false : super.checkCompatibility(ench);
	}

	@Override
	public float getDamageBonus(int level, MobType creatureType)
	{
		return (float) (Math.max(0, level - 1) * 2 + 4);
	}

}
