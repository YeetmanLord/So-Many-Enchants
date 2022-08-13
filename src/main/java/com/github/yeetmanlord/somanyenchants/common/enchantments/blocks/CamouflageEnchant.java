package com.github.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.world.entity.EquipmentSlot;

public class CamouflageEnchant extends ModEnchantment {

	public CamouflageEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentTypesInit.TRAPPED_CHEST, Config.camouflage, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 3;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 40;

	}

}
