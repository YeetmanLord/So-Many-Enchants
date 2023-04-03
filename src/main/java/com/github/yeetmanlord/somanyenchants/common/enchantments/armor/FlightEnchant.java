package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;

public class FlightEnchant extends ModEnchantment {

	public FlightEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentType.ARMOR_FEET, Config.flight, slots);

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

		return EnchantmentType.ARMOR_FEET.canEnchant(stack.getItem()) && this.config.isEnabled.get();

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
