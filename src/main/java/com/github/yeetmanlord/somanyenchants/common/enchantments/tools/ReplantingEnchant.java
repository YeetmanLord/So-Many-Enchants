package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import org.jetbrains.annotations.NotNull;

public class ReplantingEnchant extends ModEnchantment {

	public ReplantingEnchant(Rarity rarityIn, EquipmentSlot... slots) {

		super(rarityIn, EnchantmentCategory.DIGGER, Config.replanting, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 20;

	}

	@Override
	public boolean canEnchant(@NotNull ItemStack stack) {

		return stack.getItem() instanceof HoeItem || stack.getItem() instanceof AxeItem ? this.config.isEnabled.get() : false;

	}

	@Override
	public boolean canApplyAtEnchantingTable(@NotNull ItemStack stack) {

		return stack.getItem() instanceof HoeItem ? this.config.isEnabled.get() : false;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 50;

	}

}
