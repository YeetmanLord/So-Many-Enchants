package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BlockReachEnchant extends ModEnchantment {

	public BlockReachEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.DIGGER, Config.blockReach, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 10 + (enchantmentLevel - 1) * 10;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 15;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		return super.checkCompatibility(ench) && ench != EnchantmentInit.ATTACK_REACH.get();

	}

}
