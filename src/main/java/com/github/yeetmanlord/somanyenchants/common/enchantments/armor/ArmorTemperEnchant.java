package com.github.yeetmanlord.somanyenchants.common.enchantments.armor;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class ArmorTemperEnchant extends ModEnchantment {

	public ArmorTemperEnchant(Rarity rarityIn, EquipmentSlotType[] slots) {

		super(rarityIn, EnchantmentType.ARMOR, Config.temper, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 25;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return super.getMinCost(enchantmentLevel) + 100;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		return super.checkCompatibility(ench) && ench != EnchantmentInit.REINFORCED_ARMOR.get() && ench != EnchantmentInit.HEAVY_ARMOR.get();

	}

}
