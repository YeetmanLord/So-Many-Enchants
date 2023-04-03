package com.github.yeetmanlord.somanyenchants.common.enchantments.blocks;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentTypesInit;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ExtraExperienceSmelterEnchant extends ModEnchantment {

	public ExtraExperienceSmelterEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentTypesInit.SMELTER, Config.extraExperience, slots);

	}

	@Override
	public boolean canEnchant(@NotNull ItemStack stack) {

		return canApplyAtEnchantingTable(stack);

	}

	@Override
	public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {

		return EnchantmentTypesInit.SMELTER.canEnchant(stack.getItem());

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return (enchantmentLevel - 1) * 3 + 5;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 20;

	}

}
