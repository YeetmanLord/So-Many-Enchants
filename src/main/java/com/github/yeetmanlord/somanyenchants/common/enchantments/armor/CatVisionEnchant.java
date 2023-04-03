package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CatVisionEnchant extends ModEnchantment {

	public CatVisionEnchant(Rarity rarityIn, EquipmentSlotType[] slots) {

		super(rarityIn, EnchantmentType.ARMOR_HEAD, Config.catVision, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 15;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 40;

	}

}
