package com.github.yeetmanlord.somanyenchants.common.enchantments.blocks;

import net.minecraft.world.item.enchantment.Enchantment;

import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ExtraExperienceSmelterEnchant extends Enchantment {

	public ExtraExperienceSmelterEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentTypesInit.SMELTER, slots);

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		return canApplyAtEnchantingTable(stack);

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return EnchantmentTypesInit.SMELTER.canEnchant(stack.getItem());

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 5 * enchantmentLevel;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 40;

	}

	@Override
	public int getMaxLevel() {

		if (Config.extraExperience.isEnabled.get() == false) {
			return 0;
		}
		else return Config.extraExperience.maxLevel.get();

	}

}
