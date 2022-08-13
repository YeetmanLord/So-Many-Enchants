package com.github.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ExtraExperienceSmelterEnchant extends ModEnchantment {

	public ExtraExperienceSmelterEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentTypesInit.SMELTER, Config.extraExperience, slots);

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

}
