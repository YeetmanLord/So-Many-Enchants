package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class HealthBoostEnchant extends Enchantment {

	public HealthBoostEnchant(Rarity rarityIn, EquipmentSlot[] slots) {
		super(rarityIn, EnchantmentCategory.ARMOR, slots);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return EnchantmentCategory.ARMOR.canEnchant(stack.getItem());
	}

	@Override
	public boolean isTradeable() {
		return true;
	}

	@Override
	public boolean isAllowedOnBooks() {
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
	public boolean isDiscoverable() {
		return true;
	}
	
	@Override
	public int getMaxLevel() {
		if (Config.healthBoost.isEnabled.get() == false) {
			return 0;
		}
		return Config.healthBoost.maxLevel.get();
	}

}
