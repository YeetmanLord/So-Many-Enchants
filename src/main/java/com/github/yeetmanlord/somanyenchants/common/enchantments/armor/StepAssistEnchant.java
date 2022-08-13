package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class StepAssistEnchant extends ModEnchantment {

	public StepAssistEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.ARMOR_FEET, Config.stepAssist, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 25;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 40;

	}

}
