package com.github.yeetmanlord.somanyenchants.common.enchantments.weapons;

import com.github.yeetmanlord.somanyenchants.common.enchantments.ModEnchantment;
import com.github.yeetmanlord.somanyenchants.core.config.Config;
import com.github.yeetmanlord.somanyenchants.core.init.EnchantmentInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;

public class CriticalEnchant extends ModEnchantment {

	public CriticalEnchant(Rarity rarityIn, EquipmentSlotType... slots) {

		super(rarityIn, EnchantmentType.WEAPON, Config.critical, slots);

	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {

		return false;

	}

	@Override
	public boolean canEnchant(ItemStack stack) {

		boolean flag = stack.getItem() instanceof TridentItem || stack.getItem() instanceof AxeItem ? true : EnchantmentType.WEAPON.canEnchant(stack.getItem());
		return flag && this.config.isEnabled.get();

	}

	@Override
	public int getMinCost(int enchantmentLevel) {

		return 15 + (enchantmentLevel - 1) * 9;

	}

	@Override
	public int getMaxCost(int enchantmentLevel) {

		return super.getMinCost(enchantmentLevel) + 50;

	}

	@Override
	protected boolean checkCompatibility(Enchantment ench) {

		return super.checkCompatibility(ench) && ench != Enchantments.SWEEPING_EDGE && ench != EnchantmentInit.FREEZING.get();

	}

	@Override
	public boolean isTradeable() {

		return false;

	}

}
