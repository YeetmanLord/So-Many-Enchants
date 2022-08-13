package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class DoubleBreakEnchant extends ModEnchantment {

	public DoubleBreakEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.DIGGER, Config.doubleBreak, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 15 + (enchantmentLevel - 1) * 9;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return super.getMinCost(enchantmentLevel) + 50;

	}

}
