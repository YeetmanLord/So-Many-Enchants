package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class StepAssistEnchant extends ModEnchantment {

	public StepAssistEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentType.ARMOR_FEET, Config.stepAssist, slots);

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
