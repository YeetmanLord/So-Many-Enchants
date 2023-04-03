package com.github.yeetmanlord.somanyenchants.common.enchantments;

import com.github.yeetmanlord.somanyenchants.core.config.EnchantmentConfig;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ModEnchantment extends Enchantment {

	protected EnchantmentConfig config;

	public ModEnchantment(Rarity rarity, EnchantmentCategory category, EnchantmentConfig config, EquipmentSlot... slots) {

		super(rarity, category, slots);
		this.config = config;

	}

	@Override
	public int getMaxLevel() {

		if (config.isEnabled.get()) {
			return config.maxLevel.get();
		}
		else {
			return 0;
		}

	}

	@Override
	public int getMinLevel() {

		if (config.isEnabled.get()) {
			return super.getMinLevel();
		}
		else {
			return 0;
		}

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		if (config.isEnabled.get()) {
			return super.canApplyAtEnchantingTable(stack);
		}

		return false;

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		if (config.isEnabled.get()) {
			return super.canEnchant(stack);
		}

		return false;

	}

	@Override
	public boolean isAllowedOnBooks() {

		if (config.isEnabled.get()) {
			return super.isAllowedOnBooks();
		}

		return false;

	}

	@Override
	public boolean isTradeable() {

		if (config.isEnabled.get()) {
			return super.isTradeable();
		}

		return false;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		if (ench instanceof ModEnchantment modEnch) {
			return this.config.isEnabled.get() && modEnch.config.isEnabled.get() && super.checkCompatibility(ench);
		}
		else {
			return this.config.isEnabled.get() && super.checkCompatibility(ench);
		}

	}

}
