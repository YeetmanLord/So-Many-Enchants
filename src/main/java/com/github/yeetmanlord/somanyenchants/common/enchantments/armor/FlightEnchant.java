package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

public class FlightEnchant extends ModEnchantment {

	public FlightEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.ARMOR_FEET, Config.flight, slots);

	}

	@Override
	public boolean isTradeable() {

		return false;

	}

	@Override
	public boolean isTreasureOnly() {

		return true;

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return false;

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		return EnchantmentCategory.ARMOR_FEET.canEnchant(stack.getItem()) && this.config.isEnabled.get();

	}

	@Override
	public boolean checkCompatibility(Enchantment ench) {

		return super.checkCompatibility(ench) && ench != Enchantments.SOUL_SPEED;

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return enchantmentLevel * 10;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 15;

	}

	@Override
	public boolean isDiscoverable() {

		return false;

	}

}
