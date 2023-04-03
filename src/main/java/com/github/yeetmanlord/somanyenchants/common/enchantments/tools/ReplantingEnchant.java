package com.github.yeetmanlord.somanyenchants.common.enchantments.tools;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;

public class ReplantingEnchant extends ModEnchantment {

	public ReplantingEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentType.DIGGER, Config.replanting, slots);

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 20;

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		return stack.getItem() instanceof HoeItem || stack.getItem() instanceof AxeItem ? this.config.isEnabled.get() : false;

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return stack.getItem() instanceof HoeItem ? this.config.isEnabled.get() : false;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return this.getMinCost(enchantmentLevel) + 50;

	}

}
