package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ArmorReinforcementEnchant extends ModEnchantment {

	public ArmorReinforcementEnchant(Rarity rarityIn, EquipmentSlot[] slots) {

		super(rarityIn, EnchantmentCategory.ARMOR, Config.reinforcement, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 15;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return super.getMinCost(enchantmentLevel) + 100;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		return super.checkCompatibility(ench) && ench != EnchantmentInit.TEMPERED_ARMOR.get() && ench != EnchantmentInit.HEAVY_ARMOR.get();

	}

}
